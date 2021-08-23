package com.fishnco.contactroom.adapter;

import android.content.Context;
import android.util.Log;
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
    private OnContactClickListener onContactClickListener;

    public RecyclerViewAdapter(List<Contact> contactList, Context context, OnContactClickListener onContactClickListener) {
        this.contactList = contactList;
        this.context = context;
        this.onContactClickListener = onContactClickListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create view which inflates from contact_row
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row, parent, false);

        return new ViewHolder(view, onContactClickListener);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // itemView is anything that we have added inside the row
        // access to widgets inside the row
        public TextView textViewName;
        public TextView textViewOccupation;
        OnContactClickListener onContactClickListener;

        public ViewHolder(@NonNull View itemView, OnContactClickListener onContactClickListener) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.row_textView_name);
            textViewOccupation = itemView.findViewById(R.id.row_textView_occupation);
            this.onContactClickListener = onContactClickListener;

            // Every view has a click event set up
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onContactClickListener.onContactClick(getAdapterPosition());
        }
    }

    public interface OnContactClickListener {
        void onContactClick (int position);
    }
}
