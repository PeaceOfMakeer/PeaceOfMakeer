package com.javarush.IslandAnimals.horbachev;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Predator extends Animal{


    public Predator(String kind, double weight, int speed, double satiationWithFood, UUID uuid, int xProbability, int yProbability) {
        super(kind, weight, speed, satiationWithFood, uuid, xProbability, yProbability);
    }

    public Predator reproduction(){
//        System.out.println("Reproduction Predators");
        return new Predator(this.kind, this.weight, this.speed, this.satiationWithFood, this.uuid, this.xProbability, this.yProbability);
    }



    public void eat(Animal food) {
        int probability = this.getProbability(food.yProbability);
        int probabilityFood = this.getProbability(this.yProbability);

        boolean probabilityToEat = ThreadLocalRandom.current().nextDouble() < probability;
        boolean probabilityToEatFood = ThreadLocalRandom.current().nextDouble() < probabilityFood;

        if(probabilityToEat && !probabilityToEatFood) {
            this.eatProcessing(food, this);
        }else if(probabilityToEatFood && !probabilityToEat){
            this.eatProcessing(this, food);
        }
    }
    public void eatProcessing(Animal food, Animal predator){
        if (predator.satiationWithFood >= food.weight) {
            predator.satietyWithFoodCurrent = predator.satietyWithFoodCurrent + food.weight;
            food.alive = false;
        } else {
            predator.satietyWithFoodCurrent = predator.satiationWithFood;
            food.weight = food.weight - predator.satiationWithFood;
        }
    }
}
