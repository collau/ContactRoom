package com.fishnco.contactroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fishnco.contactroom.R;
import com.fishnco.contactroom.model.Contact;

import java.util.List;
import java.util.Objects;

/**
 * Created by junyi on 23/8/21
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Contact> contactList;
    private Context context;

    public RecyclerViewAdapter(List<Contact> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create view which inflates from contact_row
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Contact contact = Objects.requireNonNull(contactList.get(position));
        holder.textViewName.setText(contact.getName());
        holder.textViewOccupation.setText(contact.getOccupation());

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // itemView is anything that we have added inside the row
        // access to widgets inside the row
        public TextView textViewName;
        public TextView textViewOccupation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.row_textView_name);
            textViewOccupation = itemView.findViewById(R.id.row_textView_occupation);
        }
    }
}
