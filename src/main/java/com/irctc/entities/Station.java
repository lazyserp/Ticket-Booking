package com.irctc.entities;

public class Station 
{
    private String stationName;
    private String stationId;

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public Station(String stationId, String stationName) {
        this.stationId = stationId;
        this.stationName = stationName;
    }

    public String getStationName() {
        return stationName;
    }

    public String getStationId() {
        return stationId;
    }
    
    //Overriding toString for debugging , bcz when we print the object it should print what values is the
    //object holding without overrdiing it will print the memoryAddress of the objct.
    public String getTicketInfo()
    {
        return "Station{" +"stationId='" + stationId + '\'' +", stationName='" + stationName + '\'' +'}';
    }

    
    
}
