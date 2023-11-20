package com.example.todoapp.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

import com.example.todoapp.Models.Presupuesto;
import com.example.todoapp.UI.viewModels.BudgetVM;
import com.example.todoapp.databinding.ActivityAddBudgetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class AddBudget extends BottomSheetDialogFragment {
    private ActivityAddBudgetBinding binding;
    private BudgetVM viewModel;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Configurando el ViewModel
        viewModel = new ViewModelProvider(this).get(BudgetVM.class);

        // Use ViewBinding to inflate the layout
        binding = ActivityAddBudgetBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        binding.btnGuardarPresupuesto.setOnClickListener(v -> {
            Presupuesto mObject = new Presupuesto(
                    binding.edtATitulo.getText().toString(),
                    Double.parseDouble(binding.edtAMonto.getText().toString()),
                    true);

            viewModel.addBudget(
                    mObject,
                    documentReference -> {
                        this.dismiss();
                        Toast.makeText(getContext(), "Guardado", Toast.LENGTH_SHORT).show();
                    },
                    e -> {
                        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
            );

        });

        return view;
    }
}


