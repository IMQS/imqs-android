package za.co.imqs.meetingroom.util;

import android.app.Activity;

import java.util.List;

import za.co.imqs.meetingroom.Person;


/**
 * Abstract all the Attendee reading stuff
 * Created by donovan on 2014/08/12.
 */
public interface PeopleReaderInterface {
    List<Person> getPeople(Activity context);


}
