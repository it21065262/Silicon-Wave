package com.example.mad_mini_project;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


public class MainAdapter extends FirebaseRecyclerAdapter <item,MainAdapter.viewHolder> {

    public MainAdapter(@NonNull FirebaseRecyclerOptions<item> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, @SuppressLint("RecyclerView") final int position, @NonNull item model) {
        holder.name.setText(model.getName());
        holder.price.setText(model.getPrice());

        Glide.with(holder.img.getContext())
                .load(model.getUrl())
                .placeholder(R.drawable.b1)
                .circleCrop()
                .error(R.drawable.b1)
                .into(holder.img);

        holder.btnUpdate.setOnClickListener(v -> {
            final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                    .setContentHolder(new ViewHolder(R.layout.update_popup))
                    .setExpanded(true, 1200)
                    .create();

            dialogPlus.show();

            View view = dialogPlus.getHolderView();

            EditText name = (EditText) view.findViewById(R.id.txtName);
            EditText price = (EditText) view.findViewById(R.id.txtPrice);
            EditText url = (EditText) view.findViewById(R.id.txtImageUrl);
            Button btnUpdate = (Button) view.findViewById(R.id.btnUpdatePop);

            name.setText(model.getName());
            price.setText(model.getPrice());
            url.setText(model.getUrl());

            dialogPlus.show();

            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", name.getText().toString());
                    map.put("price", price.getText().toString());
                    map.put("url", url.getText().toString());

                    FirebaseDatabase.getInstance().getReference().child("Items")
                            .child(Objects.requireNonNull(getRef(position).getKey())).updateChildren(map)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    dialogPlus.dismiss();
                                }
                            })

                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    dialogPlus.dismiss();
                                }
                            });
                }
            });


            holder.btnDelete.setOnClickListener(view1 -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Sure?");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("Items")
                                .child(Objects.requireNonNull(getRef(position).getKey())).removeValue();

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(holder.name.getContext(), "Cancelled", Toast.LENGTH_LONG).show();
                    }
                });

            });
        });

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, parent, false);
        return new viewHolder(view);
    }

    static class viewHolder extends RecyclerView.ViewHolder {

        CircleImageView img;
        TextView name;
        TextView price;
        Button btnUpdate;
        Button btnDelete;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            img = (CircleImageView) itemView.findViewById(R.id.img1);
            name = (TextView) itemView.findViewById(R.id.nametext);
            price = (TextView) itemView.findViewById(R.id.pricetext);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
            btnUpdate = (Button) itemView.findViewById(R.id.btnUpdatePop);
        }
    }
}
