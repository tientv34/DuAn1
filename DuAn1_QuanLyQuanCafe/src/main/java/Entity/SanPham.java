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
@Table(name = "SanPham")
public class SanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "MASANPHAM")
    private String maSanPham;

    @Column(name = "TENSANPHAM", columnDefinition = "NVARCHAR(255)")
    private String tenSanPham;

    @Column(name = "NGAYTAO")
    private Date ngayTao;

    @ManyToOne
    @JoinColumn(name = "DONVITINHID")
    private DonViTinh DVT;

    @Column(name = "GIANHAP")
    private double giaNhap;

    @Column(name = "DONGIA")
    private double donGia;

    @ManyToOne
    @JoinColumn(name = "NHOMSANPHAMID")
    private NhomSanPham nhomSanPhamID;

    @ManyToOne
    @JoinColumn(name = "IMAGEID")
    private Image imageID;

    @Column(name = "TRANGTHAI")
    private int trangthai;

    @OneToMany(mappedBy="sanPham", 
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<HoaDonChiTiet> hoaDonChiTiet;
    public SanPham() {
    }

    public SanPham(Long id, String maSanPham, String tenSanPham, Date ngayTao, DonViTinh DVT, double giaNhap, double donGia, NhomSanPham nhomSanPhamID, Image imageID, int trangthai) {
        this.id = id;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.ngayTao = ngayTao;
        this.DVT = DVT;
        this.giaNhap = giaNhap;
        this.donGia = donGia;
        this.nhomSanPhamID = nhomSanPhamID;
        this.imageID = imageID;
        this.trangthai = trangthai;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public DonViTinh getDVT() {
        return DVT;
    }

    public void setDVT(DonViTinh DVT) {
        this.DVT = DVT;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public NhomSanPham getNhomSanPhamID() {
        return nhomSanPhamID;
    }

    public void setNhomSanPhamID(NhomSanPham nhomSanPhamID) {
        this.nhomSanPhamID = nhomSanPhamID;
    }

    public Image getImageID() {
        return imageID;
    }

    public void setImageID(Image imageID) {
        this.imageID = imageID;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

   

}
