/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import Entity.HoaDon;
import Entity.SanPham;

/**
 *
 * @author Admin
 */
public class ModelHoaDonChiTiet {

    private Long id;
    private ModelHoaDon hoaDon;
    private ModelSanPham sanPham;
    private int soLuong;
    private double donGia;
    private double thanhTien;
    private int trangThai;

    public ModelHoaDonChiTiet() {
    }

    public ModelHoaDonChiTiet(Long id, ModelHoaDon hoaDon, ModelSanPham sanPham, int soLuong, double donGia, double thanhTien, int trangThai) {
        this.id = id;
        this.hoaDon = hoaDon;
        this.sanPham = sanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
        this.trangThai = trangThai;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModelHoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(ModelHoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public ModelSanPham getSanPham() {
        return sanPham;
    }

    public void setSanPham(ModelSanPham sanPham) {
        this.sanPham = sanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    
}
