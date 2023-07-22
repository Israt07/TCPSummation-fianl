package server.controller;

import model.Customer;


import java.util.ArrayList;
import java.util.List;

/**
 * This class, CustomerController, acts as a controller for managing customer data.
 * It handles the storage, retrieval, addition, and removal of customer information.
 * It uses a List to store Customer objects and provides methods for various operations.
 * 
 * @author isratjahanbhuiyan
 *
 */

public class CustomerController {

    private List<Customer> customers;

    public CustomerController() {
        customers = new ArrayList<>();
        createSampleCustomers();
    }

    // Method 1 (private) - Create a list of samples of customer data
    private void createSampleCustomers() {
        // Sample data
        customers.add(new Customer(1, "John Doe"));
        customers.add(new Customer(2, "Jane Smith"));
        customers.add(new Customer(3, "Israt"));
        customers.add(new Customer(4, "Sumaiya"));
        // Add more customers as needed
    }

    // Method 2 (public) - Search customer by name (exact match)
    public Customer searchCustomerByName(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equals(name)) {
                return customer;
            }
        }
        return null;
    }

    // Method 3 (public) - Search customer by ID
    public Customer searchCustomerById(int id) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == id) {
                return customer;
            }
        }
        return null;
    }

    // Method 4 (public) - Get list of all customers
    public List<Customer> getAllCustomers() {
        return customers;
    }

    // Additional method (public) - Add a new customer
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // Additional method (public) - Remove a customer by ID
    public void removeCustomerById(int id) {
        customers.removeIf(customer -> customer.getCustomerId() == id);
    }
}
