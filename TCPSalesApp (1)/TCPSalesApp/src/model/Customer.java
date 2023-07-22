package model;

import java.io.Serializable;


/**
 * This class represents a Customer object with attributes customerId and name.
 * It implements the Serializable interface, which allows instances of this class to be
 * serialized and deserialized to be sent over a network or saved to a file.
 * 
 * @author isratjahanbhuiyn
 *
 */

public class Customer implements Serializable {
    private int customerId;
    private String name;

    public Customer(int customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer [customerId=" + customerId + ", name=" + name + "]";
    }
}
