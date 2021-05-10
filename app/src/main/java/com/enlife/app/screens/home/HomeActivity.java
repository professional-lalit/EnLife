package com.enlife.app.screens.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.enlife.app.R;
import com.enlife.app.common.Constants;
import com.enlife.app.screens.signup.SignUpActivity;

public class HomeActivity extends AppCompatActivity {

    public static void openScreen(AppCompatActivity activity, @Nullable Bundle bundle) {
        Intent intent = new Intent(activity, HomeActivity.class);
        intent.putExtra(Constants.ARG_BUNDLE, bundle);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}