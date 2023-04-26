import org.apache.commons.lang3.builder.EqualsBuilder;
import java.util.*;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Person)) {
            return false;
        }

        Person other = (Person) o;
        return new EqualsBuilder()
            .append(name, other.getName())
            .append(age, other.getAge())
            .isEquals();
    }

    @Override
    public int hashCode() {
        // Implement a hash code for Person objects based on the name and age properties
        return Objects.hash(name, age);
    }
}

public class Example {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("John", 25));
        people.add(new Person("Jane", 30));
        people.add(new Person("Bob", 20));
        people.add(new Person("John", 25)); // duplicate

        Set<Person> duplicates = new HashSet<>();
        Set<Person> uniques = new HashSet<>();

        for (Person person : people) {
            if (!uniques.add(person)) {
                duplicates.add(person);
            }
        }

        System.out.println("Duplicates: " + duplicates);
    }
}
