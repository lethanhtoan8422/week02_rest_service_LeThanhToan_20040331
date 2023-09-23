package vn.edu.iuh.fit.services;

import vn.edu.iuh.fit.models.Customer;
import vn.edu.iuh.fit.repositories.CustomerRepository;
import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService() {
        customerRepository = new CustomerRepository();
    }

    public boolean createCustomer(Customer customer) {
        return customerRepository.createCustomer(customer);
    }

    public boolean updateCustomer(Customer customer) {
        return customerRepository.updateCustomer(customer);
    }

    public boolean deleteCustomer(long customerId) {
        return customerRepository.deleteCustomer(customerId);
    }

    public Customer getCustomerById(long customerId) {
        return customerRepository.getCustomerById(customerId);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }
}