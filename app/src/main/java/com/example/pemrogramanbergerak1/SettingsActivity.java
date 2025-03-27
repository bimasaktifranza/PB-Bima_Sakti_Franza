package com.example.pemrogramanbergerak1;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Tombol kembali ke Home
        Button btnKembaliHome = findViewById(R.id.btn_kembali_home);
        btnKembaliHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(SettingsActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }
}
