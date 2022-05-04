package com.example.qlkhamsuckhoedientu_nhom3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class DangNhap_Activity extends AppCompatActivity {
    private TaiKhoan taiKhoan;
    private ThongTinCaNhan thongTinCaNhan;

    private FirebaseAuth auth;
    private DatabaseReference ref;

    TextInputEditText edtEmailDN, edtMkDN;
    TextView tvQuenMK, tvDKNgay;
    Button btnDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);

        edtEmailDN = findViewById(R.id.edtEmailDN);
        edtMkDN = findViewById(R.id.edtMkDN);
        tvQuenMK = findViewById(R.id.tvQuenMK);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        tvDKNgay = findViewById(R.id.tvDKNgay);

        auth = FirebaseAuth.getInstance();
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangNhap();
            }
        });

        quenMK();
        dangKyNgay();
    }

    public void quenMK(){
        tvQuenMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DangNhap_Activity.this.startActivity(new Intent(DangNhap_Activity.this, Quen_DoiMK_Activity.class));
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

    public void dangNhap(){
        String txtEmailDN = edtEmailDN.getText().toString().trim(),
                txtMkDN = edtMkDN.getText().toString().trim();

        //check empty
        if (txtMkDN.equals(""))
            Toast.makeText(this, "Vui lòng nhập mật khẩu đăng nhập!", Toast.LENGTH_SHORT).show();
        if (txtEmailDN.equals(""))
            Toast.makeText(this, "Vui lòng nhập email đăng nhập!", Toast.LENGTH_SHORT).show();


        if(!txtEmailDN.equals("") && !txtMkDN.equals("")){
            auth.signInWithEmailAndPassword(txtEmailDN, txtMkDN)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                taiKhoan = new TaiKhoan(txtEmailDN, txtMkDN);
                                infoTkDN(taiKhoan);

//                                thongTinCaNhan = new ThongTinCaNhan(taiKhoan.getHoTen());

                                Toast.makeText(DangNhap_Activity.this, "Tài khoản có email là " + txtEmailDN + " đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                DangNhap_Activity.this.startActivity(new Intent(DangNhap_Activity.this, NguoDung_Activity.class));

                                edtEmailDN.setText("");
                                edtMkDN.setText("");
                            }else
                                Toast.makeText(DangNhap_Activity.this, "Đăng nhập thất bại!", Toast.LENGTH_SHORT).show(); //password phải nhập >= 6 kí tự, có chữ và số trong firebase
                        }
                    });
        }
    }

    //check taikhoan đang đăng nhập
    public void infoTkDN(TaiKhoan taiKhoan){
        String log = "email: " +taiKhoan.getEmail();
        Log.d("tk đang đăng nhập: ",log);
    }
}