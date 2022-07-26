/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entity.HoaDon;
import Repositories.HoaDonRepository;
import Repositories.IRepository;
import ViewModels.ModelHoaDon;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class HoaDonService implements IService<ModelHoaDon>{

    IRepository __IRepository;
    List<ModelHoaDon> _lstHoaDon;

    public HoaDonService() {
        __IRepository = new HoaDonRepository();
    }
    
    
    @Override
    public List<ModelHoaDon> selectAll(int position, int pageSize, String... args) {
         _lstHoaDon = new ArrayList<>();
        List<HoaDon> entity = __IRepository.SelectAll(position, pageSize, args);
        for (HoaDon x : entity) {
            _lstHoaDon.add(new ModelHoaDon(x.getId(), GetService.getModelBan(x.getBan()), x.getNgayTao(), x.getNgayThanhToan(),GetService.getModelKhachHang(x.getKhachHang()),x.getTongCong(),x.getKhachDua(),x.getTraLai(),x.getTrangThai(),x.getGhiChu()));
        }
        return _lstHoaDon;
    }

    @Override
    public List<ModelHoaDon> getListFromDB() {
        return _lstHoaDon;
    }

    @Override
    public int Count(String... args) {
        return __IRepository.Count(args);
    }

    @Override
    public ModelHoaDon insert(ModelHoaDon entity) {
        entity.setId(0L);
        HoaDon x = (HoaDon) __IRepository.insert(new HoaDon(entity.getId(), GetService.getBan(entity.getBan()), entity.getNgayTao(), entity.getNgayThanhToan(), GetService.getKhachHang(entity.getKhachHang()), entity.getTongCong(),entity.getKhachDua(),entity.getTraLai(),entity.getTrangThai(),entity.getGhiChu()));
        return new ModelHoaDon(x.getId(), GetService.getModelBan(x.getBan()), x.getNgayTao(), x.getNgayThanhToan(),GetService.getModelKhachHang(x.getKhachHang()),x.getTongCong(),x.getKhachDua(),x.getTraLai(),x.getTrangThai(),x.getGhiChu());
    }

    @Override
    public ModelHoaDon update(ModelHoaDon entity) {
        HoaDon x = (HoaDon) __IRepository.insert(new HoaDon(entity.getId(), GetService.getBan(entity.getBan()), entity.getNgayTao(), entity.getNgayThanhToan(), GetService.getKhachHang(entity.getKhachHang()), entity.getTongCong(),entity.getKhachDua(),entity.getTraLai(),entity.getTrangThai(),entity.getGhiChu()));
        return new ModelHoaDon(x.getId(), GetService.getModelBan(x.getBan()), x.getNgayTao(), x.getNgayThanhToan(),GetService.getModelKhachHang(x.getKhachHang()),x.getTongCong(),x.getKhachDua(),x.getTraLai(),x.getTrangThai(),x.getGhiChu());
    }

    @Override
    public ModelHoaDon findById(String id) {
        HoaDon x = (HoaDon) __IRepository.findById(id);
        if (x == null) {
            return null;
        }
        return new ModelHoaDon(x.getId(), GetService.getModelBan(x.getBan()), x.getNgayTao(), x.getNgayThanhToan(),GetService.getModelKhachHang(x.getKhachHang()),x.getTongCong(),x.getKhachDua(),x.getTraLai(),x.getTrangThai(),x.getGhiChu());
    }

    @Override
    public List<ModelHoaDon> findList(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
