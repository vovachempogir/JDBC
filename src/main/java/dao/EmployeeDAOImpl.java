package dao;

import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtils;
import java.util.*;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public void create(Employee employee) {
        try (Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        }
    }

    @Override
    public Employee findById(Integer id) {
        try(Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            return session.get(Employee.class, id);
        }
    }

    @Override
    public List<Employee> findAll() {
        try (Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            return session.createQuery("FROM Employee").list();
        }
    }

    @Override
    public void update(Employee employee) {
        try (Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(employee);
            transaction.commit();
        }
    }

    @Override
    public void delete(Employee employee) {
        try(Session session = HibernateSessionFactoryUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(employee);
            transaction.commit();
        }
    }
}
