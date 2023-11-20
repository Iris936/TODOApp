package com.example.todoapp.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.todoapp.Models.Presupuesto;
import com.example.todoapp.UI.adapters.ChildRecyclerAdapter;
import com.example.todoapp.UI.adapters.MainRecyclerAdapter;
import com.example.todoapp.UI.viewModels.BudgetVM;
import com.example.todoapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ChildRecyclerAdapter.OnItemChildClickListener {

    private ActivityMainBinding binding;
    private BudgetVM viewModel;
    private MainRecyclerAdapter mainRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configuracion de viewbinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        //Configurando el ViewModel
        viewModel = new ViewModelProvider(this).get(BudgetVM.class);

        // Configurando adaptador
        mainRecyclerAdapter = new MainRecyclerAdapter(new ArrayList<>(), MainActivity.this);
        binding.mainRecyclerView.setAdapter(mainRecyclerAdapter);
        binding.mainRecyclerView.setHasFixedSize(true);

        // Observe changes in budgets data
        viewModel.getBudgetLiveData().observe(this, budgets -> {
            // Update the RecyclerView adapter with the new budget data
            mainRecyclerAdapter.setDataList(budgets);
        });

        binding.imgAgregar.setOnClickListener(v -> {
            AddBudget bottomSheet = new AddBudget();
            bottomSheet.show(getSupportFragmentManager(), "addBudgetFragment");
        });
    }


    @Override
    public void onItemChildClick(Presupuesto mainObject) {
        if (mainObject.isActivo()){
            Intent intent = new Intent(MainActivity.this, DetailBudget.class);
            intent.putExtra("mainBudget", mainObject);
            startActivity(intent);
        }
        else{

        }
    }
}