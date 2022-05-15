package com.example.qlkhamsuckhoedientu_nhom3;

public class ThongTinLSKham_TuVan {
    private String ngayKham;
    private String BHYT;
    private String benhVien;
    private String khoa;
    private String chuanDoan;

    public String getNgayKham() {
        return ngayKham;
    }

    public void setNgayKham(String ngayKham) {
        this.ngayKham = ngayKham;
    }

    public String getBHYT() {
        return BHYT;
    }

    public void setBHYT(String BHYT) {
        this.BHYT = BHYT;
    }

    public String getChuanDoan() {
        return chuanDoan;
    }

    public void setChuanDoan(String chuanDoan) {
        this.chuanDoan = chuanDoan;
    }

    public String getBenhVien() {
        return benhVien;
    }

    public void setBenhVien(String benhVien) {
        this.benhVien = benhVien;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public ThongTinLSKham_TuVan(String ngayKham, String BHYT, String chuanDoan, String benhVien, String khoa) {
        this.ngayKham = ngayKham;
        this.BHYT = BHYT;
        this.chuanDoan = chuanDoan;
        this.benhVien = benhVien;
        this.khoa = khoa;
    }

    public ThongTinLSKham_TuVan(String BHYT, String chuanDoan, String benhVien, String khoa) {
        this.BHYT = BHYT;
        this.chuanDoan = chuanDoan;
        this.benhVien = benhVien;
        this.khoa = khoa;
    }

    public ThongTinLSKham_TuVan(String ngayKham) {
        this.ngayKham = ngayKham;
    }

    public ThongTinLSKham_TuVan() {
    }
}
