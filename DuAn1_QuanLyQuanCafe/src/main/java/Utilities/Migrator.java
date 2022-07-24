/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import Entity.Ban;
import Entity.DonViTinh;
import Entity.Image;
import Entity.KhuVuc;
import Entity.NhanVien;
import Entity.NhomSanPham;
import Entity.SanPham;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Phong
 */
public class Migrator {

    //Tạo DB trong SQL SERVER = SOFT2041_PTPM
    //Sau đó tiến hành chạy đển zen bảng
    public static void main(String[] args) {
        // Tạo đối tượng ServiceRegistry từ hibernate.cfg.xml
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("META-INF/hibernate.cfg.xml")
                .applySetting("hibernate.hbm2ddl.auto", "create")
                .build();

        // Tạo nguồn siêu dữ liệu (metadata) từ ServiceRegistry
        Metadata metadata = new MetadataSources(serviceRegistry)
                .getMetadataBuilder().build();

        SessionFactory factory = metadata.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        Transaction trans = session.beginTransaction();

        NhanVien nv = new NhanVien();
        nv.setMaNhanVien("NV1");
        nv.setTenNhanVien("Vũ Tiến Long");
        nv.setDiaChi("Thái Bình");
        nv.setNgaySinh(XDate.getBirthDay("2021-05-01"));
        nv.setSoDienThoai("0392133732");
        nv.setEmail("longvtph18869@gmail.com");
        nv.setPassWord("123456");
        nv.setGioiTinh(1);
        nv.setId((Long) session.save(nv));

        Image img = new Image();
        img.setMaAnh("DOUONG1");
        img.setTenAnh("Đồ uống");
        img.setDuongDanAnh("coffee.png");
        img.setId((Long) session.save(img));
        Image img1 = new Image();
        img1.setMaAnh("DOUONG2");
        img1.setTenAnh("Đồ uống");
        img1.setDuongDanAnh("beer.png");
        img1.setId((Long) session.save(img1));
        Image img2 = new Image();
        img2.setMaAnh("DOUONG3");
        img2.setTenAnh("Đồ uống");
        img2.setDuongDanAnh("coke.png");
        img2.setId((Long) session.save(img2));
        
        DonViTinh dvt = new DonViTinh();
        dvt.setTenDVT("Cốc");
        dvt.setImageID(img);
        dvt.setId((Long) session.save(dvt));
        DonViTinh dvt1 = new DonViTinh();
        dvt1.setTenDVT("Lon");
        dvt1.setImageID(img2);
        dvt1.setId((Long) session.save(dvt1));
        
        KhuVuc kv = new KhuVuc();
        kv.setMaKhuVuc("KV1");
        kv.setTenKhuVuc("Khu A");
        kv.setTab(1);
        kv.setImageID(img);
        kv.setId((Long) session.save(kv));
        KhuVuc kv1 = new KhuVuc();
        kv1.setMaKhuVuc("KV2");
        kv1.setTenKhuVuc("Khu B");
        kv1.setTab(2);
        kv1.setImageID(img1);
        kv1.setId((Long) session.save(kv1));
        
        Ban ban = new Ban();
        ban.setMaBan("BAN1");
        ban.setTenBan("Bàn A1");
        ban.setKhuVuc(kv);
        ban.setTrangThai(0);
        ban.setId((Long) session.save(ban));
        Ban ban1 = new Ban();
        ban1.setMaBan("BAN2");
        ban1.setTenBan("Bàn A2");
        ban1.setKhuVuc(kv);
        ban1.setTrangThai(0);
        ban1.setId((Long) session.save(ban1));
        Ban ban2 = new Ban();
        ban2.setMaBan("BAN3");
        ban2.setTenBan("Bàn A3");
        ban2.setKhuVuc(kv);
        ban2.setTrangThai(0);
        ban2.setId((Long) session.save(ban2));
        
        Ban ban4 = new Ban();
        ban4.setMaBan("BAN4");
        ban4.setTenBan("Bàn B1");
        ban4.setKhuVuc(kv1);
        ban4.setTrangThai(0);
        ban4.setId((Long) session.save(ban4));
        Ban ban5 = new Ban();
        ban5.setMaBan("BAN5");
        ban5.setTenBan("Bàn B2");
        ban5.setKhuVuc(kv1);
        ban5.setTrangThai(0);
        ban5.setId((Long) session.save(ban5));
        
        NhomSanPham nsp = new NhomSanPham();
        nsp.setMaNhomSanPham("NSP1");
        nsp.setTenNhomSanPham("Cà phê");
        nsp.setImageID(img);
        nsp.setTrangthai(0);
        nsp.setId((Long) session.save(nsp));
        
        NhomSanPham nsp1 = new NhomSanPham();
        nsp1.setMaNhomSanPham("NSP2");
        nsp1.setTenNhomSanPham("Bia");
        nsp1.setImageID(img1);
        nsp1.setTrangthai(0);
        nsp1.setId((Long) session.save(nsp1));
        
        NhomSanPham nsp2 = new NhomSanPham();
        nsp2.setMaNhomSanPham("NSP3");
        nsp2.setTenNhomSanPham("Nước ngọt");
        nsp2.setImageID(img2);
        nsp2.setTrangthai(0);
        nsp2.setId((Long) session.save(nsp2));
        
        SanPham sp = new SanPham();
        sp.setMaSanPham("SP1");
        sp.setTenSanPham("Cà phê đen đá");
        sp.setGiaNhap(0);
        sp.setDonGia(25000);
        sp.setDVT(dvt);
        sp.setNgayTao(XDate.now());
        sp.setNhomSanPhamID(nsp);
        sp.setImageID(img);
        sp.setTrangthai(0);
        sp.setId((Long) session.save(sp));
        
        SanPham sp1 = new SanPham();
        sp1.setMaSanPham("SP2");
        sp1.setTenSanPham("TIGER Bạc");
        sp1.setGiaNhap(27000);
        sp1.setDonGia(30000);
        sp1.setDVT(dvt1);
        sp1.setNgayTao(XDate.now());
        sp1.setNhomSanPhamID(nsp1);
        sp1.setImageID(img1);
        sp1.setTrangthai(0);
        sp1.setId((Long) session.save(sp1));
        
        SanPham sp2 = new SanPham();
        sp2.setMaSanPham("SP3");
        sp2.setTenSanPham("Coca Cola");
        sp2.setGiaNhap(8000);
        sp2.setDonGia(10000);
        sp2.setDVT(dvt1);
        sp2.setNgayTao(XDate.now());
        sp2.setNhomSanPhamID(nsp2);
        sp2.setImageID(img2);
        sp2.setTrangthai(0);
        sp2.setId((Long) session.save(sp2));
        
        

        trans.commit();
    }
}
