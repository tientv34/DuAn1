/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

import Entity.Image;

/**
 *
 * @author Admin
 */
public class ModelNhomSanPham {
    private Long id;
    private String maNhomSanPham;
    private String tenNhomSanPham;
    private ModelImage imageID;
    private int trangthai;

    public ModelNhomSanPham() {
    }

    public ModelNhomSanPham(Long id, String maNhomSanPham, String tenNhomSanPham, ModelImage imageID, int trangthai) {
        this.id = id;
        this.maNhomSanPham = maNhomSanPham;
        this.tenNhomSanPham = tenNhomSanPham;
        this.imageID = imageID;
        this.trangthai = trangthai;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaNhomSanPham() {
        return maNhomSanPham;
    }

    public void setMaNhomSanPham(String maNhomSanPham) {
        this.maNhomSanPham = maNhomSanPham;
    }

    public String getTenNhomSanPham() {
        return tenNhomSanPham;
    }

    public void setTenNhomSanPham(String tenNhomSanPham) {
        this.tenNhomSanPham = tenNhomSanPham;
    }

    public ModelImage getImageID() {
        return imageID;
    }

    public void setImageID(ModelImage imageID) {
        this.imageID = imageID;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

   
    
    
}
