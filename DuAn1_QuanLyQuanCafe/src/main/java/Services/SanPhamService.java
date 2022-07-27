/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entity.SanPham;
import Repositories.IRepository;
import Repositories.SanPhamRepository;
import ViewModels.ModelSanPham;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class SanPhamService implements IService<ModelSanPham> {

    IRepository _IRepository;
    List<ModelSanPham> _lstSanPhams;

    public SanPhamService() {
        _IRepository = new SanPhamRepository();
    }
    

    @Override
    public List<ModelSanPham> selectAll(int position, int pageSize, String... args) {
        _lstSanPhams = new ArrayList<>();
        List<SanPham> entity = _IRepository.SelectAll(position, pageSize, args);
        for (SanPham x : entity) {
            _lstSanPhams.add(new ModelSanPham(x.getId(), x.getMaSanPham(), x.getTenSanPham(), x.getNgayTao(), GetService.getModelDonViTinh(x.getDVT()),
                    x.getGiaNhap(), x.getDonGia(), GetService.getModelNhomSanPham(x.getNhomSanPhamID()), GetService.getModelImage(x.getImageID()), x.getTrangthai()));
        }
        return _lstSanPhams;
    }

    @Override
    public List<ModelSanPham> getListFromDB() {
        return _lstSanPhams;
    }

    @Override
    public int Count(String... args) {
        return _IRepository.Count(args);
    }

    @Override
    public ModelSanPham insert(ModelSanPham entity) {
        entity.setId(0L);
        SanPham x = (SanPham) _IRepository.insert(new SanPham(entity.getId(), entity.getMaSanPham(), entity.getTenSanPham(),
                entity.getNgayTao(), GetService.getDonViTinh(entity.getDVT()), entity.getGiaNhap(), entity.getDonGia(), GetService.getNhomSanPham(entity.getNhomSanPhamID()),
                GetService.getImage(entity.getImageID()), entity.getTrangthai()));
        return new ModelSanPham(x.getId(), x.getMaSanPham(), x.getTenSanPham(), x.getNgayTao(), GetService.getModelDonViTinh(x.getDVT()),
                x.getGiaNhap(), x.getDonGia(), GetService.getModelNhomSanPham(x.getNhomSanPhamID()), GetService.getModelImage(x.getImageID()), x.getTrangthai());
    }

    @Override
    public ModelSanPham update(ModelSanPham entity) {
        SanPham x = (SanPham) _IRepository.insert(new SanPham(entity.getId(), entity.getMaSanPham(), entity.getTenSanPham(),
                entity.getNgayTao(), GetService.getDonViTinh(entity.getDVT()), entity.getGiaNhap(), entity.getDonGia(), GetService.getNhomSanPham(entity.getNhomSanPhamID()),
                GetService.getImage(entity.getImageID()), entity.getTrangthai()));
        return new ModelSanPham(x.getId(), x.getMaSanPham(), x.getTenSanPham(), x.getNgayTao(), GetService.getModelDonViTinh(x.getDVT()),
                x.getGiaNhap(), x.getDonGia(), GetService.getModelNhomSanPham(x.getNhomSanPhamID()), GetService.getModelImage(x.getImageID()), x.getTrangthai());
    }

    @Override
    public ModelSanPham findById(long id) {
        SanPham x = (SanPham) _IRepository.findById(id);
        if (x == null) {
            return null;
        }
        return new ModelSanPham(x.getId(), x.getMaSanPham(), x.getTenSanPham(), x.getNgayTao(), GetService.getModelDonViTinh(x.getDVT()),
                x.getGiaNhap(), x.getDonGia(), GetService.getModelNhomSanPham(x.getNhomSanPhamID()), GetService.getModelImage(x.getImageID()), x.getTrangthai());
    }

    @Override
    public long delete(long id) {
        return _IRepository.delete(id);
    }

    @Override
    public List<ModelSanPham> findList(String id) {
        _lstSanPhams = new ArrayList<>();
        List<SanPham> entity = _IRepository.findList(id);
         if (entity == null) {
            return null;
        }
        for (SanPham x : entity) {
            _lstSanPhams.add(new ModelSanPham(x.getId(), x.getMaSanPham(), x.getTenSanPham(), x.getNgayTao(), GetService.getModelDonViTinh(x.getDVT()),
                    x.getGiaNhap(), x.getDonGia(), GetService.getModelNhomSanPham(x.getNhomSanPhamID()), GetService.getModelImage(x.getImageID()), x.getTrangthai()));
        }
        return _lstSanPhams;
    }

}
