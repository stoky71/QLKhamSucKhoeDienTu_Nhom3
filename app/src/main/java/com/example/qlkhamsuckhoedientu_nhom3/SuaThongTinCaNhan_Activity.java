package com.example.qlkhamsuckhoedientu_nhom3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.squareup.picasso.Picasso;

import java.util.Calendar;

public class SuaThongTinCaNhan_Activity extends AppCompatActivity {
    EditText edtHoTen, edtNgaySinh, edtSDT, edtCMND, edtBHYT;
    RadioButton rdNam, rdNu, rdKhac;
    Button btnCapNhat, btnHuyBo;
    ImageView imgBack, imgAvatar;

    private int PICK_IMAGE_REQUEST = 1;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    // Create a storage reference from our app
    final StorageReference storageRef = storage.getReference();

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
                Intent intent=new Intent(SuaThongTinCaNhan_Activity.this, NguoiDung_Activity.class);
                startActivity(intent);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SuaThongTinCaNhan_Activity.this, NguoiDung_Activity.class);
                startActivity(intent);
            }
        });

        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
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
        imgBack=findViewById(R.id.imgBack_SuaTT);
        imgAvatar=findViewById(R.id.imgAvatar);
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
                if(!thongTinCaNhan.getImageId().trim().equalsIgnoreCase("")){
                    Picasso.get().load(thongTinCaNhan.getImageId()).into(imgAvatar);
                }
                else{
                    imgAvatar.setImageResource(R.drawable.avatar);
                }

                //imgAvatar.setImageURI(Uri.parse(thongTinCaNhan.getImageId()));
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

        // Create a storage reference from our app
        StorageReference storageRef = storage.getReference();

    // Create a reference to "mountains.jpg"
        StorageReference mountainsRef = storageRef.child("image.jpg");

    // Create a reference to 'images/mountains.jpg'
        StorageReference mountainImagesRef = storageRef.child("images");

    // While the file names are the same, the references point to different files
        mountainsRef.getName().equals(mountainImagesRef.getName());    // true
        mountainsRef.getPath().equals(mountainImagesRef.getPath());    // false

        // Get the data from an ImageView as bytes
        imgAvatar.setDrawingCacheEnabled(true);
        imgAvatar.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imgAvatar.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);
        String finalGioiTinh = gioiTinh;
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String photoLink = uri.toString();

                        ThongTinCaNhan thongTinCaNhan=new ThongTinCaNhan(finalGioiTinh,hoTen, ngaySinh, sdt, bhyt, cmnd,photoLink);
                        myReference.setValue(thongTinCaNhan, new DatabaseReference.CompletionListener() {
                            @Override
                            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                                Toast.makeText(SuaThongTinCaNhan_Activity.this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
        

//        ThongTinCaNhan thongTinCaNhan=new ThongTinCaNhan(gioiTinh,hoTen, ngaySinh, sdt, bhyt, cmnd,data);
//        myReference.setValue(thongTinCaNhan, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                Toast.makeText(SuaThongTinCaNhan_Activity.this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    private void resetData(){
        edtHoTen.setText("");
        edtNgaySinh.setText("");
        edtCMND.setText("");
        edtSDT.setText("");
        edtBHYT.setText("");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            Bitmap resizeBitmap = Bitmap.createScaledBitmap(bitmap,50,50,false);
            imgAvatar.setImageBitmap(resizeBitmap);
        }
        else if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                Bitmap resizeBitmap = Bitmap.createScaledBitmap(bitmap,50,50,false);
                imgAvatar.setImageBitmap(resizeBitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}