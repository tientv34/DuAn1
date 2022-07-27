/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Entity.KhachHang;
import Utilities.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author TranTien
 */
public class KhachHangRepository implements IRepository<KhachHang>{

    @Override
    public List<KhachHang> SelectAll(int position, int pageSize, String... args) {
        List<KhachHang> khachHangs;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM KhachHang";
            TypedQuery<KhachHang> query = session.createQuery(hql, KhachHang.class);
            query.setFirstResult(position);
            query.setMaxResults(pageSize);
            khachHangs = query.getResultList();
            return khachHangs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int Count(String... args) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public KhachHang insert(KhachHang entity) {
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
    public KhachHang findById(long id) {
        KhachHang khachHang;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                String hql = "FROM SanPham WHERE id = :id";
                TypedQuery<KhachHang> query = session.createQuery(hql, KhachHang.class);
                query.setParameter("id", id);
                khachHang = query.getSingleResult();
            } catch (Exception e) {
                khachHang = null;
            }
        }
        return khachHang;
    }

    @Override
    public List<KhachHang> findList(String text) {
        List<KhachHang> listKhachHang;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                String hql = "SELECT p FROM KhachHang p Where p.tenKhachHang LIKE %?1%" + "OR p.dienThoai LIKE %?1%";
                TypedQuery<KhachHang> query = session.createQuery(hql, KhachHang.class);
                query.setParameter("text", text);
                listKhachHang = query.getResultList();
            } catch (Exception e) {
                listKhachHang = null;
                
            }
        }
        return listKhachHang;
    }

    @Override
    public long delete(long id) {
         try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                String hql = "DELETE KhachHang p WHERE p.id = :id";
                Query query = session.createQuery(hql);
                query.setParameter("id", id);
                int result = query.executeUpdate();
                if (result == 0) {
                    id = 0;
                }
                trans.commit();
            } catch (Exception e) {
                id = -1;
            }
           
        }
        return id;
    }
    
}
