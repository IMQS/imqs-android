package za.co.imqs.meetingroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Preparing a row for the list
 *
 * The adapter needs to create a layout for each row of the list.
 * The ListView instance calls the getView() method on the adapter for each data element.
 * In this method the adapter creates the row layout and maps the data to the views in the layout.
 * This root of the layout is typically a ViewGroup (layout manager) and contains several other views
 *
 * Created by donovan on 2014/08/10.
 */
public class PeopleAdaptor extends ArrayAdapter <Person> implements View.OnTouchListener {

    HashMap<Person, Integer> attendeeToIdMap = new HashMap<Person, Integer>();
    Context context = null;
    List<Person> persons;
    PersonDragInterface dragger = null;



    /**
     * Constructor for creating an AttendeeAdaptor
     */
    public PeopleAdaptor(Context context, int viewResourceId, List<Person> persons) {
        super(context, viewResourceId, persons);
        this.context = context;
        for (int i = 0; i < persons.size(); ++i)
           attendeeToIdMap.put(persons.get(i), i);



        this.persons = persons;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Person person = persons.get(position);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int parentId = parent.getId();
        int idToInflate = parentId == R.id.lobby_people ? R.layout.row_attendee_black : R.layout.row_attendee_white;
        View rowView = inflater.inflate(idToInflate, parent, false);

            ImageView imageView = (ImageView) rowView.findViewById(R.id.avatar);
            imageView.setImageResource(R.drawable.ic_launcher); // TODO Find out how to reference the correct Id here

            TextView firstName = (TextView) rowView.findViewById(R.id.firstName);
            firstName.setText(person.firstName);

            TextView lastName = (TextView) rowView.findViewById(R.id.lastName);
            lastName.setText(person.lastName);

        rowView.setTag(person);
        rowView.setOnTouchListener(this);

        return rowView;
    }

    public  boolean onTouch(View rowView, MotionEvent motionEvent) {
        switch(motionEvent.getAction()) {
            case MotionEvent.ACTION_MOVE: {
                dragger.initiateDragPerson(rowView, (Person) rowView.getTag());
                break;
            }
        }
        return true;
    }

    @Override
    public long getItemId(int position) {Person item = getItem(position);return attendeeToIdMap.get(item);}

    @Override
    public boolean hasStableIds() {
        return true;
    }

    public PersonDragInterface getDragger() {
        return dragger;
    }

    public void setDragger(PersonDragInterface dragger) {
        this.dragger = dragger;
    }

}
