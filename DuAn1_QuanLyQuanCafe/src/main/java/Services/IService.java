/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import java.util.List;

public interface IService<T> {

    public List<T> selectAll(int position, int pageSize, String... args);

    public List<T> getListFromDB();

    public int Count(String... args);

    public T insert(T entity);

    public T update(T entity);

    public T findById(long id);

    public List<T> findList(String id);

    public long delete(long id);
}
