/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class ModelHoaDon {
     private Long id;
    private ModelBan ban;
    private Date ngayTao;
    private Date ngayThanhToan;
    private ModelKhachHang khachHang;
    private double tongCong;
    private double khachDua;
    private double traLai;
    private int trangThai;
    private String ghiChu;

    public ModelHoaDon() {
    }

    public ModelHoaDon(Long id, ModelBan ban, Date ngayTao, Date ngayThanhToan, ModelKhachHang khachHang, double tongCong, double khachDua, double traLai, int trangThai, String ghiChu) {
        this.id = id;
        this.ban = ban;
        this.ngayTao = ngayTao;
        this.ngayThanhToan = ngayThanhToan;
        this.khachHang = khachHang;
        this.tongCong = tongCong;
        this.khachDua = khachDua;
        this.traLai = traLai;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModelBan getBan() {
        return ban;
    }

    public void setBan(ModelBan ban) {
        this.ban = ban;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public ModelKhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(ModelKhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public double getTongCong() {
        return tongCong;
    }

    public void setTongCong(double tongCong) {
        this.tongCong = tongCong;
    }

    public double getKhachDua() {
        return khachDua;
    }

    public void setKhachDua(double khachDua) {
        this.khachDua = khachDua;
    }

    public double getTraLai() {
        return traLai;
    }

    public void setTraLai(double traLai) {
        this.traLai = traLai;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    
}
