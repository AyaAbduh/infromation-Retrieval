package infromation.retrieval;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InfromationRetrieval {
    
    public static void main(String[] args) throws FileNotFoundException {
    //what files contain
    //String Doc1="new home sales top forecasts";
    //String Doc2="home sales rise in july";    
    //String Doc3="increase in home sales in july";
    //String Doc4="july new home sales rise";
    String path="src\\infromation\\retrieval\\";
    String Doc1 = new Scanner(new File(path+"Doc1.txt")).useDelimiter("\\Z").next();
    String Doc2 = new Scanner(new File(path+"Doc2.txt")).useDelimiter("\\Z").next();
    String Doc3 = new Scanner(new File(path+"Doc3.txt")).useDelimiter("\\Z").next();
    String Doc4 = new Scanner(new File(path+"Doc4.txt")).useDelimiter("\\Z").next();
    //query
    String query="Rise in july";
    ArrayList Documents = new ArrayList(); //Array list contain all Doc
    Documents.add(Doc1.toLowerCase());
    Documents.add(Doc2.toLowerCase());
    Documents.add(Doc3.toLowerCase());
    Documents.add(Doc4.toLowerCase());
    //positionalIndexModel M3=new positionalIndexModel(Documents,query);
    
    positionalIndexModel M3=new positionalIndexModel();
    ArrayList<termDetails> output =M3.posIndexModel(Documents, query);
    String Doc[]=M3.matchedDocument();
         //to display all details about query 
            for (termDetails term : output){
                    System.out.print(term.term);    
                    System.out.println(term.DocPos);
                }
            //to display all documents that contain the query
            for(String doc:Doc){
                if(doc!=null)
                    System.out.println("the matched document is "+Doc[1]);
            }
    }
}
