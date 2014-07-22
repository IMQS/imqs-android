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

/**
 * This indicates the details of who is in a meeting
 * @author donovan
 */
public class AttendeesFragment extends Fragment {

    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LinearLayout fragmentAttendees = (LinearLayout) inflater.inflate(R.layout.fragment_attendees, container, false);
        initialiseAttendeesList(fragmentAttendees);
        return fragmentAttendees;
    }

	@Override
	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
        final Activity parentActivity = this.getActivity();

	}

    private void initialiseAttendeesList(View view) {
        listView = (ListView) view.findViewById(R.id.list_attendees);
        String[] values = getListValues();

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        final Activity parentActivity = this.getActivity();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(parentActivity, android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // ListView Clicked item index
                int itemPosition = position;
                // ListView Clicked item value
                String itemValue = (String) listView.getItemAtPosition(position);
                // Show Alert
                Toast.makeText(parentActivity.getApplicationContext(), "Position :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG).show();
            }

        });

    }

    private String[] getListValues() {
        return new String[]{
                "Jaco Vosloo",
                "Erick Underhill",
                "Oliete Williams",
                "Thea Bester",
                "Rob Knight",
                "Gerhard van Wyk"
        };
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
