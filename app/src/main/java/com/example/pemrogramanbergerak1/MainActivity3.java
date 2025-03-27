package com.example.pemrogramanbergerak1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity3 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        // Tombol untuk membuka Pencatatan Keuangan
        Button btnKeuangan = findViewById(R.id.btn_keuangan);
        btnKeuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity3.this, PencatatanKeuanganActivity.class));
            }
        });

        // Tombol untuk membuka Memo
        Button btnMemo = findViewById(R.id.btn_memo);
        btnMemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity3.this, MemoActivity.class));
            }
        });

        // Tombol untuk membuka Agenda
        Button btnAgenda = findViewById(R.id.btn_agenda);
        btnAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity3.this, AgendaActivity.class));
            }
        });

        // Bottom Navigation Bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.navigation_home) {
                    return true;
                
                } else if (item.getItemId() == R.id.navigation_profile) {
                    startActivity(new Intent(MainActivity3.this, ProfileActivity.class));
                    return true;
                } else if (item.getItemId() == R.id.navigation_settings) {
                    startActivity(new Intent(MainActivity3.this, SettingsActivity.class));
                    return true;
                }
                return false;
            }
        });
    }
}
