/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Entity.KhuVuc;
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
public class KhuVucRepository implements IRepository<KhuVuc>{

    @Override
    public List<KhuVuc> SelectAll(int position, int pageSize, String... args) {
        List<KhuVuc> entity;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "FROM KhuVuc";
            TypedQuery<KhuVuc> query = session.createQuery(hql, KhuVuc.class);
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
            String statement = "SELECT COUNT(id) FROM KhuVuc";
            TypedQuery<Long> query = session.createQuery(statement, Long.class);
            total = query.getSingleResult();
        }
        return (int) total;
    }

    @Override
    public KhuVuc insert(KhuVuc entity) {
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
    public KhuVuc findById(String id) {
        KhuVuc entity;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            try {
                String hql = "FROM KhuVuc WHERE id = :id";
                TypedQuery<KhuVuc> query = session.createQuery(hql, KhuVuc.class);
                query.setParameter("id", id);
                entity = query.getSingleResult();
            } catch (Exception e) {
                entity = null;
            }
        }
        return entity;
    }

    @Override
    public String delete(String id) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                String hql = "DELETE KhuVuc WHERE id = :id";
                Query query = session.createQuery(hql);
                query.setParameter("id", id);
                int result = query.executeUpdate();
                if (result == 0) {
                    id = null;
                }
                trans.commit();
            } catch (Exception e) {
                id = null;
            }

        }
        return id;
    }

    @Override
    public List<KhuVuc> findList(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
