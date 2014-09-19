package za.co.imqs.meetingroom;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.net.ParseException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

//import za.co.imqs.meetingroom.util.PeopleJsonReader;

public class MainActivity extends Activity {

    private static Room meetingRoom = null;
    private static Room lobby = null;


    public static DetailFragment detailFragment = null;
    public static MeetingRoomFragment meetingRoomFragment = null;
    public static ExitFragment exitFragment = null;
    public static EnterFragment enterFragment = null;
    public static LobbyFragment lobbyFragment = null;

    final Calendar c = Calendar.getInstance();
    int  hour = c.get(Calendar.HOUR_OF_DAY);
    int  minute = c.get(Calendar.MINUTE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        new JSONAsyncTask().execute("http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors");

        View fragmentContainer = findViewById(R.id.container_fragment);
        View detailContainer = fragmentContainer.findViewById(R.id.container_detail);
        View meetingRoomContainer = fragmentContainer.findViewById(R.id.container_meeting_room);
        View lobbyContainer = fragmentContainer.findViewById(R.id.container_lobby);

        if (detailContainer != null && meetingRoomContainer != null) {
            if (savedInstanceState != null)
                return;
            addFragments(detailContainer.getId(), getDetailFragment());
            addFragments(meetingRoomContainer.getId(), getMeetingRoomFragment());
            addFragments(lobbyContainer.getId(), getLobbyFragment());
        }

        initialiseRooms();

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
           //lobby = new Room(getResources().getString(R.string.room_lobby), new PeopleJsonReader().getPeople(this));
           // lobby = new Room(getResources().getString(R.string.room_lobby), new JSONAsyncTask().execute("http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors"));
           lobby = new Room(getResources().getString(R.string.room_chillroom));
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
        return enterFragment;
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

    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {

        ProgressDialog dialog;
        ArrayList<Person> persons;
        PeopleAdaptor adapter;
        ListView listview;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading, please wait");
            dialog.setTitle("Connecting server");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {

                //------------------>>
                HttpGet httppost = new HttpGet(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);

                    JSONObject jsono = new JSONObject(data);
                    JSONArray attendees = jsono.getJSONArray("people");

                    for (int i = 0; i < attendees.length(); i++) {
                        JSONObject object = attendees.getJSONObject(i);

                        Person people = new Person();

                        people.setFirstName(object.getString("firstName"));
                        people.setLastName(object.getString("lastName"));
                        people.setAvatarPath(object.getString("avatarPath"));

                        persons.add(people);

                    }
                    return true;
                }

                //------------------>>

            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }
        @Override
        protected void onPostExecute(Boolean result) {

            listview = (ListView) findViewById(R.id.lobby_people);
            listview.setAdapter(adapter);
            dialog.dismiss();
            if(result == false)
            Toast.makeText(getApplicationContext(), "Unable to fetch data from server", Toast.LENGTH_LONG).show();

        }


    }
}
