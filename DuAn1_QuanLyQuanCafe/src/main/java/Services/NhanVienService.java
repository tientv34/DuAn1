/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entity.NhanVien;
import Repositories.IRepository;
import Repositories.NhanVienRepository;
import ViewModels.ModelNhanVien;
import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author TranTien
 */
public class NhanVienService implements IService<ModelNhanVien>{
      IRepository _iNhanVienRepository;
      List<ModelNhanVien> _lstModelNhanViens;

    public NhanVienService() {
        _iNhanVienRepository = new NhanVienRepository();
        _lstModelNhanViens = new ArrayList<>();
    }

    @Override
    public List<ModelNhanVien> selectAll(int position, int pageSize, String... args) {
         _lstModelNhanViens = new ArrayList<>();
        
        List<NhanVien> nhanvien = _iNhanVienRepository.SelectAll(position, pageSize, args);
        
        for (NhanVien x : nhanvien) {
            _lstModelNhanViens.add(new ModelNhanVien(
                    x.getId(), 
                    x.getMaNhanVien(), 
                    x.getTenNhanVien(), 
                    x.getDiaChi(), 
                    x.getEmail(),
                    x.getSoDienThoai(), 
                    x.getGioiTinh(), 
                    x.getNgaySinh(), 
                    x.getPassWord(),
                    x.getTrangthai()
            ));
        }
        return _lstModelNhanViens;
    }

    @Override
    public List<ModelNhanVien> getListFromDB() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int Count(String... args) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ModelNhanVien insert(ModelNhanVien entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ModelNhanVien update(ModelNhanVien entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ModelNhanVien findById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<ModelNhanVien> findList(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
   
     
}
