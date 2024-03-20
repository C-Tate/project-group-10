package ca.mcgill.ecse321.scs.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.scs.dao.CustomerRepository;
import ca.mcgill.ecse321.scs.dao.InstructorRepository;
import ca.mcgill.ecse321.scs.dao.OwnerRepository;
import ca.mcgill.ecse321.scs.exception.SCSException;
import ca.mcgill.ecse321.scs.model.Customer;
import jakarta.transaction.Transactional;

@Transactional
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    // above commented are used once implementation are done

    String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                        "[a-zA-Z0-9_+&*-]+)*@" + 
                        "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                        "A-Z]{2,7}$";

    public List<Customer> getAllCustomer() {
        return ServiceUtils.toList(customerRepository.findAll());
    }

    public Customer getCustomerById(Integer id) {
        Customer customer = customerRepository.findCustomerByAccountId(id);
        if(customer == null) {
            System.out.println("!!!Customer not found." + id);
            throw new SCSException(HttpStatus.NOT_FOUND, ("Customer not found."));
        }
        return customer;
    }

    public Customer getCustomerByEmail(String customerEmail) {
        Customer customer = customerRepository.findCustomerByEmail(customerEmail);
        if (customer == null)
            throw new SCSException(HttpStatus.NOT_FOUND, "Customer not found.");
        return customer;
    }

    public Customer createCustomer(String name, String email, String password) {
        if (email == null || email.trim().length() == 0) {
            throw new SCSException(HttpStatus.BAD_REQUEST, "Email cannot be empty.");
        } else if (!Pattern.compile(emailRegex).matcher(email).matches()) {
            throw new SCSException(HttpStatus.BAD_REQUEST, "Email is not valid.");
        } else if (password == null || password.trim().length() == 0) {
            throw new SCSException(HttpStatus.BAD_REQUEST, "Password cannot be empty.");
        } else if (name == null || name.trim().length() == 0) {
            throw new SCSException(HttpStatus.BAD_REQUEST, "Name cannot be empty.");
        }

        if (instructorRepository.findInstructorByEmail(email) != null || ownerRepository.findOwnerByEmail(email) != null
                || customerRepository.findCustomerByEmail(email) != null) {
            throw new SCSException(HttpStatus.BAD_REQUEST, "An account with this email already exists.");
        }

        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setName(name);
        customer.setCreationDate(Date.valueOf(LocalDate.now()));
        customerRepository.save(customer);

        return customer;
    }

    public Customer updateCustomerById(Integer id, String name, String email, String password) {
        Customer customer = customerRepository.findCustomerByAccountId(id);
        if (customer == null) {
            throw new SCSException(HttpStatus.NOT_FOUND, "Customer not found.");
        }

        // email in use by another account
        Customer customerByEmail = customerRepository.findCustomerByEmail(email);
        if ((customerByEmail != null && customerByEmail.getAccountId() != id) || ownerRepository.findOwnerByEmail(email) != null
                || instructorRepository.findInstructorByEmail(email) != null) {
            throw new SCSException(HttpStatus.BAD_REQUEST, "An account with this email already exists.");
        }

        if (email != null && email.trim().length() > 0) {
            if (!Pattern.compile(emailRegex).matcher(email).matches()) {
                throw new SCSException(HttpStatus.BAD_REQUEST, "Email is not valid.");
            }
            customer.setEmail(email);
        }

        if (password != null && password.trim().length() > 0) {
            customer.setPassword(password);
        } else {
            throw new SCSException(HttpStatus.BAD_REQUEST, "Password cannot be empty.");
        }

        if (name != null && name.trim().length() > 0) {
            customer.setName(name);
        } else {
            throw new SCSException(HttpStatus.BAD_REQUEST, "Name cannot be empty.");
        }

        customerRepository.save(customer);
        return customer;
    }

    public void deleteCustomerById(Integer customerId) {
        if (customerRepository.findCustomerByAccountId(customerId) == null) {
            throw new SCSException(HttpStatus.NOT_FOUND, "Customer not found.");
        }
        customerRepository.delete(getCustomerById(customerId));
    }

    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }
}
