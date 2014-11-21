package za.co.imqs.meetingroom.util;

import android.app.Activity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import za.co.imqs.meetingroom.Person;
import za.co.imqs.meetingroom.R;

/**
 * Reads the people from the Json
 * Created by donovan on 2014/08/12.
 */
/**
 * Reads the people from the Json
 * Created by donovan on 2014/08/12.
 */

/**
 * Reads the people from the Json
 * Created by donovan on 2014/08/12.
 */
public class PeopleJsonReader implements PeopleReaderInterface {

    public String loadJson(Activity context) {
        String json = null;

        try {
            InputStream is = context.getResources().openRawResource(R.raw.people);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    @Override
    public List<Person> getPeople(Activity context) {

        List<Person> result = new ArrayList<Person>();

        try {

            JSONObject obj = new JSONObject(loadJson(context));
            JSONArray attendees = obj.getJSONArray("people");

            for (int count = 0; count < attendees.length(); count++) {
                result.add(readNextAttendee(attendees.getJSONObject(count)));
                 }

            Collections.sort(result, new Comparator<Person>() {
                @Override
                public int compare(Person person, Person person2) {
                    return person.getFirstName().compareTo(person2.getFirstName());

                }
            });
        }
        catch(Exception e){
            Log.e("PeopleReader", "Unable to read JSON input for People");
        }
        return result;
    }

    private Person readNextAttendee(JSONObject attendee) throws Exception {
        return new Person(attendee.getInt("i"), attendee.getString("f"), attendee.getString("l"), attendee.getString("p"));

    }

}

