package com.irctc.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.irctc.entities.Train;
import com.irctc.services.TrainService;

public class TrainServiceImpl implements TrainService
{
    private List<Train> trainList;

    public TrainServiceImpl()
    {
        this.trainList = new ArrayList<>(); //will be loaded from DB
    }

    @Override
    public void addTrain(Train train) {
        trainList.add(train);
    }

    @Override
    public List<Train> searchTrain(String source, String destination) {
        return trainList.stream()
        .filter(train -> validTrain(train,source,destination))
        .collect(Collectors.toList()) ;
    }
    
    private boolean validTrain(Train train ,String source,String destination)
    {
        List<String> stations = train.getStations();

        int sourceIndex = stations.indexOf(source);
        int destinationIndex = stations.indexOf(destination);

        return sourceIndex != -1 && destinationIndex != -1 && sourceIndex < destinationIndex;
    }
}
