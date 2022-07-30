/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import Entity.KhuVuc;
import Repositories.IRepository;
import Repositories.KhuVucRepository;
import ViewModels.ModelKhuVuc;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class KhuVucService implements IService<ModelKhuVuc> {

    IRepository _IRepository;
    List<ModelKhuVuc> _lsKhuVucs;

    public KhuVucService() {
        _IRepository = new KhuVucRepository();
    }

    @Override
    public List<ModelKhuVuc> selectAll(int position, int pageSize, String... args) {
        _lsKhuVucs = new ArrayList<>();
        List<KhuVuc> entity = _IRepository.SelectAll(position, pageSize, args);
        for (KhuVuc x : entity) {
            _lsKhuVucs.add(new ModelKhuVuc(x.getId(), x.getMaKhuVuc(), x.getTenKhuVuc(), GetService.getModelImage(x.getImageID()), x.getTab()));
        }
        return _lsKhuVucs;
    }

    @Override
    public List<ModelKhuVuc> getListFromDB() {
        return _lsKhuVucs;
    }

    @Override
    public int Count(String... args) {
        return _IRepository.Count(args);
    }

    @Override
    public ModelKhuVuc insert(ModelKhuVuc entity) {
        entity.setId(0L);
        KhuVuc x = (KhuVuc) _IRepository.insert(new KhuVuc(entity.getId(), entity.getMaKhuVuc(), entity.getTenKhuVuc(), GetService.getImage(entity.getImageID()), entity.getTab()));
        return new ModelKhuVuc(x.getId(), x.getMaKhuVuc(), x.getTenKhuVuc(), GetService.getModelImage(x.getImageID()), x.getTab());
    }

    @Override
    public ModelKhuVuc update(ModelKhuVuc entity) {
        KhuVuc x = (KhuVuc) _IRepository.insert(new KhuVuc(entity.getId(), entity.getMaKhuVuc(), entity.getTenKhuVuc(), GetService.getImage(entity.getImageID()), entity.getTab()));
        return new ModelKhuVuc(x.getId(), x.getMaKhuVuc(), x.getTenKhuVuc(), GetService.getModelImage(x.getImageID()), x.getTab());
    }

    @Override
    public ModelKhuVuc findById(long id) {
        KhuVuc x = (KhuVuc) _IRepository.findById(id);
        if (x == null) {
            return null;
        }
        return new ModelKhuVuc(x.getId(), x.getMaKhuVuc(), x.getTenKhuVuc(), GetService.getModelImage(x.getImageID()), x.getTab());
    }

    @Override
    public long delete(long id) {
        return _IRepository.delete(id);
    }

    @Override
    public List<ModelKhuVuc> findList(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
