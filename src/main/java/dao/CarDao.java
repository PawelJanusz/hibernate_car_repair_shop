package dao;

import launch.HibernateUtil;
import model.Car;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
}
