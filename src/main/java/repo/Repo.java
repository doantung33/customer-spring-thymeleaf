package repo;

import java.util.List;

public interface Repo <T>{
    List<T>findAll();
    T findById(Long id);
    void save(T t);
    void delete(Long id);
}
