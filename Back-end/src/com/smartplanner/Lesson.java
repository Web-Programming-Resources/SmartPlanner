package com.smartplanner;

import java.util.ArrayList;

public class Lesson {
    private String name;
    private int repeatingPeriod;
    private ArrayList<Term> terms;
    private int id;

    public Lesson(int id, String name, ArrayList<Term> terms, int repeatingPeriod) {
        this.id = id;
        this.name = name;
        this.terms = terms;
        this.repeatingPeriod=repeatingPeriod;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Term> getTerms() {
        return terms;
    }

    public String getName() {
        return name;
    }

    public int getRepeatingPeriod() {
        return repeatingPeriod;
    }
};
