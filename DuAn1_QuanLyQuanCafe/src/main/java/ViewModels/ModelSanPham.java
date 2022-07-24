/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import Entity.DonViTinh;
import Entity.Image;
import Entity.NhomSanPham;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class ModelSanPham {
    private Long id;
    private String maSanPham;
    private String tenSanPham;
    private Date ngayTao;
    private ModelDonViTinh DVT;
    private double giaNhap;
    private double donGia;
    private ModelNhomSanPham nhomSanPhamID;
    private ModelImage imageID;
    private int trangthai;

    public ModelSanPham() {
    }

    public ModelSanPham(Long id, String maSanPham, String tenSanPham, Date ngayTao, ModelDonViTinh DVT, double giaNhap, double donGia, ModelNhomSanPham nhomSanPhamID, ModelImage imageID, int trangthai) {
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

    public ModelDonViTinh getDVT() {
        return DVT;
    }

    public void setDVT(ModelDonViTinh DVT) {
        this.DVT = DVT;
    }

    public ModelNhomSanPham getNhomSanPhamID() {
        return nhomSanPhamID;
    }

    public void setNhomSanPhamID(ModelNhomSanPham nhomSanPhamID) {
        this.nhomSanPhamID = nhomSanPhamID;
    }

    public ModelImage getImageID() {
        return imageID;
    }

    public void setImageID(ModelImage imageID) {
        this.imageID = imageID;
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

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

   

    
    
}
