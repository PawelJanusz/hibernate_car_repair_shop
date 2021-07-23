package dao;

import launch.HibernateUtil;
import model.CarServiceRequest;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class CarServiceRequestDao {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<CarServiceRequest> findByCostsBetween(int costsFrom, int costsTo){
        List<CarServiceRequest> list = new ArrayList<>();

        try (Session session = sessionFactory.openSession()){

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<CarServiceRequest> criteriaQuery = criteriaBuilder.createQuery(CarServiceRequest.class);
            Root<CarServiceRequest> root = criteriaQuery.from(CarServiceRequest.class);

            criteriaQuery.select(root)
                    .where(
                            criteriaBuilder.between(root.get("costs"), costsFrom, costsTo)
                    );
            list.addAll(session.createQuery(criteriaQuery).list());
        }catch (HibernateException ex){
            ex.printStackTrace();
        }
        return list;
    }
}
