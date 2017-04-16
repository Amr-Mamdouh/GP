/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga_a1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author amrfo_000
 */
public class Chanonikal {

    int numOfPop;
    int sizeOfBag;
    int numOfItems;
    Chromosome chroms[];
    Chromosome chromsChild[];
    int arrOFItems[][];
    int caseNum;
    int respondArr[];
    int MaxBenefit = 0, Counter = 0, IndexOfBest = 0;

    public Chanonikal() {

    }

    public void screenOut() {
        int numTestCase;
        System.out.print("Enter The Number Of Test Cases : ");
        Scanner read = new Scanner(System.in);
        numTestCase = read.nextInt();
        for (int i = 1; i <= numTestCase; i++) {
            caseNum = i;
            System.out.print("Enter The Number Of Iteams : ");
            numOfItems = read.nextInt();
            System.out.print("Enter The Size Of Bag : ");
            sizeOfBag = read.nextInt();
            System.out.print("Enter The Weights and Benfits of each item : ");
            arrOFItems = new int[numOfItems][2];
            for (int j = 0; j < numOfItems; j++) {
                arrOFItems[j][0] = read.nextInt();
                arrOFItems[j][1] = read.nextInt();
            }
            run();
        }
    }

    public boolean Terminate() {
        for (int i = 0; i < numOfPop; i++) {
            if (chroms[i].fitness == MaxBenefit) {
                Counter++;
                IndexOfBest = i;
                System.err.println("Max Benefit : " + MaxBenefit + " With index " + IndexOfBest + " Counter " + Counter);
            } else if (chroms[i].fitness > MaxBenefit) {
                MaxBenefit = chroms[i].fitness;
                Counter = 1;
                IndexOfBest = i;
                System.err.println("Max Benefit : " + MaxBenefit + " With index " + IndexOfBest + " Counter " + Counter);
            }
        }
        if (Counter == 3) {
            System.out.println("Termenation Condition ,Max Benefit : " + MaxBenefit + " With index : " + IndexOfBest + " Counter" + Counter);
            return true;
        }
        return false;
    }

    public void GeneratePop() {
        System.out.println("");
        System.out.println("** Generate Population **");
        if (numOfItems % 2 != 0) {
            numOfPop = numOfItems + 1;
        } else {
            numOfPop = numOfItems;
        }
        chroms = new Chromosome[numOfPop];

        Random ran = new Random();
        double ran1;
        for (int i = 0; i < numOfPop; i++) {
            chroms[i] = new Chromosome(numOfItems);
            for (int j = 0; j < numOfItems; j++) {
                ran1 = Math.random();
                ran1 = 0 + (1) * ran.nextDouble();
                if (ran1 < 0.5) {
                    chroms[i].chrom[j] = 1;
                } else {
                    chroms[i].chrom[j] = 0;
                }
            }
        }
        Print();
    }

    public boolean FeasibleFun(int array[]) {
        System.out.println();
        System.out.println("** Check if Feasible **");
        int totalWight = 0;
        for (int i = 0; i < numOfItems; i++) {
            if (array[i] == 1) {
                totalWight += arrOFItems[i][0];
            }
        }
        System.out.println("Total Wight is  " + totalWight);
        if (totalWight > sizeOfBag || totalWight == 0) {
            System.out.println("Total is greater than bag size or zero ");
            return false;
        } else {
            System.out.println("Total is less than or equal bag size ");
            return true;
        }
    }

    public boolean UnRepetedchrom(int array[], int index) {
        System.out.println("");
        System.out.println("** Check if UnRepeated **");
        int count = 0;
        for (int i = 0; i < numOfPop; i++) {
            count = 0;
            for (int j = 0; j < numOfItems; j++) {
                if (array[j] == chroms[i].chrom[j] && i != index) {
                    count++;
                }
            }
            if (count == numOfItems) {
                System.out.println("This Chromosom equal with chromosome in index " + i);
                return false;
            }
        }
        return true;
    }

