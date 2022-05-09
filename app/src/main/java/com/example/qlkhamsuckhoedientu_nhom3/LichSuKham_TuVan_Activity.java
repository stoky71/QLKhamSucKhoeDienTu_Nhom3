package com.example.qlkhamsuckhoedientu_nhom3;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class LichSuKham_TuVan_Activity extends AppCompatActivity {
    private ThongTinLSKham_TuVan thongTinLSKham_tuVan;
    private ArrayList<String> arrayList=new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;

    private DatabaseReference ref;

    private Date ngayHT, gioHT;
    private SimpleDateFormat sdfNgayHT=new SimpleDateFormat("dd-MM-yyyy"), sdfGioHT =new SimpleDateFormat("HH:mm:ss a");
    private LocalDate now;
    int ngay, thang, nam, gio, phut, giay;

    ImageView imgBack_LSKham;
    EditText edtBHYT, edtBenhVien, edtKhoa, edtChuanDoan;
    Button btnLuu, btnXoa, btnCapNhatLSK, btnHuy;

    TextView tvInfoLSK;
    ListView lvLSKham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lichsukham_tuvan);

        imgBack_LSKham = findViewById(R.id.imgBack_LSKham);
        tvInfoLSK = findViewById(R.id.tvInfoLSK);
        edtBHYT = findViewById(R.id.edtBHYT);
        edtBenhVien = findViewById(R.id.edtBenhVien);
        edtKhoa = findViewById(R.id.edtKhoa);
        edtChuanDoan = findViewById(R.id.edtChuanDoan);
        btnLuu = findViewById(R.id.btnLuu);
        btnXoa = findViewById(R.id.btnXoa);
        btnCapNhatLSK = findViewById(R.id.btnCapNhatLSK);
        btnHuy = findViewById(R.id.btnHuy);
        lvLSKham = findViewById(R.id.lvLSKham);

        showDataLV(); //show LV, delete, update

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                addDataToRealtimeDB();
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
                LichSuKham_TuVan_Activity.this.startActivity(new Intent(LichSuKham_TuVan_Activity.this, NguoiDung_Activity.class));
            }
        });
    }

    public void showDataLV() {
        ref = FirebaseDatabase.getInstance().getReference("Bệnh nhân")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Lịch sử khám - Tư vấn");

        arrayAdapter=new ArrayAdapter<String>(this, R.layout.item_lichsukham, R.id.tvInfoLSK, arrayList);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String ngayKham = dataSnapshot.getKey();
                    thongTinLSKham_tuVan = dataSnapshot.getValue(ThongTinLSKham_TuVan.class);
                    String bhyt = "" + thongTinLSKham_tuVan.getBHYT(),
                            bv = "" + thongTinLSKham_tuVan.getBenhVien(),
                            khoa = "" + thongTinLSKham_tuVan.getKhoa(),
                            chd = "" + thongTinLSKham_tuVan.getChuanDoan();

                    String infoLSK = ""+ngayKham
                            +"\nBHYT: " +bhyt +" \t\t\tBệnh viện: " +bv +"   "
                            +"\nKhoa: " +khoa +"     "
                            +"\nChuẩn đoán: " +chd +"    ";
                    arrayList.removeAll(Collections.singleton(infoLSK));
                    arrayList.add(infoLSK);

                    //java.util.Collections.reverse(arrayList); //sort descending; firebase auto ascending

                    String checkLSK = "" +infoLSK;
                    Log.d("CHECK LSK: ", checkLSK);

                    chooseInfoDeleteOrUpdate(arrayList);
                }
                lvLSKham.setAdapter(arrayAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
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

        //gio,phut,giay hien tai
        gioHT = new Date();
        gio = gioHT.getHours();
        phut = gioHT.getMinutes();
        giay = gioHT.getSeconds();

        String ngayHienTai = sdfNgayHT.format(ngayHT),
                gioHienTai = sdfGioHT.format(gioHT),
                ngayGioHT = "" + ngayHienTai + ", " + gioHienTai;

        if (txtBHYT.equals("") || txtBV.equals("") || txtKhoa.equals("") || txtChuanDoan.equals(""))
            Toast.makeText(this, "Vui lòng nhập vào chỗ trống", Toast.LENGTH_SHORT).show();
        else {
            thongTinLSKham_tuVan = new ThongTinLSKham_TuVan(txtBHYT, txtChuanDoan, txtBV, txtKhoa);

            ref = FirebaseDatabase.getInstance().getReference("Bệnh nhân");
            ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .child("Lịch sử khám - Tư vấn")
                    .child("Ngày khám: " +ngayGioHT).setValue(thongTinLSKham_tuVan);

            Toast.makeText(this, "Lưu thành công", Toast.LENGTH_SHORT).show();
            resetAll();
        }
    }

    public void chooseInfoDeleteOrUpdate(ArrayList arrayList){
        lvLSKham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                ArrayList<String> arrayList1 = new ArrayList<>();
//                String getInfo = arrayList1.get(i);
                String getInfo = adapterView.getItemAtPosition(i).toString();

                //cop đoạn chuỗi từ getInfo qua getNgayKham; bắt đầu từ 0, dừng ở ký tự 33 (chữ M trong AM hoặc PM), 34 ko lấy
                String getNgayKham = getInfo.substring(0, 34);

                ref = FirebaseDatabase.getInstance().getReference("Bệnh nhân")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .child("Lịch sử khám - Tư vấn");

//                edtBHYT.setText(getInfo.substring(41, 46)); //41 là kí tự C; K //ok
//                edtBenhVien.setText(getInfo.substring(61, 67)); //50 là kí tự B, 67 \n
//                edtKhoa.setText(getInfo.substring(74, 85)); //68 là kí tự X (Xét nghiệm), 85 \n
//                edtChuanDoan.setText(getInfo.substring(98, 111)); //86 là kí tự C, 96 :,

//                if(arrayList.contains("")==true)
//                    Toast.makeText(LichSuKham_TuVan_Activity.this, "Chưa chọn thông tin lịch sử để thực hiện các chức năng", Toast.LENGTH_SHORT).show();

                if(arrayList.contains(getInfo) == true){ //check getInfo có trong arrayList hay ko
                    //xóa
                    btnXoa.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            ref.child(getNgayKham).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(LichSuKham_TuVan_Activity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            arrayList.remove(getInfo);
                            resetAll();
                        }
                    });

                    //cập nhật
                    btnCapNhatLSK.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String txtBHYT = edtBHYT.getText().toString().trim(),
                                    txtBV = edtBenhVien.getText().toString().trim(),
                                    txtKhoa = edtKhoa.getText().toString().trim(),
                                    txtChuanDoan = edtChuanDoan.getText().toString().trim();

                            if (txtBHYT.equals("") || txtBV.equals("") || txtKhoa.equals("") || txtChuanDoan.equals(""))
                                Toast.makeText(LichSuKham_TuVan_Activity.this, "Vui lòng nhập vào chỗ trống", Toast.LENGTH_SHORT).show();
                            else {
                                thongTinLSKham_tuVan = new ThongTinLSKham_TuVan(txtBHYT, txtChuanDoan, txtBV, txtKhoa);

                                ref = FirebaseDatabase.getInstance().getReference("Bệnh nhân");
                                ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .child("Lịch sử khám - Tư vấn")
                                        .child(getNgayKham).setValue(thongTinLSKham_tuVan);

                                Toast.makeText(LichSuKham_TuVan_Activity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                                resetAll();

                                //reload activity
                                finish();
                                startActivity(getIntent());
                            }
                        }
                    });

                    String log = "" + getInfo;
                    Log.d("check get 1 info: ", log);
                    Toast.makeText(LichSuKham_TuVan_Activity.this, ""+getInfo, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void resetAll(){
        edtBHYT.requestFocus();
        edtBHYT.setText("");
        edtBenhVien.setText("");
        edtKhoa.setText("");
        edtChuanDoan.setText("");
    }

}