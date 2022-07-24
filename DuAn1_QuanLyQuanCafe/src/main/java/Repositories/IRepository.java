/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface IRepository<T> {

    public List<T> SelectAll(int position, int pageSize, String... args);

    public int Count(String... args);

    public T insert(T entity);

    public T findById(String id);
    
    public List<T> findList(String id);

    public String delete(String id);
}
