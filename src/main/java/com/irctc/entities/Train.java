package com.irctc.entities;

import java.util.List;
import java.util.Map;

public class Train
{
    private String trainId;
    private String trainNo;
    private List<List<Integer>> seats; // 2D matrix to store seat allocation
    private Map<String,String> stationTimes; // Map to store Station Name ->  Time
    private List<String> stations ; // List to maintain route sequence of stations

    public Train(String trainId, String trainNo, List<List<Integer>> seats, Map<String, String> stationTimes,
            List<String> stations) {
        this.trainId = trainId;
        this.trainNo = trainNo;
        this.seats = seats;
        this.stationTimes = stationTimes;
        this.stations = stations;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public void setStationTimes(Map<String, String> stationTimes) {
        this.stationTimes = stationTimes;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }

    public String getTrainId() {
        return trainId;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public Map<String, String> getStationTimes() {
        return stationTimes;
    }

    public List<String> getStations() {
        return stations;
    }
    
    public String getTrainInfo() {
        return String.format("trainId : %s | trainNo : %s", trainId, trainNo);
    }


    
}
