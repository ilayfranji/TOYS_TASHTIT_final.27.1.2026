package com.ilay_f.tashtit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ilay_f.helper.inputValidators.EntryValidation;
import com.ilay_f.helper.inputValidators.Validator;
import com.ilay_f.tashtit.ACTIVITIES.BASE.BaseActivity;
import com.ilay_f.tashtit.ACTIVITIES.MainActivity;

public class RegisterActivity extends BaseActivity implements EntryValidation {
    private TextView tvRegisterTitle;
    private ImageView ivdefaultProfilePicture;
    private EditText etName;
    private EditText etRegisterEmail;
    private EditText etRegisterPassword;
    private Button btnSave;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setLayout(R.layout.activity_register);
        initializeActivity();
    }

    @Override
    protected void initializeActivity() {
        initializeViews();
        setValidation();
        bottomNavigationView.setVisibility(View.INVISIBLE);//העלמת התפריט
    }

    @Override
    protected void initializeViews() {
        tvRegisterTitle       = findViewById(R.id.tvRegisterTitle);
        ivdefaultProfilePicture = findViewById(R.id.ivdefaultProfilePicture);
        etName                = findViewById(R.id.etName);
        etRegisterEmail       = findViewById(R.id.etRegisterEmail);
        etRegisterPassword    = findViewById(R.id.etRegisterPassword);
        btnSave               = findViewById(R.id.btnSave);
        btnCancel             = findViewById(R.id.btnCancel);
        setListeners();
    }

    @Override
    protected void setListeners() {//פעולות לחיצה על הכפתור
        btnSave.setOnClickListener(v -> {

        });
        btnCancel.setOnClickListener(v -> {
            finish();
        });

        ivdefaultProfilePicture.setOnClickListener(v -> {

        });
    }

    @Override
    protected void setViewModel() {

    }

    @Override
    public void setValidation() {

    }

    @Override
    public boolean validate() {
        return Validator.validate();
    }
}