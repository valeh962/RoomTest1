package com.valehasadov.roomtest1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.valehasadov.roomtest1.MainActivity.EDIT_PERSON_REQUEST_CODE;

/**
 * Created by Valeh Asadov on 4/19/20.
 */

public class PersonRecyclerAdapter extends RecyclerView.Adapter<PersonRecyclerAdapter.ViewHolder> {

    private List<Person> personList = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;
    private OnDeleteClickListener listener;

    public PersonRecyclerAdapter(Context context, OnDeleteClickListener listener) {
        this.context = context;
        this.listener = listener;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PersonRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                               int viewType) {
        View view = inflater.inflate(R.layout.item_person_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonRecyclerAdapter.ViewHolder holder, int position) {
        if (!TextUtils.isEmpty(personList.get(position).getName())
                && !TextUtils.isEmpty(personList.get(position).getAge())) {
            holder.name.setText(personList.get(position).getName());
            holder.age.setText(personList.get(position).getAge());
        }

        holder.edit.setOnClickListener(v -> {
            {
                Intent intent = new Intent(context, EditPersonActivity.class);
                intent.putExtra("person_id", personList.get(position).getId());
                ((Activity) context).startActivityForResult(intent, EDIT_PERSON_REQUEST_CODE);
            }
        });

        holder.delete.setOnClickListener(v -> {
            {
                if (listener != null) {
                    listener.onDeleteClicked(personList.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public void setPeople(List<Person> people) {
        this.personList = people;
        notifyDataSetChanged();
    }

    public interface OnDeleteClickListener {
        void onDeleteClicked(Person person);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView age;
        ImageView edit;
        ImageView delete;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            edit = itemView.findViewById(R.id.edit);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
