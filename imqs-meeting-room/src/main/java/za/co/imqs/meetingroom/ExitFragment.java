package za.co.imqs.meetingroom;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * The drop area for people leaving the meeting meetingRoom
 * Created by donovan on 2014/08/12.
 */
public class ExitFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_exit, container, false);
    }



}
