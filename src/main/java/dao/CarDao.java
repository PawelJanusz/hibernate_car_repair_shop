package dao;

import launch.HibernateUtil;
import model.Car;
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

public class CarDao {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Transaction transaction = null;

    public void saveOrUpdate(Car car){
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.saveOrUpdate(car);
            transaction.commit();
        }catch (HibernateException ex){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    public void delete(Car car){
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.delete(car);
            transaction.commit();
        }catch (HibernateException ex){
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    public Optional<Car> findById(Long id) {
        try (Session session = sessionFactory.openSession()){
            return Optional.ofNullable(session.get(Car.class, id));
        }catch (HibernateException ex){
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Car> getAll(){
        List<Car> list = new ArrayList<>();

        try (Session session = sessionFactory.openSession()){

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
            Root<Car> carRoot = criteriaQuery.from(Car.class);

            criteriaQuery.select(carRoot);

            list.addAll(session.createQuery(criteriaQuery).list());
        }catch (HibernateException ex){
            ex.printStackTrace();
        }
        return list;
    }
}
