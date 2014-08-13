package za.co.imqs.meetingroom;

import java.util.ArrayList;
import java.util.List;

import za.co.imqs.meetingroom.MeetingDetail;
import za.co.imqs.meetingroom.Person;

/**
 * Represent a meeting meetingRoom
 * Created by donovan on 2014/08/12.
 */
public class Room {

    String roomName = null;
    private List<Person> people = new ArrayList<Person>();

    public Room(String roomName) {
        this.roomName = roomName;
    }

    public Room(String roomName, List<Person> people) {
        this.roomName = roomName;
        this.people = people;
    }

    public void movePerson(Person person, List<Person> outsidePeople) {
        if (outsidePeople.contains(person)) {
            this.people.add(person);
            outsidePeople.remove(person);
        }
        else if (people.contains(person)) {
            this.people.remove(person);
            outsidePeople.add(person);
        }
    }

    public List<Person> getPeople() {
        return people;
    }
}
