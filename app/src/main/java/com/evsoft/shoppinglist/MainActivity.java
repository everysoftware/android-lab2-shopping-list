package com.evsoft.shoppinglist;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> items;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        editText = findViewById(R.id.editText);

        layoutManager = new LinearLayoutManager(this);
        adapter = new MyAdapter(items, this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void addItem(View view) {
        String item = editText.getText().toString().trim();
        if (item.isEmpty()) {
            Toast.makeText(this, R.string.empty_product, Toast.LENGTH_SHORT).show();;
            return;
        }
        items.add(item);
        adapter.notifyItemInserted(items.size() - 1);
        editText.setText("");
    }
}
