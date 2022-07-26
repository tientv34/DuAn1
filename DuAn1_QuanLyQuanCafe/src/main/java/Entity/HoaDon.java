/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "HoaDon")
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BANID")
    private Ban ban;

    @Column(name = "THOIGIANTAO")
    private Date ngayTao;

    @Column(name = "THOIGIANTHANHTOAN")
    private Date ngayThanhToan;

    @ManyToOne
    @JoinColumn(name = "KHACHHANGID")
    private KhachHang khachHang;

    @Column(name = "TONGCONG")
    private double tongCong;

    @Column(name = "KHACHDUA")
    private double khachDua;

    @Column(name = "TRALAI")
    private double traLai;

    @Column(name = "TRANGTHAI")
    private int trangThai;

    @Column(name = "GHICHU")
    private String ghiChu;

    @OneToMany(mappedBy="hoaDon", 
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HoaDonChiTiet> hoaDonChiTiet;
    
    public HoaDon() {
    }

    public HoaDon(Long id, Ban ban, Date ngayTao, Date ngayThanhToan, KhachHang khachHang, double tongCong, double khachDua, double traLai, int trangThai, String ghiChu) {
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

    public Ban getBan() {
        return ban;
    }

    public void setBan(Ban ban) {
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

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
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

    public List<HoaDonChiTiet> getHoaDonChiTiet() {
        return hoaDonChiTiet;
    }

    public void setHoaDonChiTiet(List<HoaDonChiTiet> hoaDonChiTiet) {
        this.hoaDonChiTiet = hoaDonChiTiet;
    }
    
    

   
    
}
