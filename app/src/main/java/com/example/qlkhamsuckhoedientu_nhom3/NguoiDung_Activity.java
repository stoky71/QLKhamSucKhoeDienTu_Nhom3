package com.example.qlkhamsuckhoedientu_nhom3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NguoiDung_Activity extends AppCompatActivity {
    private TaiKhoan taiKhoan;

    private FirebaseAuth auth;
    private DatabaseReference ref;

    TextView tvTenNguoiDung;
    Button btnDangXuat;
    Button btnChinhSua;
    Button btnLichSuKham;
    Button btnDoiMatKhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoidung);

        tvTenNguoiDung = findViewById(R.id.tvTenNguoiDung);
        btnDangXuat = findViewById(R.id.btnDangXuat);
        btnChinhSua = findViewById(R.id.btnChinhSuaTT);
        btnLichSuKham = findViewById(R.id.btnLichSuKham);
        btnDoiMatKhau = findViewById(R.id.btnDoiMatKhau);

        //nhận data tên người dùng
        loadUserName();

        lichSuKham();
        chinhSuaThongTin();
        doiMK();

        auth = FirebaseAuth.getInstance();
        dangXuat();
    }

    public void loadUserName() {
        ref = FirebaseDatabase.getInstance().getReference("Bệnh nhân");
        ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Thông tin cá nhân")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ThongTinCaNhan thongTinCaNhan = snapshot.getValue(ThongTinCaNhan.class);
                        tvTenNguoiDung.setText(thongTinCaNhan.getHoTen());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }

    public void lichSuKham(){
        btnLichSuKham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NguoiDung_Activity.this, LichSuKham_TuVan_Activity.class);
                startActivity(intent);
            }
        });
    }

    public void chinhSuaThongTin(){
        btnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(NguoiDung_Activity.this, SuaThongTinCaNhan_Activity.class);
                startActivity(intent);
            }
        });
    }

    public void doiMK() {
        btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NguoiDung_Activity.this, Quen_DoiMK_Activity.class));
            }
        });
    }

    public void dangXuat(){
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                NguoiDung_Activity.this.startActivity(new Intent(NguoiDung_Activity.this, DangNhap_Activity.class));
                Toast.makeText(NguoiDung_Activity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

}