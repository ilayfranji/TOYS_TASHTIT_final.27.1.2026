package com.ilay_f.tashtit.ACTIVITIES;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.View;

import androidx.recyclerview.widget.ItemTouchHelper;

import com.ilay_f.helper.AlertDialogHelper;
import com.ilay_f.helper.BitMapHelper;
import com.ilay_f.helper.StringUtil;
import com.ilay_f.helper.inputValidators.NameRule;
import com.ilay_f.helper.inputValidators.Rule;
import com.ilay_f.helper.inputValidators.RuleOperation;
import com.ilay_f.helper.inputValidators.Validator;
import com.ilay_f.model.Categories;
import com.ilay_f.model.Category;
import com.ilay_f.tashtit.ACTIVITIES.BASE.BaseListActivity;
import com.ilay_f.tashtit.ADPTERS.BASE.GenericAdapter;
import com.ilay_f.tashtit.ADPTERS.BASE.SwipeCallback;
import com.ilay_f.tashtit.ADPTERS.BASE.SwipeConfig;
import com.ilay_f.tashtit.R;
import com.ilay_f.viewmodel.CategoriesViewModel;

public class CategoriesActivity extends BaseListActivity<Category, Categories, CategoriesViewModel> {

    // ==================== 1. CONFIGURATION ====================
    @SuppressLint("ResourceType")
    @Override
    protected ActivityConfig getConfig() {
        /*
        return new ActivityConfig.Builder()
                // כותרת המסך
                .headerTitle("Bla Bla")

                // צבע הכותרת
                .headerTitleColor(Color.RED)

                // צבע רקע למסך
                .backgroundColor(Color.CYAN))

                // או אם רוצים תמונת רקע במקום צבע:
                // .backgroundDrawable(R.drawable.my_background)

                // האם להציג אפשרות להוסיף תמונה?
                .showPictureInput(true)

                // תמונת ברירת מחדל לפריטים (אם יש)
                .defaultItemPicture(R.drawable.infrastructure)

                // הלייאאוט של כל פריט ברשימה (חובה!)
                .itemLayoutId(R.layout.single_base_layout)

                // אייקון מותאם אישית לכפתור OK
                //.okButtonIcon(R.drawable.ok)

                // אייקון מותאם אישית לכפתור Cancel
                //.cancelButtonIcon(R.drawable.not_ok)

                // האם לבדוק תקינות קלט
                //.validateInput(true)

                .build();
        */
        // לכולם יש ערכי ברירת מחדל
        // מגדירים כאן רק את אלה שצריך
        ActivityConfig config = new ActivityConfig.Builder()
                .headerTitle("CATEGORIES")
                .headerTitleColor(Color.RED)
                .backgroundColor(Color.WHITE)
                .showPictureInput(true)
                .defaultItemPicture(R.drawable.infrastructure)
                .itemLayoutId(R.layout.single_base_layout)
                .build();

        return config;
    }
    // ==================== 2. VIEW MODEL ====================

    /**
     * איזה ViewModel להשתמש עבור המסך הזה
     */
    @Override
    protected Class<CategoriesViewModel> getViewModelClass() {
        return CategoriesViewModel.class;
    }

    // ==================== 3. OBJECT CREATION ====================

    /**
     * איך ליצור אובייקט חדש מהשם שהמשתמש הקליד
     */
    @Override
    protected Category createNewItem(String name) {
        Category category = new Category();
        category.setName(name);
        // אפשר להוסיף עוד הגדרות ברירת מחדל כאן
        return category;
    }
    // ==================== 4. VALIDATION ====================

    /**
     * חוקי ולידציה לשדה השם
     */
    @Override
    protected void setupValidation() {
        // חוק 1: השם לא יכול להיות ריק
        Validator.add(new Rule(etName, RuleOperation.REQUIRED, "Please enter category name"));
        // חוק 2: אות ראשונה חייבת להיות גדולה
        Validator.add(new NameRule(etName, RuleOperation.NAME, "Category name is wrong"));
    }

    // ==================== 5. ADAPTER HOOKS ====================
    /**
     * מה קורה כשלוחצים על פריט או לוחצים לחיצה ארוכה או גוררים
     */
    @Override
    protected void setupAdapterHooks(GenericAdapter<Category> adapter) {

        // לחיצה בודדת לשינוי פריט
        adapter.setOnItemClickListener(new GenericAdapter.OnItemClickListener<Category>() {
            @Override
            public void onItemClick(Category item, int position) {
                oldItem = item;
                clEdit.setVisibility(View.VISIBLE);
                etName.setText(item.getCategoryName());
                if (config.showPictureInput) {
                    if (!StringUtil.isNullOrEmpty(item.getCategoryPicture()))
                        ivName.setImageBitmap(BitMapHelper.decodeBase64(item.getCategoryPicture()));
                    else
                        ivName.setImageResource(config.defaultItemPicture);
                }
            }
            });

        // גרירה שמאלה למחיקה
        SwipeCallback<Category> swipeCallback = new SwipeCallback<>(adapter, this, new SwipeConfig(), ItemTouchHelper.LEFT);
        adapter.setOnItemSwipeListener(new GenericAdapter.OnItemSwipeListener<Category>() {
            @Override
            public void onItemSwipeRight(Category item, int position) {
                // This won't be called in this case,
                //    but keep it for future use
                adapter.notifyItemChanged(position);
            }
            @Override
            public void onItemSwipeLeft(Category item, int position) {

                AlertDialogHelper.showDelete(CategoriesActivity.this,
                        "Delete " + item.getCategoryName() + " ?",
                        () ->{
                            showProgressDialog(config.headerTitle,
                                    "Deleting " + item.getCategoryName() + "...");

                            viewModel.delete(item);
                            adapter.getItems().remove(position);
                            adapter.notifyItemRemoved(position);
                        },
                        () -> {adapter.notifyItemChanged(position);});
            }
        });

        ItemTouchHelper itemTouchHelper =
                new ItemTouchHelper(swipeCallback);

        itemTouchHelper.attachToRecyclerView(rvList);
    }


    @Override
    public void setValidation() {
        // אין לממש
    }

    @Override
    public  boolean validate() {
        return false; // לא לשנות
    }

    @Override
    protected void initializeActivity() {
        // אין לממש
    }
}





