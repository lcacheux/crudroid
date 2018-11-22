package net.cacheux.crud.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import net.cacheux.crud.example.model.Customer;
import net.cacheux.crud.example.storage.ItemList;

import org.junit.Test;

public class StorageUnitTest {

    private static final String NAME1 = "Peter";
    private static final String NAME2 = "Steven";
    private static final String NAME3 = "Georges";

    @Test
    public void testItemList() {
        ItemList<Customer> itemList = new ItemList<>();

        Customer customer = new Customer();
        customer.setName(NAME1);

        itemList.add(customer);
        assertEquals(1, customer.getId());

        Customer customer2 = new Customer();
        customer2.setName(NAME2);

        itemList.add(customer2);
        assertEquals(2, customer2.getId());
        assertEquals(2, itemList.getContent().size());

        Customer customerUpdate = new Customer();
        customerUpdate.setId(1);
        customerUpdate.setName(NAME3);

        assertTrue(itemList.update(customerUpdate));
        assertEquals(2, itemList.getContent().size());
        assertEquals(NAME3, itemList.getContent().get(0).getName());

        Customer customerDelete = new Customer();
        customerDelete.setId(1);
        assertTrue(itemList.delete(customerDelete));

        assertEquals(1, itemList.getContent().size());
        assertEquals(NAME2, itemList.getContent().get(0).getName());
    }
}