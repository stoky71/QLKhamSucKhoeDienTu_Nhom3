package com.example.qlkhamsuckhoedientu_nhom3;

public class ThongTinCaNhan {
    private String gioiTinh;
    private String hoTen;
    private String ngaySinh;
    private String sdt;
    private String soBHYT;
    private String soCMND;


    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getSoCMND() {
        return soCMND;
    }

    public void setSoCMND(String soCMND) {
        this.soCMND = soCMND;
    }

    public String getSoBHYT() {
        return soBHYT;
    }

    public void setSoBHYT(String soBHYT) {
        this.soBHYT = soBHYT;
    }

//    public ThongTinCaNhan(String hoTen, String ngaySinh, String gioiTinh, String sdt, String soCMND, String soBHYT) {
//        this.hoTen = hoTen;
//        this.ngaySinh = ngaySinh;
//        this.gioiTinh = gioiTinh;
//        this.sdt = sdt;
//        this.soCMND = soCMND;
//        this.soBHYT = soBHYT;
//    }

    public ThongTinCaNhan(String hoTen) {
        this.hoTen = hoTen;
    }

    public ThongTinCaNhan() {
    }

    public ThongTinCaNhan(String gioiTinh, String hoTen, String ngaySinh, String sdt, String soBHYT, String soCMND) {
        this.gioiTinh = gioiTinh;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.sdt = sdt;
        this.soBHYT = soBHYT;
        this.soCMND = soCMND;
    }

    @Override
    public String toString() {
        return "ThongTinCaNhan{" +
                "gioiTinh='" + gioiTinh + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", ngaySinh='" + ngaySinh + '\'' +
                ", sdt='" + sdt + '\'' +
                ", soBHYT='" + soBHYT + '\'' +
                ", soCMND='" + soCMND + '\'' +
                '}';
    }
}
