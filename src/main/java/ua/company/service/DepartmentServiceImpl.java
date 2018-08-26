package ua.company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.company.dao.DepartmentDao;
import ua.company.entity.Department;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DepartmentServiceImpl implements DepartmentService{

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public List<Department> getAll() {
        return departmentDao.getAll();
    }

    @Override
    public Department get(int id) {
        return departmentDao.get(id);
    }
}
