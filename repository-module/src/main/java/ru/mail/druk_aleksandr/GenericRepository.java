package ru.mail.druk_aleksandr;

import java.util.List;

public interface GenericRepository<I, T> {
    void add(T entity);

    List<T> findAll();

    T findById(I id);

    void remove(T entity);
}
