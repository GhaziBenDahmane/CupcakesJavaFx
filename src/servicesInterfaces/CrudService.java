package servicesInterfaces;

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
}
