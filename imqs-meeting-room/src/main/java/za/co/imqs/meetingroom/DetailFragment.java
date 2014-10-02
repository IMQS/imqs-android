package za.co.imqs.meetingroom;

import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.TimePicker;
/**
 * This indicates the details of a meeting
 * @author donovan
 */
public class DetailFragment extends Fragment implements View.OnClickListener{



    MeetingDetail meetingDetail = null;
    MainActivity mainActivity = null;
    GridView listView = null;

    public  int hour;
    public int minute;

    public AutoCompleteTextView My_auto_Cmplt_Tv;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity)getActivity();
        meetingDetail = MeetingDetail.getMeetingDetail();

        View result = inflater.inflate(R.layout.fragment_detail, container, false);
        result.setBackgroundColor(getResources().getColor(R.color.imqs_blue));


        final String[] MeetingNames= getResources().getStringArray(R.array.MeetingNames);

        ArrayAdapter<String> My_arr_adapter= new ArrayAdapter<String>(getActivity(),
        R.layout.simple_dropdown_item_1line,MeetingNames);
         My_auto_Cmplt_Tv=(AutoCompleteTextView)result.findViewById(R.id.autoNames);

        My_auto_Cmplt_Tv.setThreshold(1);
        My_auto_Cmplt_Tv.setAdapter(My_arr_adapter);
        My_auto_Cmplt_Tv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {

         My_auto_Cmplt_Tv.getDropDownBackground();
            }
        });

        result = initiateButtons(result);




        return result;
    }

    public View initiateButtons(View view) {
        final View startTime;
        startTime = (View) view.findViewById(R.id.fragment_detail_start_time);
        final View endTime;
        endTime = (View) view.findViewById(R.id.fragment_detail_end_time);

        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);

        if(meetingDetail != null){
            refreshStartTime(view, meetingDetail);
            refreshEndTime(view, meetingDetail);
        }
        EndMeting(view);

       return view;
    }

    public View refreshStartTime(View parentView, MeetingDetail meetingDetail) {
        View startTime = (View) parentView.findViewById(R.id.fragment_detail_start_time);
        ((TextView)startTime).setText(meetingDetail.getStartTimeHour() + ":" + meetingDetail.getStartTimeMin());
        return startTime;
    }

    public View refreshEndTime(View parentView, MeetingDetail meetingDetail) {

        View endTime = (View) parentView.findViewById(R.id.fragment_detail_end_time);
        ((TextView)endTime).setText(meetingDetail.getEndTimeHour() + ":" + meetingDetail.getEndTimeMin());

        return endTime;
    }


    public View EndMeting(View v) {
        final Button button = (Button) v.findViewById(R.id.EndMeeting);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                My_auto_Cmplt_Tv.setText("");
            }
        });
        return button;
    }



    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public void onStop() {
        super.onStop();
        meetingDetail.getStartTimeMin();
        meetingDetail.getEndTimeMin();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        meetingDetail.getStartTimeMin();
        meetingDetail.getEndTimeMin();
    }

    @Override
    public void onPause() {
        super.onPause();
        meetingDetail.getStartTimeMin();
        meetingDetail.getEndTimeMin();
    }

    @Override
    public void onResume() {
        super.onResume();
        meetingDetail.getStartTimeMin();
        meetingDetail.getEndTimeMin();
    }
    public void onRestart(){
        meetingDetail.getStartTimeMin();
        meetingDetail.getEndTimeMin();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_detail_start_time: { onClickStartTime(view, meetingDetail); return;}
            case R.id.fragment_detail_end_time: { onClickEndTime(view, meetingDetail); return;}

        }
    }

    /**
     * TODO Refacrotr onClickStartTime() and onClickEndTime() to be DRY
     * Opens the Start Time Dialog
     */
    public View onClickStartTime(final View view, final MeetingDetail meetingDetail) {
        TimePickerDialog dialog = new TimePickerDialog(mainActivity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int selectedMinute) {

                hour = hourOfDay;
                minute = selectedMinute;

                ((TextView)view).setText(new StringBuilder().append(pad(hour)).append(":").append(pad(minute)));
                meetingDetail.setStartTime(hour, minute);


            }
        }, this.meetingDetail.getStartTimeHour(),this.meetingDetail.getStartTimeMin(), true);
        dialog.setTitle("Select Meeting Start Time");
        dialog.show();
        return view;
    }


    /**
     * Opens the End Time Dialog
     */
    public View onClickEndTime(final View view, final MeetingDetail meetingDetail) {
        TimePickerDialog dialog = new TimePickerDialog(mainActivity, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int selectedMinute) {
                hour = hourOfDay;
                minute = selectedMinute;


                ((TextView)view).setText(new StringBuilder().append(pad(hour)).append(":").append(pad(minute)));
                meetingDetail.setEndTime(hour, minute);



            }

        }, this.meetingDetail.getEndTimeHour(), this.meetingDetail.getStartTimeMin(), true);
        dialog.setTitle("Select Meeting End Time");
        dialog.show();
        return view;
    }
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }

 }
