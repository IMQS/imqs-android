package za.co.imqs.meetingroom;

import java.util.Calendar;

/**
 * Holds info related to the details of a meeting
 * Created by donovan on 2014/08/10.
 */
public class Detail {

    Calendar startTime;
    Calendar endTime;
    String description;

    private static Detail detail = null;

    /**
     * Singleton implementation
     */
    public static Detail getDetail() {
        return detail == null ? createNewDetail() : detail;
    }

    public void reset() {
        detail = createNewDetail();
    }

    private static Detail createNewDetail() {
        detail = new Detail();
        detail.startTime = getTimeFromHourMinuteArray(null);
        detail.endTime = getTimeFromHourMinuteArray(null);
        detail.endTime.set(Calendar.MINUTE, 0);
        detail.endTime.set(Calendar.HOUR, detail.startTime.get(Calendar.HOUR) + 1);
        detail.setDescription("No Meeting Description Set");
        return detail;
    }

    public void setStartTime(int hour, int min) {
        this.startTime = getTimeFromHourMinuteArray(new Integer[]{hour, min});
    }
    public void setEndTime(int hour, int min) {
        this.endTime = getTimeFromHourMinuteArray(new Integer[]{hour, min});
    }

    public int getStartTimeHour() {
        return startTime.get(Calendar.HOUR);
    }
    public int getStartTimeMin() {
        return startTime.get(Calendar.MINUTE);
    }
    public int getEndTimeHour() {
        return endTime.get(Calendar.HOUR);
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
