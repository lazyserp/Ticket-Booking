package com.irctc.services;

import com.irctc.entities.Train;
import java.util.List;

public interface TrainService
{
    //Admin adds a train
    void addTrain(Train train);
    Train getTrain(String trainNumber);

    //Search for a Train
    List<Train> searchTrain(String source , String destination);

}