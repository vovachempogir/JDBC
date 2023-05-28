import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import model.Employee;

import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();

//        Создание объекта
        Employee employee1 = new Employee(null,"first_name","last_name","gender",22,1);
        employeeDAO.create(employee1);

//        Поиск объекта
        System.out.println(employeeDAO.findById(10));

//        Поиск всех объектов
        employeeDAO.findAll().forEach(System.out::println);

        Employee employee2 = employeeDAO.findById(10);
//        Изменение объекта
        employee2.setFirst_name("first_name2");
        employee2.setLast_name("last_name2");
        employee2.setGender("man");
        employee2.setAge(32);
        employee2.setCity_id(2);
        employeeDAO.update(employee2);
        System.out.println(employeeDAO.findById(10));

//        Удаление объекта
        employeeDAO.delete(employee2);
    }
}
