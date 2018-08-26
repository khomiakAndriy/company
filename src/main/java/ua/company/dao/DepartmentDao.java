package ua.company.dao;

import ua.company.entity.Department;

import java.util.List;

public interface DepartmentDao {

    public List<Department> getAll();

    public Department get(int id);
}
