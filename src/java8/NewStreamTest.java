package java8;

import java.sql.SQLOutput;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Emp {
    int id;
    String name;
    int age;
    String gender;
    String department;
    int yearOfJoining;

    double salary;
    public Emp(int id, String name, int age, String gender, String department, int yearOfJoining, double salary) {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setYearOfJoining(int yearOfJoining) {
        this.yearOfJoining = yearOfJoining;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", department='" + department + '\'' +
                ", yearOfJoining=" + yearOfJoining +
                ", salary=" + salary +
                '}';
    }
}

public class NewStreamTest {
    public static void main(String args[]){
        String input = "Java articles are Awesome"; // Find first not repetitive characters ==> j

        Character ch = input.chars().mapToObj(c -> Character.toLowerCase((char) c)).
                collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(d -> d.getValue() == 1l).map(e -> e.getKey()).findFirst().get();
        System.out.println(ch);

        // Find the longest word in the sentence  ==> articles
        String str =Arrays.stream(input.split(" ")).
                max((word1, word2) -> Integer.compare(word1.length(), word2.length())).orElse("");
        System.out.println(str);
        //OR
        String max = Arrays.stream(input.split(" ")).max((w1,w2)->w1.length()-w2.length()).orElse("");
        System.out.println("maximum is:"+max);
        // Find The Shortest word in the sentence  ==> articles
        String strShort =Arrays.stream(input.split(" ")).
                max((word1, word2) -> Integer.compare(word2.length(), word1.length())).orElse("");
        System.out.println(strShort);
        // find the duplicate char
        List<Character> dcl =input.chars().mapToObj( c->Character.toLowerCase(Character.valueOf((char) c))).
                collect(Collectors.groupingBy(Function.identity(),  LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(d->d.getValue()>1l).map(e->e.getKey()).collect(Collectors.toList());
        System.out.println(dcl);
        //Number starts with 1
        List<Integer> myList = Arrays.asList(10,15,8,49,25,25,10);
        List<String>newList = myList.stream().map(l->l+"").filter(m->m.startsWith("1")).collect(Collectors.toList());
        System.out.println(newList);
        //find duplicate in  list of integer
        HashSet setDup = new HashSet<Integer>();
        myList.stream().filter(l->!setDup.add(l)).sorted().forEach(System.out::println);
        Map<Integer,Long> namesCount =myList.stream().filter(x->Collections.frequency(myList,x)>1).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        System.out.println(namesCount);
        //convert the sentance into upper case
        Arrays.stream(input.split(" ")).map(String::toUpperCase).forEach(e->System.out.print(e+" "));

        //Employee related real time Use case

        List<Emp> employeeList = new ArrayList<Emp>();

        employeeList.add(new Emp(111, "Jiya Brein", 32, "Female", "HR", 2011, 25000.0));
        employeeList.add(new Emp(122, "Paul Niksui", 25, "Male", "Sales And Marketing", 2015, 13500.0));
        employeeList.add(new Emp(133, "Martin Theron", 29, "Male", "Infrastructure", 2012, 18000.0));
        employeeList.add(new Emp(144, "Murali Gowda", 28, "Male", "Product Development", 2014, 32500.0));
        employeeList.add(new Emp(155, "Nima Roy", 27, "Female", "HR", 2013, 22700.0));
        employeeList.add(new Emp(166, "Iqbal Hussain", 43, "Male", "Security And Transport", 2016, 10500.0));
        employeeList.add(new Emp(177, "Manu Sharma", 35, "Male", "Account And Finance", 2010, 27000.0));
        employeeList.add(new Emp(188, "Wang Liu", 31, "Male", "Product Development", 2015, 34500.0));
        employeeList.add(new Emp(199, "Amelia Zoe", 24, "Female", "Sales And Marketing", 2016, 11500.0));
        employeeList.add(new Emp(200, "Jaden Dough", 38, "Male", "Security And Transport", 2015, 11000.5));
        employeeList.add(new Emp(211, "Jasna Kaur", 27, "Female", "Infrastructure", 2014, 15700.0));
        employeeList.add(new Emp(222, "Nitin Joshi", 25, "Male", "Product Development", 2016, 28200.0));
        employeeList.add(new Emp(233, "Jyothi Reddy", 27, "Female", "Account And Finance", 2013, 21300.0));
        employeeList.add(new Emp(244, "Nicolus Den", 24, "Male", "Sales And Marketing", 2017, 10700.5));
        employeeList.add(new Emp(255, "Ali Baig", 23, "Male", "Infrastructure", 2018, 12700.0));
        employeeList.add(new Emp(266, "Sanvi Pandey", 26, "Female", "Product Development", 2015, 28900.0));
        employeeList.add(new Emp(277, "Anuj Chettiar", 31, "Male", "Product Development", 2012, 35700.0));

        //Find out the count of male and female employees present in the organization?
        Map<String,Long> mfc =employeeList.stream().collect(Collectors.groupingBy(Emp::getGender, Collectors.counting()));
        System.out.println(mfc);
        //Write a program to print the names of all departments in the organization.
        List<String> dl = employeeList.stream().map(e->e.getDepartment()).distinct().collect(Collectors.toList());
        System.out.println(dl);
        //Find the average age of Male and Female Employees.
        Map<String,Double> mfavg= employeeList.stream().collect(Collectors.groupingBy(e->e.getGender(),Collectors.averagingInt(p->p.age)));
        System.out.println(mfavg);
        //Get the Names of employees who joined after 2015.
        List<String>nameList = employeeList.stream().filter(e->e.yearOfJoining>2015).map(Emp::getName).collect(Collectors.toList());
        System.out.println(nameList);
        //Count the number of employees in each department.
        Map<String, Long>dpC =employeeList.stream().collect(Collectors.groupingBy(Emp::getDepartment,Collectors.counting()));
        System.out.println(dpC);
        //Find out the average salary of each department.
        Map<String, Double>dpavgSal =employeeList.stream().collect(Collectors.groupingBy(Emp::getDepartment,Collectors.averagingDouble(Emp::getSalary)));
        System.out.println(dpavgSal);
        //Find out the oldest employee, his/her age and department?
        Emp e =employeeList.stream().sorted(Comparator.comparingInt(Emp::getYearOfJoining)).findFirst().get();
        System.out.println(e);
        Emp e2 =employeeList.stream().min(Comparator.comparingInt(Emp::getYearOfJoining)).get();
        System.out.println(e2);
        //Find out the average and total salary of the organization.
        double ts =employeeList.stream().collect(Collectors.summingDouble(Emp::getSalary));
        double as =employeeList.stream().collect(Collectors.averagingDouble(Emp::getSalary));
        System.out.println("Total salary = "+ts+" Average salary = "+as);

        Map empList11 = employeeList.stream().collect(Collectors.groupingBy(Emp::getDepartment));
        System.out.println("empList11"+empList11);

        //employeeList.stream().collect(Collectors.groupingBy(Emp::getDepartment)).values().stream().forEach(System.out::println);

        //List down the employees of each department.
        Map<String, List<Emp>>ls= employeeList.stream().collect(Collectors.groupingBy(Emp::getDepartment));
        ls.entrySet().stream().forEach(q->{
            System.out.println("========  "+q.getKey()+" Department =======");
            q.getValue().forEach(System.out::println);
        });


        Map<String, Integer> map = new HashMap<>();
       /* map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        map.put("Four", 4);*/

        Optional<Map.Entry<String, Integer>> firstEntry = map.entrySet().stream().findFirst();
        System.out.println(firstEntry.orElseGet(()->Map.entry("defaultKey", 42)));

        Optional<Map.Entry<String, Integer>> firstEntry1 = map.entrySet().stream().findFirst();
        System.out.println(firstEntry1.orElse(Map.entry("defaultKey", 42)));
    }
}
