package com.smartplanner;

import java.util.ArrayList;

public class Activity {
    private int ID;
    static private int objectsCreated=0;


    private String name;
    private int repeatEvery;

    private ArrayList<Term> terms;

    public Activity(String name, ArrayList<Term> terms) {
        this.name = name;
        this.terms=terms;
        this.ID=objectsCreated;
        ++objectsCreated;
    }

    public ArrayList<Term> getTerms() {
        return terms;
    }

    public int getID() {
        return ID;
    }
}


