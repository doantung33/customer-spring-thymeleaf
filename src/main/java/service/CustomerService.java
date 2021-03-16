package service;

import model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import repo.ICustomerRepo;

import java.util.List;

public class CustomerService implements ICustomerService{
    @Autowired
    private ICustomerRepo customerRepo;

    @Override
    public List<Customer> findAll() {
        return customerRepo.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepo.findById(id);
    }

    @Override
    public void save(Customer customer) {
        customerRepo.save(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepo.delete(id);
    }
}
