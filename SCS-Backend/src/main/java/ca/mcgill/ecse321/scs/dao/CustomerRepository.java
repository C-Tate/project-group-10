package ca.mcgill.ecse321.scs.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.scs.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, String> {

    public Customer findCustomerByEmail(String email);

    public Customer deleteCustomerByEmail(String email);

}
