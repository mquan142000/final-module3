package com.example.finalmodule3.model;

import java.time.LocalDate;

public class Offices {
    private String maMatBang;
    private String trangThai;
    private double dienTich;
    private int tang;
    private String loaiMatBang;
    private double giaTien;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;

    public Offices() {
    }

    public Offices(String maMatBang, String trangThai, double dienTich, int tang, String loaiMatBang, double giaTien, LocalDate ngayBatDau, LocalDate ngayKetThuc) {
        this.maMatBang = maMatBang;
        this.trangThai = trangThai;
        this.dienTich = dienTich;
        this.tang = tang;
        this.loaiMatBang = loaiMatBang;
        this.giaTien = giaTien;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getMaMatBang() {
        return maMatBang;
    }

    public void setMaMatBang(String maMatBang) {
        this.maMatBang = maMatBang;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public double getDienTich() {
        return dienTich;
    }

    public void setDienTich(double dienTich) {
        this.dienTich = dienTich;
    }

    public int getTang() {
        return tang;
    }

    public void setTang(int tang) {
        this.tang = tang;
    }

    public String getLoaiMatBang() {
        return loaiMatBang;
    }

    public void setLoaiMatBang(String loaiMatBang) {
        this.loaiMatBang = loaiMatBang;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public LocalDate getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public boolean isValid() {
        return maMatBang.matches("[A-Z]{3}-[0-9]{2}-[0-9]{2}") &&
                (trangThai.equals("Trống") || trangThai.equals("Hạ tầng") || trangThai.equals("Đầy đủ")) &&
                dienTich > 20 &&
                tang >= 1 && tang <= 15 &&
                (loaiMatBang.equals("Văn phòng chia sẻ") || loaiMatBang.equals("Văn phòng trọn gói")) &&
                giaTien > 1000000 &&
                ngayBatDau.isBefore(ngayKetThuc) &&
                ngayKetThuc.isAfter(ngayBatDau.plusMonths(6));
    }

}
