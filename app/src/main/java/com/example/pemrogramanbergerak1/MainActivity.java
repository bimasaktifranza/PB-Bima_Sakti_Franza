package com.example.pemrogramanbergerak1;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    TextInputEditText username, password;
    CheckBox checkBoxes;
    Button btnLogin;
    TextView forgotPass, signUp;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return WindowInsetsCompat.CONSUMED;
            });
            mAuth = FirebaseAuth.getInstance();
            username = findViewById(R.id.username);
            password = findViewById(R.id.password);
            checkBoxes = findViewById(R.id.checkbox);
            btnLogin = findViewById(R.id.btnLogin);
            forgotPass = findViewById(R.id.LupaSandi);
            signUp= findViewById(R.id.DaftarSekarang);


            btnLogin.setOnClickListener(view -> {
                String email = String.valueOf(username.getText()).trim();
                String passwordUser = String.valueOf(password.getText()).trim();

                if (email.isEmpty()) {
                    username.setError("Email tidak boleh kosong!");
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    username.setError("Format email tidak valid!");
                    return;
                }
                if (passwordUser.isEmpty()) {
                    password.setError("Password tidak boleh kosong!");
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, passwordUser)
                        .addOnSuccessListener(authResult -> {
                            Toast.makeText(MainActivity.this, "Login Berhasil", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(MainActivity.this, MainActivity3.class));
                            finish();
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(MainActivity.this, "Login Gagal: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        });
            });

            signUp.setOnClickListener(view -> {
                Intent intent = new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
                finish();
            });

        }
    }
}

