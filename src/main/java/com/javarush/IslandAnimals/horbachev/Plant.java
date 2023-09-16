package com.javarush.IslandAnimals.horbachev;

import java.util.UUID;

public class Plant extends LifeForm{

    String kind;
    double weight;
    UUID uuid;
    int x;
    int y;

    public Plant(String kind, int weight, UUID uuid, int x, int y, int yProbability) {
        super(kind, yProbability);
        this.kind = kind;
        this.weight = weight;
        this.uuid = uuid;
        this.x = x;
        this.y = y;
    }

}


