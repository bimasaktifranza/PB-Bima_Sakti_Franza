package com.example.pemrogramanbergerak1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PencatatanKeuanganActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    EditText etTanggal, etKeterangan, etJumlah;
    Button btnSimpan, btnKembaliHome;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pencatatan_keuangan);

        databaseHelper = new DatabaseHelper(this);

        etTanggal = findViewById(R.id.et_tanggal);
        etKeterangan = findViewById(R.id.et_keterangan);
        etJumlah = findViewById(R.id.et_jumlah);
        btnSimpan = findViewById(R.id.btn_simpan);
        btnKembaliHome = findViewById(R.id.btn_kembali_home);
        listView = findViewById(R.id.list_view_keuangan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tanggal = etTanggal.getText().toString().trim();
                String keterangan = etKeterangan.getText().toString().trim();
                String jumlahStr = etJumlah.getText().toString().trim();

                if (tanggal.isEmpty() || keterangan.isEmpty() || jumlahStr.isEmpty()) {
                    Toast.makeText(PencatatanKeuanganActivity.this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int jumlah = Integer.parseInt(jumlahStr);

                    boolean berhasil = databaseHelper.tambahData(tanggal, keterangan, jumlah);
                    if (berhasil) {
                        Toast.makeText(PencatatanKeuanganActivity.this, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show();
                        etTanggal.setText("");
                        etKeterangan.setText("");
                        etJumlah.setText("");
                    } else {
                        Toast.makeText(PencatatanKeuanganActivity.this, "Gagal menyimpan data", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(PencatatanKeuanganActivity.this, "Masukkan angka yang valid!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnKembaliHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PencatatanKeuanganActivity.this, MainActivity3.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
