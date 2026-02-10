package com.ilay_f.tashtit.ACTIVITIES;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ilay_f.helper.AlertDialogHelper;
import com.ilay_f.helper.BitMapHelper;
import com.ilay_f.helper.DateUtil;
import com.ilay_f.helper.StringUtil;
import com.ilay_f.helper.inputValidators.EntryValidation;
import com.ilay_f.helper.inputValidators.Validator;
import com.ilay_f.model.Toy;
import com.ilay_f.tashtit.ACTIVITIES.BASE.BaseActivity;
import com.ilay_f.tashtit.ADPTERS.BASE.GenericAdapter;
import com.ilay_f.tashtit.ADPTERS.BASE.SwipeCallback;
import com.ilay_f.tashtit.ADPTERS.BASE.SwipeConfig;
import com.ilay_f.tashtit.ADPTERS.ToysAdapter;
import com.ilay_f.tashtit.R;
import com.ilay_f.viewmodel.ToysViewModel;

import java.text.DecimalFormat;

public class ToysActivity extends BaseActivity {
    private RecyclerView rvToys;
    private FloatingActionButton fabAdd;
    private ToysAdapter toysAdapter;
    private ToysViewModel toysViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_toys);

        initializeActivity();
    }

    protected  void initializeActivity(){
        initializeViews();
        setViewModel();
        setRecyclerView();
    }

    protected  void initializeViews(){
        rvToys = findViewById(R.id.toysRecycleView);
        fabAdd = findViewById(R.id.fabAdd);

        setListeners();
    }

    protected  void setListeners(){
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ToysActivity.this, ToyActivity.class);
                startActivity(intent);
            }
        });
    }

    protected  void setViewModel(){
        toysViewModel = new ViewModelProvider(this).get(ToysViewModel.class);
        toysViewModel.getAll();

    }


    private void setRecyclerView() {

        toysAdapter = new ToysAdapter(null, R.layout.single_toy_layout,

                // Initialize ViewHolder - this runs only once per ViewHolder
                holder -> {
                    // Set views to ViewHolder
                    // - this runs until screen is full or list ends - what is shorter

                    holder.putView("ivToy",      holder.itemView.findViewById(R.id.ivToy));
                    holder.putView("tvName",     holder.itemView.findViewById(R.id.tvName));
                    holder.putView("tvPrice",    holder.itemView.findViewById(R.id.tvPrice));
                    holder.putView("tvQuantity", holder.itemView.findViewById(R.id.tvQuantity));
                    holder.putView("tvDate",     holder.itemView.findViewById(R.id.tvDate));
                },
                ((holder, item, position) -> {
                    // Bind data to ViewHolder - this runs for every item

                    if (StringUtil.isNullOrEmpty(item.getImage())) {
                        ((ImageView) holder.getView("ivToy")).setImageResource(R.drawable.infrastructure);
                    }
                    else {
                        ((ImageView) holder.getView("ivToy")).setImageBitmap(BitMapHelper.decodeBase64(item.getImage()));
                    }

                    ((TextView) holder.getView("tvName")).setText(item.getName());

                    DecimalFormat df = new DecimalFormat("0.00");
                    ((TextView) holder.getView("tvPrice")).setText(df.format(item.getPrice()));

                    ((TextView) holder.getView("tvQuantity")).setText(String.valueOf(item.getQuantity()));

                    ((TextView) holder.getView("tvDate")).setText(DateUtil.longToString(item.getDesignDate(), DateUtil.FORMAT_DD_MM_YYYY));
                })
        );

        rvToys.setAdapter(toysAdapter);
        rvToys.setLayoutManager(new LinearLayoutManager(this));
        toysAdapter.setOnItemClickListener(new GenericAdapter.OnItemClickListener<Toy>() {
            @Override
            public void onItemClick(Toy item, int position) {
                Intent intent = new Intent(ToysActivity.this, ToyActivity.class);
                intent.putExtra("TOY", (Toy)item);
                startActivity(intent);
            }
        });
        // הגדרת מאזין ללחיצה על פריט - מעבר למסך פרטי צעצוע
        toysAdapter.setOnItemClickListener(new GenericAdapter.OnItemClickListener<Toy>() {
            @Override
            public void onItemClick(Toy item, int position) {
                Intent intent = new Intent(ToysActivity.this, ToyActivity.class);
                intent.putExtra("TOY", (Toy)item);
                startActivity(intent);
            }
        });

        // הגדרת מאזין להחלקה (Swipe) על פריט - מחיקה או ביטול
        toysAdapter.setOnItemSwipeListener(new GenericAdapter.OnItemSwipeListener<Toy>() {
            @Override
            public void onItemSwipeRight(Toy item, int position) {
                // החלקה ימינה מחזירה את הפריט למצבו הרגיל
                toysAdapter.notifyItemChanged(position);
            }

            @Override
            public void onItemSwipeLeft(Toy item, int position) {
                // החלקה שמאלה מבקשת אישור מחיקה
                AlertDialogHelper.showDelete(ToysActivity.this,
                        "Delete " + item.getName() + " ?",
                        () -> {
                            // במידה ואישר מחיקה
                            showProgressDialog("Delete", "Deleting " + item.getName() + ", Please wait...");
                            toysViewModel.delete(item);
                            toysAdapter.getItems().remove(position);
                            toysAdapter.notifyItemRemoved(position);
                        },
                        () -> toysAdapter.notifyItemChanged(position) // במידה וביטל - מחזיר את הפריט
                );
            }
        });
        // הגדרת ה-Helper שאחראי על זיהוי מחוות המגע (החלקה שמאלה)
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new
                SwipeCallback<Toy>(toysAdapter,
                ToysActivity.this,
                new SwipeConfig(),
                ItemTouchHelper.LEFT));
        // הצמדת ה-Helper ל-RecyclerView שלנו
        itemTouchHelper.attachToRecyclerView(rvToys);
    }
}