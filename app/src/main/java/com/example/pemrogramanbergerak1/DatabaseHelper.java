package com.example.pemrogramanbergerak1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "keuangan.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "transaksi";
    private static final String COL_ID = "id";
    private static final String COL_TANGGAL = "tanggal";
    private static final String COL_KETERANGAN = "keterangan";
    private static final String COL_JUMLAH = "jumlah";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE transaksi (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "tanggal TEXT, " +
                "keterangan TEXT, " +
                "jumlah INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

//    public boolean tambahData(String tanggal, String keterangan, int jumlah) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put(COL_TANGGAL, tanggal);
//        values.put(COL_KETERANGAN, keterangan);
//        values.put(COL_JUMLAH, jumlah);
//
//        long result = db.insert(TABLE_NAME, null, values);
//        return result != -1; // Jika -1 berarti gagal
//    }

    public boolean tambahData(String tanggal, String keterangan, int jumlah) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tanggal", tanggal);
        values.put("keterangan", keterangan);
        values.put("jumlah", jumlah);

        long result = db.insert("transaksi", null, values);
        db.close();

        if (result == -1) {
            Log.e("DB_ERROR", "Gagal menyimpan data!");
        } else {
            Log.d("DB_SUCCESS", "Data tersimpan: " + tanggal + " | " + keterangan + " | " + jumlah);
        }

        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM transaksi", null);

        Log.d("DB_QUERY", "Jumlah data yang ditemukan: " + cursor.getCount());

        return cursor;
    }

}
