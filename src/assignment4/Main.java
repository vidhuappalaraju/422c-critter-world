/* CRITTERS Main.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * Devin Amatya
 * dga383
 * 16455
 * Vidhu Appalaraju
 * vsa267
 * 16465
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
        while(kb.hasNextLine()){
        	 String line = kb.nextLine();        //reads the line
        	if(line.trim().length() == 0){      //i think we might have to delete this because nothing
        		continue;                       //is an invalid command
        	}
        	
        	String[] word = line.split("\\s+");     //removes the whitespaces in the line
        try{
        	switch(word[0]){                        //we take the first word of the line and do different things
        	case "quit":                            //according to the first word
        		if(word.length > 1)
        			throw new Exception();          //if the first word is quit, we break out of the loop and the
        		break;                              //program ends
        	case "show":
        		if(word.length == 1)                //if the first word is show, we display the Critter world
        		 Critter.displayWorld();            //if there is anything other than show, we will have an invalid
        		else{                               //command
        			throw new Exception();
        		}
        		 break;
        	case "step":
        		if(word.length == 1){               //if the first word is step and not followed by any numbers,
        			Critter.worldTimeStep();           //we perform the worldTimeStep method once
        			
        		}
        		else if(word.length == 2){
        			int step = Integer.parseInt(word[1]);
        			if(step <= 0){                  //if there is a number after step that's greater than 0,
        				throw new Exception();      //we perform the worldTimeStep as many times the number is
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
        		if(word.length == 1){               //if the first word is seed, we set the seed according to the
        			Critter.setSeed(Integer.parseInt(word[1]));     //number that seed is followed by
        		}
        		else if(word.length > 2){
        			throw new NumberFormatException();
        		}
        		else{
        			
        			throw new Exception();
        		}
        		break;
        	case "make":                                //if the first word is make then we need to make a critter
        		if(word.length == 2){
        			String class_name = word[1];        //the word make is followed by the class name
        			Critter.makeCritter(class_name);        //we make the Critter of the class name
        		}
        		else if(word.length == 3){              //if there is a number after the class name, then we
        			String class_name = word[1];        //make the Critters of the class name equal the specified
        			int count = Integer.parseInt(word[2]);      //number
        			if(count == 0){
        				throw new Exception();
        			}
        			for(int i = count; i > 0; i--){
        				Critter.makeCritter(class_name);  //for loop to make the specified number of critters
        			}
        		}
        		else{
        			throw new Exception();
        		}
        		break;
        	case "stats":
        		if(word.length == 2){
        			Class<?> critters = Class.forName(myPackage + "." + word[1]);//retrieves the class from the
        			Method meth = critters.getMethod("runStats", List.class);    //string. this gets a method
        			meth.invoke(null, Critter.getInstances(word[1])); //called runStats with the paramater as
        		}                                                      //a List
        		else{
        			throw new Exception();              //if stats has a word after it,then it's an invalid command
        		}
        	default:
        		System.out.println("error processing: " + line);   //if the first word is not any of the cases
        	}                                                       //then it's an error processing
        }
        catch(NumberFormatException e){
        	System.out.println("error processing: " + line);
        }
        catch(Exception e){
        	System.out.println("invalid command: " + line); //for any exceptions caught, it's an invalid command
        }
        
       }
       
      //  System.out.println("GLHF");
        
        /* Write your code above */
     //   System.out.flush();

    }
}
