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
import java.util.*;
import java.io.*;
import java.lang.reflect.Method;


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
        if (args.length != 0) {
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
        while(kb.hasNext()){
        	 String line = kb.nextLine();
        	
        	
        	String[] word = line.split("\\s+");
        try{
        	switch(word[0]){
        	case "quit":
        		if(word.length == 1)
        		System.exit(0);
        		else{
        			throw new Exception();
        		}
        		break;
        	case "show":
        		if(word.length == 1)
        		 Critter.displayWorld();
        		else{
        			throw new Exception();
        		}
        		 break;
        	case "step":
        		if(word.length == 1){
        			Critter.worldTimeStep();
        			
        		}
        		else if(word.length == 2){
        			int step = Integer.parseInt(word[1]);
        			if(step <= 0){
        				throw new Exception();
        			}
        			for(int j = step; j > 0; j--){
            			Critter.worldTimeStep();
            		}
        		}
        		else{
        			throw new Exception();
        		}
        		break;
        	case "seed":
        		if(word.length == 1){
        			Critter.setSeed(Integer.parseInt(word[1]));
        		}
        		else{
        			throw new Exception();
        		}
        		break;
        	case "make":
        		if(word.length == 2){
        			String class_name = word[1];
        			Critter.makeCritter(class_name);
        		}
        		else if(word.length == 3){
        			String class_name = word[1];
        			int count = Integer.parseInt(word[2]);
        			if(count == 0){
        				throw new Exception();
        			}
        			for(int i = count; i > 0; i--){
        				Critter.makeCritter(class_name);
        			}
        		}
        		else{
        			throw new Exception();
        		}
        		break;
        	case "stats":
        		if(word.length == 2){
        			Class<?> critters = Class.forName(myPackage + "." + word[1]);
        			Method meth = critters.getMethod("runStats", List.class);
        			meth.invoke(null, Critter.getInstances(word[1]));
        		}
        		else{
        			throw new Exception();
        		}
        	default:
        		System.out.println("error processing: " + line);
        	}
        }
        catch(Exception e){
        	System.out.println("invalid command: " + line);
        }
       }
       
        System.out.println("GLHF");
        
        /* Write your code above */
        System.out.flush();

    }
}
