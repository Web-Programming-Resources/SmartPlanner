package com.smartplanner;

public class Task {
    private String name;
    private int repeatingPeriod;
    private Term term;

    public Task(String name, int repeatingPeriod, Term term) {
        this.name = name;
        this.repeatingPeriod = repeatingPeriod;
        this.term = term;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRepeatingPeriod() {
        return repeatingPeriod;
    }

    public void setRepeatingPeriod(int repeatingPeriod) {
        this.repeatingPeriod = repeatingPeriod;
    }

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }
}
