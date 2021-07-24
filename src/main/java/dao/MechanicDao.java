package dao;

import launch.HibernateUtil;
import model.Mechanic;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class MechanicDao {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<Mechanic> findByMechanicLastName(String lastName){
        List<Mechanic> list = new ArrayList<>();

        try (Session session = sessionFactory.openSession()){

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Mechanic> criteriaQuery = criteriaBuilder.createQuery(Mechanic.class);
            Root<Mechanic> root = criteriaQuery.from(Mechanic.class);

            criteriaQuery.select(root)
                    .where(
                            criteriaBuilder.equal(root.get("lastName"), lastName)
                    );
            list.addAll(session.createQuery(criteriaQuery).list());
         }catch (HibernateException ex){
            ex.printStackTrace();
        }
        return list;
    }
}
