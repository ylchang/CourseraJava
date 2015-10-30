import edu.duke.*;

/**
 * Write a description of FindAllURL here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class findAllURL {
    public StorageResource storeAll(URLResource URL){
        //System.out.println("DNA string is: " + dna);
        StorageResource s = new StorageResource();
        for(String line : URL.lines()){
            String lineLower = line.toLowerCase();
            int beg = 0;
            while(true){
                int log = lineLower.indexOf("<a href",beg);
                if(log == -1){
                break;
                }
                beg = log + 9;
                //System.out.println("LOG " + log);
                if(beg > lineLower.length()-5){
                    break;
                }
                int stop = lineLower.indexOf("\">", beg);
                if(stop == -1){
                    break;
                }
                else {
                    System.out.println(line.substring(beg,stop));
                    s.add(line.substring(beg,stop));
                    beg = stop + 2;    
                } 
            }
        }
        return s;
    }
    public void printURL(StorageResource sr){
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int count4 = 0;
        //print the URLs,
        for(String item : sr.data()){
            String itemLower = item.toLowerCase();
            System.out.println(item);
            //count number of secure links (https)
            int check1 = item.indexOf("https");
            if(check1 != -1){
                count1++;
            }
            //count number of links that have ".com"
            int check2 = item.indexOf(".com");
            if(check2 != -1){
                count2++;
            }
            //count the number of links that end with “.com” or “.com/”
            int check3 = item.indexOf(".com/");
            System.out.println((check2+4)+ " " + (check3 + 5) + " " + item.length());
            if( (check2 + 4) == item.length() || (check3 + 5) == item.length()){
                count3++;
            }
            //count total number of "."
            int beg = 0;
            while(true){
                int pos = item.indexOf(".",beg);
                if(pos == -1){
                    break;
                }
                count4++;
                beg = pos + 1;
            }
        }
        //print the number of URL's found
        System.out.println("the number of URL's found: " + sr.size());
        //print the the number of secure links found,
        System.out.println("the number of secure links: " + count1);
        //print the number of links that have “.com” in them
        System.out.println("the number of links that have “.com”: " + count2);
        //print the number of links that end with “.com” or “.com/”
        System.out.println("the number of links that end with “.com” or “.com/”: " + count3);
        //print the total number of "."
        System.out.println("the total number of .: " + count4);
    }
    public void testFindURL(){
        URLResource ur = new URLResource("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        StorageResource store = storeAll(ur);
        printURL(store);
    }
}
