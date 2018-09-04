package infromation.retrieval;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class positionalIndexModel {
private ArrayList<String> Documents;
private String Query;
private ArrayList<termDetails> output;
//private  String Doc[];
/*positionalIndexModel(ArrayList documents ,String query){
    Documents=documents;
    Query=query;
}*/
    public ArrayList<termDetails> posIndexModel(ArrayList documents ,String query){
        Documents=documents;
        Query=query;
        boolean checker=true;  //to check if the term has taken before
        int frequency,DocNumb;
        ArrayList<termDetails> terms=new ArrayList<>();
        // loop to get each Doc from entered Documents 
        for (String Doc:Documents){
            StringTokenizer token = new StringTokenizer(Doc);   
            while(token.hasMoreTokens()){ //loop for each word in all Docs
                //get each word in each Doc
                String word = token.nextToken();
                DocNumb=0;
                frequency=0;
                //loop in termDetails arraylist to check if term is already taken
                for (termDetails term:terms){
                    //if word has been taken before
                    if(word.equals(term.term)){
                        checker=false;
                        break;
                    }
                    else{
                        checker=true;}
                }
                //if word has never been taken
                if(checker){
                termDetails term=new termDetails(word);
                    for(String doc:Documents){                        
                        DocNumb++;
                        int pos=0;
                        String d="d";
                        term.DocPos.put(d+DocNumb,"0");
                        StringTokenizer t = new StringTokenizer(doc);
                        while(t.hasMoreTokens()){
                            pos++;
                            if(word.equals(t.nextToken())){
                               frequency++;
                               String postion=term.DocPos.get(d+DocNumb);
                               if (postion.equals("0")){
                               postion=String.valueOf(pos);
                               term.DocPos.put(d+DocNumb,postion);
                               }
                               else{
                               postion=postion+","+pos;
                               term.DocPos.put(d+DocNumb,postion);
                               }  
                            }
                        }                      
                    }
                terms.add(term);
                term.noOFDocContainTerm=frequency;
                }
            }    
        }
       ArrayList<termDetails>output =positionalIndex(terms);
       return output;
    }
     //return positonal index data 
      public ArrayList<termDetails> positionalIndex(ArrayList<termDetails> terms){
        output=new ArrayList<>();
        StringTokenizer token = new StringTokenizer(Query.toLowerCase());   
            while(token.hasMoreTokens()){
                //get each word in each Doc
                String word = token.nextToken();
                for (termDetails term : terms){
                    if(word.equals(term.term)){
                        output.add(term);
                    }
                }
            }
    return output;
    }
    // return Matched Document
    public String[] matchedDocument(){
        String [] Doc=new String[Documents.size()];
        for(int i=1;i<=Documents.size();i++){
            int counter=0;
            String value="",prevalue="";
            boolean flag=false;
            for (termDetails term : output){ 
                value=term.DocPos.get("d"+i);
                if (value.contains(",")){
                    String[] values = value.split(",");
                    int freq=values.length;
                    if(flag){
                        while(true){ //case value=n,n ,prevalue=n
                            if(Integer.valueOf(prevalue)+1==Integer.valueOf(values[freq-1])){
                                counter++;
                                prevalue=values[freq-1];
                            }
                            freq--;
                            if(freq==0)
                                break;
                        }
                        //no need to go on 
                        if(counter==0) //prevalue=values[freq]; make no diffrence
                            break;
                    }
                    else{
                    prevalue=value;
                    }           
                    flag=true;
                }
                else{
                    if(flag){   //case value=n,n ,prevalue=n,n
                        if (prevalue.contains(",")& value.contains(",")){
                            String[] prevalues = prevalue.split(",");
                            String[] values = value.split(",");
                            prevalue="";
                            String ch="";
                            int prefreq=prevalues.length;
                            int freq=values.length;
                            boolean know=false;
                            for(int k=0;k<freq;k++){ 
                                for(int c=0;c<prefreq;c++){ //case value=n,n ,prevalue=n,n
                                    if(Integer.valueOf(values[k])-1==Integer.valueOf(prevalues[c])){
                                        know=true;
                                        prevalue+=values[k]+ch;
                                        ch=",";
                                    }
                                }   
                            }
                            if(know)
                                counter++;
                        }
                        else if (prevalue.contains(",")){ //case value=n ,prevalue=n,n
                            String[] prevalues = prevalue.split(",");
                            int freq=prevalues.length;
                            while(true){ //case value=n ,prevalue=n,n
                            if(Integer.valueOf(value)-1==Integer.valueOf(prevalues[freq-1])){
                                counter++;
                                prevalue=value;
                            }
                            freq--;
                            if(freq==0)
                                break;
                            }
                        }
                        else if(Integer.valueOf(prevalue)+1==Integer.valueOf(value)){
                            counter++;
                        }
                    }
                    prevalue=value;
                    flag=true;  
                }
            }
            if(counter==output.size()-1){
                Doc[i-1]="d"+i;
            }   
        }
    return Doc;
    }
}
