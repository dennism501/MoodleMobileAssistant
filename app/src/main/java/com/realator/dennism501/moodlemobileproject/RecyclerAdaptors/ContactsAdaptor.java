package com.realator.dennism501.moodlemobileproject.RecyclerAdaptors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.realator.dennism501.moodlemobileproject.POJO.Contact;
import com.realator.dennism501.moodlemobileproject.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dennism501 on 5/19/17.
 */

public class ContactsAdaptor extends RecyclerView.Adapter<ContactsAdaptor.ViewHolder> {

    private Context mcontext;
    private onItemClickListener itemClickListener;
    private List<Contact> contacts;

    public ContactsAdaptor(List<Contact> contacts, Context mcontext) {
        this.contacts = contacts;
        this.mcontext = mcontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_view,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Contact contact = contacts.get(position);
        holder.txtContactname.setText(contact.getFullname());
        holder.txtContactstatus.setText(contact.getStatus());
        Picasso.with(mcontext)
                .load(contact.getGetProfileimageurlsmall())
                .fit().centerCrop()
                .into(holder.imgContactimage);


    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        private TextView txtContactname;
        private TextView txtContactstatus;
        private ImageView imgContactimage;
        private LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);

            txtContactname = (TextView)itemView.findViewById(R.id.contact_name);
            txtContactstatus = (TextView)itemView.findViewById(R.id.contact_status);
            imgContactimage = (ImageView)itemView.findViewById(R.id.contact_image);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.contact_holder);
            linearLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {

            if (itemClickListener != null) {

                itemClickListener.onItemClick(itemView, getPosition());
            }

        }
    }

    public interface onItemClickListener {

        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(final onItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;

    }
}
