package za.co.imqs.meetingroom;

import java.util.Calendar;
import java.util.List;

/**
 * Holds info related to the details of a meeting
 * Created by donovan on 2014/08/10.
 */
public class MeetingDetail {

    Calendar startTime;
    Calendar endTime;
    String description;

    private static List<Person> outsidePeople;
    private static List<Person> insidePeople;

    private static MeetingDetail meetingDetail = null;

    /**
     * Singleton implementation
     */
    public static MeetingDetail getMeetingDetail() {
        return meetingDetail == null ? createNewDetail() : meetingDetail;
    }

    public void reset() {
        meetingDetail = createNewDetail();
    }

    private static MeetingDetail createNewDetail() {
        meetingDetail = new MeetingDetail();
        meetingDetail.startTime = getTimeFromHourMinuteArray(null);
        meetingDetail.endTime = getTimeFromHourMinuteArray(null);
        meetingDetail.endTime.set(Calendar.MINUTE, 0);
        meetingDetail.endTime.set(Calendar.HOUR_OF_DAY, meetingDetail.startTime.get(Calendar.HOUR_OF_DAY) + 1);
        meetingDetail.setDescription("No Meeting Description Set");
        return meetingDetail;
    }

    public void setStartTime(int hour, int min) {

        this.startTime = getTimeFromHourMinuteArray(new Integer[]{hour, min});
    }
    public void setEndTime(int hour, int min) {
        this.endTime = getTimeFromHourMinuteArray(new Integer[]{hour, min});
    }

    public int getStartTimeHour() {
        return startTime.get(Calendar.HOUR_OF_DAY);
    }
    public int getStartTimeMin() {
        return startTime.get(Calendar.MINUTE);
    }
    public int getEndTimeHour() {
        return endTime.get(Calendar.HOUR_OF_DAY);
    }
    public int getEndTimeMin() {
        return endTime.get(Calendar.MINUTE);
    }

    private static Calendar getTimeFromHourMinuteArray(Integer[] hourMinute) {
        Calendar calendar = Calendar.getInstance();
        if (hourMinute != null) {
            calendar.set(Calendar.HOUR, hourMinute[0]);
            calendar.set(Calendar.MINUTE, hourMinute[1]);
        }
        return calendar;
    }

    /* --- Getters and Setters --- */

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
