package com.example.qlkhamsuckhoedientu_nhom3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class DangKy_Activity extends AppCompatActivity {

    TextInputEditText edtHoTenDK, edtSDTDK, edtEmailDK, edtMKDK;
    Button btnDangKy;
    TextView tvDNNgay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        edtHoTenDK = findViewById(R.id.edtHoTenDK);
        edtSDTDK = findViewById(R.id.edtSDTDK);
        edtEmailDK = findViewById(R.id.edtEmailDK);
        edtMKDK = findViewById(R.id.edtMKDK);
        btnDangKy = findViewById(R.id.btnDangKy);
        tvDNNgay = findViewById(R.id.tvDNNgay);

        dangNhapNgay();

    }

    public void dangNhapNgay(){
        tvDNNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DangKy_Activity.this.startActivity(new Intent(DangKy_Activity.this, DangNhap_Activity.class));
            }
        });
    }
}