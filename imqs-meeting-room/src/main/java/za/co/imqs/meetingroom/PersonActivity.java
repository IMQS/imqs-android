package za.co.imqs.meetingroom;

import android.app.ListActivity;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * This activity describes how to handle and display individual list items in the Attendees list
 * Created by donovan on 2014/08/10.
 */
public class PersonActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We'll bind the custom screen layout here
        setContentView(R.layout.row_attendee_white);

        // TODO populate the list of attendees
        PeopleAdaptor adapter = new PeopleAdaptor(this, R.layout.row_attendee_white, new ArrayList<Person>());

        // Bind to our new adapter.
        setListAdapter(adapter);
    }



}
