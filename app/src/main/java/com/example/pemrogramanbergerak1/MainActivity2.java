package com.example.pemrogramanbergerak1;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity2 extends AppCompatActivity {
    TextInputEditText username, pw, useremail, Nim;
    Button BtnSignUp;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        auth = FirebaseAuth.getInstance();
        username = findViewById(R.id.pengguna);
        pw = findViewById(R.id.password);
        useremail = findViewById(R.id.email);
        Nim = findViewById(R.id.username);
        BtnSignUp = findViewById(R.id.btnSign);

        BtnSignUp.setOnClickListener(view -> {
            String userName = username.getText().toString().trim();
            String password = pw.getText().toString().trim();
            String email = useremail.getText().toString().trim();
            String NIM = Nim.getText().toString().trim();

            if (TextUtils.isEmpty(userName)) {
                username.setError("Masukkan Username!");
                return;
            }
            if (TextUtils.isEmpty(password)) {
                pw.setError("Masukkan Password!");
                return;
            }
            if (password.length() < 6) {
                pw.setError("Password minimal 6 karakter!");
                return;
            }
            if (TextUtils.isEmpty(email)) {
                useremail.setError("Masukkan Email!");
                return;
            }
            if (TextUtils.isEmpty(NIM)) {
                Nim.setError("Masukkan NIM!");
                return;
            }

            registerUser(userName, email, password, NIM);
        });
    }

    private void registerUser(String username, String email, String password, String NIM) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                FirebaseUser fUser = auth.getCurrentUser();
                if (fUser != null) {
                    String uid = fUser.getUid();

                    DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("Users");
                    User user = new User(uid, username, email, password, NIM);

                    usersRef.child(uid).setValue(user).addOnCompleteListener(task1 -> {
                        if (task1.isSuccessful()) {
                            Toast.makeText(MainActivity2.this, "Akun berhasil dibuat", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(MainActivity2.this, "Gagal menyimpan data ke database.", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "Database Error: ", task1.getException());
                        }
                    });
                }
            } else {
                Toast.makeText(MainActivity2.this, "Registrasi gagal: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                Log.e(TAG, "Auth Error: ", task.getException());
            }
        });
    }
}