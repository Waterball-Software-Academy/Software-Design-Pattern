package tw.waterballsa.designpattern.oopdemo.association.unidirectional;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author - johnny850807@gmail.com (Waterball)
 */
public class Student {
    private final int id;
    private String name;
    private String description;
    private int age;

    // One(Student) to "Many(Subject)" association
    // Use whatever data structure that fit your case
    private final Collection<Subject> subjects = new HashSet<>();

    public Student(int id, String name,
                   String description, int age) {
        this.id = id;
        setName(name);
        setDescription(description);
        setAge(age);
    }

    public void study(Subject subject /*Unidirectional Association*/) {
        System.out.println("Study the subject " + subject.getName());
        subjects.add(subject);
    }

    public Collection<Subject> getSubjects() {
        return subjects;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.length() > 200) {
            throw new IllegalArgumentException("Description's length should not be greater than 200.");
        }
        this.description = description;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 7 || age > 150) {
            throw new IllegalArgumentException("Age must be with 7~150.");
        }
        this.age = age;
    }

}
