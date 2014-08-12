package za.co.imqs.meetingroom.util;

import android.app.Activity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import za.co.imqs.meetingroom.Attendee;
import za.co.imqs.meetingroom.R;

/**
 * Reads the people from the Json
 * Created by donovan on 2014/08/12.
 */
public class AttendeeJsonReader implements AttendeeReaderInterface {

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
    public List<Attendee> getAttendees(Activity context) {
        List<Attendee> result = new ArrayList<Attendee>();
        try {
            JSONObject obj = new JSONObject(loadJson(context));
            JSONArray attendees = obj.getJSONArray("people");
            for (int count = 0; 1 < attendees.length(); count++)
                result.add(readNextAttendee(attendees.getJSONObject(count)));
        }
        catch (Exception e) {
            Log.e("PeopleReader", "Unable to read JSON input for People");
        }
        return result;
    }

    private Attendee readNextAttendee(JSONObject attendee) throws Exception {
        return new Attendee(attendee.getString("f"), attendee.getString("l"), attendee.getString("p"));
    }
}
