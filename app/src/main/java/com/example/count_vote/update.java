package com.example.count_vote;

public class update {

    public String v1;
    public String v2;
    public String v3;
    public String electionGoingOn;

    public update() {
    }

    public update(String v1, String v2, String v3, String electionGoingOn) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.electionGoingOn = electionGoingOn;
    }

    public String getV1() {
        return v1;
    }

    public void setV1(String v1) {
        this.v1 = v1;
    }

    public String getV2() {
        return v2;
    }

    public void setV2(String v2) {
        this.v2 = v2;
    }

    public String getV3() {
        return v3;
    }

    public void setV3(String v3) {
        this.v3 = v3;
    }

    public String getElectionGoingOn() {
        return electionGoingOn;
    }

    public void setElectionGoingOn(String electionGoingOn) {
        this.electionGoingOn = electionGoingOn;
    }
}
