package java8;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindNthSalary {

    public static class Employee{
        int id;
        String name;
        int salary;
        String department;

        public Employee(int id, String name, int salary, String department) {
            this.id = id;
            this.name = name;
            this.salary = salary;
            this.department = department;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        // Test Case 2 : Duplicate Employee List
        List<Employee> empList2 = Arrays.asList(
                new Employee(11, "Sagar", 4400, "SALES"),
                new Employee(101, "Manish", 5000, "IT"),
                new Employee(109, "Atul", 3000, "HR"),
                new Employee(166, "Santosh", 4400, "IT"),
                new Employee(109, "Rupendra", 3200, "FIN"),
                new Employee(411, "Priyanka", 4400, "ADMIN")
        );

        Map.Entry<Integer, List<String>> nthHighestSalary2 = getNthHighestSalary(empList2, 3);
        System.out.println("Test Case 2: " + nthHighestSalary2);


        //Example 2

        Map<String,Integer> map2 = new HashMap<>();
        map2.put("anil",1000);
        map2.put("ankit",1200);
        map2.put("bhavna",1200);
        map2.put("james",1200);
        map2.put("micael",1000);
        map2.put("tom",1300);
        map2.put("daniel",1300);

        Map<Integer,List<String>> m= map2.entrySet().stream().collect(Collectors.groupingBy(e->e.getValue(), Collectors.mapping(e->e.getKey(),Collectors.toList())));

        System.out.println(m);

        Map.Entry bb = m.entrySet().stream().sorted(Comparator.comparingInt(e->-e.getKey())).collect(Collectors.toList()).get(0);
        System.out.println(bb);
    }

    private static Map.Entry<Integer, List<String>> getNthHighestSalary(List<Employee> empList, int nth) {

        if (empList.isEmpty() || nth <= 0 || empList.size() < nth)
            throw new IllegalArgumentException("Please validate your inputs.");

        return empList.stream()
                .collect(Collectors.groupingBy(Employee::getSalary, Collectors.mapping(Employee::getName, Collectors.toList())
                ))
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
                .collect(Collectors.toList())
                .get(nth - 1);
    }
}