package com.ilay_f.viewmodel;

import android.app.Application;

import com.ilay_f.model.User;
import com.ilay_f.model.Users;
import com.ilay_f.repository.BASE.DB.BaseRepository;
import com.ilay_f.repository.DB.UsersRepository;
import com.ilay_f.viewmodel.BASE.BaseViewModel;

public class UsersViewModel extends BaseViewModel<User, Users> {
    public UsersRepository repository;

    public UsersViewModel(Class<User> tEntity, Class<Users> tCollection, Application application) {//יוצר וויו מודל חדש
        super(User.class, Users.class, application);
    }

    @Override
    protected BaseRepository<User, Users> createRepository(Application application) {//יוצרת רפוסיטורי
        repository=new UsersRepository(application);// יוצרים רפוסיטורי חדש לוויו מודל
        return repository;
    }
}