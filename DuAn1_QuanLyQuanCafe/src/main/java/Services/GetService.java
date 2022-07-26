/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entity.Ban;
import Entity.DonViTinh;
import Entity.Image;
import Entity.KhachHang;
import Entity.KhuVuc;
import Entity.NhomSanPham;
import ViewModels.ModelBan;
import ViewModels.ModelDonViTinh;
import ViewModels.ModelImage;
import ViewModels.ModelKhachHang;
import ViewModels.ModelKhuVuc;
import ViewModels.ModelNhomSanPham;

/**
 *
 * @author Admin
 */
public class GetService {

    public static ModelKhuVuc getModelKhuVuc(KhuVuc x) {
        return new ModelKhuVuc(x.getId(), x.getMaKhuVuc(), x.getTenKhuVuc(), getModelImage(x.getImageID()), x.getTab());
    }

    public static KhuVuc getKhuVuc(ModelKhuVuc x) {
        return new KhuVuc(x.getId(), x.getMaKhuVuc(), x.getTenKhuVuc(), getImage(x.getImageID()), x.getTab());
    }
    
    public static Image getImage(ModelImage x) {
        return new Image(x.getId(), x.getMaAnh(), x.getTenAnh(), x.getDuongDanAnh());
    }
    
    public static ModelImage getModelImage(Image x) {
        return new ModelImage(x.getId(), x.getMaAnh(), x.getTenAnh(), x.getDuongDanAnh());
    }
    public static DonViTinh getDonViTinh(ModelDonViTinh x) {
        return new DonViTinh(x.getId(), x.getTenDVT(), getImage(x.getImageID() ));
    }
    
    public static ModelDonViTinh getModelDonViTinh(DonViTinh x) {
        return new ModelDonViTinh(x.getId(), x.getTenDVT(), getModelImage(x.getImageID()));
    }
     public static NhomSanPham getNhomSanPham(ModelNhomSanPham x) {
        return new NhomSanPham(x.getId(), x.getMaNhomSanPham(), x.getTenNhomSanPham(), getImage(x.getImageID()),x.getTrangthai());
    }
    
    public static ModelNhomSanPham getModelNhomSanPham(NhomSanPham x) {
        return new ModelNhomSanPham(x.getId(), x.getMaNhomSanPham(), x.getTenNhomSanPham(), getModelImage(x.getImageID()),x.getTrangthai());
    }
    
    public static Ban getBan(ModelBan x) {
        return new Ban(x.getId(), x.getMaBan(), x.getTenBan(), getKhuVuc(x.getKhuVucID()), x.getTrangThai());
    }
    
    public static ModelBan getModelBan(Ban x) {
        return new ModelBan(x.getId(), x.getMaBan(), x.getTenBan(), getModelKhuVuc(x.getKhuVuc()), x.getTrangThai());
    }
    
     public static KhachHang getKhachHang(ModelKhachHang x) {
        return new KhachHang(x.getId(), x.getMaKhachHang(), x.getTenKhachHang(), x.getDiaChi(), x.getDienThoai(), x.getEmail(), x.getFax(), x.getTrangThai(), x.getGhiChu());
    }
    
    public static ModelKhachHang getModelKhachHang(KhachHang x) {
        return new ModelKhachHang(x.getId(), x.getMaKhachHang(), x.getTenKhachHang(), x.getDiaChi(), x.getDienThoai(), x.getEmail(), x.getFax(), x.getTrangThai(), x.getGhiChu());
    }
    
}
