/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "KhachHang")
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "MAKHACHHANG")
    private String maKhachHang;
    
    @Column(name = "TENKHACHHANG", columnDefinition = "NVARCHAR(255)")
    private String tenKhachHang;
    
    @Column(name = "DIACHI")
    private String diaChi;
    
    @Column(name = "DIENTHOAI")
    private String dienThoai;
    
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "FAX")
    private String fax;
    
    @Column(name = "TRANGTHAI")
    private int trangThai;
    
    @Column(name = "GHICHU")
    private String ghiChu;
    
    @OneToMany(mappedBy="khachHang", 
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HoaDon> HoaDon;

    public KhachHang() {
    }

    public KhachHang(Long id, String maKhachHang, String tenKhachHang, String diaChi, String dienThoai, String email, String fax, int trangThai, String ghiChu) {
        this.id = id;
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.email = email;
        this.fax = fax;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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
