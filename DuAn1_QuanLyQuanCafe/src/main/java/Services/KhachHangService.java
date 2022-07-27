/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entity.KhachHang;
import Repositories.IRepository;
import Repositories.KhachHangRepository;
import Utilities.HibernateUtil;
import ViewModels.ModelKhachHang;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Admin
 */
public class KhachHangService implements IService<ModelKhachHang> {
    
    IRepository _IKhachhangIRepository;
    List<ModelKhachHang> _lstKhachHang;

    public KhachHangService() {
        _IKhachhangIRepository = new KhachHangRepository();
        _lstKhachHang = new ArrayList<>();
    }
    
    @Override
    public List<ModelKhachHang> selectAll(int position, int pageSize, String... args) {
         _lstKhachHang = new ArrayList<>();
            List<KhachHang> khachHangs = _IKhachhangIRepository.SelectAll(position, pageSize, args);
            for (KhachHang x : khachHangs) {
                _lstKhachHang.add(new ModelKhachHang(
                       x.getId(), 
                       x.getMaKhachHang(), 
                       x.getTenKhachHang(), 
                       x.getDiaChi(), 
                       x.getDienThoai(), 
                       x.getEmail(), 
                       x.getFax(), 
                       x.getTrangThai(), 
                       x.getGhiChu()));
        }
            return _lstKhachHang;
    }

    @Override
    public List<ModelKhachHang> getListFromDB() {
        return _lstKhachHang;
    }

    @Override
    public int Count(String... args) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ModelKhachHang insert(ModelKhachHang entity) {
        entity.setId(null);
        KhachHang x = (KhachHang) _IKhachhangIRepository.insert(new KhachHang(entity.getId(), entity.getMaKhachHang(), entity.getTenKhachHang(), 
                entity.getDiaChi(), entity.getDienThoai(), entity.getEmail(), entity.getFax(), entity.getTrangThai(), entity.getGhiChu()));
        return new ModelKhachHang(x.getId(), x.getMaKhachHang(), x.getTenKhachHang(), x.getDiaChi(), x.getDienThoai(), x.getEmail(), 
                x.getFax(), x.getTrangThai(), x.getGhiChu());
    }

    @Override
    public ModelKhachHang update(ModelKhachHang entity) {
        KhachHang x = (KhachHang) _IKhachhangIRepository.insert(new KhachHang(entity.getId(), entity.getMaKhachHang(), entity.getTenKhachHang(), 
                entity.getDiaChi(), entity.getDienThoai(), entity.getEmail(), entity.getFax(), entity.getTrangThai(), entity.getGhiChu()));
        return new ModelKhachHang(x.getId(), x.getMaKhachHang(), x.getTenKhachHang(), x.getDiaChi(), x.getDienThoai(), x.getEmail(), 
                x.getFax(), x.getTrangThai(), x.getGhiChu());
    }

    @Override
    public ModelKhachHang findById(long id) {
        KhachHang x = (KhachHang) _IKhachhangIRepository.findById(id);
        return new ModelKhachHang(x.getId(), x.getMaKhachHang(), x.getTenKhachHang(), x.getDiaChi(), x.getDienThoai(), x.getEmail(), 
                x.getFax(), x.getTrangThai(), x.getGhiChu());
    }

    @Override
    public List<ModelKhachHang> findList(String id) {
        _lstKhachHang = new ArrayList<>();
        List<KhachHang> khachHangs = _IKhachhangIRepository.findList(id);
        if (khachHangs.isEmpty()) {
            return null;
        }
        for (KhachHang x : khachHangs) {
            _lstKhachHang.add(new ModelKhachHang(x.getId(), x.getMaKhachHang(), x.getTenKhachHang(), x.getDiaChi(), x.getDienThoai(), 
                    x.getEmail(), x.getFax(), x.getTrangThai(), x.getGhiChu()));
        }
        return _lstKhachHang;
    }

    @Override
    public long delete(long id) {
        return _IKhachhangIRepository.delete(id);
    }

   
    
    
}
