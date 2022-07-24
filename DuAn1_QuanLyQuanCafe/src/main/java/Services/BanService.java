/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entity.Ban;
import Repositories.BanRepository;
import Repositories.IRepository;
import ViewModels.ModelBan;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class BanService implements IService<ModelBan> {

    IRepository _IRepository;
    List<ModelBan> _lstBans;

    public BanService() {
        _IRepository = new BanRepository();
    }

    @Override
    public List<ModelBan> selectAll(int position, int pageSize, String... args) {
        _lstBans = new ArrayList<>();
        List<Ban> entity = _IRepository.SelectAll(position, pageSize, args);
        for (Ban x : entity) {
            _lstBans.add(new ModelBan(x.getId(), x.getMaBan(), x.getTenBan(), GetService.getModelKhuVuc(x.getKhuVuc()), x.getTrangThai()));
        }
        return _lstBans;
    }

    @Override
    public List<ModelBan> getListFromDB() {
        return _lstBans;
    }

    @Override
    public int Count(String... args) {
        return _IRepository.Count(args);
    }

    @Override
    public ModelBan insert(ModelBan entity) {
        entity.setId(0L);
        Ban x = (Ban) _IRepository.insert(new Ban(entity.getId(), entity.getMaBan(), entity.getTenBan(), GetService.getKhuVuc(entity.getKhuVucID()), entity.getTrangThai()));
        return new ModelBan(x.getId(), x.getMaBan(), x.getTenBan(), GetService.getModelKhuVuc(x.getKhuVuc()), x.getTrangThai());
    }

    @Override
    public ModelBan update(ModelBan entity) {
        Ban x = (Ban) _IRepository.insert(new Ban(entity.getId(), entity.getMaBan(), entity.getTenBan(), GetService.getKhuVuc(entity.getKhuVucID()), entity.getTrangThai()));
        return new ModelBan(x.getId(), x.getMaBan(), x.getTenBan(), GetService.getModelKhuVuc(x.getKhuVuc()), x.getTrangThai());
    }

    @Override
    public ModelBan findById(String id) {
        Ban x = (Ban) _IRepository.findById(id);
        if (x == null) {
            return null;
        }
        return new ModelBan(x.getId(), x.getMaBan(), x.getTenBan(), GetService.getModelKhuVuc(x.getKhuVuc()), x.getTrangThai());
    }

    @Override
    public String delete(String id) {
        return _IRepository.delete(id);
    }

    @Override
    public List<ModelBan> findList(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
