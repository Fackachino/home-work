package com.sbrf.reboot.staff;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StabTest {
    class ITDepartment extends Department{
        public ITDepartment(String name) {
            super(name);
        }
    }

    class FinancialDepartment extends Department{
        public FinancialDepartment(String name) {
            super(name);
        }
    }

    Stab<Department> mainStab;
    ITDepartment itDepartment;
    FinancialDepartment financialDepartment;
    @BeforeAll
    public void init(){
        mainStab = new Stab<>("Main Stab");

        itDepartment = new ITDepartment("IT Department #3");
        itDepartment.addEmployee(new Employee(1, "Sergey", "Smolov", "Junior Java Developer"));
        itDepartment.addEmployee(new Employee(2, "Ivan", "Ivanov", "Senior Java Developer"));

        financialDepartment = new FinancialDepartment("Financial Department #666");
        financialDepartment.addEmployee(new Employee(77, "Svetlana", "Svetlaya", "Accountant"));
        financialDepartment.addEmployee(new Employee(33, "Nina", "Zaiceva", "Analyst"));

        mainStab.addDepartment(itDepartment);
        mainStab.addDepartment(financialDepartment);
    }

    @Test
    void getCountStabEmployees(){
        Assertions.assertEquals(4, mainStab.getAllEmployeesNumber());
    }

    @Test
    void getAllStabEmployees(){
        List<Employee> employeeList = Stream.of(itDepartment.getEmployees(), financialDepartment.getEmployees())
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        Assertions.assertEquals(employeeList, mainStab.getAllEmployees());
    }

    @Test
    void getDepartmentNumber(){
        Assertions.assertEquals(2,mainStab.getDepartmentNumber());
    }
}
