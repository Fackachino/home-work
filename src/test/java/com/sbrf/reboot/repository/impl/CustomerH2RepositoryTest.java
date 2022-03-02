package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerH2RepositoryTest {

    private static CustomerRepository customerRepository;

    @BeforeAll
    public static void before() {
        customerRepository = new CustomerH2Repository();
    }

    @Test
    void getAll() {
        boolean tomCreated = customerRepository.createCustomer("Tom", "tom@ya.ru");
        List<Customer> all = customerRepository.getAll();
        assertTrue(all.size() != 0);
    }

    @Test
    void createCustomer() {
        boolean mariaCreated = customerRepository.createCustomer("Maria", "maria98@ya.ru");
        assertTrue(mariaCreated);
    }

    @Test
    void getCustomerID(){
        customerRepository.createCustomer("Danya", "danya@sber.net");
        Long expectedID;
        Long actualID;

        expectedID = customerRepository.getAll()
                .stream().filter(customer -> customer.getName().equals("Danya")
                        && customer.getEMail().equals("danya@sber.net")).findFirst().get().getId();

        actualID = ((CustomerH2Repository) customerRepository).getCustomerID("Danya", "danya@sber.net");

        assertEquals(expectedID, actualID);
    }
    @Test
    void changeCustomerEmail(){
        String expectedCustomerEmail = "mark666@mail.ru";
        String actualCustomerEmail;
        Long id;

        customerRepository.createCustomer("Mark", "mark@gmail.com");
        id = ((CustomerH2Repository) customerRepository).getCustomerID("Mark", "mark@gmail.com");

        ((CustomerH2Repository) customerRepository)
                .changeCustomerEmail(id, "Mark", expectedCustomerEmail);

        actualCustomerEmail = customerRepository.getAll().stream()
                .filter(customer -> customer.getId().equals(id) && customer.getName().equals("Mark"))
                .findFirst().get().getEMail();

        assertEquals(expectedCustomerEmail, actualCustomerEmail);

    }

    @Test
    void checkCustomerExists(){
        boolean customerCreated = customerRepository.createCustomer("Diana", "diana@kiev.ukr");
        boolean customerExists = ((CustomerH2Repository) customerRepository)
                .checkCustomerExists("Diana", "diana@kiev.ukr");
        assertEquals(customerCreated, customerExists);

    }
}