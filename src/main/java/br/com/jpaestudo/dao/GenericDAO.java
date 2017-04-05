package br.com.jpaestudo.dao;

import br.com.jpaestudo.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GenericDAO<T extends Serializable> {

    private Class<T> aClass;

    protected GenericDAO(Class<T> aClass){
        this.aClass = aClass;
    }
    protected EntityManager getEntityManager(){
        return JPAUtil.getInstance().getEntityManager();
    }

    public long count(){

        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createQuery("select count(c) from " + aClass.getSimpleName() + " c ");
        Long count = (Long) query.getSingleResult();
        manager.getTransaction().commit();
        manager.close();
        return count;
    }


    public T findOne(String jpql, HashMap<String, Object> params){

        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createQuery(jpql);


        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        T entity = (T) query.getSingleResult();
        manager.getTransaction().commit();
        manager.close();
        return entity;
    }

    @SuppressWarnings("unchecked")
    public List<T> find(String jpql, HashMap<String, Object> params){

        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createQuery(jpql);

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        List<T> entities = query.getResultList();
        manager.getTransaction().commit();
        manager.close();
        return entities;
    }

    @SuppressWarnings("unchecked")
    public List<T> findAll(){
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        Query query = manager.createQuery("from " + aClass.getSimpleName());
        List<T> entities = query.getResultList();
        manager.getTransaction().commit();
        manager.close();
        return entities;
    }

    public T findById(Long id){
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        T entity = (T)manager.find(aClass, id);
        manager.getTransaction().commit();
        manager.close();
        return entity;
    }

    public void save(T entity){
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        manager.persist(entity);
        manager.getTransaction().commit();
        manager.close();
    }

    public void update(T entity){
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        manager.merge(entity);
        manager.getTransaction().commit();
        manager.close();
    }


    public void delete(Long id){
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        manager.remove(manager.getReference(aClass, id));
        manager.getTransaction().commit();
        manager.close();
    }

    public void delete(T entity){
        EntityManager manager = getEntityManager();
        manager.getTransaction().begin();
        manager.remove(manager.merge(entity));
        manager.getTransaction().commit();
        manager.close();
    }

}
