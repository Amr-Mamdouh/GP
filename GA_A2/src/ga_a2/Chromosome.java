/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga_a2;

import java.util.Random;

/**
 *
 * @author amrfo_000
 */
public class Chromosome {

    public float  chrom[];
    int SZ;
    double fitness = 0;

    public Chromosome(int sz) {
        chrom = new float[sz];
        SZ = sz;
        for (int i = 0; i < sz; i++) {
            chrom[i] = 0;
        }
    }

    public void setChromosome(int sz) {
        chrom = new float[sz];
        SZ = sz;
        for (int i = 0; i < sz; i++) {
            chrom[i] = 0;
        }

    }

    public void Mutation(int t,int T)
        {
            System.out.println("** Mutation **");
            double pm;
            double xmin,xmax,y=0,deltea,r1,r2;
                for (int i = 0; i < SZ; i++) 
                {
                    Random r = new Random();
                    pm = 0.001 + (Math.random() * (0.1 - 0.001));
                    System.out.println("Pm " + pm);
                    double R = Math.random();
                    if (R <= pm){
                    xmin=chrom[i]-(-10);
                    xmax=10-(chrom[i]);
                    r1=0 + (Math.random() * (1));
                        if(r1>0.5)
                            {y=xmax;}
                        else if (r1<=0.5)
                            {y=xmin;}
                    r2=0 + (Math.random() * (1));
                    deltea=y* (1-Math.pow(r2, ((1-t)/T)));
                    System.out.println("R " + R);
                        
                            
                                double r3=Math.random();
                                if (r3<=0.5)
                                    chrom[i]-=deltea;
                                 else 
                                    chrom[i]+=deltea;
                                        
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
       
        double y;
        double sum=0;
       for (int i=0;i<arr.length;i++)
       {
           y=chrom[0];
           for(int j=1;j<SZ;j++)
           {
               y+=chrom[j]*(Math.pow(arr[i][0], j));
           }
           sum+=Math.pow(y-arr[i][1], 2);
       }
       fitness=sum/arr.length;
       }

    public void print() {
        for (int i = 0; i < SZ; i++) {
            System.out.print(chrom[i] + " ");
        }

        System.out.println("Fitness = " + fitness);
    }

}
