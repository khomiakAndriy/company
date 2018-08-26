package ua.company.service;

import ua.company.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getPageList(int pageNumber);

    public Employee save(Employee employee);

    public void delete(int id);

    public Employee get(int id);

    public List<Employee> searchEmployeesByName(String searchText);
}
