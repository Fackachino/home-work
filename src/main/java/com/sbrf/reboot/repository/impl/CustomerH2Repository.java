package com.sbrf.reboot.repository.impl;

import com.sbrf.reboot.dto.Customer;
import com.sbrf.reboot.dto.CustomerNotFoundException;
import com.sbrf.reboot.repository.CustomerRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerH2Repository implements CustomerRepository {

    private final String JDBC_DRIVER = "org.h2.Driver";
    private final String DB_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";

    private final String USER = "sa";
    private final String PASS = "";

    public CustomerH2Repository() {
        createCustomerTable();
        Connection conn = null;
        Statement stmt = null;

    }

    private void createCustomerTable() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
        ) {
            String sql = "CREATE TABLE IF NOT EXISTS CUSTOMER " +
                    "(id INT PRIMARY KEY AUTO_INCREMENT, " +
                    " NAME VARCHAR(255), " +
                    " EMAIL VARCHAR(255)) ";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement smt = conn.createStatement();
             ResultSet rs = smt.executeQuery("SELECT * FROM CUSTOMER");
        ) {
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getLong("ID"));
                customer.setName(rs.getString("NAME"));
                customer.setEMail(rs.getString("EMAIL"));
                customers.add(customer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public boolean createCustomer(String name, String eMail) {
        String sql = "INSERT INTO CUSTOMER (NAME, EMAIL) VALUES (? , ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setString(1, name);
            smt.setString(2, eMail);
            if (!checkCustomerExists(name, eMail)) {
                smt.execute();
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean checkCustomerExists(String name, String eMail) {
        String sql = "SELECT COUNT(*) FROM CUSTOMER WHERE NAME = ? AND EMAIL = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setString(1, name);
            smt.setString(2, eMail);
            ResultSet rs = smt.executeQuery();
            rs.next();
            return rs.getInt("COUNT(*)") != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean changeCustomerEmail(Long id, String name, String eMail) {
        String sql = "UPDATE CUSTOMER SET EMAIL = ? WHERE ID = ? AND NAME = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setString(1, eMail);
            smt.setLong(2, id);
            smt.setString(3, name);
            smt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Long getCustomerID(String name, String email) throws CustomerNotFoundException {
        String sql = "SELECT ID FROM CUSTOMER WHERE NAME = ? AND EMAIL = ?";
        boolean idExists = false;
        Long customerID = 0l;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement smt = conn.prepareStatement(sql);
            smt.setString(1, name);
            smt.setString(2, email);
            ResultSet rs = smt.executeQuery();
            if (rs.next()) {
                idExists = true;
                customerID = rs.getLong("ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(idExists){
            return customerID;
        }
        else throw new CustomerNotFoundException("Customer not found");
    }



}


