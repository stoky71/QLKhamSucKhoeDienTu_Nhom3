package com.example.qlkhamsuckhoedientu_nhom3;

public class ThongTinCaNhan {
    private String hoTen;
    private String ngaySinh;
    private String gioiTinh;
    private String sdt;
    private String soCMND;
    private String soBHYT;

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

    public ThongTinCaNhan(String hoTen, String ngaySinh, String gioiTinh, String sdt, String soCMND, String soBHYT) {
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.soCMND = soCMND;
        this.soBHYT = soBHYT;
    }

    public ThongTinCaNhan(String hoTen, String sdt) {
        this.hoTen = hoTen;
        this.sdt = sdt;
    }

    public ThongTinCaNhan() {
    }
}
