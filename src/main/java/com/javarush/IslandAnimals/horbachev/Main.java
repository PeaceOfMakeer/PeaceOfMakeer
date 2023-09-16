package com.javarush.IslandAnimals.horbachev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args){

        Main main = new Main();
        List<Plant> plants = new ArrayList<>(Arrays.asList(
                new Plant("\uD83C\uDF40", 1, UUID.randomUUID(), 1, 2, 15),
                new Plant("\uD83C\uDF40", 1, UUID.randomUUID(), 1, 7, 15),
                new Plant("\uD83C\uDF40", 1, UUID.randomUUID(),2, 7,15),
                new Plant("\uD83C\uDF40", 1, UUID.randomUUID(),3, 9,15),
                new Plant("\uD83C\uDF40", 1, UUID.randomUUID(),5, 10,15),
                new Plant("\uD83C\uDF40", 1, UUID.randomUUID(),10, 9,15),
                new Plant("\uD83C\uDF40", 1, UUID.randomUUID(),9, 10,15)
        ));



        Island island = new Island(main.getPredators(), main.getHerbivores(), plants, 100, 20, 10);
        island.startDay();


    }

    public List<Predator> getPredators(){
        return new ArrayList<>(Arrays.asList(
                new Predator("\uD83D\uDC3A", 44, 2, 4, UUID.randomUUID(), 0, 0),
                new Predator("\uD83D\uDC3A", 44, 2, 4, UUID.randomUUID(), 0, 0),
                new Predator("\uD83D\uDC0D", 15, 1, 3, UUID.randomUUID(), 1, 1),
                new Predator("\uD83D\uDC0D", 15, 1, 3, UUID.randomUUID(), 1, 1),
                new Predator("\uD83E\uDD8A", 8, 2, 2, UUID.randomUUID(), 2, 2),
                new Predator("\uD83E\uDD8A", 8, 2, 2, UUID.randomUUID(), 2, 2),
                new Predator("\uD83D\uDC3B", 500, 2, 80, UUID.randomUUID(), 3, 3),
                new Predator("\uD83D\uDC3B", 500, 2, 80, UUID.randomUUID(), 3, 3),
                new Predator("\uD83E\uDD85", 6, 3, 1, UUID.randomUUID(), 4, 4),
                new Predator("\uD83E\uDD85", 6, 3, 1, UUID.randomUUID(), 4, 4)
        ));
    }

    public List<Herbivore> getHerbivores(){
        return new ArrayList<>(Arrays.asList(
                new Herbivore("\uD83D\uDC0E", 400, 4, 60, UUID.randomUUID(), 5, 5),
                new Herbivore("\uD83D\uDC0E", 400, 4, 60, UUID.randomUUID(), 5, 5),
                new Herbivore("\uD83E\uDD8C", 300, 4, 50, UUID.randomUUID(), 6, 6),
                new Herbivore("\uD83E\uDD8C", 300, 4, 50, UUID.randomUUID(), 6, 6),
                new Herbivore("\uD83D\uDC07", 2, 2, 0.45, UUID.randomUUID(), 7, 7),
                new Herbivore("\uD83D\uDC07", 2, 2, 0.45, UUID.randomUUID(), 7, 7),
                new Herbivore("\uD83D\uDC01", 0.05, 1, 0.01, UUID.randomUUID(), 8, 8),
                new Herbivore("\uD83D\uDC01", 0.05, 1, 0.01, UUID.randomUUID(), 8, 8),
                new Herbivore("\uD83D\uDC10", 60, 3, 10, UUID.randomUUID(), 9, 9),
                new Herbivore("\uD83D\uDC10", 60, 3, 10, UUID.randomUUID(), 9, 9),
                new Herbivore("\uD83D\uDC11", 70, 3, 15, UUID.randomUUID(), 10, 10),
                new Herbivore("\uD83D\uDC11", 70, 3, 15, UUID.randomUUID(), 10, 10),
                new Herbivore("\uD83D\uDC17", 400, 2, 50, UUID.randomUUID(), 11, 11),
                new Herbivore("\uD83D\uDC17", 400, 2, 50, UUID.randomUUID(), 11, 11),
                new Herbivore("\uD83D\uDC03", 700, 3, 100, UUID.randomUUID(), 12, 12),
                new Herbivore("\uD83D\uDC03", 700, 3, 100, UUID.randomUUID(), 12, 12),
                new Herbivore("\uD83E\uDD86", 1, 4, 0.15, UUID.randomUUID(), 13, 13),
                new Herbivore("\uD83E\uDD86", 1, 4, 0.15, UUID.randomUUID(), 13, 13),
                new Herbivore("\uD83D\uDC1B", 0.01, 1, 0, UUID.randomUUID(), 14, 14),
                new Herbivore("\uD83D\uDC1B", 0.01, 1, 0, UUID.randomUUID(), 14, 14)
        ));
    }
}