package dao;

import launch.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EntityDao<T> {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Transaction transaction = null;

    public void saveOrUpdate(T entity){
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        }catch (HibernateException ex){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    public void delete(T entity){
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        }catch (HibernateException ex){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    public Optional<T> findById(Class<T> classType, Long id){
        try (Session session = sessionFactory.openSession()){
            return Optional.ofNullable(session.get(classType, id));
        }catch (HibernateException ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    public List<T> findAll(Class<T> classType){
     List<T> list = new ArrayList<>();

     try (Session session = sessionFactory.openSession()){

         CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
         CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(classType);
         Root<T> root = criteriaQuery.from(classType);

         criteriaQuery.select(root);

         list.addAll(session.createQuery(criteriaQuery).list());
     }catch (HibernateException ex){
         ex.printStackTrace();
     }
     return list;
    }
}
