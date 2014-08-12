package za.co.imqs.meetingroom;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import za.co.imqs.meetingroom.util.AttendeeJsonReader;
import za.co.imqs.meetingroom.util.AttendeeReaderInterface;

/**
 * This indicates the details of who is in a meeting
 * @author donovan
 */
public class AttendeesFragment extends Fragment {

    ListView listView;

    public AttendeesFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_attendees, container, false);
        initialiseAttendeesList(result, new AttendeeJsonReader());
        return result;
    }

    private void initialiseAttendeesList(View view, AttendeeReaderInterface peopleReader) {
        listView = (ListView) view.findViewById(R.id.list_attendees);
        List<Attendee> people = peopleReader.getAttendees(this.getActivity());

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        final Activity parentActivity = this.getActivity();
        ArrayAdapter<Attendee> adapter = new AttendeesAdaptor(parentActivity, R.layout.row_attendee, people);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ListView Clicked item index
                int itemPosition = position;
                // ListView Clicked item value
                Attendee person = (Attendee) listView.getItemAtPosition(position);
                // Show Alert
                Toast.makeText(parentActivity.getApplicationContext(), "Position :" + itemPosition + "  Person : " + person.firstName, Toast.LENGTH_LONG).show();
            }

        });

    }

	@Override
	public void onPause() {
		super.onPause();
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}
}
