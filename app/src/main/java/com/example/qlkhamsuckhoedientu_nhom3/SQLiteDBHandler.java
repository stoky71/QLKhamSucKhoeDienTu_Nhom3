package com.example.qlkhamsuckhoedientu_nhom3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class SQLiteDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "N3-QLKhamSucKhoeDienTu";

    private static final String TABLE_CONTACTS_BENHNHAN = "BenhNhan";
//    private static final String KEY_ID = "id";
//    private static final String KEY_EMAIL = "email";
    private static final String KEY_HOTEN = "hoten";
    private static final String KEY_NGAYKHAM = "ngaykham";
    private static final String KEY_BHYT = "bhyt";
    private static final String KEY_BENHVIEN = "benhvien";
    private static final String KEY_KHOA = "khoa";
    private static final String KEY_CHUANDOAN = "chuandoan";

    private static final String create_table_lsk = "CREATE TABLE " + TABLE_CONTACTS_BENHNHAN + "("
//            + KEY_ID + " INTEGER PRIMARY KEY, "
//            + KEY_EMAIL + " TEXT, "
            + KEY_HOTEN + " TEXT, "
            + KEY_NGAYKHAM + " TEXT, " + KEY_BHYT + " TEXT, " + KEY_BENHVIEN + " TEXT, "
            + KEY_KHOA + " TEXT, " + KEY_CHUANDOAN + " TEXT"+ ")";

    public SQLiteDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table_lsk);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS_BENHNHAN);

        // Create tables again
        onCreate(db);
    }

    public void addPatientsInfo(ThongTinChung thongTinChung) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
//        values.put(KEY_EMAIL, thongTinChung.getTaiKhoan().getEmail());

        values.put(KEY_HOTEN, thongTinChung.getThongTinCaNhan().getHoTen());

        values.put(KEY_NGAYKHAM, thongTinChung.getThongTinLSKham_tuVan().getNgayKham());
        values.put(KEY_BHYT, thongTinChung.getThongTinLSKham_tuVan().getBHYT());
        values.put(KEY_BENHVIEN, thongTinChung.getThongTinLSKham_tuVan().getBenhVien());
        values.put(KEY_KHOA, thongTinChung.getThongTinLSKham_tuVan().getKhoa());
        values.put(KEY_CHUANDOAN, thongTinChung.getThongTinLSKham_tuVan().getChuanDoan());

        // Inserting Row
        db.insert(TABLE_CONTACTS_BENHNHAN, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    public List<ThongTinChung> getAllPatientsInfo() {
        List<ThongTinChung> thongTinChungList=new ArrayList<ThongTinChung>();
        // Select All Query
        String selectQuery = "SELECT ngaykham, bhyt, benhvien, khoa, chuandoan FROM " + TABLE_CONTACTS_BENHNHAN;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ThongTinChung thongTinChung=new ThongTinChung();
//                thongTinChung.getTaiKhoan().setEmail(cursor.getString(0));
//                thongTinChung.getThongTinCaNhan().setHoTen(cursor.getString(1));
                thongTinChung.getThongTinLSKham_tuVan().setNgayKham(cursor.getString(0));
                thongTinChung.getThongTinLSKham_tuVan().setBHYT(cursor.getString(1));
                thongTinChung.getThongTinLSKham_tuVan().setBenhVien(cursor.getString(2));
                thongTinChung.getThongTinLSKham_tuVan().setKhoa(cursor.getString(3));
                thongTinChung.getThongTinLSKham_tuVan().setChuanDoan(cursor.getString(4));

                thongTinChungList.add(thongTinChung);
            } while (cursor.moveToNext());
        }
        return thongTinChungList;
    }

    public int updatePatientsInfo(ThongTinChung thongTinChung) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BHYT, thongTinChung.getThongTinLSKham_tuVan().getBHYT());
        values.put(KEY_BENHVIEN, thongTinChung.getThongTinLSKham_tuVan().getBenhVien());
        values.put(KEY_KHOA, thongTinChung.getThongTinLSKham_tuVan().getKhoa());
        values.put(KEY_CHUANDOAN, thongTinChung.getThongTinLSKham_tuVan().getChuanDoan());

        // updating row
        return db.update(TABLE_CONTACTS_BENHNHAN, values, KEY_NGAYKHAM + " = ?",
                new String[] { String.valueOf(thongTinChung.getThongTinLSKham_tuVan().getNgayKham()) });
    }

    public void deletePatientsInfo(ThongTinChung thongTinChung) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS_BENHNHAN, KEY_NGAYKHAM + " = ?",
                new String[] { String.valueOf(thongTinChung.getThongTinLSKham_tuVan().getNgayKham()) });
        db.close();
    }

}
