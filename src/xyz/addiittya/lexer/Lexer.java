package xyz.addiittya.lexer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
*
* @author addiittya
*/

public class Lexer {
	
	public static final String REGEX_VAR = "^[a-zA-Z_@$][a-zA-Z0-9_]*$";
	public static final String REGEX_NUM = "[0-9]*";
	
    static Set keywordSet ;
	static Set arithmeticSet;
	static Set comparisonSet;
	static Set logicalSet;
    
    static String lexed="";
    
    public static void lex()
    {

         try {
             keywordSet = new HashSet();
             arithmeticSet = new HashSet();
             comparisonSet = new HashSet();
             logicalSet = new HashSet();
             Scanner keywordFile = new Scanner(new File("keywords.txt"));
             Scanner arithmeticFile = new Scanner(new File("operators/arithmetic.txt"));
             Scanner comparisonFile = new Scanner(new File("operators/comparison.txt"));
             Scanner logicalFile = new Scanner(new File("operators/logical.txt"));
             
             while(keywordFile.hasNext())
             {
                 String word = keywordFile.next();
                 keywordSet.add(word);
             }
             while(arithmeticFile.hasNext())
             {
                 String word = arithmeticFile.next();
                 arithmeticSet.add(word);
             }
             while(comparisonFile.hasNext())
             {
                 String word = comparisonFile.next();
                 comparisonSet.add(word);
             }
             while(logicalFile.hasNext())
             {
                 String word = logicalFile.next();
                 logicalSet.add(word);
             }
             
             Scanner file = new Scanner(new File("input.txt"));
             
             while(file.hasNext())
             {
                 String token = file.next();
                 token = token.toLowerCase();
                 if(keywordSet.contains(token))
                     lexed+=token + " is a keyword \n\n";
                 else if(arithmeticSet.contains(token))
                     lexed+=token + " is an arithmetic operator \n\n";
                 else if(token.equals("="))
                     lexed+=token + " is an assignment operator \n\n";
                 else if(comparisonSet.contains(token))
                     lexed+=token + " is a comparison operator \n\n";
                 else if(logicalSet.contains(token))
                     lexed+=token + " is a logical operator \n\n";
                 else if(token.matches(REGEX_NUM))
                     lexed+=token + " is a constant \n\n";
                 else if(token.matches(REGEX_VAR)) {   
                 	lexed+=token + " is a variable \n\n";
                 	//System.out.println(lexed);
                 }
                 else
                     lexed+=token + " \n\n";
             }
             
             // TODO Implementation of Parser Here
            
             } catch (FileNotFoundException ex) {
                 Logger.getLogger(Lexer.class.getName()).log(Level.SEVERE, null, ex);
         }
    	
    	
    }
    
    public  Lexer()
    {   
    	// TODO implementing a UI
    	
        lex();        
    }
}
