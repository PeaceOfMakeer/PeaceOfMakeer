package com.javarush.IslandAnimals.horbachev;

import java.util.*;

public class Island {

    public static class IslandThread extends Thread {
        Island island;
        int j;
        int k;

        public IslandThread(Island island, int j, int k) {
            this.island = island;
            this.j = j;
            this.k = k;
        }

        @Override
        public void run() {

            ArrayList<LifeForm> reproduced = new ArrayList<>();
            List<Predator> predators = island.checkCollisionPredators(this.j, this.k);
            List<Herbivore> herbivores = island.checkCollisionHerbivores(this.j, this.k);
            List<Plant> plants = island.checkCollisionPlants(this.j, this.k);
            for (int l = 0; l < predators.size(); l++) {
                Predator predator = predators.get(l);
                for (int m = l + 1; m < predators.size(); m++) {
                    Predator nextPredator = predators.get(m);
                    if (!predator.alive) {
                        break;
                    } else if (!nextPredator.alive) {
                        continue;
                    }
                    if (predator.kind.compareTo(nextPredator.kind) == 0 && !reproduced.contains(predator) && !reproduced.contains(nextPredator)) {
                        island.predatorList.add(predator.reproduction());
                        reproduced.add(predator);
                        reproduced.add(nextPredator);
                    } else {
                        predator.eat(nextPredator);
                    }
                }
                for (Herbivore herbivore : herbivores) {
                    if (!predator.alive) {
                        break;
                    }
                    if (!herbivore.alive) {
                        continue;
                    }
                    predator.eat(herbivore);

                }
            }
            for (int l = 0; l < herbivores.size(); l++) {
                Herbivore herbivore = herbivores.get(l);
                for (int m = l + 1; m < herbivores.size(); m++) {
                    Herbivore nextHerbivore = herbivores.get(m);
                    if (!herbivore.alive) {
                        break;
                    } else if (!nextHerbivore.alive) {
                        continue;
                    }
                    if (herbivore.kind.compareTo(nextHerbivore.kind) == 0 && !reproduced.contains(herbivore) && !reproduced.contains(nextHerbivore)) {
                        island.herbivoreList.add(herbivore.reproduction());
                        reproduced.add(herbivore);
                        reproduced.add(nextHerbivore);
                    } else {
                        herbivore.eat(nextHerbivore);
                    }
                }
                for (Plant plant : plants) {
                    if (!herbivore.alive) {
                        break;
                    } else {
                        herbivore.eat(plant);
                    }

                }
            }

        }
    }

    int xSize;
    int ySize;
    int quantityDays;
    private final String[] directions = {"x", "y"};

    List<Predator> predatorList;
    List<Herbivore> herbivoreList;
    List<Plant> plantList;

    public Island(List<Predator> predatorList, List<Herbivore> herbivoreList, List<Plant> plantList, int xSize, int ySize, int quantityDay) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.predatorList = predatorList;
        this.herbivoreList = herbivoreList;
        this.plantList = plantList;
        this.quantityDays = quantityDay;
    }

    public void walk(Animal animal) {

        Random random = new Random();
        String direction = this.directions[random.nextInt(this.directions.length)];
        int way = random.nextInt(-animal.speed, animal.speed);
        if (direction.equals("x")) {
            animal.x = animal.x + way;
            if (animal.x > this.xSize) {
                animal.x = animal.x - this.xSize;
            } else if (animal.x < 0) {
                animal.x = animal.x + this.xSize;
            }
        } else {
            animal.y = animal.y + way;
            if (animal.y > this.ySize) {
                animal.y = animal.y - this.ySize;
            } else if (animal.y < 0) {
                animal.y = animal.y + this.ySize;
            }
        }
//        System.out.printf("%s, %d, %d\n", animal.uuid, animal.x, animal.y);
    }

    public void startDay() {
        for (int i = 0; i < this.quantityDays; i++) {
            for (Animal animal : this.predatorList) {
                this.walk(animal);
            }
            for (Animal animal : this.herbivoreList) {
                this.walk(animal);
            }
            System.out.println("\nResult day: " + (i + 1) + "\n" );

            for (int j = 0; j < this.xSize; j++) {
                for (int k = 0; k < this.ySize; k++) {
                    IslandThread islandThread = new IslandThread(this, j, k);
                    islandThread.start();
                    try{
                    islandThread.join();
                    } catch (InterruptedException exc){
                        System.out.println("InterruptedException" + exc);
                    }
                }
            }
            removeObject(this.predatorList);
            System.out.println("Print Predators: " + this.predatorList.size());
            removeObject(this.herbivoreList);
            System.out.println("Print Herbivores: " + this.herbivoreList.size());
            removeObject(this.plantList);
            System.out.println("Print Plants: " + this.plantList.size());
        }

        Map<String, Integer> result = new HashMap<>();

        for (Predator pre : predatorList) {
            if(result.containsKey(pre.kind)) {
                result.put(pre.kind, result.get(pre.kind) + 1);
            }else{
                result.put(pre.kind, 1);
            }
        }
        for (Herbivore her : herbivoreList) {
            if(result.containsKey(her.kind)){
                result.put(her.kind, result.get(her.kind) + 1);
            }else {
                result.put(her.kind, 1);
            }
        }
        for (Map.Entry<String, Integer> map : result.entrySet()) {
            System.out.println(map.getKey() + " - " + map.getValue());
        }

    }


    public void removeObject(List<? extends LifeForm> lifeForms) {
        List<LifeForm> deadLifeForm = new ArrayList<>();

        for (LifeForm lifeForm : lifeForms) {
            if (!lifeForm.alive) {
                deadLifeForm.add(lifeForm);
                System.out.println("Dead " + lifeForm.kind);
            }
        }
        lifeForms.removeAll(deadLifeForm);
    }


    public List<Predator> checkCollisionPredators(int x, int y) {
        List<Predator> predators = new ArrayList<>();

        for (Predator animal : this.predatorList) {
            if (animal.x == x && animal.y == y) {
                predators.add(animal);
            }
        }
        return predators;
    }

    public List<Herbivore> checkCollisionHerbivores(int x, int y) {
        List<Herbivore> herbivores = new ArrayList<>();
        for (Herbivore animal : this.herbivoreList) {
            if (animal.x == x && animal.y == y) {
                herbivores.add(animal);
            }
        }
        return herbivores;
    }

    public List<Plant> checkCollisionPlants(int x, int y) {
        List<Plant> plants = new ArrayList<>();
        for (Plant plant : this.plantList) {
            if (plant.x == x && plant.y == y) {
                plants.add(plant);
            }
        }
        return plants;
    }

}

//                    List<Predator> predatorsList1 = new ArrayList<>();
//                    for (Predator predator : this.predatorList) {
//                        if (!predator.alive) {
//                            predatorsList1.add(predator);
//                            System.out.println("Dead Predator");
//                        }
//                    }
//                    this.predatorList.removeAll(predatorsList1);
//
//                    List<Herbivore> herbivoreList1 = new ArrayList<>();
//                    for (Herbivore herbivore : this.herbivoreList) {
//                        if (!herbivore.alive) {
//                            herbivoreList1.add(herbivore);
//                            System.out.println("Dead Herbivore");
//                        }
//                    }
//                    this.herbivoreList.removeAll(herbivoreList1);
//
//                    List<Plant> plantList1 = new ArrayList<>();
//                    for (Plant plant : this.plantList) {
//                        if (!plant.alive) {
//                            plantList1.add(plant);
//                            System.out.println("Dead Plant");
//                        }
//                    }
//                    this.plantList.removeAll(plantList1);
