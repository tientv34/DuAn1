/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author TranTien
 */
@Entity
@Table(name = "NhanVien")
public class NhanVien implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "MANHANVIEN")
    private String maNhanVien;
    
    @Column(name = "TENNHANVIEN", columnDefinition = "NVARCHAR(255)")
    private String tenNhanVien;
    
    @Column(name = "DIACHI")
    private String diaChi;
    
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "SODIENTHOAI")
    private String soDienThoai;
    
    @Column(name = "GIOITINH")
    private int gioiTinh;
    
    @Column(name = "NGAYSINH")
    private Date ngaySinh;
    
    @Column(name = "PASSWORD")
    private String passWord;
    
    @Column(name = "TRANGTHAI")
    private int trangthai;

    public NhanVien() {
    }

    public NhanVien(Long id, String maNhanVien, String tenNhanVien, String diaChi, String email, String soDienThoai, int gioiTinh, Date ngaySinh, String passWord, int trangthai) {
        this.id = id;
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.diaChi = diaChi;
        this.email = email;
        this.soDienThoai = soDienThoai;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.passWord = passWord;
        this.trangthai = trangthai;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public int getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(int gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
    

    @Override
    public String toString() {
        return "NhanVien{" + "id=" + id + ", maNhanVien=" + maNhanVien + ", tenNhanVien=" + tenNhanVien + ", diaChi=" + diaChi + ", email=" + email + ", soDienThoai=" + soDienThoai + ", gioiTinh=" + gioiTinh + ", ngaySinh=" + ngaySinh + ", passWord=" + passWord + '}';
    }

}
