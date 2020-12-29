package com.example.bigsteps;

public interface StepsListener {

    //the interface will listen to alerts from other class about steps being detected
    public void step(long timeNs);
}
