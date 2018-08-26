package ua.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.company.dao.EmployeeDao;
import ua.company.entity.Employee;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> getPageList(int pageNumber) {
        return employeeDao.getPageList(pageNumber);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        return employeeDao.save(employee);
    }

    @Override
    @Transactional
    public void delete(int id) {
        employeeDao.delete(id);
    }

    @Override
    public Employee get(int id) {
        return employeeDao.get(id);
    }

    @Override
    public List<Employee> searchEmployeesByName(String searchText) {
        return employeeDao.searchEmployeesByName(searchText);
    }
}
