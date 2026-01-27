package com.ilay_f.viewmodel;

import android.app.Application;

import com.ilay_f.model.Categories;
import com.ilay_f.model.Category;
import com.ilay_f.repository.BASE.DB.BaseRepository;
import com.ilay_f.repository.DB.CategoriesRepository;
import com.ilay_f.viewmodel.BASE.BaseViewModel;

public class CategoriesViewModel extends BaseViewModel<Category, Categories> {
    private CategoriesRepository repository;

    public CategoriesViewModel(Application application) {
        super(Category.class, Categories.class, application);
    }

    @Override
    protected BaseRepository<Category, Categories> createRepository(
            Application application) {
        repository = new CategoriesRepository(application);
        return repository;
    }

    public void getAll() {
        getAllAscending(null, "name");
    }
}


