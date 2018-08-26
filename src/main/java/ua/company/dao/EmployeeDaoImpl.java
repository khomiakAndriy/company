package ua.company.dao;

import org.springframework.stereotype.Repository;
import ua.company.entity.Employee;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Employee> getPageList(int pageNumber) {
        Query query = em.createQuery("select e from Employee e order by e.id asc");
        query.setFirstResult(1+pageNumber*10);
        query.setMaxResults(10+pageNumber*10);
        return query.getResultList();
    }

    @Override
    public Employee save(Employee employee) {
        if (employee.isNew()){
            em.persist(employee);
            return employee;
        } else {
            return em.merge(employee);
        }
    }

    @Override
    public void delete(int id) {
        em.remove(em.getReference(Employee.class, id));
    }

    @Override
    public Employee get(int id) {
        return em.find(Employee.class, id);
    }

    @Override
    public List<Employee> searchEmployeesByName(String searchText) {
        Query query = em.createQuery("select e from Employee e where e.name like :searchText order by e.id asc");
        query.setParameter("searchText", searchText+"%");
        return query.getResultList();
    }

}
