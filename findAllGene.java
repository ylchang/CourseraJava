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
            int stop = findStopIndex(dnaLower, log + 3);
            if(stop == dnaLower.length()){
                if(beg > dnaLower.length()-6){
                    break;
                }
                else {
                    beg = log + 3;    
                }
            } 
            else {
                String result = dna.substring(log,stop+3);
                System.out.println(result + " " + cgRatio(result));
                //System.out.println("HERE " + beg + " " + stop);
                beg = stop + 3;
            }
        }
    }
    public StorageResource storeAll(String dna){
        //System.out.println("DNA string is: " + dna);
        StorageResource store = new StorageResource();
        String dnaLower = dna.toLowerCase();
        int beg = 0;
        while(true){
            int log = dnaLower.indexOf("atg",beg);
            if(log == -1){
            break;
            }
            //System.out.println("LOG " + log);
            int stop = findStopIndex(dnaLower, log+3);
            if(stop == dnaLower.length()){
                if(beg > dnaLower.length()-6){
                    break;
                }
                else {
                    beg = log + 3;    
                }
            } 
            else {
                //System.out.println(dna.substring(log,stop+3));
                //System.out.println("HERE " + beg + " " + stop);
                store.add(dna.substring(log,stop+3));
                beg = stop + 3;
            }
        }
        return store;
    }
    public double cgRatio(String dna){
        String dnaLower = dna.toLowerCase();
        //System.out.println(dnaLower);
        int countC = 0;
        int beg = 0;
        while(true){
            int check = dnaLower.indexOf("c",beg);
            if(check == -1){
                break;
            }
            countC++;
            beg = check + 1;
            //System.out.println(beg);
        }
        int countG = 0;
        beg = 0;
        while(true){
            int check = dnaLower.indexOf("g",beg);
            if(check == -1){
                break;
            }
            countG++;
            beg = check + 1;
        }
        return (double)(countC+countG)/dna.length();
    }
    public int countCTG(String dna){
        String dnaLower = dna.toLowerCase();
        int count = 0;
        int beg = 0;
        while(true){
            int check = dnaLower.indexOf("ctg",beg);
            if(check == -1){
                break;
            }
            count++;
            beg = check + 3;
            //System.out.println(beg);
        }
        return count;
    }
    public void printGenes(StorageResource sr){
        int count1 = 0;
        int count2 = 0;
        for(String item : sr.data()){
            if(item.length() > 60){
                //System.out.println(item);
                count1++;
            }
        }
        System.out.println("Number of item that are longer than 60 characters: " + count1);
        for(String item : sr.data()){
            if(cgRatio(item) > 0.35){
                //System.out.println(item);
                count2++;
            }
        }
        System.out.println("Number of item whose C-G-ratio is higher than 0.35: " + count2);
        String longString = "";
        for(String item : sr.data()){
            if(item.length() > longString.length()){
                //System.out.println(item);
                longString = item;
            }
        }
        System.out.println("The longest gene in this collection of genes is: " + longString);
        System.out.println("It has " + longString.length() + " characters");
    }
    public void testPrintFinder(){
        StorageResource s = new StorageResource();
        //String dna1 = "ATGAAATGAAAA";
        //String dna2 = "ccatgccctaataaatgtctgtaatgtaga";
        String dna3 = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";
        //printAll(dna1);
        //printAll(dna2);
        printAll(dna3);
    }
        public void testStorageFinder(){
        FileResource fr = new FileResource("C:/Users/13360/Desktop/Coursera/Java/dna/brca1line.fa");
        String dna = fr.asString();
        StorageResource s = new StorageResource();
        //String dna1 = "ATGAAATGAAAA";
        //String dna2 = "ccatgccctaataaatgtctgtaatgtaga";
        //String dna3 = "CATGTAATAGATGAATGACTGATAGATATGCTTGTATGCTATGAAAATGTGAAATGACCCA";
        s = storeAll(dna);
        for(String gene : s.data()){
           System.out.println(gene.length()+"\t"+gene);
        }
        System.out.println(s.size());
        printGenes(s);
    }
}