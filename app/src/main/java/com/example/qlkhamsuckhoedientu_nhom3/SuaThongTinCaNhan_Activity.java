package com.example.qlkhamsuckhoedientu_nhom3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SuaThongTinCaNhan_Activity extends AppCompatActivity {
    EditText edtHoTen, edtNgaySinh, edtSDT, edtCMND, edtBHYT;
    RadioButton rdNam, rdNu, rdKhac;
    Button btnCapNhat, btnHuyBo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suathongtincanhan);

        init();
        loadData();

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
                resetData();
                loadData();
            }
        });

        btnHuyBo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetData();
            }
        });


    }

    private void init(){
        edtHoTen=findViewById(R.id.edtHoTenCapNhat);
        edtNgaySinh=findViewById(R.id.edtNgaySinh);
        edtSDT=findViewById(R.id.edtSDT);
        edtCMND=findViewById(R.id.edtCMND);
        edtBHYT=findViewById(R.id.edtBHYT);
        rdNam=findViewById(R.id.rdNam);
        rdNu=findViewById(R.id.rdNu);
        rdKhac=findViewById(R.id.rdKhac);
        btnCapNhat=findViewById(R.id.btnCapNhat);
        btnHuyBo=findViewById(R.id.btnHuyBo);
    }

    private void loadData(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myReference=database.getReference("Bệnh nhân")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Thông tin cá nhân");

        myReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ThongTinCaNhan thongTinCaNhan=snapshot.getValue(ThongTinCaNhan.class);
                edtHoTen.setText(thongTinCaNhan.getHoTen());
                edtNgaySinh.setText(thongTinCaNhan.getNgaySinh());
                edtCMND.setText(thongTinCaNhan.getSoCMND());
                edtSDT.setText(thongTinCaNhan.getSdt());
                edtBHYT.setText(thongTinCaNhan.getSoBHYT());
                if(thongTinCaNhan.getGioiTinh().equalsIgnoreCase("Nam")){
                    rdNam.setChecked(true);
                }
                else if(thongTinCaNhan.getGioiTinh().equalsIgnoreCase("Nữ")){
                    rdNu.setChecked(true);
                }
                else
                    rdKhac.setChecked(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void updateData(){
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        DatabaseReference myReference=database.getReference("Bệnh nhân")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Thông tin cá nhân");

        String hoTen= String.valueOf(edtHoTen.getText());
        String ngaySinh= String.valueOf(edtNgaySinh.getText());
        String cmnd= String.valueOf(edtCMND.getText());
        String sdt= String.valueOf(edtSDT.getText());
        String bhyt= String.valueOf(edtBHYT.getText());
        String gioiTinh="";

        if(rdNam.isChecked()==true) {
            gioiTinh="Nam";
        }
        else if(rdNu.isChecked()==true){
            gioiTinh="Nữ";
        }
        else {
            gioiTinh="Khác";
        }

        ThongTinCaNhan thongTinCaNhan=new ThongTinCaNhan(gioiTinh,hoTen, ngaySinh, sdt, bhyt, cmnd);
        myReference.setValue(thongTinCaNhan, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                Toast.makeText(SuaThongTinCaNhan_Activity.this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void resetData(){
        edtHoTen.setText("");
        edtNgaySinh.setText("");
        edtCMND.setText("");
        edtSDT.setText("");
        edtBHYT.setText("");
    }
}