package com.example.searchapp.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.searchapp.Model.Person;
import com.example.searchapp.R;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {

    List<Person> personList;

    public PersonAdapter(List<Person> personList) {
        this.personList = personList;
    }

    @NonNull
    @androidx.annotation.NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @androidx.annotation.NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.person_layout, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @androidx.annotation.NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.text_name.setText(personList.get(i).getName());
        myViewHolder.text_email.setText(personList.get(i).getEmail());
        myViewHolder.text_PhoneNumber.setText(personList.get(i).getPhone());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        CardView root_view;
        TextView text_PhoneNumber, text_name, text_email;

        public MyViewHolder(@NonNull @androidx.annotation.NonNull View itemView) {
            super(itemView);
            root_view = (CardView) itemView.findViewById(R.id.root_view);
            text_PhoneNumber = (TextView) itemView.findViewById(R.id.text_PhoneNumber);
            text_email = (TextView) itemView.findViewById(R.id.text_email);
            text_name = (TextView) itemView.findViewById(R.id.text_name);


        }
    }

}
