package com.example.lab;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public  class CA extends RecyclerView.Adapter<CA.ViewHolder> {
    public static int pos;
    static List<Contact> contacts;
    Context context;

    public CA(Context context,List<Contact> contacts) {
        this.context = context;
        this.contacts=contacts;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView firstName;
        TextView lastName;
        TextView phone;
        ImageButton btnEdit,btnDel,btnCall;

        public ViewHolder(View v) {
            super(v);
            firstName = v.findViewById(R.id.fn);
            lastName = v.findViewById(R.id.ln);
            phone = v.findViewById(R.id.pn);
            btnEdit=v.findViewById(R.id.btnEdit);
            btnDel=v.findViewById(R.id.btnDelete);
            btnCall=v.findViewById(R.id.btnCall);
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i =new Intent(context,MainActivity3.class);
                    i.putExtra("fn",firstName.getText().toString());
                    i.putExtra("ln",lastName.getText().toString());
                    i.putExtra("pn",phone.getText().toString());
                    context.startActivity(i);

                }
            });
            btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                     pos=getBindingAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        ExecutorService es= Executors.newSingleThreadExecutor();
                        es.execute(new Runnable() {
                            @Override
                            public void run() {
                                MYDialog m=new MYDialog();
                                m.show(((MainActivity)context).getSupportFragmentManager(),"default");



//
                            }
                        });
                    }
                }
            });
            btnCall.setOnClickListener(view -> {
                int pos = getBindingAdapterPosition();
                if (pos != RecyclerView.NO_POSITION) {
                    Intent i = new Intent(Intent.ACTION_DIAL);
                    i.setData(Uri.parse("tel:" + contacts.get(pos).phoneNumber));
                    context.startActivity(i);
                }
            });




       }

    }
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(v);
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.firstName.setTextColor(Color.BLACK);
        holder.lastName.setTextColor(Color.BLACK);
        holder.phone.setTextColor(Color.BLACK);

        holder.firstName.setText(contacts.get(position).firstName);
        holder.lastName.setText(contacts.get(position).lastName);
        holder.phone.setText(contacts.get(position).phoneNumber+"");

    }
    public int getItemCount() {
        return contacts.size();
    }
}
