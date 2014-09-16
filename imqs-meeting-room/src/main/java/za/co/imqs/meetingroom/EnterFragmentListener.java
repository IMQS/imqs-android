package za.co.imqs.meetingroom;

import android.content.ClipData;
import android.view.DragEvent;
import android.view.View;

/**
 * Created by donovan on 2014/08/13.
 */
public class EnterFragmentListener implements View.OnDragListener {

    private MainActivity mainActivity = null;

    public EnterFragmentListener(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }



    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DROP: {
                ClipData clipData = dragEvent.getClipData();
                Person person = mainActivity.getPersonById(new Integer(clipData.getItemAt(0).getText().toString()));
                mainActivity.getLobby().personExitTo(person, mainActivity.getMeetingRoom());
                mainActivity.getMeetingRoomFragment().refreshView(mainActivity.getMeetingRoom().getPeople());
                mainActivity.getLobbyFragment().refreshView(mainActivity.getLobby().getPeople());


            }

        }
        return true;
    }
}
