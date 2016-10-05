/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hmm_statistic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sen
 */
public class EventTrace {

    private String traceID;

    /* length of trace */
    private int traceLength;

    /* the number of activities (no repetition) in the eventTrace */
    //private int traceActivityNum;

    /* activities (no repetition) in the eventTrace */
    private HashSet<String> traceActivitySet = new HashSet<String>();

    /* Duration of trace: start of the first
    activity to the end of the last activity */
    private long traceDuration;

    /* List of Events */
    public ArrayList<Event> events;

    /* only create a empty list when constructed */
    public EventTrace(String traceid) {
        traceID = traceid;
        traceLength = 0;
        //maybe delete because of no usage
        //traceActivityNum = 0;
        traceDuration = 0;
        events = new ArrayList<Event>();
    }

    public EventTrace() {
        traceLength = 0;
        traceDuration = 0;
        //traceActivityNum = 0;
        events = new ArrayList<Event>();
    }

    /**
     * @param event
     */
    public void add(Event event) {
        if (!event.getActivity().equals("-")) {
            traceID = event.getId();
        }

        events.add(event);
        traceLength++;
        traceActivitySet.add(event.getActivity());

        //point 1
//        traceActivityNum = traceActivitySet.size();
//        traceDuration = event.getCompleteTime() - events.get(0).getStartTime();
//        traceDuration = traceDuration + event.getEventDuration();
    }

    public void add(Event event, int index) {
        if (!event.getActivity().equals("-")) {
            traceID = event.getId();
        }
        events.add(index, event);
        traceLength++;
        traceActivitySet.add(event.getActivity());

        //point 1
//        traceActivityNum = traceActivitySet.size();
//        traceDuration = event.getCompleteTime() - events.get(0).getStartTime();
//        traceDuration = traceDuration + event.getEventDuration();
    }

    public void deleteEvent(int index) {
        if (index < this.traceLength && index >= 0) {
            traceLength--;
//            traceDuration = traceDuration - events.get(index).getEventDuration();
            String activityname = events.get(index).getActivity();
            events.remove(index);
            if (!CheckActivitySet(activityname)) {
                traceActivitySet.remove(activityname);
            }
        }
    }

    private boolean CheckActivitySet(String activity_name) {
        for (Event e : events) {
            if (e.getActivity().equals(activity_name)) {
                return true;
            }
        }
        return false;
    }

    public void addDash() {
        int index = events.size() - 1;
        //Event dash = new Event(traceID, "-", events.get(index).getCompleteTime(), events.get(index).getCompleteTime(), "dash");
        Event dash = new Event("-", "-", -1, -1, "dash");
        events.add(dash);
        traceLength++;
    }

    /**
     * @desc reverse the current trace
     * @return
     */
    public EventTrace reverse() {
        EventTrace reversedTrace = new EventTrace();
        for (int i = this.getEvents().size() - 1; i >= 0; i--) {
            reversedTrace.add(this.getEvents().get(i));
        }
        return reversedTrace;
    }

    /**
     *
     * @return
     */
    public int getTraceLength() {
        return traceLength;
    }

    /**
     *
     * @return
     */
    public long getTraceDuration() {
        return traceDuration;
    }

    /**
     *
     * @return
     */
    public HashSet<String> getTraceActivitySet() {
        return traceActivitySet;
    }

    /**
     *
     * @param time
     * @return
     */
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

    public ArrayList<Event> getEvents() {
        return events;
    }

    public String[] getActivities() {
        List<String> activities = new ArrayList();
        int i = 0;
        for (Event e : events) {
            activities.add(e.getActivity());
        }
        return activities.toArray(new String[0]);
    }

    public String getTraceID() {
        return traceID;
    }

//    public int getTraceActivityNum() {
//        return traceActivityNum;
//    }
    /**
     * @desc contains means another trace is fully contained (subsumed) by "this
     * trace". Here the "contain" only refers to the list of activities. Other
     * properties are not considered
     * @param anOtherTrace
     * @return
     */
//    public boolean contains(EventTrace anOtherTrace){
//        for()
//        
//        
//        return true;
//    }
}
