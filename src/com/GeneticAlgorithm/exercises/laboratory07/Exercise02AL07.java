package com.GeneticAlgorithm.exercises.laboratory07;

import com.GeneticAlgorithm.genetic_algorithm.GeneticAlgorithm;
import com.GeneticAlgorithm.printing.Printing;
import com.GeneticAlgorithm.util.RastriginFunctionMin;
import com.GeneticAlgorithm.util.Solution;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.function.Function;

public class Exercise02AL07 {
    static final Function<Double[], Double> fMin = new RastriginFunctionMin(10,10);

    public static void main(String[] args) throws IOException {
        for (int pop = 20; pop <= 200; pop += 20) {
            GeneticAlgorithm algorithm = new GeneticAlgorithm(10,-5.21,5.21,3,pop,0.02,0.6);
            Integer[][] population = algorithm.generatePopulation();
            FileWriter out = new FileWriter(String.format("measurements/rastrigin50Steps/resultsRastrigin%d.csv",pop));
            CSVPrinter cvs = CSVFormat.EXCEL.withHeader("Generacja","Średnia wartość","Najlepszy wynik").print(out);

            for (int j = 0; j <= 10000; j++) {
                //Calculate values
                Double[] valuesOfFunction = algorithm.computeValues(population, fMin);
                double min = Double.MAX_VALUE;
                int k = 0;
                for (int i = 0; i < valuesOfFunction.length; i++) {
                    if (valuesOfFunction[i] < min){
                        min = valuesOfFunction[i];
                        k = i;
                    }
                }

                algorithm.setTheBestSolution(algorithm.decodeChromosome(population[k]),min);

                if (j % 50 == 0) {
                    double average = algorithm.computeAverageValue(population, fMin);
                    cvs.printRecord(j, String.format("%f", -average), String.format("%f", -algorithm.getTheBestSolution().getY()));
                }

                Integer[][] temporaryPopulation = algorithm.roulette(population, fMin);
                temporaryPopulation = algorithm.applyCrossover(temporaryPopulation, 8);
                temporaryPopulation = algorithm.applyMutation(temporaryPopulation);
                population = temporaryPopulation;
            }
            cvs.close();
            Solution finalSolution = algorithm.getTheBestSolution();
            System.out.println("Rozmiar populacji: "+pop);
            System.out.println("Najlepsze rozwiązanie: ");
            Printing.printFunctionValue(finalSolution.getX(),-finalSolution.getY());
            System.out.println();
        }
    }
}
