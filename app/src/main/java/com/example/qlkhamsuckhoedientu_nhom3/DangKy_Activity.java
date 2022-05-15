package com.example.qlkhamsuckhoedientu_nhom3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DangKy_Activity extends AppCompatActivity {
    private TaiKhoan taiKhoan;
    private ThongTinCaNhan thongTinCaNhan;

    private FirebaseAuth auth;
    private DatabaseReference ref;

    TextInputEditText edtHoTenDK, edtSdtDK, edtEmailDK, edtMkDK;
    Button btnDangKy;
    TextView tvDNNgay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        edtHoTenDK = findViewById(R.id.edtHoTenDK);
        edtSdtDK = findViewById(R.id.edtSdtDK);
        edtEmailDK = findViewById(R.id.edtEmailDK);
        edtMkDK = findViewById(R.id.edtMkDK);
        btnDangKy = findViewById(R.id.btnDangKy);
        tvDNNgay = findViewById(R.id.tvDNNgay);

        auth = FirebaseAuth.getInstance();
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangky();
            }
        });

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

    public void dangky() {
        String txtHoTenDK = edtHoTenDK.getText().toString().trim(),
                txtSdtDK = edtSdtDK.getText().toString().trim(),
                txtEmailDK = edtEmailDK.getText().toString().trim(),
                txtMkDK = edtMkDK.getText().toString().trim();

        //check empty
        if (txtMkDK.equals(""))
            Toast.makeText(this, "Vui lòng nhập mật khẩu đăng ký!", Toast.LENGTH_SHORT).show();
        if (txtEmailDK.equals(""))
            Toast.makeText(this, "Vui lòng nhập email đăng ký!", Toast.LENGTH_SHORT).show();
        if (txtSdtDK.equals(""))
            Toast.makeText(this, "Vui lòng nhập số điện thoại đăng ký!", Toast.LENGTH_SHORT).show();
        if (txtHoTenDK.equals(""))
            Toast.makeText(this, "Vui lòng nhập họ tên đăng ký!", Toast.LENGTH_SHORT).show();

        //regex email
        if(!Patterns.EMAIL_ADDRESS.matcher(txtEmailDK).matches()){
            edtEmailDK.requestFocus();
            edtEmailDK.setError("Email không hợp lệ!");
        }

        //insert account
        if (!txtHoTenDK.equals("") && !txtSdtDK.equals("") && !txtEmailDK.equals("") && !txtMkDK.equals("")) {
            ref = FirebaseDatabase.getInstance().getReference("Bệnh nhân");
            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    if(!snapshot.exists())
                        createUserFirebaseAuth(txtHoTenDK, txtSdtDK, txtEmailDK, txtMkDK);
//                    else {
//                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                            String emailExist = dataSnapshot.child("Tài khoản").child("email").getValue().toString();
//                            if(txtEmailDK.equals(emailExist))
//                                Toast.makeText(DangKy_Activity.this, "Email này đã được đăng ký!", Toast.LENGTH_SHORT).show();
//                            else
//                                createUserFirebaseAuth(txtHoTenDK, txtSdtDK, txtEmailDK, txtMkDK);
//                        }
//                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    public void createUserFirebaseAuth(String txtHoTenDK, String txtSdtDK, String txtEmailDK, String txtMkDK){
        auth.createUserWithEmailAndPassword(txtEmailDK, txtMkDK)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            thongTinCaNhan = new ThongTinCaNhan("", txtHoTenDK, "", txtSdtDK, "", "","");
                            taiKhoan = new TaiKhoan(txtEmailDK, txtMkDK);

                            addDataToRealtimeDB(taiKhoan, thongTinCaNhan);

                            Toast.makeText(DangKy_Activity.this, "Tài khoản có email là " +txtEmailDK+" đăng ký thành công!", Toast.LENGTH_SHORT).show();
//                            DangKy_Activity.this.startActivity(new Intent(DangKy_Activity.this, NguoiDung_Activity.class));
                            DangKy_Activity.this.startActivity(new Intent(DangKy_Activity.this, DangNhap_Activity.class));

                            edtHoTenDK.setText("");
                            edtSdtDK.setText("");
                            edtEmailDK.setText("");
                            edtMkDK.setText("");
                            edtHoTenDK.requestFocus();

                            infoTkDK(taiKhoan, thongTinCaNhan);
                        }
//                        else
//                            Toast.makeText(DangKy_Activity.this, "Đăng ký thất bại!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void addDataToRealtimeDB(TaiKhoan taiKhoan, ThongTinCaNhan thongTinCaNhan){
        ref = FirebaseDatabase.getInstance().getReference("Bệnh nhân");
        ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Tài khoản").setValue(taiKhoan);
        ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Thông tin cá nhân").setValue(thongTinCaNhan);
    }

    //check tk đăng ký
    public void infoTkDK(TaiKhoan taiKhoan, ThongTinCaNhan thongTinCaNhan){
        String log = "tên: " +thongTinCaNhan.getHoTen()+ "sđt: "+thongTinCaNhan.getSdt()
                    +"email: "+taiKhoan.getEmail() + "mk: "+taiKhoan.getMatKhau();
        Log.d("tk ĐK thành công: ",log);
    }
}