package com.example.covid19;

import java.util.ArrayList;

public class api {
    ArrayList<model> statewise;

    public api(ArrayList<model> statewise) {
        this.statewise = statewise;
    }

    public ArrayList<model> getStatewise() {
        return statewise;
    }

    public void setStatewise(ArrayList<model> statewise) {
        this.statewise = statewise;
    }
}
