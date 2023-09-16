package com.javarush.IslandAnimals.horbachev;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends LifeForm {

    public static final int[][] PROBABILITIES =
                    {{0,0,  0,  0,  0,  10, 15, 60, 80, 60, 70, 15, 10, 40, 0,  0},
                    {0, 0,  15, 0,  0,  0,  0,  20, 40, 0,  0,  0,  0,  10, 0,  0},
                    {0,	0,  0,  0,  0,  0,  0,  70, 90, 0,  0,  0,  0,  60, 40, 0},
                    {0,	80,	0,	0,	0,	40,	80,	80,	90,	70,	70,	50,	20,	10,	0,	0},
                    {0,	0,	10,	0,	0,	0,	0,	90, 90,	0,	0,	0,	0,	80,	0,	0},
                    {0,	0,	0,	0,  0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	100},
                    {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	100},
                    {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,  0,	0,	100},
                    {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	90,	100},
                    {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	100},
                    {0,	0,	0,	0,	0,	0,  0,	0,	0,	0,	0,	0,	0,	0,	0,	100},
                    {0,	0,	0,	0,	0,	0,	0,	0,	50,	0,	0,	0,	0,	0,	90,	100},
                    {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	100},
                    {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	90,	100},
                    {0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	0,	100}
                    };


    double weight;
    double satietyWithFoodCurrent = 0; // Сколько животное сьело еды
    double satiationWithFood; // Максимально возможное насыщение
    int speed; // Скорость перемещения
//    String kind;
    UUID uuid;
    int xProbability;
    int x;
    int y;


    public Animal(String kind, double weight, int speed, double satiationWithFood, UUID uuid, int xProbability, int yProbability) {
        super(kind, yProbability);
        this.xProbability = xProbability;
//        this.kind = kind;
        this.weight = weight;
        this.speed = speed;
        this.satiationWithFood = satiationWithFood;
        this.uuid = uuid;
    }



    public void eat(LifeForm food) {
        int probability = getProbability(food.yProbability);
        int probabilityFood = getProbability(this.yProbability);

        boolean probabilityToEat = ThreadLocalRandom.current().nextDouble() < probability;
        boolean probabilityToEatFood = ThreadLocalRandom.current().nextDouble() < probabilityFood;

        if (probabilityToEat && !probabilityToEatFood) {
            if (this.satiationWithFood >= food.weight) {
                this.satietyWithFoodCurrent = this.satietyWithFoodCurrent + food.weight;
                food.alive = false;
            } else {
                this.satietyWithFoodCurrent = this.satiationWithFood;
                food.weight = food.weight - this.satiationWithFood;
            }
        } else if (probabilityToEatFood && !probabilityToEat) {
            if (this.satiationWithFood >= food.weight) {
                this.satietyWithFoodCurrent = this.satietyWithFoodCurrent + food.weight;
                food.alive = false;
            } else {
                this.satietyWithFoodCurrent = this.satiationWithFood;
                food.weight = food.weight - this.satiationWithFood;
            }
        }
    }

    public int getProbability(int yProbability) {
        return this.PROBABILITIES[this.xProbability][yProbability];
    }


}
