package za.co.imqs.meetingroom;

import android.app.Fragment;
import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Fragment for the lobby
 * Created by donovan on 2014/08/13.
 */
public class LobbyFragment extends Fragment implements AdapterView.OnItemLongClickListener {

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
        initialiseBackButton(view);
    }

    private void initialiseListView(View parentView) {
        listView = (ListView) parentView.findViewById(R.id.lobby_people);
        listView.setOnItemLongClickListener(this);
    }

    public View initialiseBackButton(View view) {
        final ImageView button = (ImageView) view.findViewById(R.id.finished_add_people_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getMainActivity().displayDetailFragment();
                getMainActivity().displayMeetingRoomFragment();
            }
        });
        return button;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
        Toast.makeText(getMainActivity().getApplicationContext(), "Drag to Enter -->", Toast.LENGTH_LONG).show();
        initiateDragPerson(view, position);
        return true;
    }

    private void initiateDragPerson(View view, int position) {
        Person person = (Person) listView.getItemAtPosition(position);
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
                        mainActivity.displayMeetingRoomFragment(); return true;
                    }
                }
                return false;
            }
        });
        view.startDrag(data, shadowBuilder, null, 0);
    }

    public void refreshView(List<Person> people) {
        ArrayAdapter<Person> adapter = new PeopleAdaptor(getMainActivity(), R.layout.row_attendee_white, people);
        listView.setAdapter(adapter);
    }


}
