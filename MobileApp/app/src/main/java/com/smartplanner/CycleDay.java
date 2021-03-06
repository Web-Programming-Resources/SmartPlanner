package com.smartplanner;

import java.util.ArrayList;

public class CycleDay {
    ArrayList<planEntry> planEntries;
    String name;

    public CycleDay(String name) {
        this.name = name;
        planEntries = new ArrayList<>();
    }

    public void addPlanEntry(String name, String startingTime, int duration){
        String endingTime = createEndingTime(startingTime, duration);
        planEntries.add(getIndexToAddAt(startingTime), new planEntry(startingTime, endingTime, name));
    }
    //@param String startTime - string in HH:MM format representing beggining time of task
    private String createEndingTime(String startTime, int duration){
        String timeParts[] = startTime.split(":");
        int startHour = Integer.parseInt(timeParts[0]);
        int startMinutes = Integer.parseInt(timeParts[1]);
        int endMinutes = startMinutes + duration;
        int endHour = startHour + endMinutes / 60;
        endMinutes -= (endMinutes / 60) * 60;

        StringBuilder builder = new StringBuilder();
        if(endHour < 10) // 09: cases etc
            builder.append("0");

        builder.append(endHour);
        builder.append(":");
        if(endMinutes < 10) // 09 cases etc
            builder.append("0");
        builder.append(endMinutes);
        return builder.toString();
    }
    //@param String startTime - string in HH:MM format representing beggining time of task
    private int getIndexToAddAt(String startTime){
        int index = 0;
        if(planEntries.isEmpty())
            index = 0;
        else {
            for (int i = 0; i < planEntries.size(); i++) {
                if (timeToMinutes(startTime) < timeToMinutes(planEntries.get(i).getStartingTime())) {
                    break;
                }
                index = i + 1;
            }
        }
        return index;
    }
    /*
    utility function to represent day in minutes format 24h - 1440min
    @param String hour - string in HH:MM format
    @return int representing minute in day [0:1440]
    */
    private int timeToMinutes(String hour){
        String timeParts[] = hour.split(":");
        return Integer.parseInt(timeParts[0])*60 + Integer.parseInt(timeParts[1]);

    }

    public ArrayList<planEntry> getPlanEntries() {
        return planEntries;
    }

    public String getName() {
        return name;
    }
}
