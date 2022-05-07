//package com.example.qlkhamsuckhoedientu_nhom3;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.constraintlayout.widget.ConstraintLayout;
//
//import java.util.ArrayList;
//
//public class LSKham_TuVan_Adapter extends BaseAdapter {
//    private Context context;
//    private int layout;
//    private ArrayList<ThongTinLSKham_TuVan> arrayList;
////    private DatabaseReference ref;
//
//    public LSKham_TuVan_Adapter(Context context, int layout, ArrayList<ThongTinLSKham_TuVan> arrayList) {
//        this.context = context;
//        this.layout = layout;
//        this.arrayList = arrayList;
////        this.ref = ref;
//    }
//
//    @Override
//    public int getCount() {
//        if(arrayList!=null && !arrayList.isEmpty())
//            return arrayList.size();
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
//
//        ConstraintLayout layoutLSKham = view.findViewById(R.id.layoutLSKham);
//        ImageView img_logo_lv = view.findViewById(R.id.img_logo_lv);
//        TextView tvNgayKham = view.findViewById(R.id.tvNgayKham),
//                 tv_lv_BHYT = view.findViewById(R.id.tv_lv_BHYT),
//                 tv_lv_bv = view.findViewById(R.id.tv_lv_bv),
//                 tv_lv_khoa = view.findViewById(R.id.tv_lv_khoa),
//                 tv_lv_chuandoan = view.findViewById(R.id.tv_lv_chuandoan),
//
//                tv_co_khong = view.findViewById(R.id.tv_co_khong),
//                tvTenBV = view.findViewById(R.id.tvTenBV),
//                tvTenKhoa = view.findViewById(R.id.tvTenKhoa),
//                tvChuanDoan = view.findViewById(R.id.tvInfoLSK);
//
//        ThongTinLSKham_TuVan thongTinLichSuKham = arrayList.get(i);
//
//        if(arrayList!=null && !arrayList.isEmpty()){
//            tvNgayKham.setText(thongTinLichSuKham.getNgayKham());
//            tv_co_khong.setText(thongTinLichSuKham.getBHYT());
//            tvTenBV.setText(thongTinLichSuKham.getBenhVien());
//            tvTenKhoa.setText(thongTinLichSuKham.getKhoa());
//            tvChuanDoan.setText(thongTinLichSuKham.getChuanDoan());
//        }
//
//        return view;
//    }
//}
