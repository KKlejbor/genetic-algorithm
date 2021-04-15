package com.GeneticAlgorythm.exercises.laboratory06;


import com.GeneticAlgorythm.geneticAlgorythm.GeneticAlgorithm;
import com.GeneticAlgorythm.printing.Printing;
import com.GeneticAlgorythm.util.quadraticFunction;

public class Exercise01 {
    public static void main(String[] args) {
        GeneticAlgorithm algorithm = new GeneticAlgorithm(2,-2,2,5,0.1,5);
        Integer[][] P1 = algorithm.generatePopulation();
        Integer[][] P2 = algorithm.roulette(P1,new quadraticFunction());
        System.out.println("Populacja P1:\n\n");
        Printing.printChromosomes(P1);
        System.out.printf("Średnia wartość funkcji przystowania dla populacji P1 %f\n", algorithm.computeAverageValue(P1,new quadraticFunction()));

        System.out.println("Populacja P2:\n\n");
        Printing.printChromosomes(P2);
        System.out.printf("Średnia wartość funkcji przystowania dla populacji P2 %f\n", algorithm.computeAverageValue(P2,new quadraticFunction()));
    }
}
