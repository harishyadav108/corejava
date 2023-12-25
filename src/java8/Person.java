package java8;

import java.util.*;
import java.util.stream.Collectors;

class Person implements Comparable{
    public String name;
    public String lastName;

    public Person(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }



    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public static void main(String arg[]) {
        List<Person> myList = new ArrayList<>();
        myList.add(new Person("Robert", "USA"));
        myList.add(new Person("Andy", "UK"));
        myList.add(new Person("Harish", "India"));
        for (Person person : myList) {
            System.out.println("My name is " + person.getName());
        }

        //after sorting
      myList.stream().sorted(Comparator.comparing(Person::getName)).forEach(System.out::println);


        myList.sort(Comparator.comparing(Person::getName));
        System.out.println(myList);

        Collections.sort(myList);
    }

    @Override
    public int compareTo(Object o) {
        return this.name.compareTo(((Person)o).getName());
    }
}