    public boolean UnRepetedchrom2(int array[]) {
        System.out.println("");
        System.out.println("** Check if UnRepeated **");
        int count = 0;
        for (int i = 0; i < numOfPop; i++) {
            count = 0;
            for (int j = 0; j < numOfItems; j++) {
                if (array[j] == chroms[i].chrom[j]) {
                    count++;
                }
            }
            if (count == numOfItems) {
                System.out.println("This Chromosom equal with chromosome in index " + i);
                return false;
            }
        }
        return true;
    }

    public void check() {
        boolean test1, test2;
        for (int i = 0; i < numOfPop; i++) {
            test1 = FeasibleFun(chroms[i].chrom);
            System.out.println("Feasible : " + test1);
            test2 = UnRepetedchrom(chroms[i].chrom, i);
            System.out.println("Un Repeat : " + test2);
            if (test1 == false || test2 == false) {
                System.out.println("The test is false ");
                GetNewPop(i);
            }
        }
    }

    public boolean GetNewPop(int indexOFNew) {
        System.out.println("");
        System.out.println("** Get New Chrome **");
        Random ran = new Random();
        double ran1;
        boolean test1, test2;
        for (int j = 0; j < numOfItems; j++) {
            ran1 = Math.random();
            ran1 = 0 + (1) * ran.nextDouble();
            //System.out.println("ran1 " + ran1);
            if (ran1 < 0.5) {
                chroms[indexOFNew].chrom[j] = 1;
            } else {
                chroms[indexOFNew].chrom[j] = 0;
            }
        }
        //System.out.println("New Chromosome ");
        for (int i = 0; i < numOfItems; i++) {
            System.out.print(chroms[indexOFNew].chrom[i]);
        }
        test1 = FeasibleFun(chroms[indexOFNew].chrom);
        test2 = UnRepetedchrom(chroms[indexOFNew].chrom, indexOFNew);
        if (test1 == false || test2 == false) {
            GetNewPop(indexOFNew);
        } else {
            System.out.println("Done! It Is Feasible & Not Repeated ");
            return true;
        }
        return false;
    }

    public void run() {
        while (Terminate() != true) {
            GeneratePop();
            check();
            Print();

            for (int i = 0; i < numOfPop; i++) {
                chroms[i].calcFitness(arrOFItems);
            }

            chromsChild = new Chromosome[2];
            for (int i = 0; i < 2; i++) {
                chromsChild[i] = new Chromosome(numOfItems);
            }

            respondArr = new int[2]; // index for selection chromosoms in chroms
            boolean test1, test2;
            respondArr = Selection();
            for (int i = 0; i < numOfItems; i++) {
                chromsChild[0].chrom[i] = chroms[respondArr[0]].chrom[i];
                chromsChild[1].chrom[i] = chroms[respondArr[1]].chrom[i];
            }

            System.out.println("Childes Chromosoms ");
            for (int i = 0; i <= 1; i++) {
                for (int j = 0; j < numOfItems; j++) {
                    System.out.print(chromsChild[i].chrom[j]);
                }
                System.out.println("");
            }

            Crossover(chromsChild[0].chrom, chromsChild[1].chrom);
            chromsChild[0].Mutation();
            chromsChild[1].Mutation();
            for (int i = 0; i <= 1; i++) {
                chromsChild[i].calcFitness(arrOFItems);
            }
            System.out.println("");
            Replacement();
        }
        Decode(chroms[IndexOfBest].chrom);
    }

