package com.smartplanner;

import com.smartplanner.BruteForcer;


import java.util.ArrayList;

public class SmartPlanner
{
    private TimeDistanceManager timeDistanceManager;
    private ArrayList<Activity> activities;
    private int cycleLength;

    public SmartPlanner(ArrayList<Activity> activities, ArrayList<ArrayList<Integer>> travelTime, int cycleLength)
    {
        this.activities=activities;
        timeDistanceManager=new TimeDistanceManager(travelTime);
        this.cycleLength=cycleLength;
    }

    ArrayList<TimetableEntry> calculate()
    {
        //TODO might be used later
        /*        validateActivities() //TODO: check if fits in cycleLength
        int maxTimeSpentOnOptimizedActivity=0;
        ArrayList<TimetableEntry> bestTimeTable=null;

        ArrayList<TimetableEntry> possibleTimeTable;

        BruteForcer bruteForcer(activities);
        while(bruteForcer.isNext())
        {
            possibleTimeTable = bruteForcer.getNext();

            if(!isValidTimetable(possibleTimeTable))
                continue;

            int timeSpentOnOptimizedActivity=calculateTimeSpentOnOptimizedActivity(possibleTimeTable);
            if(timeSpentOnOptimizedActivity>maxTimeSpentOnOptimizedActivity)
            {
                maxTimeSpentOnOptimizedActivity=timeSpentOnOptimizedActivity;
                bestTimeTable=possibleTimeTable;
            }
        }

        return bestTimeTable;*/

        return null;

    }



}
