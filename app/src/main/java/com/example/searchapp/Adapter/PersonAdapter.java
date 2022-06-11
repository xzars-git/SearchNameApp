package com.example.searchapp.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.searchapp.Model.Person;
import com.example.searchapp.R;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {

    List<Person> personList;

    public PersonAdapter(List<Person> personList) {
        this.personList = personList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.person_layout, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder myViewHolder, int i) {
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

        public MyViewHolder(@NonNull  View itemView) {
            super(itemView);
            root_view = (CardView) itemView.findViewById(R.id.root_view);
            text_PhoneNumber = (TextView) itemView.findViewById(R.id.text_PhoneNumber);
            text_email = (TextView) itemView.findViewById(R.id.text_email);
            text_name = (TextView) itemView.findViewById(R.id.text_name);


        }
    }

}
