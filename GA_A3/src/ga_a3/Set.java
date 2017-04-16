/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga_a3;

/**
 *
 * @author amrfo_000
 */
public class Set {
    String name,kind;//the name and type of the set 
    double r1,r2,r3,r4;
    double Fuzzy;//the fuzzyfication value the Y 
   double point[][]=new double [4][2]; 
    public void TriangleSet(String na,double r1,double r2,double r3)
    {name=na;
    kind="triangle"; 
    this.r1=r1;
    this.r2=r2;
    this.r3=r3;
    point[0][0]=r1;point[0][1]=0;
    point[1][0]=r2;point[1][1]=1;
    point[2][0]=r3;point[2][1]=0;
    
    }
     public void TrapezoidalSet(String na,double r1,double r2,double r3,double r4)
    {name=na;
    kind="trapezoidal";
    this.r1=r1;
    this.r2=r2;
    this.r3=r3;
    this.r4=r4;
    point[0][0]=r1;point[0][1]=0;
    point[1][0]=r2;point[1][1]=1;
    point[2][0]=r3;point[2][1]=1;
    point[3][0]=r4;point[2][1]=0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public double getFuzzy() {
        return Fuzzy;
    }

    public void setFuzzy(double Fuzzy) {
        this.Fuzzy = Fuzzy;
    }
    
    
}
