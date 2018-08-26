package ua.company.dao;

import org.springframework.stereotype.Repository;
import ua.company.entity.Department;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Department> getAll() {
        return em.createNamedQuery(Department.GET_ALL_SORTED, Department.class).getResultList();
    }

    @Override
    public Department get(int id) {
        return em.find(Department.class, id);
    }
}
