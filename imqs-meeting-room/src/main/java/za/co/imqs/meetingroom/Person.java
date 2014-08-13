package za.co.imqs.meetingroom;

/**
 * POJO for representing a person who could attend a meetin
 * Created by donovan on 2014/08/10.
 */
public class Person {

    int id;
    String firstName;
    String lastName;
    String avatarPath;

    public Person(int id, String firstName, String lastName, String avatarPath) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarPath = avatarPath;
    }

    @Override
    public boolean equals(Object person) {
        return person instanceof Person && ((Person)person).id == this.id;
    }
}
