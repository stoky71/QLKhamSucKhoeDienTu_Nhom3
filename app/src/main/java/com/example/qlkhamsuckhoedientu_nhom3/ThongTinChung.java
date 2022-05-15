package com.example.qlkhamsuckhoedientu_nhom3;

public class ThongTinChung {
    private ThongTinCaNhan thongTinCaNhan;
    private ThongTinLSKham_TuVan thongTinLSKham_tuVan;

    public ThongTinCaNhan getThongTinCaNhan() {
        return thongTinCaNhan;
    }

    public void setThongTinCaNhan(ThongTinCaNhan thongTinCaNhan) {
        this.thongTinCaNhan = thongTinCaNhan;
    }

    public ThongTinLSKham_TuVan getThongTinLSKham_tuVan() {
        return thongTinLSKham_tuVan;
    }

    public ThongTinChung(ThongTinCaNhan thongTinCaNhan, ThongTinLSKham_TuVan thongTinLSKham_tuVan) {
        this.thongTinCaNhan = thongTinCaNhan;
        this.thongTinLSKham_tuVan = thongTinLSKham_tuVan;
    }

    public ThongTinChung() {
    }

    @Override
    public String toString() {
        return "ThongTinChung{" +
                "thongTinCaNhan=" + thongTinCaNhan +
                ", thongTinLSKham_tuVan=" + thongTinLSKham_tuVan +
                '}';
    }
}
