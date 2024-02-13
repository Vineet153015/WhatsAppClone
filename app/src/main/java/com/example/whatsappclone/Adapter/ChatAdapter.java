package com.example.whatsappclone.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.Models.Messagmodel;
import com.example.whatsappclone.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatAdapter extends RecyclerView.Adapter {

    ArrayList<Messagmodel> messagmodels;
    Context context;
    String recId;

    public ChatAdapter(ArrayList<Messagmodel> messagmodels, Context context, String recId) {
        this.messagmodels = messagmodels;
        this.context = context;
        this.recId = recId;
    }

    int SENDER_VIEW_TYPE= 1;
    int RECIVER_VIEW_TYPE=2;

    public ChatAdapter(ArrayList<Messagmodel> messagmodels ,Context context){
        this.messagmodels = messagmodels;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
            return new SenderViewHolder(view);
        }

        else {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_reciver,parent,false);
            return new RecieverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (messagmodels.get(position).getuId().equals(FirebaseAuth.getInstance().getUid())){
            return SENDER_VIEW_TYPE;
        }
        else{
            return RECIVER_VIEW_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Messagmodel messagmodel = messagmodels.get(position);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                new AlertDialog.Builder(context).setTitle("Delete")
                        .setMessage("Are you sure You want to Delete this Message?..")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String senderRoom = FirebaseAuth.getInstance().getUid() + recId;
                                database.getReference().child("chats").child(senderRoom)
                                        .child(messagmodel.getMessageId())
                                        .setValue(null);

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                return false;
            }
        });

        if (holder.getClass() == SenderViewHolder.class){
            ((SenderViewHolder)holder).senderMsg.setText(messagmodel.getMessage());

            Date date = new Date(messagmodel.getTimestamp());
            SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("h:mm:a");
            String strDate = simpleDateFormat.format(date);
            ((SenderViewHolder)holder).senderTime.setText(strDate.toString());
        }

        else {
            ((RecieverViewHolder)holder).recieverMsg.setText(messagmodel.getMessage());

            Date date = new Date(messagmodel.getTimestamp());
            SimpleDateFormat simpleDateFormat  = new SimpleDateFormat("h:mm:a");
            String strDate = simpleDateFormat.format(date);
            ((RecieverViewHolder)holder).recieveTime.setText(strDate.toString());
        }
    }






    @Override
    public int getItemCount() {
        return messagmodels.size();
    }

    public class RecieverViewHolder extends RecyclerView.ViewHolder{

        TextView recieverMsg, recieveTime;


        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);

            recieverMsg = itemView.findViewById(R.id.recivertext);
            recieveTime = itemView.findViewById(R.id.reciverTime);
        }
    }


    public  class SenderViewHolder extends  RecyclerView.ViewHolder{

        TextView senderMsg, senderTime;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);

            senderMsg = itemView.findViewById(R.id.sendertext);
            senderTime = itemView.findViewById(R.id.sendertime);
        }
    }

}
