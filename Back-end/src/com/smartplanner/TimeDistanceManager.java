package com.smartplanner;

import java.util.ArrayList;

public class TimeDistanceManager {
    private ArrayList<ArrayList<Integer>> timeDistanceInMin; //timeDistance[from ID][to ID]

    public TimeDistanceManager(ArrayList<ArrayList<Integer>> timeDistanceInMin) {
        this.timeDistanceInMin = timeDistanceInMin;
    }

    public int getTimeDistanceInMin(int fromID, int toID){
        return timeDistanceInMin.get(fromID).get(toID);
    }
}
