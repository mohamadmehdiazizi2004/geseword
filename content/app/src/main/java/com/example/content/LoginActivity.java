package com.example.content;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText etUser, etPass;
    private Button btnLogin;
    private ImageButton btnEye;

    private RecyclerView rvSaved;
    private TextView tvEmpty;

    private boolean isPassVisible = false;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        btnLogin = findViewById(R.id.btnLogin);
        btnEye = findViewById(R.id.btnEye);

        rvSaved = findViewById(R.id.rvSaved);
        tvEmpty = findViewById(R.id.tvEmpty);

        db = AppDatabase.getInstance(this);

        // دکمه چشم (نمایش/مخفی کردن پسورد)
        btnEye.setOnClickListener(v -> togglePasswordVisibility());

        // راه‌اندازی لیست
        rvSaved.setLayoutManager(new LinearLayoutManager(this));

        // بارگذاری اولیه لیست
        loadSavedList();

        // ذخیره در دیتابیس و رفرش لیست
        btnLogin.setOnClickListener(v -> saveCredential());
    }

    private void togglePasswordVisibility() {
        if (isPassVisible) {
            etPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        } else {
            etPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        isPassVisible = !isPassVisible;
        etPass.setSelection(etPass.getText().length());
    }

    private void saveCredential() {
        String user = etUser.getText().toString().trim();
        String pass = etPass.getText().toString().trim();

        if (user.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "همه فیلدها باید پر باشد", Toast.LENGTH_SHORT).show();
            return;
        }

        db.credentialDao().insert(new CredentialEntity(user, pass, System.currentTimeMillis()));
        Toast.makeText(this, "ذخیره شد ✅", Toast.LENGTH_SHORT).show();

        // پاک کردن فیلدها (اختیاری)
        etUser.setText("");
        etPass.setText("");
        etUser.requestFocus();

        // رفرش لیست
        loadSavedList();
    }

    private void loadSavedList() {
        List<CredentialEntity> data = db.credentialDao().getAll();

        if (data == null || data.isEmpty()) {
            tvEmpty.setVisibility(TextView.VISIBLE);
            rvSaved.setAdapter(null);
            return;
        }

        tvEmpty.setVisibility(TextView.GONE);
        rvSaved.setAdapter(new CredentialAdapter(this, data));
    }
}
