package com.telcco.klipmunk;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InviteContactAdapter extends RecyclerView.Adapter<InviteContactAdapter.ViewHolder> {
    ArrayList<InviteContMod> inviteContModArrayList;
    Activity context;

    public InviteContactAdapter(ArrayList<InviteContMod> inviteContModArrayList, Activity context) {
        this.inviteContModArrayList = inviteContModArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public InviteContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.invitecontactsrec,parent,false);
        InviteContactAdapter.ViewHolder viewHolder = new InviteContactAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InviteContactAdapter.ViewHolder holder, int position) {
        holder.con_name.setText(inviteContModArrayList.get(position).getName());
        holder.con_email.setText(inviteContModArrayList.get(position).getName());
        holder.con_number.setText(inviteContModArrayList.get(position).getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return inviteContModArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.con_name)
        TextView con_name;
        @BindView(R.id.con_email)
        TextView con_email;
        @BindView(R.id.con_number)
        TextView con_number;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
