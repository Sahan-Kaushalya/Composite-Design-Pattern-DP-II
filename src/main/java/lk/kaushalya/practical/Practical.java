package lk.kaushalya.practical;

import java.util.ArrayList;

/**
 * Practical 02
 * Composite Pattern
 *
 * PIX Soft is a software development company which has three types of employee categories. Each
 * employee has a name and basic salary. Write a program to calculate the followings by applying the
 * Composite Design Pattern,
 *
 * - Calculate the total salary paid to all the employees when requesting the manager's salary.
 * - Calculate the total salary paid to the project lead ("Levi") and his team.
 *
 * Company Structure:
 * - "Kevin" (Manager) - Rs. 180000.00
 *   - "Henry" (Project Lead) - Rs. 100000.00
 *     - "Ryan" (Developer) - Rs. 80000.00
 *   - "Matthew" (Project Lead) - Rs. 90000.00
 *     - "Jason" (Developer) - Rs. 60000.00
 *   - "Levi" (Project Lead) - Rs. 100000.00
 *     - "Kevin" (Developer) - Rs. 60000.00
 *     - "Alex" (Developer) - Rs. 70000.00
 */

interface  Employee {
    void printDetails();
    double getSalary();
}

// leaf object
class Developer implements Employee{

    private String name;
    private double salary;

    public Developer(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    @Override
    public void printDetails() {
        System.out.println("Developer: " + this.name + ", Salary: " + this.salary);
    }


    @Override
    public double getSalary() {
        return this.salary;
    }
}

// Composite Class (Manager / Project Lead)
class Supervisor implements Employee{

    private String name;
    private String role;
    private double salary;
    private ArrayList<Employee> subordinates = new ArrayList<>();

    public Supervisor(String name, String role, double salary) {
        this.name = name;
        this.role = role;
        this.salary = salary;
    }

    public void assignSubordinate(Employee employee) {
        subordinates.add(employee);
    }

    public void removeSubordinate(Employee employee) {
        subordinates.remove(employee);
    }

    @Override
    public void printDetails() {
        System.out.println("[" + this.role + "]: " + this.name + ", Salary: " + this.salary);
        for (Employee emp : subordinates) {
            emp.printDetails();
        }
        System.out.println("-----------------------------------------");
    }

    @Override
    public double getSalary() {
        double totalSalary = this.salary;

        for (Employee emp : subordinates) {
            totalSalary += emp.getSalary();
        }
        return totalSalary;
    }
}
public class Practical {
    public static void main(String[] args) {

        // Creating Developers (Leafs)
        Employee ryan = new Developer("Ryan", 80000.00);
        Employee jason = new Developer("Jason", 60000.00);
        Employee devKevin = new Developer("Kevin", 60000.00);
        Employee alex = new Developer("Alex", 70000.00);

        // Creating Project Leads (Composites)
        Supervisor henry = new Supervisor("Henry", "Project Lead", 100000.00);
        henry.assignSubordinate(ryan);

        Supervisor matthew = new Supervisor("Matthew", "Project Lead", 90000.00);
        matthew.assignSubordinate(jason);

        Supervisor levi = new Supervisor("Levi", "Project Lead", 100000.00);
        levi.assignSubordinate(devKevin);
        levi.assignSubordinate(alex);

        // Creating Manager (Composite)
        Supervisor kevin = new Supervisor("Kevin", "Manager", 180000.00);
        kevin.assignSubordinate(henry);
        kevin.assignSubordinate(matthew);
        kevin.assignSubordinate(levi);

        // 1. Calculate the total salary paid to all the employees when requesting the manager's salary.
        System.out.println();
        System.out.println("=========================================");
        System.out.println("          PIX Soft (Pvt) Ltd. ");
        System.out.println("=========================================");
        System.out.println("1. Manager's Hierarchy & Total Company Salary:");
        System.out.println("=========================================");
        kevin.printDetails();
        System.out.println("-----------------------------------------");
        System.out.println("Total Salary (Manager & All Employees): Rs. " + kevin.getSalary());
        System.out.println("=========================================\n");


        // 2. Calculate the total salary paid to the project lead ("Levi") and his team.
        System.out.println();
        System.out.println("=========================================");
        System.out.println("          PIX Soft (Pvt) Ltd. ");
        System.out.println("=========================================");
        System.out.println("2. Project Lead ('Levi') Hierarchy & Total Salary:");
        System.out.println("=========================================");
        levi.printDetails();
        System.out.println("-----------------------------------------");
        System.out.println("Total Salary (Levi & Team): Rs. " + levi.getSalary());
        System.out.println("=========================================");

    }
}
