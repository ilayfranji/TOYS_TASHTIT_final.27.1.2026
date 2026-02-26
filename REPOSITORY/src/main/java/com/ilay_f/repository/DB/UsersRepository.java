package com.ilay_f.repository.DB;

import android.app.Application;

import com.google.firebase.firestore.Query;
import com.ilay_f.model.User;
import com.ilay_f.model.Users;
import com.ilay_f.repository.BASE.DB.BaseRepository;

public class UsersRepository extends BaseRepository<User, Users> {//יורשת מבייס רפוסיטורי
    public UsersRepository(Application application) {//פעולה בונה למחלקה
        super(User.class, Users.class,application);
    }
    @Override
    protected Query getQueryForExist(User entity) {//מתודה שבודקת האם קיימת יישות בדאטה בייס כדי לא להוסיף אותה שוב
        return getCollection().whereEqualTo("email",entity.getEmail());
    }
}