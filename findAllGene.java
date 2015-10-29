import edu.duke.*;
/**
 * Write a description of findAllGene here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class findAllGene {
    public int findStopIndex(String dna, int index){
       // System.out.println(dna+ " " + index);
        int stop1 = dna.indexOf("tag", index);
       //System.out.println("stop1 "+ stop1);
        if(stop1 == -1 || (stop1-index)%3 !=0){
            stop1 = dna.length();
        }
        int stop2 = dna.indexOf("tga", index);
       // System.out.println("stop2 "+ stop2);
        if(stop2 == -1 || (stop2-index)%3 !=0){
            stop2 = dna.length();
        }
        int stop3 = dna.indexOf("taa", index);
       // System.out.println("stop3 "+ stop3);
        if(stop3 == -1 || (stop3-index)%3 !=0){
            stop3 = dna.length();
        }
       //System.out.println(stop1 + " " + stop2 + " " + stop3);
        return Math.min(stop1,Math.min(stop2,stop3));
    }
    public void printAll(String dna){
        //System.out.println("DNA string is: " + dna);
        String dnaLower = dna.toLowerCase();
        int beg = 0;
        while(true){
            int log = dnaLower.indexOf("atg",beg);
            if(log == -1){
            break;
            }
            //System.out.println("LOG " + log);
            int stop = findStopIndex(dnaLower, log);
            if(stop == dnaLower.length()){
                if(beg > dnaLower.length()-6){
                    break;
                }
                else {
                    beg = log + 3;    
                }
            } 
            else {
                System.out.println(dna.substring(log,stop+3));
                //System.out.println("HERE " + beg + " " + stop);
                beg = stop + 3;
            }
        }
    }
    public void testFinder(){
        //String dna1 = "ATGAAATGAAAA";
        //String dna2 = "ccatgccctaataaatgtctgtaatgtaga";
        String dna3 = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";
        //printAll(dna1);
        //printAll(dna2);
        printAll(dna3);
    }
}