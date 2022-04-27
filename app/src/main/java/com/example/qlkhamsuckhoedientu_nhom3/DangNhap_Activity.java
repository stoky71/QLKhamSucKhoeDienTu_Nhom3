package com.example.qlkhamsuckhoedientu_nhom3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class DangNhap_Activity extends AppCompatActivity {

    TextInputEditText edtEmailDN, edtMKDN;
    TextView tvQuenMK, tvDKNgay;
    Button btnDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        edtEmailDN = findViewById(R.id.edtEmailDN);
        edtMKDN = findViewById(R.id.edtMKDN);
        tvQuenMK = findViewById(R.id.tvQuenMK);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        tvDKNgay = findViewById(R.id.tvDKNgay);

        quenMK();
        dangKyNgay();
    }

    public void quenMK(){
        tvQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DangNhap_Activity.this.startActivity(new Intent(DangNhap_Activity.this, QuenMK_Activity.class));
            }
        });
    }

    public void dangKyNgay(){
        tvDKNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DangNhap_Activity.this.startActivity(new Intent(DangNhap_Activity.this, DangKy_Activity.class));
            }
        });
    }
}