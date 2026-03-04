package com.ilay_f.tashtit.ACTIVITIES;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ilay_f.helper.inputValidators.CompareRule;
import com.ilay_f.helper.inputValidators.EmailRule;
import com.ilay_f.helper.inputValidators.EntryValidation;
import com.ilay_f.helper.inputValidators.NameRule;
import com.ilay_f.helper.inputValidators.PasswordRule;
import com.ilay_f.helper.inputValidators.Rule;
import com.ilay_f.helper.inputValidators.RuleOperation;
import com.ilay_f.helper.inputValidators.Validator;
import com.ilay_f.model.User;
import com.ilay_f.tashtit.R;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ilay_f.tashtit.ACTIVITIES.BASE.BaseActivity;
import com.ilay_f.tashtit.RegisterActivity;
import com.ilay_f.viewmodel.UsersViewModel;

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
    private UsersViewModel viewModel;

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
        setViewModel();
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
            if(validate()){
                viewModel.login(etEmail.getText().toString(), etPassword.getText().toString());
            }

        });
        tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
        tvContinueAsGuest.setOnClickListener(v -> {

        });
    }

    @Override
    protected void setViewModel() {
        viewModel=new ViewModelProvider(this).get(UsersViewModel.class);

        viewModel.getLiveDataEntity().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user==null) {
                    Toast.makeText(MainActivity.this, "Login succeed", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void setValidation() {//פעולה שמחייבת להכניס נתונים לשדות שחייבים להיות בהם נתונים
        Validator.add(new Rule(etEmail, RuleOperation.REQUIRED,"Email is required"));
        Validator.add(new Rule(etPassword, RuleOperation.REQUIRED,"Password is required"));

        Validator.add(new EmailRule(etEmail, RuleOperation.TEXT,"Email is not valid"));
        Validator.add(new PasswordRule(etPassword, RuleOperation.TEXT,"Password is not valid"));
    }

    @Override
    public boolean validate() {
        return Validator.validate();
    }
}