package com.ilay_f.tashtit.ACTIVITIES;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;

import com.google.android.material.button.MaterialButton;
import com.ilay_f.helper.inputValidators.EntryValidation;
import com.ilay_f.helper.inputValidators.Validator;
import com.ilay_f.tashtit.ACTIVITIES.BASE.BaseActivity;
import com.ilay_f.tashtit.R;

public class ToyActivity extends BaseActivity implements EntryValidation  {
    private EditText etName, etDescription, etPrice, etQuantity, etDesignDate;
    private MaterialButton btnSave, btnCancel;
    private Spinner spinnerCategory;
    private ImageView ivImage;
    private ImageButton ibPencil, ibCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setLayout(R.layout.activity_toy);
        initializeActivity();

    }

    @Override
    protected void initializeActivity() {
        initializeViews();
        setViewModel();
    }

    @Override
    protected void initializeViews() {
        setListeners();
    }

    @Override
    protected void setListeners() {}

    @Override
    protected void setViewModel() {}

    @Override
    public void setValidation() {}

    @Override
    public boolean validate() {
        return /*false*/ Validator.validate();
    }
}