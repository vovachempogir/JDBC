package dao;

import model.City;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtils;
import java.util.List;

public class CityDAOImpl implements CityDAO{
    @Override
    public void create(City city) {
        try (Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(city);
            transaction.commit();
        }
    }

    @Override
    public City findById(Integer id) {
        try(Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            return session.get(City.class, id);
        }
    }

    @Override
    public List<City> findAll() {
        try (Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            return session.createQuery("FROM City ").list();
        }
    }

    @Override
    public void update(City city) {
        try (Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(city);
            transaction.commit();
        }
    }

    @Override
    public void deleteById(Integer id) {
        try(Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(session.get(City.class, id));
            transaction.commit();
        }
    }
}
