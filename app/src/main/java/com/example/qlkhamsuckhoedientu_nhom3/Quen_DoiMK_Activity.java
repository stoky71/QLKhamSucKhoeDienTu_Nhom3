package com.example.qlkhamsuckhoedientu_nhom3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Quen_DoiMK_Activity extends AppCompatActivity {
    private DatabaseReference ref;
    private FirebaseAuth auth;
    private FirebaseUser user;

    TextInputEditText edtEmailMKMoi, edtMKMoi;
    Button btnCapNhatMK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_doimk);

        edtEmailMKMoi = findViewById(R.id.edtEmailMKMoi);
        edtMKMoi = findViewById(R.id.edtMKMoi);
        btnCapNhatMK = findViewById(R.id.btnCapNhatMK);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        btnCapNhatMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDoiMK();
            }
        });
    }

    public void handleDoiMK() {
        String txtEmailMkMoi = edtEmailMKMoi.getText().toString().trim(),
                txtMkMoi = edtMKMoi.getText().toString().trim();

        if(txtMkMoi.equals(""))
            Toast.makeText(this, "Vui lòng nhập mật khẩu mới", Toast.LENGTH_SHORT).show();
        if(txtEmailMkMoi.equals(""))
            Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();

        if(!Patterns.EMAIL_ADDRESS.matcher(txtEmailMkMoi).matches()){
            edtEmailMKMoi.requestFocus();
            edtEmailMKMoi.setError("Email không hợp lệ!");
        }

        if(!txtEmailMkMoi.equals("") && !txtMkMoi.equals("")) {
            if (user != null) {
                if (txtEmailMkMoi.equals(user.getEmail())) {
                    //đã login và đổi mk
                    user.updatePassword(txtMkMoi).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            //change data matKhau in realtimeDB
                            changeDataMK(txtEmailMkMoi, txtMkMoi);

                            Toast.makeText(Quen_DoiMK_Activity.this, "Cập nhật mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                            edtEmailMKMoi.requestFocus();
                            edtEmailMKMoi.setText("");
                            edtMKMoi.setText("");
                        }
                    });
                } else
                    Toast.makeText(Quen_DoiMK_Activity.this, "Email không đúng!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void changeDataMK(String txtEmailMkMoi, String txtMkMoi){
        ref = FirebaseDatabase.getInstance().getReference("Bệnh nhân")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .child("Tài khoản");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ref.setValue(new TaiKhoan(txtEmailMkMoi, txtMkMoi));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}