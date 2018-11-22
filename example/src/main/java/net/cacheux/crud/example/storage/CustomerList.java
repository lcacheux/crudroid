package net.cacheux.crud.example.storage;

import net.cacheux.crud.example.model.Customer;

public class CustomerList extends ItemList<Customer> {

    private static CustomerList instance = new CustomerList();

    private CustomerList() {

    }

    public static CustomerList getInstance() {
        return instance;
    }
}
