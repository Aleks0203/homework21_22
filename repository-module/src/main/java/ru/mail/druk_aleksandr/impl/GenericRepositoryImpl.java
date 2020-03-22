package ru.mail.druk_aleksandr.impl;

import ru.mail.druk_aleksandr.GenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;


public abstract class GenericRepositoryImpl<I, T> implements GenericRepository<I, T> {
    protected Class<T> entityClass;
    @PersistenceContext
    private EntityManager entityManager;

    public GenericRepositoryImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[1];
    }

    @Override
    public void add(T entity) {
        entityManager.persist(entity);
    }

    @Override
    public List<T> findAll() {
        String query = "from " + entityClass.getName() + " c";
        Query q = entityManager.createQuery(query);
        return q.getResultList();
    }

    @Override
    public T findById(I id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public void remove(T entity) {
        entityManager.remove(entity);
    }
}
