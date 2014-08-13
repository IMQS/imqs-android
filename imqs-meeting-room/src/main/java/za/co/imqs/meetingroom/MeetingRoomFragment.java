package za.co.imqs.meetingroom;

import android.app.Fragment;
import android.content.ClipData;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * This indicates the details of who is in a meeting
 * @author donovan
 */
public class MeetingRoomFragment extends Fragment implements AdapterView.OnItemLongClickListener {

    ListView listView = null;
    MainActivity mainActivity = null;

    public MeetingRoomFragment() {
        super();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_attendees, container, false);
        initialise(result);
        return result;
    }

    private void initialise(View view) {
        MainActivity mainActivity = ((MainActivity)getActivity());
        initialiseListView(view);
        refreshView(mainActivity.getMeetingRoom().getPeople());
    }

    private void initialiseListView(View parentView) {
        listView = (ListView) parentView.findViewById(R.id.meeting_room_people);
        listView.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(getMainActivity().getApplicationContext(), "<-- Drag to Exit", Toast.LENGTH_LONG).show();
        initiateDragPerson(view, position);
        return true;
    }

    private void initiateDragPerson(View view, int position) {
        Person person = (Person) listView.getItemAtPosition(position);
        ClipData data = ClipData.newPlainText("person", Integer.toString(person.id));
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        view.setOnDragListener(new ExitFragmentListener(getMainActivity()));
        view.startDrag(data, shadowBuilder, null, 0);
    }

    /**
     * Singleton Accessor
     */
    private MainActivity getMainActivity() {
        if (mainActivity == null)
            mainActivity = (MainActivity)getActivity();
        return mainActivity;
    }

    public void refreshView(List<Person> people) {
        ArrayAdapter<Person> adapter = new PeopleAdaptor(getActivity(), R.layout.row_attendee, people);
        listView.setAdapter(adapter);
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
