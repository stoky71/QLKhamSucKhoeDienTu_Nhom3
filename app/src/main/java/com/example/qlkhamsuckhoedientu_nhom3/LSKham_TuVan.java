package com.example.qlkhamsuckhoedientu_nhom3;

public class LSKham_TuVan {
    private String tinhTrangBHYT;
    private String benhVien;
    private String khoa;
    private String chuanDoan;

    public String getTinhTrangBHYT() {
        return tinhTrangBHYT;
    }

    public void setTinhTrangBHYT(String tinhTrangBHYT) {
        this.tinhTrangBHYT = tinhTrangBHYT;
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

    public String getChuanDoan() {
        return chuanDoan;
    }

    public void setChuanDoan(String chuanDoan) {
        this.chuanDoan = chuanDoan;
    }

    public LSKham_TuVan(String tinhTrangBHYT, String benhVien, String khoa, String chuanDoan) {
        this.tinhTrangBHYT = tinhTrangBHYT;
        this.benhVien = benhVien;
        this.khoa = khoa;
        this.chuanDoan = chuanDoan;
    }

    public LSKham_TuVan() {
    }
}
