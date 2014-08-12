package za.co.imqs.meetingroom;

import android.content.Context;
import android.view.LayoutInflater;
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
public class AttendeesAdaptor extends ArrayAdapter<Attendee> {

    HashMap<Attendee, Integer> attendeeToIdMap = new HashMap<Attendee, Integer>();
    Context context = null;
    List<Attendee> attendees;

    /**
     * Constructor for creating an AttendeeAdaptor
     */
    public AttendeesAdaptor(Context context, int viewResourceId, List<Attendee> attendees) {
        super(context, viewResourceId, attendees);
        this.context = context;
        for (int i = 0; i < attendees.size(); ++i)
            attendeeToIdMap.put(attendees.get(i), i);
        this.attendees = attendees;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Attendee attendee = attendees.get(position);
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_attendee, parent, false);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.avatar);
        imageView.setImageResource(R.drawable.ic_launcher); // TODO Find out how to reference the correct Id here

        TextView firstName = (TextView) rowView.findViewById(R.id.firstName);
        firstName.setText(attendee.firstName);

        TextView lastName = (TextView) rowView.findViewById(R.id.lastName);
        lastName.setText(attendee.lastName);

        return rowView;
    }

    @Override
    public long getItemId(int position) {
        Attendee item = getItem(position);
        return attendeeToIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
