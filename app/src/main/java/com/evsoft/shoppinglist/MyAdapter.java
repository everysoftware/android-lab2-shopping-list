package com.evsoft.shoppinglist;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final ArrayList<String> items;
    private final MainActivity mainActivity; // Ссылка на главную активность

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MyViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.textView);
        }
    }

    public MyAdapter(ArrayList<String> items, MainActivity mainActivity) {
        this.items = items;
        this.mainActivity = mainActivity; // Инициализация ссылки на активность
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);

        // Обработчик нажатия для редактирования элемента
        vh.textView.setOnClickListener(view -> {
            int position = vh.getAdapterPosition();
            String item = items.get(position);

            // Создание диалогового окно для редактирования элемента
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(R.string.edit_button);

            final EditText input = new EditText(mainActivity);
            input.setText(item);
            builder.setView(input);

            builder.setPositiveButton("OK", (dialog, which) -> {
                String newItem = input.getText().toString().trim();
                if (newItem.isEmpty()) {
                    Toast.makeText(mainActivity, R.string.empty_product, Toast.LENGTH_SHORT).show();;
                    return;
                }
                items.set(position, newItem);
                notifyItemChanged(position);
            });
            builder.setNegativeButton("Отмена", null);

            builder.show();
        });

        // Обработчик долгого нажатия для удаления элемента
        vh.textView.setOnLongClickListener(view -> {
            int position = vh.getAdapterPosition();
            items.remove(position);
            notifyItemRemoved(position);
            return true;
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
