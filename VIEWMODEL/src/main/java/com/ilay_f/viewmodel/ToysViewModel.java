package com.ilay_f.viewmodel;

import android.app.Application;

import com.ilay_f.model.Categories;
import com.ilay_f.model.Category;
import com.ilay_f.model.Toy;
import com.ilay_f.model.Toys;
import com.ilay_f.repository.BASE.DB.BaseRepository;
import com.ilay_f.repository.DB.CategoriesRepository;
import com.ilay_f.repository.DB.ToysRepository;
import com.ilay_f.viewmodel.BASE.BaseViewModel;

public class ToysViewModel extends BaseViewModel<Toy, Toys> {
    private ToysRepository repository;
    private Application application;


    public ToysViewModel(Application application) {
        super(Toy.class, Toys.class, application);
        this.application = application;
    }

    @Override
    protected BaseRepository<Toy, Toys> createRepository(Application application) {
        repository = new ToysRepository(application);
        return repository;
    }

    public void getAll() {
        getAllAscending(null, "name");
    }
}
