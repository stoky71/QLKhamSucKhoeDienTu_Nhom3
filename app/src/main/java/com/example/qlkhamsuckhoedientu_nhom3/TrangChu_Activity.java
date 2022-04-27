package com.example.qlkhamsuckhoedientu_nhom3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TrangChu_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);

        //dang nhap
        findViewById(R.id.btnDangNhapTC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrangChu_Activity.this.startActivity(new Intent(TrangChu_Activity.this, DangNhap_Activity.class));
            }
        });

        //dang ky
        findViewById(R.id.btnDangKyTC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrangChu_Activity.this.startActivity(new Intent(TrangChu_Activity.this, DangKy_Activity.class));
            }
        });
    }
}