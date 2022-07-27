/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Entity.HoaDon;
import Utilities.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Admin
 */
public class HoaDonRepository implements IRepository<HoaDon> {

    @Override
    public List<HoaDon> SelectAll(int position, int pageSize, String... args) {
         List<HoaDon> entity;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM HoaDon";
            TypedQuery<HoaDon> query = session.createQuery(hql, HoaDon.class);
            query.setFirstResult(position);
            query.setMaxResults(pageSize);
            entity = query.getResultList();
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int Count(String... args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HoaDon insert(HoaDon entity) {
         try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                session.saveOrUpdate(entity);
                trans.commit();
            } catch (Exception e) {
                e.printStackTrace();
                trans.rollback();
                entity = null;
            }
        } finally {
            return entity;
        }
    }

    @Override
    public HoaDon findById(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<HoaDon> findList(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long delete(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
