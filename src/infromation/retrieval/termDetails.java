
package infromation.retrieval;

import java.util.HashMap;

public class termDetails {
    protected final String term;
    protected int noOFDocContainTerm=0;
    protected HashMap<String,String> DocPos;
    
    termDetails(String Term){
        term=Term;
        DocPos=new HashMap<String,String>();
    }
}
