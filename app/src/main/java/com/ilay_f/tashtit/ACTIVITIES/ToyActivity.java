package com.ilay_f.tashtit.ACTIVITIES;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.ilay_f.helper.AlertDialogHelper;
import com.ilay_f.helper.BitMapHelper;
import com.ilay_f.helper.DateUtil;
import com.ilay_f.helper.LauncherHelper;
import com.ilay_f.helper.ListUtil;
import com.ilay_f.helper.inputValidators.DateRule;
import com.ilay_f.helper.inputValidators.EntryValidation;
import com.ilay_f.helper.inputValidators.ImageRule;
import com.ilay_f.helper.inputValidators.NameRule;
import com.ilay_f.helper.inputValidators.NumberRule;
import com.ilay_f.helper.inputValidators.Rule;
import com.ilay_f.helper.inputValidators.RuleOperation;
import com.ilay_f.helper.inputValidators.TextRule;
import com.ilay_f.helper.inputValidators.Validator;
import com.ilay_f.model.Category;
import com.ilay_f.model.Toy;
import com.ilay_f.tashtit.ACTIVITIES.BASE.BaseActivity;
import com.ilay_f.tashtit.R;
import com.ilay_f.viewmodel.CategoriesViewModel;
import com.ilay_f.viewmodel.ToysViewModel;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ToyActivity extends BaseActivity implements EntryValidation {
    private EditText etToyName, etToyDescription, etToyPrice, etToyQuantity, etDesignDate;
    private MaterialButton btnSave, btnCancel;
    private Spinner spnToyCategory;
    private ImageButton ivPencil, ivDesignDate;
    private ImageView ivToy;
    private ImageView ivError;
    private Bitmap toyBitmap;
    private LauncherHelper launcherHelper;
    private  ToysViewModel toyViewModel;
    private CategoriesViewModel categoriesViewModel;

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
        spnToyCategory = findViewById(R.id.spnToyCategory);
        ivToy = findViewById(R.id.ivToy);
        ivPencil = findViewById(R.id.ibPencil);
        ivDesignDate = findViewById(R.id.ivDesignDate);
        etDesignDate = findViewById(R.id.etDesignDate);
        etToyQuantity = findViewById(R.id.etToyQuantity);
        etToyPrice = findViewById(R.id.etToyPrice);
        etToyDescription = findViewById(R.id.etToyDescription);
        etToyName = findViewById(R.id.etToyName);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);
        launcherHelper = new LauncherHelper(this);
        setListeners();
    }

    @Override
    protected void setListeners() {
        ivPencil.setOnClickListener(v -> {
            AlertDialogHelper.showTakePicture(this,
                    () -> {
                        launcherHelper.takePhotoWithPermissionCheck(bitmap -> {
                            if (bitmap != null) {
                                // Photo taken successfully
                                toyBitmap = bitmap;
                                ivToy.setImageBitmap(bitmap);
                            } else {
                                // Photo failed or permission denied
                                Toast.makeText(ToyActivity.this, "Photo failed or permission denied", Toast.LENGTH_SHORT).show();
                            }
                        });
                    },
                    () -> {
                        launcherHelper.pickImage(uri -> {
                            if (uri != null) {
                                toyBitmap = BitMapHelper.getBitmapFromUri(this, uri,
                                        ivToy.getWidth(), ivToy.getHeight());
                                ivToy.setImageBitmap(toyBitmap);
                            } else {
                                // Photo failed or permission denied
                                // Toast.makeText(ToyActivity.this, "Photo failed or permission denied", Toast.LENGTH_SHORT).show();
                            }
                        });
                    });
        });

        ivDesignDate.setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                CalendarConstraints constraints =
                        DateUtil.buidCalendarConstrains(LocalDate.now().minusYears(100),
                                LocalDate.now(),
                                LocalDate.now());

                MaterialDatePicker.Builder<Long> builder =
                        MaterialDatePicker.Builder.datePicker();
                MaterialDatePicker<Long> datePicker = builder
                        .setTitleText("Select Date")
                        .setCalendarConstraints(constraints)
                        .setTextInputFormat(new SimpleDateFormat("dd/MM/yyyy"))
                        .build();

                datePicker.addOnPositiveButtonClickListener(selection -> {
                    LocalDate date = Instant.ofEpochMilli(selection)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();

                    DateTimeFormatter formatter =
                            DateTimeFormatter.ofPattern(DateUtil.FORMAT_DD_MM_YYYY);

                    etDesignDate.setText(date.format(formatter));
                });

                datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
            }
        });
        btnSave.setOnClickListener( v ->{

            if (validate()) {
                Toy toy = new Toy();
                toy.setImage(BitMapHelper.encodeTobase64(toyBitmap));
                toy.setName(etToyName.getText().toString());
                toy.setDescription(etToyDescription.getText().toString());
                toy.setCategoryId(((Category) spnToyCategory.getSelectedItem()).getIdFs());
                toy.setPrice(Double.parseDouble(etToyPrice.getText().toString()));
                toy.setQuantity(Integer.parseInt(etToyQuantity.getText().toString()));
                toy.setDesignDate(DateUtil.stringToLong(etDesignDate.getText().toString(), "dd/MM/yyyy"));
                //if (oldToy != null)
                //    toy.setIdFs(oldToy.getIdFs());
                showProgressDialog("Save", "Saving " + toy.getName() + "...");
                toyViewModel.save(toy);
                finish();
            }
        });
    }

    @Override
    protected void setViewModel() {
        toyViewModel = new ViewModelProvider(this)
                .get(ToysViewModel.class);

        categoriesViewModel = new ViewModelProvider(this)
                .get(CategoriesViewModel.class);

        categoriesViewModel.getAll();

        categoriesViewModel.getLiveDataCollection()
                .observe(this, categories -> {
                    if (categories != null) {
                        spnToyCategory.setAdapter(new ArrayAdapter<Category>(this,
                                android.R.layout.simple_spinner_item,
                                ListUtil.addTopElement(categories, "Select category")));
                    }
                });
    }

    @Override
    public void setValidation() {
        Validator.add(new Rule(etToyName,
                RuleOperation.REQUIRED, "Toy name is required"));

        Validator.add(new NameRule(etToyPrice,
                RuleOperation.TEXT, "Wrong toy name"));

        Validator.add(new Rule(etToyDescription,
                RuleOperation.REQUIRED, "Toy description is required"));

        Validator.add(new TextRule(etToyDescription,
                RuleOperation.TEXT, "Wrong description",
                2, 1000, true, true));

        Validator.add(new Rule(spnToyCategory,
                RuleOperation.REQUIRED, "Category is required"));

        Validator.add(new Rule(etToyPrice,
                RuleOperation.REQUIRED, "Toy price is required"));

        Validator.add(new NumberRule(etToyPrice, RuleOperation.NUMBER,
                "Wrong price", 0.0, 10000.00));

        Validator.add(new Rule(etToyQuantity,
                RuleOperation.REQUIRED, "Toy quantity is required"));

        Validator.add(new NumberRule(etToyQuantity,
                RuleOperation.NUMBER, "Wrong quantity", 1, 10000));

        Validator.add(new Rule(etDesignDate,
                RuleOperation.REQUIRED, "Design date is required"));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Validator.add(new DateRule(etDesignDate, RuleOperation.DATE,
                    "Wrong design date",
                    LocalDate.now().minusYears(100), LocalDate.now()));
        }
        Validator.add(new ImageRule(ivToy, () -> toyBitmap, "Please select an image", ivError));
    }

    // ביצוע הבדיקה עצמה
    @Override
    public boolean validate() {
        return Validator.validate();
    }
}