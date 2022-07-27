/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Entity.SanPham;
import Utilities.HibernateUtil;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Admin
 */
public class SanPhamRepository implements IRepository<SanPham> {

    @Override
    public List<SanPham> SelectAll(int position, int pageSize, String... args) {
        List<SanPham> entity;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM SanPham";
            TypedQuery<SanPham> query = session.createQuery(hql, SanPham.class);
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
        long total = 0;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String statement = "SELECT COUNT(id) FROM SanPham";
            TypedQuery<Long> query = session.createQuery(statement, Long.class);
            total = query.getSingleResult();
        }
        return (int) total;
    }

    @Override
    public SanPham insert(SanPham entity) {
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
    public SanPham findById(long id) {
        SanPham entity;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                String hql = "FROM SanPham WHERE id = :id";
                TypedQuery<SanPham> query = session.createQuery(hql, SanPham.class);
                query.setParameter("id", id);
                entity = query.getSingleResult();
            } catch (Exception e) {
                entity = null;
            }
        }
        return entity;
    }

    @Override
    public long delete(long id) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                String hql = "DELETE SanPham WHERE id = :id";
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

    @Override
    public List<SanPham> findList(String id) {
        List<SanPham> entity;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                String hql = "FROM SanPham WHERE NHOMSANPHAMID = :id";
                TypedQuery<SanPham> query = session.createQuery(hql, SanPham.class);
                query.setParameter("id", id);
                entity = query.getResultList();
            } catch (Exception e) {
                entity = null;
            }
        }
        return entity;
    }

}
