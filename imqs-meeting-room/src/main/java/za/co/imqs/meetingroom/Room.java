package za.co.imqs.meetingroom;

import java.util.ArrayList;
import java.util.List;

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

    public void personExitTo(Person person, Room room) {
        people.remove(person);
        if (!room.getPeople().contains(person)) {room.getPeople().add(person);};
    }

    public List<Person> getPeople() {
        return people;
    }
}
