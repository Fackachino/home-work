package com.sbrf.reboot.staff;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Stab<T extends Department> {
    private final String name;
    private final List<Department> departmentList;

    public Stab(String name) {
        this.name = name;
        this.departmentList = new ArrayList<>();
    }

    public void addDepartment(Department department) {
        departmentList.add(department);
    }

    public String getName() {
        return name;
    }

    public ArrayList<Employee> getAllEmployees() {
        ArrayList<Employee> employees;
        employees = (ArrayList<Employee>) departmentList.stream()
                .map(department -> department.getEmployees())
                .flatMap(employees1 -> employees1.stream())
                .collect(Collectors.toList());
        return employees;
    }

    public int getAllEmployeesNumber(){
        return getAllEmployees().size();
    }

    public int getDepartmentNumber(){
        return departmentList.size();
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }
}
