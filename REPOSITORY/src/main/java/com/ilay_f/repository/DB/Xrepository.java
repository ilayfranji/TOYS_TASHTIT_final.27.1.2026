package com.ilay_f.repository.DB;

import com.google.firebase.firestore.Query;

import com.ilay_f.model.BASE.BaseEntity;
import com.ilay_f.repository.BASE.DB.BaseRepository;

public class Xrepository extends BaseRepository {
    @Override
    protected Query getQueryForExist(BaseEntity entity) {
        return null;
    }
}
