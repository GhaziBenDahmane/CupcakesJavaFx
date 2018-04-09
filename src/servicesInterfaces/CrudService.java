package servicesInterfaces;

import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author ding
 * @param <T>
 */
public interface CrudService<T> {

    List<T> getAll();

    T get(int id);

    List<T> getBy(String param, String val);

    void add(T a);

    void update(T a);

    void delete(T a);

    void deleteId(int a);

    T fromRs(ResultSet rs);
}
