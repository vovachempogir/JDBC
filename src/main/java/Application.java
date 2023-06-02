import dao.CityDAO;
import dao.CityDAOImpl;
import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import model.City;
import model.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        CityDAO cityDAO = new CityDAOImpl();

//        Создание объекта
        Employee employee1 = new Employee();
        employee1.setFirst_name("Иван");
        employee1.setLast_name("Иванов");
        employee1.setGender("муж");
        employee1.setAge(34);

        Employee employee2 = new Employee();
        employee2.setFirst_name("Петр");
        employee2.setLast_name("Петров");
        employee2.setGender("муж");
        employee2.setAge(23);

        City city1 = new City();
        city1.setCity_name("Орел");
        city1.setEmployees(new ArrayList<Employee>(Arrays.asList(employee1, employee2)));
        cityDAO.create(city1);
        System.out.println(city1);

        City city = cityDAO.findById(7);

        employee1.setCity(city);
        employee2.setCity(city);

        employeeDAO.update(employee1);
        employeeDAO.update(employee2);

        cityDAO.deleteById(7);



    }
}
