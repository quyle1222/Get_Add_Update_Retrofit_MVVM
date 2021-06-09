package com.example.retrofit_get_update_add.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.retrofit_get_update_add.model.Items;
import com.example.retrofit_get_update_add.model.User;
import com.example.retrofit_get_update_add.repository.Repository;

public class ViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<Items> getUserList;
    private LiveData<Items> postUserList;
    private LiveData<Items> updateUserList;
    private User user;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository();
        this.getUserList = repository.getListUser();

    }

    public LiveData<Items> getUserListLiveData() {
        return getUserList;
    }

    public LiveData<Items> postUser() {
        this.postUserList = repository.postListUser(user);
        return postUserList;
    }

    public LiveData<Items> updateUser() {
        String id = user.getId();
        User userUpdate = new User(user.getUsername(), user.getEmail(), user.getPassword());
        this.updateUserList = repository.updateUser(id,userUpdate);
        return updateUserList;
    }

    public User setUser(User user) {
        return this.user = user;
    }

}
