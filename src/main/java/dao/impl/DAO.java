package dao.impl;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public abstract class DAO<T> {

    @PersistenceContext
    private EntityManager entityManager;

    protected EntityManager getEntityManager (){
        return this.entityManager;
    }

    public T add(T entity) {
       return this.entityManager.merge(entity);
    }

    public T getById(int id, Class<T> clazz) {
        return this.entityManager.find(clazz, id);
    }

    public void remove(T entity) {
       this.entityManager.remove(entity);
    }

    public List<T> getAll(Class<T> clazz) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery< T > criteria = cb.createQuery(clazz);
        criteria.select(criteria.from(clazz));
        return entityManager.createQuery( criteria ).getResultList();
    }
}
