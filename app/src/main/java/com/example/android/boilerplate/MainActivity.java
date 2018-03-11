package com.example.android.boilerplate;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.boilerplate.databinding.ActivityMainBinding;
import com.example.android.boilerplate.network.Repository;
import com.example.android.boilerplate.ui.Adapter;
import com.example.android.boilerplate.viewmodels.GitHubViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements ICallback {

    @Inject
    Repository repository;
    private ActivityMainBinding binding;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BoilerplateApplication.getApp().getDataComponent().inject(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        GitHubViewModel viewModel = new GitHubViewModel(this, repository);
        binding.setVm(viewModel);

        viewModel.getData();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


        recyclerView.setHasFixedSize(true);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        List data = new ArrayList();
        mAdapter = new Adapter(data);
        recyclerView.setAdapter(mAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showResults(List data) {
        this.data = data;
        mAdapter = new Adapter(data);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    public void showError() {
        this.data.add("No results");
        mAdapter = new Adapter(data);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }
}
