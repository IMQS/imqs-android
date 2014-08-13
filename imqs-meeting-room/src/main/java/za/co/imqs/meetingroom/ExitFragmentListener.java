package za.co.imqs.meetingroom;

import android.content.ClipData;
import android.view.DragEvent;
import android.view.View;

import java.util.List;

/**
 * Created by donovan on 2014/08/13.
 */
public class ExitFragmentListener implements View.OnDragListener {

    private MainActivity mainActivity = null;

    public ExitFragmentListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED: {
                mainActivity.displayExitFragment();
                return true;
            }
            case DragEvent.ACTION_DROP: {
                ClipData clipData = dragEvent.getClipData();
                Person person = mainActivity.getPersonById(new Integer(clipData.getItemAt(0).toString()));
                List<Person> meetingRoomPeople = mainActivity.getMeetingRoom().getPeople();
                mainActivity.getLobby().movePerson(person, meetingRoomPeople);
                mainActivity.getMeetingRoomFragment().refreshView(meetingRoomPeople);
            }
            case DragEvent.ACTION_DRAG_ENDED: {
                mainActivity.displayDetailFragment();
                return true;
            }
        }
        return true;
    }
}
