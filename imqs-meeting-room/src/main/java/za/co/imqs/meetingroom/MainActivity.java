package za.co.imqs.meetingroom;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import za.co.imqs.meetingroom.util.PeopleJsonReader;

public class MainActivity extends Activity implements DetailFragment.EndMeetingInterface {

    private static Room meetingRoom = null;
    private static Room lobby = null;


    public static DetailFragment detailFragment = null;
    public static MeetingRoomFragment meetingRoomFragment = null;
    public static ExitFragment exitFragment = null;
    public static EnterFragment enterFragment = null;
    public static LobbyFragment lobbyFragment = null;
    public MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        View fragmentContainer = findViewById(R.id.container_fragment);
        View detailContainer = fragmentContainer.findViewById(R.id.container_detail);
        View meetingRoomContainer = fragmentContainer.findViewById(R.id.container_meeting_room);
        View lobbyContainer = fragmentContainer.findViewById(R.id.container_lobby);

        if (detailContainer != null && meetingRoomContainer != null && lobbyContainer != null ) {
            if (savedInstanceState != null)
                return;
            addFragments(detailContainer.getId(), getDetailFragment());
            addFragments(meetingRoomContainer.getId(), getMeetingRoomFragment());
            addFragments(lobbyContainer.getId(), getLobbyFragment());
        }

        initialiseRooms();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_room,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);

        switch(item.getItemId()){
            case R.id.action_settings:
                finish();
                System.exit(0);
                break;             }
        return true;
    }


    public void initialiseRooms() {
        lobby = getLobby();
        meetingRoom = getMeetingRoom();
    }

    private <F extends Fragment> void addFragments(int container, F... fragments) {
        FragmentTransaction tx = getFragmentManager().beginTransaction();
        for (F f : fragments) {
            f.setArguments(new Bundle());
            tx.add(container, f);
        }
        tx.commit();
    }

    public void displayExitFragment() {
        this.replaceFragment(R.id.container_detail, getExitFragment());
    }

    public void displayLobbyFragment() {
        this.replaceFragment(R.id.container_lobby, getLobbyFragment());
    }

    public void displayEnterFragment() {
        this.replaceFragment(R.id.container_meeting_room, getEnterFragment());
    }

    public void displayDetailFragment() {
        this.replaceFragment(R.id.container_detail, getDetailFragment());
    }
    public void displayMeetingRoomFragment() {

        this.replaceFragment(R.id.container_meeting_room, getMeetingRoomFragment());
    }

    public void replaceFragment(int container, Fragment fragment) {
        if (!fragment.isAdded()) {
            FragmentTransaction tx = getFragmentManager().beginTransaction();
            fragment.setArguments(new Bundle());
            tx.replace(container, fragment);
            tx.commit();
        }
    }

    /* --- Singleton Accessors --- */

    public Room getLobby() {
        if (lobby == null)
            lobby = new Room(getResources().getString(R.string.room_lobby), new PeopleJsonReader().getPeople(this));
        return lobby;
    }

    public Room getMeetingRoom() {
        if (meetingRoom == null)
            meetingRoom = new Room(getResources().getString(R.string.room_boardroom));
        return meetingRoom;
    }

    public DetailFragment getDetailFragment() {
        if (detailFragment == null)
            detailFragment = new DetailFragment();
        return detailFragment;
    }

    public MeetingRoomFragment getMeetingRoomFragment() {
        if (meetingRoomFragment == null)
            meetingRoomFragment = new MeetingRoomFragment();
        return meetingRoomFragment;
    }

    public LobbyFragment getLobbyFragment() {
        if (lobbyFragment == null)
            lobbyFragment = new LobbyFragment();
        return lobbyFragment;
    }

    public ExitFragment getExitFragment() {
        if (exitFragment == null)
            exitFragment = new ExitFragment();
        return exitFragment;
    }

    public EnterFragment getEnterFragment() {
        if (enterFragment == null)
            enterFragment = new EnterFragment();

        return   enterFragment;

    }

    public Person getPersonById(int id) {
        Person result = getPersonFromLobbyById(id);
        return result == null ? getPersonFromMeetingRoomById(id) : result;
    }

    public Person getPersonFromLobbyById(int id) {
        return getPersonFromRoomById(lobby, id);
    }

    public Person getPersonFromMeetingRoomById(int id) {
        return getPersonFromRoomById(meetingRoom, id);
    }

    private Person getPersonFromRoomById(Room room, int id) {
        for (Person person : room.getPeople())
            if (person.id == id)
                return person;
        return null;
    }

    @Override
    public void endMeeting(View view) {
        getMeetingRoomFragment().endMeting(view);
        }

}
