package com.example.qlkhamsuckhoedientu_nhom3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Quen_DoiMK_Activity extends AppCompatActivity {
    private DatabaseReference ref;

    TextInputEditText edtEmailMKMoi, edtMKMoi;
    Button btnCapNhatMK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_doimk);

        edtEmailMKMoi = findViewById(R.id.edtEmailMKMoi);
        edtMKMoi = findViewById(R.id.edtMKMoi);
        btnCapNhatMK = findViewById(R.id.btnCapNhatMK);

        btnCapNhatMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capNhatMK();
            }
        });
    }

    public void capNhatMK() {
        String txtEmailMKMoi = edtEmailMKMoi.getText().toString().trim(),
                txtMKMoi = edtMKMoi.getText().toString().trim();

        if(txtMKMoi.equals(""))
            Toast.makeText(this, "Vui lòng nhập mật khẩu mới", Toast.LENGTH_SHORT).show();
        if(txtEmailMKMoi.equals(""))
            Toast.makeText(this, "Vui lòng nhập email", Toast.LENGTH_SHORT).show();

        if(!txtEmailMKMoi.equals("") && !txtMKMoi.equals("")){
            ref = FirebaseDatabase.getInstance().getReference("Bệnh nhân");
            ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("Tài khoản")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        TaiKhoan existTK = snapshot.getValue(TaiKhoan.class);
                        String existEmail = existTK.getEmail();

                        if(txtEmailMKMoi.equals(existEmail)){
                            ref.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .child("Tài khoản").setValue(new TaiKhoan(txtEmailMKMoi, txtMKMoi));

                            Toast.makeText(Quen_DoiMK_Activity.this, "Cập nhật mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                            edtEmailMKMoi.requestFocus();
                            edtEmailMKMoi.setText("");
                            edtMKMoi.setText("");
                        }else
                            Toast.makeText(Quen_DoiMK_Activity.this, "Email không đúng!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        }
    }
}