package com.ilay_f.repository.DB;

import android.content.Context;

import com.google.firebase.firestore.Query;
import com.ilay_f.model.Categories;
import com.ilay_f.model.Category;
import com.ilay_f.model.Toy;
import com.ilay_f.model.Toys;
import com.ilay_f.repository.BASE.DB.BaseRepository;

public class ToysRepository extends BaseRepository<Toy, Toys> {

    public ToysRepository(Context context) {
        super(Toy.class, Toys.class, context);
    }

    @Override
    protected Query getQueryForExist(Toy entity) {
        return getCollection().whereEqualTo("name", entity.getName());
    }
}
