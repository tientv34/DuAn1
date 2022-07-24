/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "NhomSanPham")
public class NhomSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "MANHOMSANPHAM")
    private String maNhomSanPham;

    @Column(name = "TENNHOMSANPHAM", columnDefinition = "NVARCHAR(255)")
    private String tenNhomSanPham;
    
    @ManyToOne
    @JoinColumn(name = "IMAGEID")
    private Image imageID;
    
    @Column(name = "TRANGTHAI")
    private int trangthai;

    public NhomSanPham() {
    }

    public NhomSanPham(Long id, String maNhomSanPham, String tenNhomSanPham, Image imageID, int trangthai) {
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
