package ua.company.service;

import ua.company.entity.Department;

import java.util.List;

public interface DepartmentService {

    public List<Department> getAll();

    public Department get(int id);
}
