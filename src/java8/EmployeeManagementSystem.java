package java8;

import java.util.*;
import java.util.stream.Collectors;

class Employee {
    int id;

    String name;

    int age;

    String gender;

    String department;

    int yearOfJoining;

    double salary;

    public Employee(int id, String name, int age, String gender, String department, int yearOfJoining, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.yearOfJoining = yearOfJoining;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getDepartment() {
        return department;
    }

    public int getYearOfJoining() {
        return yearOfJoining;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Id : " + id
                + ", Name : " + name
                + ", age : " + age
                + ", Gender : " + gender
                + ", Department : " + department
                + ", Year Of Joining : " + yearOfJoining
                + ", Salary : " + salary;
    }
}

public class EmployeeManagementSystem {

    public static void main(String[] args) {
        List<Employee> employeeList = createEmployeeList();

        //Query 3.1 : How many male and female employees are there in the organization?

        Map<String,Long> nameOfMaleAndFemaleEmployee = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
        System.out.println(nameOfMaleAndFemaleEmployee);
        /*Output :
        {Male=11, Female=6}*/

        //Query 3.2 : Print the name of all departments in the organization?
        employeeList.stream().map(Employee::getDepartment).distinct().forEach(System.out::println);

        //Query 3.3 : What is the average age of male and female employees?
        Map<String,Double> avgAgeMaleFemale= employeeList.stream().collect(Collectors.groupingBy(Employee::getGender, Collectors.averagingDouble(Employee::getAge)));
        System.out.println(avgAgeMaleFemale);

        //Query 3.4 : Get the details of highest paid employee in the organization?
        Employee maxSalaryEmp = employeeList.stream().max(Comparator.comparingDouble(Employee::getSalary)).get();
        System.out.println(maxSalaryEmp);

        //or

        Employee maxSalaryEmp1 = employeeList.stream().collect(Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))).get();
        System.out.println(maxSalaryEmp1);

        //Query 3.5 : Get the names of all employees who have joined after 2015?
        employeeList.stream().filter(e-> e.getYearOfJoining() > 2015).forEach(e->System.out.println(e.name));

        //Query 3.6 : Count the number of employees in each department?
        Map<String,Long> noOfEmpEachDep = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.counting()));
        System.out.println(noOfEmpEachDep);

        //Query 3.7 : What is the average salary of each department?
        Map<String,Double> avgSalaryEachDepartment = employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(avgSalaryEachDepartment);

        //Query 3.8 : Get the details of youngest male employee in the product development department?
        Employee youngMaleEmpl = employeeList.stream().filter(e->e.gender.equals("Male") && e.department.equals("Product Development")).min(Comparator.comparingInt(Employee::getAge)).get();
        System.out.println(youngMaleEmpl);

        //Query 3.9 : Who has the most working experience in the organization?
        Employee mostWorkingExpEmp = employeeList.stream().min(Comparator.comparing(Employee::getYearOfJoining)).get();
        System.out.println(mostWorkingExpEmp);

        //Query 3.10 : How many male and female employees are there in the sales and marketing team?
        Map<String,Long> maleFemaleCountInSalesMarketingDep = employeeList.stream().filter(e->e.getDepartment().equals("Sales And Marketing")).collect(Collectors.groupingBy(Employee::getGender,Collectors.counting()));
        System.out.println(maleFemaleCountInSalesMarketingDep);

        //Query 3.11 : What is the average salary of male and female employees?
        Map<String,Double> avgSalaryMaleFemaleEmp = employeeList.stream().collect(Collectors.groupingBy(Employee::getGender,Collectors.averagingDouble(Employee::getSalary)));
        System.out.println(avgSalaryMaleFemaleEmp);

        //Query 3.12 : List down the names of all employees in each department?
        employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment)).entrySet().forEach(e-> System.out.println(e.getValue()));

        //Query 3.13 : What is the average salary and total salary of the whole organization?
        DoubleSummaryStatistics summaryStatistics = employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println("avg Salary="+summaryStatistics.getAverage());
        System.out.println("sum Salary="+summaryStatistics.getSum());
        System.out.println("max Salary="+summaryStatistics.getMax());

        ///Query 3.14 : Separate the employees who are younger or equal to 25 years from those employees who are older than 25 years.
        Map<Boolean, List<Employee>> partitionEmployeesByAge = employeeList.stream().collect(Collectors.partitioningBy(e->e.getAge()>25));
        System.out.println(partitionEmployeesByAge);
        partitionEmployeesByAge.entrySet().stream().forEach(e->{
            if(e.getKey()){
                System.out.println("=========Employee younger than or equal to 25 years ==========");
                e.getValue().forEach(emp-> System.out.println(emp.getName()+" and age is="+emp.getAge()));
            }else{
                System.out.println("=========employee older than 25 years==========");
                e.getValue().forEach(emp-> System.out.println(emp.getName()+" and age is="+emp.getAge()));
            }
        });

        //Query 3.15 : Who is the oldest age employee in the organization? What is his age and which department he belongs to?
        Employee oldestAgeEmp= employeeList.stream().max(Comparator.comparingInt(Employee::getAge)).get();
        System.out.println(oldestAgeEmp);

        List<String> list = Arrays.asList("JAVA", "J2EE", "Spring", "Hibernate");
        list.forEach(System.out::println);
        //find the all departments and its employee
        Map<String,List<String>> noOfEmpEachDepWithOrder= employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,LinkedHashMap::new,
                Collectors.mapping(Employee::toString,Collectors.toList())));
        System.out.println(noOfEmpEachDepWithOrder);


        Map<String,List<String>> noOfEmpEachDepWithOrder1= employeeList.stream().collect(Collectors.groupingBy(Employee::getDepartment,LinkedHashMap::new,Collectors.mapping(Employee::toString)));

    }

    private static List<Employee> createEmployeeList() {
        List<Employee> employeeList = new ArrayList<Employee>();
        employeeList.add(new Employee(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Employee(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Employee(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Employee(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Employee(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Employee(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Employee(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Employee(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Employee(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Employee(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new Employee(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Employee(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Employee(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new Employee(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Employee(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Employee(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Employee(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));
        return employeeList;
    }
}
