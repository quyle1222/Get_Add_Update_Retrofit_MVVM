package com.example.retrofit_get_update_add.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit_get_update_add.Fragment.Fragment_User;
import com.example.retrofit_get_update_add.R;
import com.example.retrofit_get_update_add.model.User;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<User> userModelArrayList;
    LinearLayout linearLayout;

    public RecyclerViewAdapter(Context mContext, ArrayList<User> userModelArrayList) {
        this.context = mContext;
        this.userModelArrayList = userModelArrayList;
        notifyDataSetChanged();
    }

    public RecyclerViewAdapter(Context mContext, ArrayList<User> userModelArrayList, LinearLayout linearLayout) {
        this.context = mContext;
        this.userModelArrayList = userModelArrayList;
        this.linearLayout = linearLayout;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        User userModel = userModelArrayList.get(position);
        holder.txtID.setText(userModel.getId());
        holder.txtUserName.setText(userModel.getUsername());
        holder.txtEmail.setText(userModel.getEmail());
        holder.txtPassword.setText(userModel.getPassword());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activityCompat = (AppCompatActivity) v.getContext();
                Fragment_User fragmentList = new Fragment_User();

                Bundle bundle = new Bundle();
                bundle.putString("id", holder.txtID.getText().toString());
                bundle.putString("userName", holder.txtUserName.getText().toString());
                bundle.putString("email", holder.txtEmail.getText().toString());
                bundle.putString("password", holder.txtPassword.getText().toString());
                fragmentList.setArguments(bundle);
                activityCompat.getSupportFragmentManager().beginTransaction().replace(R.id.mainContainer, fragmentList).addToBackStack(null).commit();
                linearLayout.setVisibility(View.GONE);

            }
        });
    }

    @Override
    public int getItemCount() {
        return userModelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtID;
        private TextView txtUserName;
        private TextView txtEmail;
        private TextView txtPassword;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtID = itemView.findViewById(R.id.txtID);
            txtUserName = itemView.findViewById(R.id.txtUserName);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtPassword = itemView.findViewById(R.id.txtPassword);
        }
    }
}
