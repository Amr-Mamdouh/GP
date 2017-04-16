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
public class FuzzyLogic {

    int numofInPut;
    Curve inputCurvs[];
    Curve outputCurv;
    int numOfRules;
    Rule setRule[];
    double ruleout[];
    String output[];
    double ruleOfvalues[];
    double Decision = 0;

    public void input() throws FileNotFoundException, IOException {
        double crispvalue;//the value that will cut the curve
        String Curvename;
        int numberofsets;
        FileReader inputFile = new FileReader("Data.txt");
        BufferedReader bufferReader = new BufferedReader(inputFile);
        String line = bufferReader.readLine();

        System.out.println(line);
        numofInPut = Integer.parseInt(line);
        System.out.println("Num Of InPut " + numofInPut);
        inputCurvs = new Curve[numofInPut];
        line = bufferReader.readLine();
        System.out.println("line : "+line);
        int counter = 0;
        int value = 0;
        for (int i = 0; i < numofInPut; i++) {
            //inputCurvs=new Curve[numofInPut];
            Curvename = line.substring(0, line.indexOf(" "));
            System.out.println("Curve Name :" + Curvename);
            crispvalue = Double.parseDouble(line.substring(line.indexOf(" ") + 1));
            System.out.println("crisp value:" + crispvalue);
            line = bufferReader.readLine();
            numberofsets = Integer.parseInt(line);
            value=(numberofsets*2)+1;
            System.out.println("Num Of Sets :" + numberofsets);
            inputCurvs[i] = new Curve(Curvename, crispvalue, numberofsets);
            inputCurvs[i].setCurve();

            while (counter != value) {
                line = bufferReader.readLine();
                counter++;
            }
            System.out.println("Line is " + line);
            counter = 0;
            
        }
        counter = 0;
        value = 0;
        Curvename = line;
        System.out.println("Curve out name :" + Curvename);
        line = bufferReader.readLine();
        numberofsets = Integer.parseInt(line);
        System.out.println("num of sets : " + numberofsets);
        value = (numberofsets * 2) + 1;
        outputCurv = new Curve(Curvename, 0, numberofsets);
        outputCurv.setCurve();
        while (counter != value) {//8
            line = bufferReader.readLine();
            counter++;
        }
        numOfRules = Integer.parseInt(line);
        System.out.println("Num Of Rules " + numOfRules);
        setRule = new Rule[numOfRules];
        String r = "";
        line = bufferReader.readLine();
        for (int i = 0; i < numOfRules; i++) {
            setRule[i] = new Rule();
            setRule[i].numOfPremise = Integer.parseInt(line.substring(0, line.indexOf(" ")));
            r = line.substring(line.indexOf(" ") + 1);
            setRule[i].dividerule(line.substring(line.indexOf(" ") + 1, line.indexOf("then") - 1));
            setRule[i].out = r.substring(r.indexOf("then") + 5);
            line = bufferReader.readLine();
        }
        System.out.println("sys out " + setRule[0].out);
        bufferReader.close();

    }

    public void run() throws IOException {
        input();
        for (int i = 0; i < numofInPut; i++) {
            inputCurvs[i].fuzzification();
        }
        setRule();
        for (int i = 0; i < numOfRules; i++) {
            System.out.print("The output value : " + ruleout[i]);
            System.out.println(" " + output[i]);
        }
        Defuzzification();
        System.out.println("The Decision is :" + Decision);
    }

