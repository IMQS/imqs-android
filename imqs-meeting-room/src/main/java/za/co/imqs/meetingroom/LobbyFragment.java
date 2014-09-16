package za.co.imqs.meetingroom;

import android.app.Fragment;
import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

/**
 * Fragment for the lobby
 * Created by donovan on 2014/08/13.
 */
public class LobbyFragment extends Fragment implements PersonDragInterface {

    ListView listView = null;
    private static MainActivity mainActivity = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_lobby, container, false);
        initialise(result);
        refreshView(getMainActivity().getLobby().getPeople());
        return result;
    }
    /**
     * Singleton Accessor
     */
    public MainActivity getMainActivity() {
        if (mainActivity == null)
            mainActivity = (MainActivity)getActivity();
        return mainActivity;
    }

    private void initialise(View view) {
        initialiseListView(view);
    }

    private void initialiseListView(View parentView) {
        listView = (ListView) parentView.findViewById(R.id.lobby_people);
    }


    public void initiateDragPerson(View view, Person person) {
        ClipData data = ClipData.newPlainText("person", Integer.toString(person.id));
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        view.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                switch (dragEvent.getAction()) {
                    case DragEvent.ACTION_DRAG_STARTED: {
                        mainActivity.displayEnterFragment();
                        View dropzone = mainActivity.findViewById(R.id.container_meeting_room);
                        dropzone.setOnDragListener(new EnterFragmentListener(mainActivity));
                        return true;
                    }
                    case DragEvent.ACTION_DRAG_ENDED: {

                        if (dragEvent.getResult()) {
                            mainActivity.displayMeetingRoomFragment();
                            dragEvent.getResult();

                            return true;
                        }
                    }
                }
                return false;
            }
        });
        view.startDrag(data, shadowBuilder, null, 0);
    }

    public void refreshView(List<Person> people) {
        PeopleAdaptor adapter = new PeopleAdaptor(getMainActivity(), R.layout.row_attendee_white, people);
        adapter.setDragger(this);
        listView.setAdapter(adapter);
    }


}
