package za.co.imqs.meetingroom;

/**
 * POJO for representing a person who could attend a meeting
 * Created by donovan on 2014/08/10.
 */
public class Person {
    int id;
    String firstName;
    String lastName;
    String avatarPath;
    public Person(int id, String firstName, String lastName, String avatarPath) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarPath = avatarPath;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object person) {
        return person instanceof Person && ((Person)person).id == this.id;
    }
}
