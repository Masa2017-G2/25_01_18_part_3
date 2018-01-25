package com.sheygam.masa_g2_25_01_18_part_3;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gregorysheygam on 25/01/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<Person> persons;
    private AdapterClickListener listener;

    public MyAdapter(AdapterClickListener listener) {
        this.listener = listener;
        persons = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            persons.add(new Person("Person " + i, "person" + i + "@mail.com"));
        }
    }

    public void addPerson(){
        persons.add(1,new Person("User","user@mail.com"));
        notifyItemInserted(1);
    }

    public void remove(){
        persons.remove(3);
        notifyItemRemoved(3);
    }

    public void remove(int index){
        persons.remove(index);
        notifyItemRemoved(index);
    }

    public void onMove(int from, int to){
        Person p = persons.remove(from);
        persons.add(to,p);
        notifyItemMoved(from,to);
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("MY_TAG", "onCreateViewHolder: ");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_row, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Log.d("MY_TAG", "onBindViewHolder: ");
        Person p = persons.get(position);
        holder.nameTxt.setText(p.getName());
        holder.emailTxt.setText(p.getEmail());
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTxt, emailTxt;
        public MyViewHolder(View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.name_txt);
            emailTxt = itemView.findViewById(R.id.email_txt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.onRowClick(getAdapterPosition(),persons.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public interface AdapterClickListener{
        void onRowClick(int position, Person p);
    }
}
