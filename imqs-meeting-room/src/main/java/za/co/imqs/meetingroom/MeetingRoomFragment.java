package za.co.imqs.meetingroom;

import android.app.Fragment;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import java.util.HashMap;
import java.util.List;

/**
 * This indicates the details of who is in a meeting
 * @author donovan
 */
public class MeetingRoomFragment extends Fragment implements PersonDragInterface {

    GridView listView = null;
    MainActivity mainActivity = null;
    HashMap<Person, Integer> attendeeToIdMap = new HashMap<Person, Integer>();
    Context context = null;
    List<Person> persons;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View  result = inflater.inflate(R.layout.fragment_attendees, container, false);
        initialise(result);

        mainActivity = (MainActivity)getActivity();
        return result;

    }

    private void initialise(View view) {
        MainActivity mainActivity = ((MainActivity)getActivity());
        initialiseListView(view);
        refreshView(mainActivity.getMeetingRoom().getPeople());


    }

    private void initialiseListView(View parentView) {
        listView = (GridView) parentView.findViewById(R.id.meeting_room_people);
        listView.setEmptyView(getMainActivity().findViewById(R.id.room_empty));



    }

    public void endMeting(View v) {

        final Button button = (Button) v.findViewById(R.id.endMeeting);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               listView.setAdapter(null);
                listView.getAdapter();
            }
        });

    }



    public void initiateDragPerson(View view, Person person) {
        ClipData data = ClipData.newPlainText("person", Integer.toString(person.id));
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        view.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                switch (dragEvent.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED: {
                        mainActivity.displayExitFragment();
                        View dropzone = mainActivity.findViewById(R.id.container_detail);
                        dropzone.setOnDragListener(new ExitFragmentListener(mainActivity));
                        return true;
                    }
                    case DragEvent.ACTION_DRAG_ENDED: {
                        mainActivity.displayLobbyFragment();
                        mainActivity.displayDetailFragment();

                        return true;
                    }
                }
                return false;
            }
        });
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
        PeopleAdaptor adapter = new PeopleAdaptor(getMainActivity(), R.layout.row_attendee_white, people);
        adapter.setDragger(this);
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
