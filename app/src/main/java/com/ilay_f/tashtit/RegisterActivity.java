package com.ilay_f.tashtit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ilay_f.helper.inputValidators.CompareRule;
import com.ilay_f.helper.inputValidators.EmailRule;
import com.ilay_f.helper.inputValidators.EntryValidation;
import com.ilay_f.helper.inputValidators.NameRule;
import com.ilay_f.helper.inputValidators.PasswordRule;
import com.ilay_f.helper.inputValidators.Rule;
import com.ilay_f.helper.inputValidators.RuleOperation;
import com.ilay_f.helper.inputValidators.Validator;
import com.ilay_f.model.User;
import com.ilay_f.tashtit.ACTIVITIES.BASE.BaseActivity;
import com.ilay_f.tashtit.ACTIVITIES.MainActivity;
import com.ilay_f.tashtit.ACTIVITIES.MembersActivity;
import com.ilay_f.viewmodel.UsersViewModel;

public class RegisterActivity extends BaseActivity implements EntryValidation {
    private TextView tvRegisterTitle;
    private ImageView ivdefaultProfilePicture;
    private EditText etName;
    private EditText etRegisterEmail;
    private EditText etRegisterPassword;
    private EditText etRegisterConfirmPassword;
    private Button btnSave;
    private Button btnCancel;
    private UsersViewModel viewModel;
    private User user;

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
        setViewModel();
        bottomNavigationView.setVisibility(View.INVISIBLE);//העלמת התפריט
    }

    @Override
    protected void initializeViews() {
        tvRegisterTitle           = findViewById(R.id.tvRegisterTitle);
        ivdefaultProfilePicture   = findViewById(R.id.ivdefaultProfilePicture);
        etName                    = findViewById(R.id.etName);
        etRegisterEmail           = findViewById(R.id.etRegisterEmail);
        etRegisterPassword        = findViewById(R.id.etRegisterPassword);
        etRegisterConfirmPassword = findViewById(R.id.etRegisterConfirmPassword);
        btnSave                   = findViewById(R.id.btnSave);
        btnCancel                 = findViewById(R.id.btnCancel);
        setListeners();
    }

    @Override
    protected void setListeners() {//פעולות לחיצה על הכפתור
        btnSave.setOnClickListener(v -> {
            if(validate()){
                Toast.makeText(this,"Registered successfully",Toast.LENGTH_SHORT).show();

                user = new User();
                user.setEmail(etRegisterEmail.getText().toString());
                user.setPassword(etRegisterPassword.getText().toString());
                user.setUserName(etRegisterEmail.getText().toString());

                viewModel.save(user);
            }
            else{
                Toast.makeText(this,"Validation failed",Toast.LENGTH_SHORT).show();
            }
        });
        btnCancel.setOnClickListener(v -> {
            finish();
        });

        ivdefaultProfilePicture.setOnClickListener(v -> {

        });
    }

    @Override
    protected void setViewModel() {
        viewModel = new ViewModelProvider(this).get(UsersViewModel.class);

        viewModel.getSuccess().observe(this, aBoolean -> {
            if(aBoolean){
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish();
            }
            else{
                Toast.makeText(this,"Register Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void setValidation() {//פעולה שמחייבת להכניס נתונים לשדות שחייבים להיות בהם נתונים
        Validator.add(new Rule(etName, RuleOperation.REQUIRED,"Name is required"));
        Validator.add(new Rule(etRegisterEmail, RuleOperation.REQUIRED,"Email is required"));
        Validator.add(new Rule(etRegisterPassword, RuleOperation.REQUIRED,"Password is required"));
        Validator.add(new Rule(etRegisterConfirmPassword, RuleOperation.REQUIRED,"Confirm password is required"));


        Validator.add(new NameRule(etName, RuleOperation.TEXT,"Name is not valid"));
        Validator.add(new EmailRule(etRegisterEmail, RuleOperation.TEXT,"Email is not valid"));
        Validator.add(new PasswordRule(etRegisterPassword, RuleOperation.TEXT,"Password is not valid"));
        Validator.add(new CompareRule(etRegisterConfirmPassword,etRegisterPassword,RuleOperation.COMPARE,"Passwords are not match"));
    }

    @Override
    public boolean validate() {
        return Validator.validate();
    }
}