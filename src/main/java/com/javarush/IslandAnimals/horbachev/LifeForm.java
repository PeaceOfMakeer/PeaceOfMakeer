package com.javarush.IslandAnimals.horbachev;

public abstract class LifeForm  {

    double weight = 0;
    boolean alive = true;

    double satietyWithFoodCurrent; // Сколько животное сьело еды
    double satiationWithFood; // Максимально возможное насыщение

    int yProbability;
    String kind;

    public LifeForm(String kind, int yProbability) {
        this.kind = kind;
        this.yProbability = yProbability;
    }
}
