package dao;

import launch.HibernateUtil;
import model.Brand;
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


    public List<Car> findByBrandName(Brand brand){
        List<Car> list = new ArrayList<>();

        try (Session session = sessionFactory.openSession()){
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
            Root<Car> carRoot = criteriaQuery.from(Car.class);

            criteriaQuery.select(carRoot)
                    .where(
                            criteriaBuilder.equal(carRoot.get("brand"), brand)
                    );
            list.addAll(session.createQuery(criteriaQuery).list());
        }catch (HibernateException ex){
            ex.printStackTrace();
        }
        return list;
    }

    public List<Car> findByProductionYearBetween(int productionYearFrom, int productionYearTo){
        List<Car> list = new ArrayList<>();

        try (Session session = sessionFactory.openSession()){

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
            Root<Car> carRoot = criteriaQuery.from(Car.class);

            criteriaQuery.select(carRoot)
                    .where(
                            criteriaBuilder.between(carRoot.get("productionYear"), productionYearFrom, productionYearTo)
                    )
                    .orderBy(
                            criteriaBuilder.desc(carRoot.get("productionYear"))
                    );
            list.addAll(session.createQuery(criteriaQuery).list());
        }catch (HibernateException ex){
            ex.printStackTrace();
        }
        return list;
    }
}
