package com.ilay_f.repository.BASE.DB;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

public class FirebaseInstance {
    private static volatile FirebaseInstance _instance = null;
    public static FirebaseApp app;

    private FirebaseInstance(Context context) {
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setProjectId("tashtitfirebase")
                .setApplicationId("1:786698151056:android:1b91b3147fbece55102415")
                .setApiKey("AIzaSyCuGYxFnw2mTnDVbErlz_l1SbiXI5PzLOI")
                .setStorageBucket("tashtitfirebase.firebasestorage.app")
                .build();

        app = FirebaseApp.initializeApp(context, options);
    }

    public static FirebaseInstance instance(Context context) {
        if (_instance == null) {  // 1st check
            synchronized (FirebaseInstance.class) {
                if (_instance == null){ // 2nd check
                    _instance = new FirebaseInstance(context);
                }
            }
        }

        return _instance;
    }
}
