package java8;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GroupByDepartmentAndSalary {

    public static class Employee{
        String name;
        int salary;
        String department;

        public Employee(String name, int salary, String department) {
            this.name = name;
            this.salary = salary;
            this.department = department;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }
    }

    public static void main(String[] args) {
        List<Employee> employees = List.of(
                new Employee("John Doe", 10000, "Sales"),
                new Employee("Jane Doe", 20000, "Engineering"),
                new Employee("Peter Parker", 30000, "Marketing"),
                new Employee("Mary Jane Watson", 40000, "Sales")
        );

        //avg salary for each department
        Map<String,Double> gg = employees.stream().
                collect(Collectors.groupingBy(Employee::getDepartment,Collectors.averagingInt(Employee::getSalary)));
        System.out.println(gg);
        //max salary for each dep
        Map<String, Optional<Employee>> maxSalryEachDep = employees.stream().
                collect(Collectors.groupingBy
                        (Employee::getDepartment,Collectors.maxBy(Comparator.comparingInt(Employee::getSalary))));
        maxSalryEachDep.entrySet().stream().forEach(e->{
            System.out.println("departmentName="+e.getKey());
            e.getValue().stream().forEach(emp->
            {
                System.out.println("Employee name="+emp.getName());
            });
        });
        System.out.println(maxSalryEachDep);

        // Group employees by department and salary
        Map<String, Map<Integer, List<Employee>>> groupedEmployees = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.groupingBy(Employee::getSalary)));
        System.out.println(groupedEmployees);
        // Print the grouped employees
        for (String department : groupedEmployees.keySet()) {
            System.out.println("Department: " + department);
            for (Integer salary : groupedEmployees.get(department).keySet()) {
                System.out.println("Salary: " + salary);
                for (Employee employee : groupedEmployees.get(department).get(salary)) {
                    System.out.println("Employee: " + employee.getName());
                }
            }
        }
    }
}