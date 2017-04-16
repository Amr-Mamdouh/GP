/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga_a3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author amrfo_000
 */
public class Curve {

    int NumofSet;
    Set set[];
    String name;
    double crisp;

    Curve(String na, double Fuzzy, int numofset) {
        name = na;
        NumofSet = numofset;
        set = new Set[NumofSet];
        crisp = Fuzzy;
    }

    public void setCurve() throws FileNotFoundException, IOException {
        System.out.println("Set Curve ");
        Scanner read = new Scanner(System.in);
        String na, kind;
        int counter=1;
        FileReader inputFile = new FileReader("Data.txt");
        BufferedReader bufferReader = new BufferedReader(inputFile);
        String line = bufferReader.readLine();
        while(!line.contains(name))
        {
            line = bufferReader.readLine();
        }
        while(counter!=3)
        {
            line = bufferReader.readLine();
            System.out.println("line nfso "+line);
            counter++;
        }
        for (int i = 0; i < NumofSet; i++) {
            set[i] = new Set();
            na = line.substring(0,line.indexOf(" "));
            System.out.println("Name Of Set is :"+na);
            kind = line.substring(line.indexOf(" ")+1);
            System.out.println("Kind is :"+kind);
            if (kind.equals("trapezoidal")) {
                double a1, a2, a3, a4;//the number for trapezoidal range 
                line = bufferReader.readLine();
                String arr[];
                arr=line.split(" ");
                a1 = Double.parseDouble(arr[0]);
                a2 = Double.parseDouble(arr[1]);
                a3 = Double.parseDouble(arr[2]);
                a4 = Double.parseDouble(arr[3]);
                set[i].TrapezoidalSet(na, a1, a2, a3, a4);
            } else if (kind.equals("triangle")) {
                double a1, a2, a3;//the number for trapezoidal range 
                line = bufferReader.readLine();
                String arr[];
                arr=line.split(" ");
                a1 = Double.parseDouble(arr[0]);
                a2 = Double.parseDouble(arr[1]);
                a3 = Double.parseDouble(arr[2]);
                set[i].TriangleSet(na, a1, a2, a3);
            }
            line = bufferReader.readLine();
        }
    }

    public void fuzzification() {
        System.out.println("Fuzzification ");
        for (int i = 0; i < NumofSet; i++) {
            if (set[i].kind == "triangle") {
                if (crisp >= set[i].r1 && crisp <= set[i].r3) {
                    if (crisp == set[i].r2) {
                        set[i].Fuzzy = 1;
                        System.out.println("Fuzzy value is : "+set[i].Fuzzy);
                    } else if (crisp >= set[i].r1 && crisp < set[i].r2) {
                        set[i].Fuzzy = (crisp - set[i].r1) / (set[i].r2 - set[i].r1);
                        System.out.println("Fuzzy value is : "+set[i].Fuzzy);
                    } else if (crisp > set[i].r2 && crisp <= set[i].r3) {
                        set[i].Fuzzy = (crisp - set[i].r2) / (set[i].r3 - set[i].r2);
                        System.out.println("Fuzzy value is : "+set[i].Fuzzy);
                    }
                } else {
                    set[i].Fuzzy = 0;
                    System.out.println("Fuzzy value is : "+set[i].Fuzzy);
                }
            } else if (set[i].kind == "trapezoidal") {
                if (crisp >= set[i].r1 && crisp <= set[i].r4) {
                    if (crisp >= set[i].r2 && crisp <= set[i].r3) {
                        set[i].Fuzzy = 1;
                        System.out.println("Fuzzy value is : "+set[i].Fuzzy);
                    } else if (crisp >= set[i].r1 && crisp < set[i].r2) {
                        set[i].Fuzzy = (crisp - set[i].r1) / (set[i].r2 - set[i].r1);
                        System.out.println("Fuzzy value is : "+set[i].Fuzzy);
                    } else if (crisp > set[i].r3 && crisp <= set[i].r4) {
                        set[i].Fuzzy = (crisp - set[i].r3) / (set[i].r4 - set[i].r3);
                        System.out.println("Fuzzy value is : "+set[i].Fuzzy);
                    }
                } else {
                    set[i].Fuzzy = 0;
                    System.out.println("Fuzzy value is : "+set[i].Fuzzy);
                }
            }
        }
    }

    public void print() {
        for (int i = 0; i < NumofSet; i++) {
            System.out.println("name is : " + set[i].name);
            System.out.println("kind is : " + set[i].kind);
            System.out.println("fuzzy : " + set[i].Fuzzy);
        }
    }
}
