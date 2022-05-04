package com.example.qlkhamsuckhoedientu_nhom3;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LichSuKham_TuVan_Activity extends AppCompatActivity {
    private TaiKhoan taiKhoan;
    private ThongTinLSKham_TuVan thongTinLSKham_tuVan;
    private LSKham_TuVan_Adapter lsKhamTuVanAdapter;
    private ArrayList<ThongTinLSKham_TuVan> arrayList;

    private DatabaseReference ref;

    private Date ngayHT, gioHT;
    private SimpleDateFormat sdfNgayHT=new SimpleDateFormat("dd-MM-yyyy"), sdfGioHT =new SimpleDateFormat("HH:mm:ss a");
    private LocalDate now;
    int ngay, thang, nam, gio, phut, giay;

    ImageView imgBack_LSKham;
    EditText edtBHYT, edtBenhVien, edtKhoa, edtChuanDoan;
    Button btnLuu, btnXoa, btnHuy;
    ListView lvLSKham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichsukham_tuvan);

        imgBack_LSKham = findViewById(R.id.imgBack_LSKham);
        edtBHYT = findViewById(R.id.edtBHYT);
        edtBenhVien = findViewById(R.id.edtBenhVien);
        edtKhoa = findViewById(R.id.edtKhoa);
        edtChuanDoan = findViewById(R.id.edtChuanDoan);
        btnLuu = findViewById(R.id.btnLuu);
        btnXoa = findViewById(R.id.btnXoa);
        btnHuy = findViewById(R.id.btnHuy);
        lvLSKham = findViewById(R.id.lvLSKham);

//        arrayList = new ArrayList<ThongTinLichSuKham>();
//        ref = FirebaseDatabase.getInstance().getReference("Bệnh nhân");
//        ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Lịch sử khám - Tư vấn");
//
//        lsKhamTuVanAdapter = new LSKham_TuVan_Adapter(getApplicationContext(), R.layout.item_lichsukham, arrayList, ref);
//        lvLSKham.setAdapter(lsKhamTuVanAdapter);


        btnLuu.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                addDataToRealtimeDB();
                showLV();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetAll();
            }
        });

        imgBack_LSKham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LichSuKham_TuVan_Activity.this.startActivity(new Intent(LichSuKham_TuVan_Activity.this, NguoDung_Activity.class));
            }
        });
    }

    public void showLV() {

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addDataToRealtimeDB() {
        String txtBHYT = edtBHYT.getText().toString().trim(),
                txtBV = edtBenhVien.getText().toString().trim(),
                txtKhoa = edtKhoa.getText().toString().trim(),
                txtChuanDoan = edtChuanDoan.getText().toString().trim();

        //ngay,thang,nam hien tai
        now = LocalDate.now();
        ngay = now.getDayOfMonth();
        thang = now.getMonthValue();
        nam = now.getYear();
        ngayHT = new Date(nam - 1900, thang - 1, ngay);
        String ngayHienTai = sdfNgayHT.format(ngayHT);

        //gio,phut,giay hien tai
        gioHT = new Date();
        gio = gioHT.getHours();
        phut = gioHT.getMinutes();
        giay = gioHT.getSeconds();
        String gioHienTai = sdfGioHT.format(gioHT);

        if (txtBHYT.equals("") || txtBV.equals("") || txtKhoa.equals("") || txtChuanDoan.equals(""))
            Toast.makeText(this, "Vui lòng nhập vào chỗ trống", Toast.LENGTH_SHORT).show();
        else {
            thongTinLSKham_tuVan = new ThongTinLSKham_TuVan(txtBHYT, txtChuanDoan, txtBV, txtKhoa);

            ref = FirebaseDatabase.getInstance().getReference("Bệnh nhân");
            ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("Lịch sử khám - Tư vấn")
                    .child("Ngày khám: " + ngayHienTai + ", " + gioHienTai).setValue(thongTinLSKham_tuVan);

            Toast.makeText(this, "Lưu thành công", Toast.LENGTH_SHORT).show();
            resetAll();
        }
    }

    public void resetAll(){
        edtBHYT.requestFocus();
        edtBHYT.setText("");
        edtBenhVien.setText("");
        edtKhoa.setText("");
        edtChuanDoan.setText("");
    }

//        public List<ThongTinLichSuKham> allThongTinLSKham(){
//        List<ThongTinLichSuKham> list = new ArrayList<ThongTinLichSuKham>();
//
//
//        return  list;
//    }
}