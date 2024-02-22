package com.example.gamecenetr_antoniroig;
import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<String> dataList;
    private MainAdapter adapter;
    private boolean isRecyclerViewVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        dataList = generateData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainAdapter(dataList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new MainAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Fragment fragment = null;
                isRecyclerViewVisible = false;
                recyclerView.setVisibility(View.GONE);
                switch (position) {
                    case 0:
                        fragment = new FragmentSenku();
                        break;
                    case 1:
                        fragment = new Fragment2048();
                        break;
                    default:
                        break;
                }

                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (!isRecyclerViewVisible) {
                    isRecyclerViewVisible = true;
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    finish();
                }
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private List<String> generateData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i <= 1; i++) {
            switch (i) {
                case 0:
                    data.add("Senku");
                    break;
                case 1:
                    data.add("2048");
                    break;
                default:
                    break;
            }
        }
        return data;
    }
}
