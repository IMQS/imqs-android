package za.co.imqs.meetingroom;

import android.view.View;

/**
 * This ensures that the implementing class is capable of initialising a Person to drag
 * Created by PhikoN on 2014-09-04.
 */
public interface PersonDragInterface {
    public void initiateDragPerson(View rowView, Person personToDrag);

}
