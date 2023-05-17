package dao;

import model.Employee;
import java.sql.*;
import java.util.*;
;

public class EmployeeDAOImpl implements EmployeeDAO {

    private final Connection connection;

    public EmployeeDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO employee(first_name, " +
                "last_name," +
                "gender, " +
                "age," +
                "city_id ) " +
                "VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, employee.getFirst_name());
            preparedStatement.setString(2, employee.getLast_name());
            preparedStatement.setString(3, employee.getGender());
            preparedStatement.setInt(4, employee.getAge());
            preparedStatement.setInt(5,employee.getCity_id());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee findById(Integer id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT * FROM employee INNER JOIN city ON employee.city_id = city.city_id AND id = (?)")) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Employee.create(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> findAll() {
        try (PreparedStatement preparedStatement = connection.prepareStatement
                ("SELECT * FROM employee INNER JOIN city ON employee.city_id = city.city_id")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Employee> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(Employee.create(resultSet));
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Integer id, String first_name, String last_name, String gender, Integer age, Integer city_id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(
        "UPDATE employee SET first_name = (?), " +
                "last_name = (?), " +
                "gender = (?), " +
                "age = (?), " +
                "city_id = (?) " +
                "WHERE id = (?)")) {
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, gender);
            preparedStatement.setInt(4, age);
            preparedStatement.setInt(5, city_id);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        try(PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE id = ?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
