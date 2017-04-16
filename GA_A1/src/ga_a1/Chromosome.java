/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga_a1;

import java.util.Random;

/**
 *
 * @author amrfo_000
 */
public class Chromosome {

    public int chrom[];
    int SZ;
    int fitness = 0;

    public Chromosome(int sz) {
        chrom = new int[sz];
        SZ = sz;
        for (int i = 0; i < sz; i++) {
            chrom[i] = 0;
        }
    }

    public void setChromosome(int sz) {
        chrom = new int[sz];
        SZ = sz;
        for (int i = 0; i < sz; i++) {
            chrom[i] = 0;
        }

    }

    public void Mutation() {
        System.out.println("** Mutation **");
        double pm;
        for (int i = 0; i < SZ; i++) {
            Random r = new Random();
            pm = 0.001 + (Math.random() * (0.1 - 0.001));
            System.out.println("Pm " + pm);
            double R = Math.random();
            System.out.println("R " + R);
            if (R <= pm) {
                if (chrom[i] == 1) {
                    chrom[i] = 0;
                } else if (chrom[i] == 0) {
                    chrom[i] = 1;
                }
            }
        }
        
        System.out.println("After Mutation ");
        for(int i=0;i<SZ;i++)
        {
            System.out.print(chrom[i]);
        }
        System.out.println("");
    }

    public void calcFitness(int arr[][]) {
        int f = 0;
        for (int i = 0; i < SZ; i++) {
            f += chrom[i] * arr[i][1];
        }
        fitness = f;
    }

    public void print() {
        for (int i = 0; i < SZ; i++) {
            System.out.print(chrom[i] + " ");
        }

        System.out.println("Fitness = " + fitness);
    }

}
