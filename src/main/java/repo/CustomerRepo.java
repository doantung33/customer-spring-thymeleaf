package repo;

import model.Customer;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class CustomerRepo implements ICustomerRepo{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Customer> findAll() {
        TypedQuery<Customer>typedQuery=entityManager.createQuery("select c from Customer c",Customer.class);
        return typedQuery.getResultList();
    }

    @Override
    public Customer findById(Long id) {
        TypedQuery<Customer>typedQuery=entityManager.createQuery("select c from Customer c where c.id=:id",Customer.class);
        typedQuery.setParameter("id",id);
        try {
            return typedQuery.getSingleResult();
        }catch (NoResultException e){
            return null;
        }

    }

    @Override
    public void save(Customer customer) {
        if (customer.getId()!=null){
            entityManager.merge(customer);
        }else {
            entityManager.persist(customer);
        }
    }

    @Override
    public void delete(Long id) {
        Customer customer=findById(id);
        if (customer!=null){
            entityManager.remove(customer);
        }
    }
}
