/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entity.NhomSanPham;
import Repositories.IRepository;
import Repositories.NhomSanPhamRepository;
import ViewModels.ModelNhomSanPham;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class NhomSanPhamService implements IService<ModelNhomSanPham>  {

    IRepository _IRepository;
    List<ModelNhomSanPham> _lstNhomSanPhams;

    public NhomSanPhamService() {
        _IRepository = new NhomSanPhamRepository();
    }
    
    
    @Override
    public List<ModelNhomSanPham> selectAll(int position, int pageSize, String... args) {
        _lstNhomSanPhams = new ArrayList<>();
        List<NhomSanPham> entity = _IRepository.SelectAll(position, pageSize, args);
        for (NhomSanPham x : entity) {
            _lstNhomSanPhams.add(new ModelNhomSanPham(x.getId(), x.getMaNhomSanPham(), x.getTenNhomSanPham(), GetService.getModelImage(x.getImageID()), x.getTrangthai()));
        }
        return _lstNhomSanPhams;
    }

    @Override
    public List<ModelNhomSanPham> getListFromDB() {
        return _lstNhomSanPhams;
    }

    @Override
    public int Count(String... args) {
        return _IRepository.Count(args);
    }

    @Override
    public ModelNhomSanPham insert(ModelNhomSanPham entity) {
        entity.setId(0L);
        NhomSanPham x = (NhomSanPham) _IRepository.insert(new NhomSanPham(entity.getId(), entity.getMaNhomSanPham(), entity.getTenNhomSanPham(), GetService.getImage(entity.getImageID()), entity.getTrangthai()));
        return new ModelNhomSanPham(x.getId(), x.getMaNhomSanPham(), x.getTenNhomSanPham(), GetService.getModelImage(x.getImageID()), x.getTrangthai());
    }

    @Override
    public ModelNhomSanPham update(ModelNhomSanPham entity) {
        NhomSanPham x = (NhomSanPham) _IRepository.insert(new NhomSanPham(entity.getId(), entity.getMaNhomSanPham(), entity.getTenNhomSanPham(), GetService.getImage(entity.getImageID()), entity.getTrangthai()));
        return new ModelNhomSanPham(x.getId(), x.getMaNhomSanPham(), x.getTenNhomSanPham(), GetService.getModelImage(x.getImageID()), x.getTrangthai());
    }

    @Override
    public ModelNhomSanPham findById(long id) {
        NhomSanPham x = (NhomSanPham) _IRepository.findById(id);
        if (x == null) {
            return null;
        }
        return new ModelNhomSanPham(x.getId(), x.getMaNhomSanPham(), x.getTenNhomSanPham(), GetService.getModelImage(x.getImageID()), x.getTrangthai());
    }

    @Override
    public long delete(long id) {
        return _IRepository.delete(id);
    }

    @Override
    public List<ModelNhomSanPham> findList(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
