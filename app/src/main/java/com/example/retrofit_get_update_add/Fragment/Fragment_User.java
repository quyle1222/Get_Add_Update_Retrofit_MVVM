package com.example.retrofit_get_update_add.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.retrofit_get_update_add.R;
import com.example.retrofit_get_update_add.model.User;
import com.example.retrofit_get_update_add.viewmodel.ViewModel;

public class Fragment_User extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String id, userName, email, password;
    private Button btnADD, btnUpdate;

    ViewModel viewModel;
    private String mParam1;
    private String mParam2;

    private EditText edtID, edtUserName, edtEmail, edtPassword;

    public Fragment_User() {
    }

    public static Fragment_User newInstance(String param1, String param2) {
        Fragment_User fragment = new Fragment_User();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment__list, container, false);
        id = getArguments().getString("id");
        userName = getArguments().getString("userName");
        email = getArguments().getString("email");
        password = getArguments().getString("password");
        initView(view);

        edtID.setText(id);
        edtUserName.setText(userName);
        edtEmail.setText(email);
        edtPassword.setText(password);

        btnADD.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(edtID.getText().toString().trim(), edtUserName.getText().toString().trim(), edtEmail.getText().toString().trim(), edtPassword.getText().toString().trim());
                postUser(view, user);
            }
        });
        btnUpdate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User(edtID.getText().toString().trim(), edtUserName.getText().toString().trim(), edtEmail.getText().toString().trim(), edtPassword.getText().toString().trim());
                updateUser(view, user);
            }
        });
        return view;
    }

    private void initView(View view) {
        edtID = view.findViewById(R.id.edtID);
        edtUserName = view.findViewById(R.id.edtUserName);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPassword = view.findViewById(R.id.edtPassword);
        btnADD = view.findViewById(R.id.btnADD);
        btnUpdate = view.findViewById(R.id.btnUpdate);
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);

    }

    private void postUser(View view, User user) {
        viewModel.setUser(user);
        viewModel.postUser().observe(this, items -> {
            if (items != null) {
                Toast.makeText(view.getContext(), "Post Complete", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUser(View view, User user) {
        viewModel.setUser(user);
        viewModel.updateUser().observe(this, items -> {
            if (items != null) {
                Toast.makeText(view.getContext(), "Update Complete", Toast.LENGTH_SHORT).show();
            }
        });
    }
}