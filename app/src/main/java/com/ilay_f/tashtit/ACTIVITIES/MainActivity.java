package com.ilay_f.tashtit.ACTIVITIES;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ilay_f.helper.inputValidators.EntryValidation;
import com.ilay_f.helper.inputValidators.Validator;
import com.ilay_f.tashtit.R;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.ilay_f.tashtit.ACTIVITIES.BASE.BaseActivity;
import com.ilay_f.tashtit.RegisterActivity;

public class MainActivity extends BaseActivity implements EntryValidation {
    private TextView  tvTitle;
    private TextView  tvWelcome;
    private ImageView ivlogo;
    private EditText  etEmail;
    private EditText  etPassword;
    private CheckBox  cbRememberMe;
    private Button    btnLogIn;
    private TextView  tvContinueAsGuest;
    private TextView  tvDontHaveAccount;
    private TextView  tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_main);
        //getLayoutInflater().inflate(R.layout.activity_main, findViewById(R.id.content_frame));
        setLayout(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeActivity();
    }


    @Override
    protected void initializeActivity() {
        initializeViews();
        setValidation();
        bottomNavigationView.setVisibility(View.INVISIBLE);//העלמת התפריט בתחתית המסך
    }


    @Override
    protected void initializeViews() {
        tvTitle           = findViewById(R.id.tvTitle);
        tvWelcome         = findViewById(R.id.tvWelcome);
        ivlogo            = findViewById(R.id.ivLogo);
        etEmail           = findViewById(R.id.etEmail);
        etPassword        = findViewById(R.id.etPassword);
        cbRememberMe      = findViewById(R.id.cbRememberMe);
        btnLogIn          = findViewById(R.id.btnLogIn);
        tvContinueAsGuest = findViewById(R.id.tvContinueAsGuest);
        tvDontHaveAccount = findViewById(R.id.tvDontHaveAccount);
        tvRegister        = findViewById(R.id.tvRegister);
        setListeners();
    }

    @Override
    protected void setListeners() {
        btnLogIn.setOnClickListener(v -> {

        });
        tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
        tvContinueAsGuest.setOnClickListener(v -> {

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