package hmm_statistic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author yuan
 *
 */
public class Event implements Comparable<Event> {

    private String CaseID;
    private String Activity;
    private long StartTime;
    private long CompleteTime;
    private String Notes;

    /**
     * @param id
     * @param Activity
     * @param StartTime
     * @param CompleteTime
     * @param Timestamp
     */
//    public Event(String CaseID, String Activity, int StartTime, int CompleteTime,
//            String Timestamp) {
//        super();
//        this.CaseID = CaseID;
//        this.Activity = Activity;
//        this.StartTime = StartTime;
//        this.CompleteTime = CompleteTime;
//        this.Notes = Timestamp;
//    }

    public Event(String CaseID, String Activity, long StartTime, long CompleteTime,

            String Timestamp) {
        super();
        this.CaseID = CaseID;
        this.Activity = Activity;
        this.StartTime = StartTime;
        this.CompleteTime = CompleteTime;
        /**
         * This is added to fix the bug that timestamp might over 60 mins
         */
        if (this.CompleteTime - this.StartTime < 0) {
            this.CompleteTime += 3600;
        }
        this.Notes = Timestamp;
    }

    /**
     * @return the id
     */
    public String getId() {
        return CaseID;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.CaseID = id;
    }

    /**
     * @return the Activity
     */
    public String getActivity() {
        return Activity;
    }

    /**
     * @param Activity the Activity to set
     */
    public void setActivity(String Activity) {
        this.Activity = Activity;
    }

    /**
     * @return the StartTime
     */
    public long getStartTime() {
        return StartTime;
    }

    /**
     * @param StartTime the StartTime to set
     */
    public void setStartTime(int StartTime) {
        this.StartTime = StartTime;
    }

    /**
     * @return the CompleteTime
     */
    public long getCompleteTime() {
        return CompleteTime;
    }

    /**
     * @param CompleteTime the CompleteTime to set
     */
    public void setCompleteTime(int CompleteTime) {
        this.CompleteTime = CompleteTime;
    }

    /**
     * @return the Timestamp
     */
    public String getTimestamp() {
        return Notes;
    }

    /**
     * @param Timestamp the Timestamp to set
     */
    public void setTimestamp(String Timestamp) {
        this.Notes = Timestamp;
    }

    

    public Date timeFormat(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        Date date = new Date();
        try {
            date = sdf.parse(time);
            return date;
        } catch (ParseException ex) {
            Logger.getLogger(EventTrace.class.getName()).log(Level.SEVERE, null, ex);
            return date;
        }
    }

    @Override
    public int compareTo(Event o) {
        return this.Activity.compareTo(o.Activity); //To change body of generated methods, choose Tools | Templates.
    }

}
