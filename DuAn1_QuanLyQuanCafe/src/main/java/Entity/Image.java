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
@Table(name = "IMAGE")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "MAANH")
    private String maAnh;
    
    @Column(name = "NAMEIMAGE",columnDefinition = "NVARCHAR(255)")
    private String tenAnh;
    
    @Column(name = "LINK")
    private String duongDanAnh;
    
    @OneToMany(mappedBy="imageID", 
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<KhuVuc> khuVuc;
    
    @OneToMany(mappedBy="imageID", 
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SanPham> sanPham;
    
    @OneToMany(mappedBy="imageID", 
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<NhomSanPham> nhomSanPham;
    
    @OneToMany(mappedBy="imageID", 
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DonViTinh> donViTinh;

    public Image() {
    }

    public Image(Long id, String maAnh, String tenAnh, String duongDanAnh) {
        this.id = id;
        this.maAnh = maAnh;
        this.tenAnh = tenAnh;
        this.duongDanAnh = duongDanAnh;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaAnh() {
        return maAnh;
    }

    public void setMaAnh(String maAnh) {
        this.maAnh = maAnh;
    }

    public String getTenAnh() {
        return tenAnh;
    }

    public void setTenAnh(String tenAnh) {
        this.tenAnh = tenAnh;
    }

    public String getDuongDanAnh() {
        return duongDanAnh;
    }

    public void setDuongDanAnh(String duongDanAnh) {
        this.duongDanAnh = duongDanAnh;
    }

    public List<KhuVuc> getKhuVuc() {
        return khuVuc;
    }

    public void setKhuVuc(List<KhuVuc> khuVuc) {
        this.khuVuc = khuVuc;
    }

    public List<SanPham> getSanPham() {
        return sanPham;
    }

    public void setSanPham(List<SanPham> sanPham) {
        this.sanPham = sanPham;
    }

    public List<NhomSanPham> getNhomSanPham() {
        return nhomSanPham;
    }

    public void setNhomSanPham(List<NhomSanPham> nhomSanPham) {
        this.nhomSanPham = nhomSanPham;
    }

    public List<DonViTinh> getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(List<DonViTinh> donViTinh) {
        this.donViTinh = donViTinh;
    }
    
    
    
   
}
