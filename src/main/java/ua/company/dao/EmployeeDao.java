package ua.company.dao;

import ua.company.entity.Employee;

import java.util.List;

public interface EmployeeDao {

    public List<Employee> getPageList(int pageNumber);

    public Employee save(Employee employee);

    public void delete(int id);

    public Employee get(int id);

    public List<Employee> searchEmployeesByName(String searchText);
}
