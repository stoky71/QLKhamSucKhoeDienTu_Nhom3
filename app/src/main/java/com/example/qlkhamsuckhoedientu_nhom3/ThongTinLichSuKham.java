package com.example.qlkhamsuckhoedientu_nhom3;

public class ThongTinLichSuKham {
    private String BHYT;
    private String chuanDoan;
    private String benhVien;
    private String khoa;

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

    public ThongTinLichSuKham(String BHYT, String chuanDoan, String benhVien, String khoa) {
        this.BHYT = BHYT;
        this.chuanDoan = chuanDoan;
        this.benhVien = benhVien;
        this.khoa = khoa;
    }

    public ThongTinLichSuKham() {
    }
}
