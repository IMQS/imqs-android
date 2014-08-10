package za.co.imqs.meetingroom;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TimePicker;

/**
 * This indicates the details of a meeting
 * @author donovan
 */
public class DetailFragment extends Fragment implements View.OnClickListener {

    Detail detail = null;
    Activity parentActivity = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_detail, container, false);
        result.setBackgroundColor(Color.parseColor("#DFECF5"));
        detail = Detail.getDetail();
        result = initiateButtons(result);
        return result;
    }

    public View initiateButtons(View view) {
        final View startTime = (View) view.findViewById(R.id.fragment_detail_start_time);
        final View endTime = (View) view.findViewById(R.id.fragment_detail_end_time);
        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);
        return view;
    }

	@Override
	public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
	}

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
	
	@Override
	public void onPause() {
		super.onPause();
	}
	
	@Override
	public void onResume() {
		super.onResume();
	}

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_detail_start_time: { onClickStartTime(view, detail); return;}
            case R.id.fragment_detail_end_time: { onClickEndTime(view, detail); return;}
        }
    }

    /**
     * TODO Refacrotr onClickStartTime() and onClickEndTime() to be DRY
     * Opens the Start Time Dialog
     */
    public View onClickStartTime(final View view, final Detail detail) {
        TimePickerDialog dialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                detail.setStartTime(hour, minute);
                ((EditText)view).setText(hour + ":" + minute);
            }
        }, this.detail.getStartTimeHour(), this.detail.getStartTimeMin(), true);
        dialog.setTitle("Select Meeting Start Time");
        dialog.show();
        return view;
    }

    /**
     * Opens the End Time Dialog
     */
    public View onClickEndTime(final View view, final Detail detail) {
        TimePickerDialog dialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                detail.setEndTime(hour, minute);
                ((EditText)view).setText( hour + ":" + minute);
            }
        }, this.detail.getStartTimeHour(), this.detail.getStartTimeMin(), true);
        dialog.setTitle("Select Meeting End Time");
        dialog.show();
        return view;
    }
}
