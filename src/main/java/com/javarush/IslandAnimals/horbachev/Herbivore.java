package com.javarush.IslandAnimals.horbachev;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Herbivore extends Animal{


    public Herbivore(String kind, double weight, int speed, double satiationWithFood, UUID uuid, int xProbability, int yProbability) {
        super(kind, weight, speed, satiationWithFood, uuid, xProbability, yProbability);
    }

    public Herbivore reproduction(){
//        System.out.println("Reproduction Herbivore");

        return new Herbivore(this.kind, this.weight, this.speed, this.satiationWithFood, this.uuid, this.xProbability, this.yProbability);
    }

    public void eat(LifeForm food) {
        int probability = getProbability(food.yProbability);
        int probabilityFood = getProbability(this.yProbability);

        boolean probabilityToEat = ThreadLocalRandom.current().nextDouble() < probability;
        boolean probabilityToEatFood = ThreadLocalRandom.current().nextDouble() < probabilityFood;

        if(probabilityToEat && !probabilityToEatFood) {
            this.eatProcessing(food, this);
        }else if(probabilityToEatFood && !probabilityToEat && food instanceof Animal){

                this.eatProcessing(this, food);
        }
    }
    public void eatProcessing(LifeForm food, LifeForm herbivore){
        if (herbivore.satiationWithFood >= food.weight) {
            herbivore.satietyWithFoodCurrent = herbivore.satietyWithFoodCurrent + food.weight;
            food.alive = false;
        } else {
            herbivore.satietyWithFoodCurrent = herbivore.satiationWithFood;
            food.weight = food.weight - herbivore.satiationWithFood;
        }
    }
}
