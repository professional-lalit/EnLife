package com.enlife.app.screens.signup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.enlife.app.R;
import com.enlife.app.common.Constants;
import com.enlife.app.screens.login.LoginActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    public static void openScreen(AppCompatActivity activity, @Nullable Bundle bundle) {
        Intent intent = new Intent(activity, SignUpActivity.class);
        intent.putExtra(Constants.ARG_BUNDLE, bundle);
        activity.startActivity(intent);
    }

    private Toolbar toolbar;
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtConfirmPassword;
    private TextView txtBirthDate;
    private TextView txtLoginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();
        initActionbar();
        setSignInLinkText();
        setViews();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        edtName = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        edtConfirmPassword = findViewById(R.id.edt_confirm_password);
        txtBirthDate = findViewById(R.id.txt_birth_date);
        txtLoginLink = findViewById(R.id.txt_login_link);
    }

    private void initActionbar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setViews() {
        txtLoginLink.setOnClickListener(this);
        txtBirthDate.setOnClickListener(this);
    }

    private void setSignInLinkText() {
        String strSpannableFirst = getString(R.string.already_hav_acc);
        String strSpannableSecond = getString(R.string.sign_in);
        SpannableStringBuilder spannableText = new SpannableStringBuilder(
                strSpannableFirst + " " + strSpannableSecond
        );
        spannableText.setSpan(
                new ForegroundColorSpan(ContextCompat.getColor(this, R.color.greyText)),
                0,
                strSpannableFirst.length(),
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        );
        txtLoginLink.setText(spannableText);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt_login_link:
                LoginActivity.openScreen(this, null);
                finishAffinity();
                break;
            case R.id.txt_birth_date:
                showBirthdayDatePicker();
                break;
        }
    }

    final Calendar myCalendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
        myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, monthOfYear);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        updateBirthDate();
    };

    private void updateBirthDate() {
        String myFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        txtBirthDate.setText(sdf.format(myCalendar.getTime()));
    }

    private void showBirthdayDatePicker() {
        DatePickerDialog dialog = new DatePickerDialog(
                this,
                date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
        );
        dialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        dialog.show();
    }
}