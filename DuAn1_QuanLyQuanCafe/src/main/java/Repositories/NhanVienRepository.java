/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Entity.NhanVien;
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
public class NhanVienRepository implements IRepository<NhanVien>{

    @Override
    public List<NhanVien> SelectAll(int position, int pageSize, String... args) {
         List<NhanVien> nhanViens;
         try(Session session = HibernateUtil.getSessionFactory().openSession()){
            String hql = "SELECT n FROM NhanVien n";
            TypedQuery<NhanVien> query = session.createQuery(hql, NhanVien.class);
            query.setFirstResult(position);
            query.setMaxResults(pageSize);
            nhanViens = query.getResultList(); 
            return nhanViens;
        }catch (Exception e){
            e.printStackTrace();
        }
       return  null;
    }

    @Override
    public int Count(String... args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NhanVien insert(NhanVien entity) {
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
                Transaction trans = session.getTransaction();
                trans.begin();
                try {
                   session.saveOrUpdate(entity);
                }catch(Exception ex){
                    ex.printStackTrace();
                    trans.rollback();
                    entity = null;
                }
        }finally{
            return entity;
        }
    }

    @Override
    public NhanVien findById(long id) {
        NhanVien nhanvien;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            
                String hql = "SELECT n FROM NhanVien n WHERE n.id = :id";
                TypedQuery<NhanVien> query = session.createQuery(hql, NhanVien.class);
                query.setParameter("id", id);
                nhanvien = query.getSingleResult();
            } 
        return nhanvien;
    }

    @Override
    public long delete(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction trans = session.getTransaction();
            trans.begin();
            try {
                String hql = "DELETE NhanVien n WHERE n.id = :id";
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
    public List<NhanVien> findList(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
