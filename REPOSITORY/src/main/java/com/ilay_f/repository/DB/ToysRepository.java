package com.ilay_f.repository.DB;

import android.content.Context;

import com.google.firebase.firestore.Query;
import com.ilay_f.model.Categories;
import com.ilay_f.model.Category;
import com.ilay_f.repository.BASE.DB.BaseRepository;

public class ToysRepository extends BaseRepository<Category, Categories> {

    public ToysRepository(Context context) {
        super(Category.class, Categories.class, context);
    }

    @Override
    protected Query getQueryForExist(Category entity) {
        return getCollection().whereEqualTo("name", entity.getName());
    }
}
