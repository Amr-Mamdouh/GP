/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga_a3;

/**
 *
 * @author Mery
 */
public class Rule {

    int numOfPremise;
    String rule[];
    String gate[];
    String out;

    public Rule() {

    }

    public void dividerule(String Rule) {
        int g = 0, r = 0;
        String rle = "";
        gate = new String[numOfPremise - 1];
        rule = new String[numOfPremise];
        for (int i = 0; i < Rule.length()-2; i++) {
            if (Rule.charAt(i) == 'A' && Rule.charAt(i + 1) == 'N' && Rule.charAt(i + 2) == 'D') {
                gate[g] = "AND";
                rule[r] = rle;
                g++;
                r++;
                i+=3;
                rle = "";
            } else if (Rule.charAt(i) == 'O' && Rule.charAt(i + 1) == 'R') {
                gate[g] = "OR";
                rule[r] = rle;
                g++;
                r++;
                i+=2;
                rle = "";
            } else if (Rule.charAt(i)!=' ')
            {
                rle += Rule.charAt(i);
            }
        }

        //System.out.println("rle"+rle+"rule2"+Rule.charAt(Rule.length() - 2)+"rule3"+Rule.charAt(Rule.length() - 1));
        if(Rule.charAt(Rule.length()-2)!=' ')
        rle=rle+Rule.charAt(Rule.length()-2);
        if(Rule.charAt(Rule.length()-1)!=' ')
       rle=rle+Rule.charAt(Rule.length()-1);
        rule[r] = rle;
    }

}
