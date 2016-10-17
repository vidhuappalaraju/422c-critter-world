/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */
package assignment4; // cannot be in default package
import java.util.List;
import java.util.Scanner;
import java.io.*;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     */
    public static void main(String[] args) { 
    	Critter.displayWorld();
        /*if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        
    	 boolean process = true;
         while(process){
        String word = kb.next();
        int step;
       //String cut = word.substring(0,5);
        if(word.equals("quit")){
     	   System.exit(0);
        }
        else if(word.equals("show")){
     	   Critter.displayWorld();
        }
        else if(word.equals("step")){
     	   if(kb.hasNextInt()){
     		   step = kb.nextInt();
     	   }
     	   else{
     		    step = 1;
     	   }
        }
        else if(word.equals("seed")){
     	   if(kb.hasNextInt()){
     		   int seed = kb.nextInt();
     		   Critter.setSeed(seed);
     	   }
        }
        else if(word.equals("make")){
     	   if(kb.hasNext()){
     		   String class_name = kb.next();
     		   if(kb.hasNextInt()){
     			    step = kb.nextInt();
     		   }
     		   else{
     			    step = 1;
     		   }
     		  while(step > 0){ 
     		   try{
     		   Critter.makeCritter(class_name);
     		   }
     		   catch(InvalidCritterException e){
     			   e.printStackTrace();
     		   }
     		   step--;
     		  }
     	   }
        }
        else if(word.equals("stats")){
     	   if(kb.hasNext()){
     		   String class_name = kb.next();
     		   try 
     		   {
     				List<Critter> result = new java.util.ArrayList<Critter>();
     			   result = Critter.getInstances(class_name);
     			 
     	    		   @SuppressWarnings("unchecked")
 					Class<Critter> C = (Class<Critter>)Class.forName(class_name);
     	    		   Critter Crit = (Critter) C.newInstance();
     	    		   if(!(Crit instanceof Critter)) throw new InvalidCritterException(class_name);
     	    		   
     	    		   
     		   }
     		   catch(InvalidCritterException e){
     			   e.printStackTrace();
     		   } catch (InstantiationException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			} catch (IllegalAccessException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			} catch (ClassNotFoundException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}
     
     		  
     	   }
        }
      }
        //System.out.println("GLHF");
        
        /* Write your code above */
        //System.out.flush();

    }
}
