/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entity.DonViTinh;
import Entity.Image;
import Entity.KhuVuc;
import Entity.NhomSanPham;
import ViewModels.ModelDonViTinh;
import ViewModels.ModelImage;
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
}