    public int[] Selection() {
        System.out.println("");
        System.out.println("** Selection **");
        //intervals
        int arrOffitness[] = new int[numOfPop];
        arrOffitness[0] = chroms[0].fitness;
        for (int i = 1; i < numOfPop; i++) {
            arrOffitness[i] = chroms[i].fitness + arrOffitness[i - 1];
        }
        System.out.println("The intervals : ");
        for (int i = 0; i < numOfPop; i++) {
            System.out.println(arrOffitness[i]);
        }
        Random ran = new Random();
        int r1 = 0 + (int) (Math.random() * (arrOffitness[numOfPop - 1] - 0) + 1);
        //System.out.println("random1 " + r1);
        int r2 = 0 + (int) (Math.random() * (arrOffitness[numOfPop - 1] - 0) + 1);
        //System.out.println("random2 " + r2);
        int index1 = -1, index2 = -1;
        int array[] = new int[2];
        for (int i = 0; i < numOfPop; i++) {
            if (i == 0) {
                if (r1 >= 0 && r1 <= arrOffitness[i]) {
                    index1 = i;
                }
            }
            if (r1 > arrOffitness[i] && r1 <= arrOffitness[i + 1]) {
                index1 = i + 1;
            }

        }
        for (int i = 0; i < numOfPop; i++) {
            if (i == 0) {
                if (r2 >= 0 && r2 <= arrOffitness[i]) {
                    index2 = i;
                }
            }
            if (r2 > arrOffitness[i] && r2 <= arrOffitness[i + 1]) {
                index2 = i + 1;
            }
        }
        while (index1 == index2) {

            r2 = 0 + (int) (Math.random() * (arrOffitness[numOfPop - 1] - 0) + 1);
            System.out.println("Index one equals index two, new random is " + r2);
            for (int i = 0; i < numOfPop; i++) {
                if (i == 0) {
                    if (r2 >= 0 && r2 <= arrOffitness[i]) {
                        index2 = i;
                    }
                }
                if (r2 > arrOffitness[i] && r2 <= arrOffitness[i + 1]) {
                    index2 = i + 1;
                }
            }
        }
        array[0] = index1;
        array[1] = index2;
        System.out.println("index1 " + array[0] + " index2 " + array[1]);
        return array;
    }

    public void Crossover(int arr1[], int arr2[]) {
        System.out.println("");
        System.out.println("** CrossOver **");
        Random r = new Random();
        double r1, r2;
        r1 = Math.random();
        r1 = 0.4 + (Math.random() * (0.3));
        System.out.println("Random one " + r1);
        r2 = Math.random();
        r2 = 0 + (1) * r.nextDouble();
        System.out.println("Random two " + r2);
        if (r1 > r2) {
            System.out.println("Will Cross over ");
            int R = 1 + (int) (Math.random() * (numOfItems - 1 - 1) + 1);

            System.out.println("Random for cross " + R);
            for (int i = 0; i <= R - 1; i++) {
                int c = arr1[i];
                arr1[i] = arr2[i];
                arr2[i] = c;
            }
            System.out.println("After Cross Over ");
            for (int i = 0; i < numOfItems; i++) {
                System.out.print(arr1[i]);
            }
            System.out.println("");
            for (int j = 0; j < numOfItems; j++) {
                System.out.print(arr2[j]);
            }
            System.out.println("");
        } else {
            System.out.println("No CrossOver");
        }
    }

    public void Replacement() {
        System.out.println("");
        System.out.println("** Replacement **");
        for (int i = 0; i <= 1; i++) {

            chromsChild[i].print();
        }

        for (int i = 0; i <= 1; i++) {
            if (FeasibleFun(chromsChild[i].chrom) == true) {
                if (UnRepetedchrom2(chromsChild[i].chrom) == true) {
                    if (chromsChild[i].fitness > chroms[respondArr[i]].fitness) {
                        chroms[respondArr[i]] = chromsChild[i];
                        System.out.println("The child is replaced with its parents ");
                    } else {
                        System.out.println("No Replacement For This Child because its fitness less than its parent " + chroms[respondArr[i]].fitness);
                    }
                } else {
                    System.out.println("No Replacement For This Child because it is repeated ");
                }
            } else {
                System.out.println("No Replacement For This Child because it is un feasible ");
            }
        }
    }

    public void Decode(int chromosome[]) {
        int benefit = 0;
        int counter = 0;
        System.out.println("pair values (weight, benefit) of selected items : ");
        for (int i = 0; i < numOfItems; i++) {
            if (chromosome[i] == 1) {
                System.out.println(arrOFItems[i][0] + "  " + arrOFItems[i][1]);
                benefit += arrOFItems[i][1];
                counter++;
            }
        }
        System.out.println("Case " + caseNum + ": " + benefit);
        System.out.println("number of items that could be placed in the knapsack: " + counter);

    }

    public void Print() {
        System.out.println("** Print ** ");
        for (int i = 0; i < numOfPop; i++) {
            for (int j = 0; j < numOfItems; j++) {
                System.out.print(chroms[i].chrom[j]);
            }
            System.out.println("");
        }
    }

}