    public void print()
    {
        for(int i=0;i<inputCurvs.length;i++)
        {
            System.out.print("Curve Name:"+inputCurvs[i].name);
            System.out.println(" Crisp:"+inputCurvs[i].crisp);
            for(int j=0;j<inputCurvs[i].set.length;j++)
            {
               System.out.print("Set Name:"+inputCurvs[i].set[j].name);
               System.out.print(" Set kind:"+inputCurvs[i].set[j].kind);
               System.out.println(" Set fuzzy:"+inputCurvs[i].set[j].Fuzzy);
            }
        }
    }
    public void setRule() {
        print();
        ruleout = new double[numOfRules];
        output = new String[numOfRules];
        for (int i = 0; i < numOfRules; i++) {
            ruleOfvalues = new double[setRule[i].numOfPremise];
            for (int g = 0; g < setRule[i].numOfPremise; g++) {
                String in, set;
                System.out.println("Rule:" + setRule[i].rule[g]);
                in = setRule[i].rule[g].substring(0, setRule[i].rule[g].indexOf("="));
                System.out.println("The in:" + in);
                set = setRule[i].rule[g].substring(setRule[i].rule[g].indexOf("=") + 1);
                System.out.println("The set:" + set);
                for (int j = 0; j < inputCurvs.length; j++) {
                    if (inputCurvs[j].name.equals(in)) {
                        System.out.println("if input curve name:" + inputCurvs[j].name);
                        for (int k = 0; k < inputCurvs[j].NumofSet; k++) {
                            if (inputCurvs[j].set[k].name.equals(set)) {
                                System.out.println("input curve set:" + inputCurvs[j].set[k].name);
                                ruleOfvalues[g] = inputCurvs[j].set[k].Fuzzy;
                                System.out.println("RuleOfValue:" + ruleOfvalues[g]);
                                break;
                            } else {
                                ruleOfvalues[g] = 0;
                                System.out.println("if not found " + ruleOfvalues[g]);
                            }
                        }
                        break;
                    } else {
                        ruleOfvalues[g] = 0;
                        System.out.println("if not found " + ruleOfvalues[g]);
                    }
                }
            }
            int counter = 0;
            for (int c = 0; c < ruleOfvalues.length; c++) {
                System.out.println("ruleValue " + ruleOfvalues[c]);
            }
            for (int f = 0; f < setRule[i].gate.length; f++) {
                System.out.println("The gate " + setRule[i].gate[f]);
                if (setRule[i].gate[f] == "AND") {
                    System.out.println("**And gate**");
                    ruleout[i] = Math.min(ruleOfvalues[counter], ruleOfvalues[counter + 1]);
                    ruleOfvalues[counter + 1] = ruleout[f];
                    System.out.println("counter +1 " + ruleOfvalues[counter + 1]);
                    counter++;
                } else if (setRule[i].gate[f] == "OR") {
                    System.out.println("**OR gate**");
                    ruleout[i] = Math.max(ruleOfvalues[counter], ruleOfvalues[counter + 1]);
                    ruleOfvalues[counter + 1] = ruleout[f];
                    System.out.println("counter +1 " + ruleOfvalues[counter + 1]);
                    counter++;
                }
            }
            System.out.println("The output is:" + setRule[i].out);
            output[i] = setRule[i].out.substring(setRule[i].out.indexOf("=") + 2);
            System.out.println("The output only:" + output[i]);
        }
    }

    public int getPlace(String s) {
        for (int i = 0; i < outputCurv.NumofSet; i++) {
            if (outputCurv.set[i].name.equals(s)) {
                return i;
            }
        }
        return -1;
    }

    public double getCenter(int p) {
        double A = 0, Center = 0, sumofpoint = 0;
        if (outputCurv.set[p].kind == "trapezoidal") {
            for (int i = 0; i < 3; i++) {
                sumofpoint += (outputCurv.set[p].point[i][0] + outputCurv.set[p].point[i + 1][0]);
                // A+=(outputCurv.set[p].point[i][0]*outputCurv.set[p].point[i+1][1]-outputCurv.set[p].point[i+1][0]*outputCurv.set[p].point[i][1]);
            }
            //A=A*(1/2);
            Center = (1 / 3) * (sumofpoint);
            return Center;
        } else if (outputCurv.set[p].kind == "triangle") {
            for (int i = 0; i < 2; i++) { //System.out.println("point 1 is "+outputCurv.set[p].point[i][0]);
                //System.out.println("point 2 is "+outputCurv.set[p].point[i+1][0]);
                sumofpoint += (outputCurv.set[p].point[i][0] + outputCurv.set[p].point[i + 1][0]) * (outputCurv.set[p].point[i][0] * outputCurv.set[p].point[i + 1][1] - outputCurv.set[p].point[i + 1][0] * outputCurv.set[p].point[i][1]);
                A += (outputCurv.set[p].point[i][0] * outputCurv.set[p].point[i + 1][1] - outputCurv.set[p].point[i + 1][0] * outputCurv.set[p].point[i][1]);
            }
            A = A / 2;
            System.out.println("Sum of Points is " + sumofpoint);
            Center = (sumofpoint) / (6 * A);
            // System.out.println("Center fun "+Center);
            return Center;
        }
        return 0;
    }

    public void Defuzzification() {
        double sumofRuleoutpu = 0, SumofDefuzzy = 0;
        String S;
        for (int i = 0; i < ruleout.length; i++) {
            //S = output[i].substring(15, output[i].length());
            sumofRuleoutpu += ruleout[i];
            System.out.println("output i:" + output[i]);
            int p = getPlace(output[i]);
            //System.out.println("P is "+p);
            if (p == -1) {
                SumofDefuzzy += 0;
                System.out.println("Not Found " + output[i]);
            } else {
                double center = getCenter(p);
                System.out.println("Center " + center);
                SumofDefuzzy += ruleout[i] * center;
            }
        }
        System.out.println("Sum of defuz : " + SumofDefuzzy);
        System.out.println("Sum of Rule : " + sumofRuleoutpu);
        Decision = SumofDefuzzy / sumofRuleoutpu;
    }
}
