package com.enlife.app.screens.main.fragments.login;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.enlife.app.R;
import com.enlife.app.common.Constants;
import com.enlife.app.screens.forgot_password.ForgotPasswordActivity;
import com.enlife.app.screens.signup.SignUpActivity;
import com.enlife.app.screens.walkthrough.WalkThroughActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static void openScreen(AppCompatActivity activity, @Nullable Bundle bundle) {
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.putExtra(Constants.ARG_BUNDLE, bundle);
        activity.startActivity(intent);
    }

    private TextView edtEmail;
    private TextView edtPassword;
    private TextView txtForgotPassword;
    private TextView btnSignIn;
    private TextView txtSignUpLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        setSignUpLinkText();
        setViews();
    }

    private void setViews() {
        txtSignUpLink.setOnClickListener(this);
        txtForgotPassword.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
    }

    private void initViews() {
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        txtForgotPassword = findViewById(R.id.txt_forgot_password);
        btnSignIn = findViewById(R.id.btn_sign_in);
        txtSignUpLink = findViewById(R.id.txt_signup_link);
    }

    private void setSignUpLinkText() {
        String strSpannableFirst = getString(R.string.dont_hav_acc);
        String strSpannableSecond = getString(R.string.sign_up);
        SpannableStringBuilder spannableText = new SpannableStringBuilder(
                strSpannableFirst + " " + strSpannableSecond
        );
        spannableText.setSpan(
                new ForegroundColorSpan(ContextCompat.getColor(this, R.color.greyText)),
                0,
                strSpannableFirst.length(),
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        );
        txtSignUpLink.setText(spannableText);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_signup_link:
                SignUpActivity.openScreen(this, null);
                break;
            case R.id.txt_forgot_password:
                ForgotPasswordActivity.openScreen(this, null);
                break;
            case R.id.btn_sign_in:
                WalkThroughActivity.openScreen(this, null);
                finish();
                break;
        }
    }
}