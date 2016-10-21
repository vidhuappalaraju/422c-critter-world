/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * Devin Amatya
 * dga383
 * <Student1 5-digit Unique No.>
 * Vidhu Appalaraju
 * vsa267
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */
package assignment4;

import java.util.List;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private static  String [][]worldMatrix = new String[Params.world_height][Params.world_width];
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> Moved = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	private static boolean AllMove;

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	/**
	 * Sets the random number generator seed
	 * @param new_seed the new seed to set the random number generator to
	 */
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);																				//setting new random number generator
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	/**
	 * Returns a visual depiction of our critter in the ASCII interface, subclasses should override this method
	 */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	private int x_coord;
	private int y_coord;
	
	/**
	 * moves critter 1 space in the direction specified. Will not move to location if trying to run from a fight and the space is occupied, or it has already moved prior
	 * @param direction the direction the critter should move to
	 */
	protected final void walk(int direction) {
		this.energy -= Params.walk_energy_cost;																				//deducts walk energy regardless of walking
		boolean move = Moved.contains(this);																				//checks if critter has moved
		int oldX = this.x_coord;
		int oldY = this.y_coord;
		if(!move){
			switch(direction){																								//walk in the desired direction
				case 0:
					x_coord++;
					if(x_coord >= Params.world_width){																		//consider if the critter goes to other side of the grid, checks for all 8 directions
						x_coord -= Params.world_width;
					}
					break;
				case 1:
					x_coord++;
					y_coord--;
					if(x_coord >= Params.world_width){
						x_coord -= Params.world_width;
					}
					if(y_coord < 0){
						y_coord += Params.world_height;
					}
					break;
				case 2:
					y_coord--;
					if(y_coord < 0){
						y_coord += Params.world_height;
					}
					break;
				case 3:
					x_coord--;
					y_coord--;
					if(x_coord < 0){
						x_coord += Params.world_width;
					}
					if(y_coord < 0){
						y_coord += Params.world_height;
					}
					break;
				case 4:
					x_coord--;
					if(x_coord < 0){
						x_coord += Params.world_width;
					}
					break;
				case 5:
					x_coord--;
					y_coord++;
					if(x_coord < 0){
						x_coord += Params.world_width;
					}
					if(y_coord >= Params.world_height){
						y_coord -= Params.world_height;
					}
					break;
				case 6:
					y_coord++;
					if(y_coord >= Params.world_height){
						y_coord -= Params.world_height;
					}
					break;
				case 7:
					x_coord++;
					y_coord++;
					if(x_coord >= Params.world_width){
						x_coord -= Params.world_width;
					}
					if(y_coord >= Params.world_height){
						y_coord -= Params.world_height;
					}
					break;
			}	
			Moved.add(this);																							//add to array of moved critters
			if(AllMove){																								//check if the moving period of the worldtimestep has happened
				for(Critter C: population){
					if(!(population.indexOf(this) == population.indexOf(C)) && this.x_coord == C.x_coord && this.y_coord == C.y_coord && C.energy > 0){	//don't allow a critter to enter a living critters spot
						this.x_coord = oldX;
						this.y_coord = oldY;
						break;
					}
				}
			}
		}
	}
	/**
	 * moves critter 2 spaces in the direction specified. Will not move to location if trying to run from a fight and the space is occupied, or it has already moved prior
	 * @param direction the direction the critter should move to
	 */
	protected final void run(int direction) {
		energy -= Params.run_energy_cost;																				//deducts walk energy regardless of walking
		boolean move = Moved.contains(this);																			//checks if critter has moved
		int oldX = this.x_coord;
		int oldY = this.y_coord;
		if(!move){
			switch(direction){																							//walk in the desired direction
				case 0:
					x_coord+=2;
					if(x_coord >= Params.world_width){																	//consider if the critter goes to other side of the grid, checks for all 8 directions
						x_coord -= Params.world_width;
					}
					break;
				case 1:
					x_coord+=2;
					y_coord-=2;
					if(x_coord >= Params.world_width){
						x_coord -= Params.world_width;
					}
					if(y_coord < 0){
						y_coord += Params.world_height;
					}
					break;
				case 2:
					y_coord+=2;
					if(y_coord < 0){
						y_coord += Params.world_height;
					}
					break;
				case 3:
					x_coord-=2;
					y_coord-=2;
					if(x_coord < 0){
						x_coord += Params.world_width;
					}
					if(y_coord < 0){
						y_coord += Params.world_height;
					}
					break;
				case 4:
					x_coord-=2;
					if(x_coord < 0){
						x_coord += Params.world_width;
					}
					break;
				case 5:
					x_coord-=2;
					y_coord+=2;
					if(x_coord < 0){
						x_coord += Params.world_width;
					}
					if(y_coord >= Params.world_height){
						y_coord -= Params.world_height;
					}
					break;
				case 6:
					y_coord+=2;
					if(y_coord >= Params.world_height){
						y_coord -= Params.world_height;
					}
					break;
				case 7:
					x_coord+=2;
					y_coord+=2;
					if(x_coord >= Params.world_width){
						x_coord -= Params.world_width;
					}
					if(y_coord >= Params.world_height){
						y_coord -= Params.world_height;
					}
					break;
			}
			Moved.add(this);																							//add to array of moved critters
			if(AllMove){																								//check if the moving period of the worldtimestep has happened
				for(Critter C: population){
					if(!(population.indexOf(this) == population.indexOf(C)) && this.x_coord == C.x_coord && this.y_coord == C.y_coord && C.energy > 0){ //don't allow a critter to enter a living critters spot
						this.x_coord = oldX;
						this.y_coord = oldY;
						break;
					}
				}
			}
		}
	}
	/**
	 * adds a new child critter of a parent critter, gives it half their energy and moves it an adjacent space from the parent, and then adds it to the babies collection
	 * @param offspring the child getting the energy from the parent
	 * @param direction the direction the child will go to after reproduction
	 */
	protected final void reproduce(Critter offspring, int direction) {
		
		if(energy >= Params.min_reproduce_energy){																		//check if critter has enough energy to reproduce
			offspring.energy = this.energy/2;																			//give offspring half of parent's energy rounded down
			if(this.energy%2 == 1)																						//cut parent's energy in half rounded up
				this.energy = this.energy/2 + 1;
			if(this.energy%2 == 0)
				this.energy = this.energy/2;
			offspring.x_coord = this.x_coord;																			//set offspring to parent location
			offspring.y_coord = this.y_coord;
			offspring.walk(direction);																					//move offspring
			offspring.energy += Params.walk_energy_cost;																//restore energy to offspring- it was technically born there
			babies.add(offspring);																						//add offspring to babies collection
		}
		
		else
			return;
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name the name of the critter subclass
	 * @throws InvalidCritterException the exception to throw if the name is invalid
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		Critter C;
		try{
			Class<?> Crit = Class.forName(Critter.myPackage + "." + critter_class_name);
			C = (Critter)(Crit.newInstance());																			//make a new instance of the subclass
		}
		catch(Exception e){
			throw new InvalidCritterException(critter_class_name);														//throw exception if string is not a class name
		}
		C.x_coord = Critter.getRandomInt(Params.world_width);
		C.y_coord = Critter.getRandomInt(Params.world_height);
		C.energy = Params.start_energy;
		population.add(C);																								//put new critter in population
	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		List<Critter> result = new java.util.ArrayList<Critter>();
        Class<?> critter, critterclass;
        try{
            critterclass = Class.forName(myPackage + ".Critter");
            critter = Class.forName(myPackage + "." + critter_class_name);												
            if(!critterclass.isAssignableFrom(critter)){
                throw new InvalidCritterException(critter_class_name);													//throw exception if not a class name
            }
        }catch(ClassNotFoundException e){
            throw new InvalidCritterException(critter_class_name);
        }
        for(int i = 0; i < population.size(); i++){
            if(critter.isInstance(population.get(i))){
                result.add(population.get(i));																			//create list of critters in population of that subclass
            }
        }
        return result;
    }
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every time step.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		population.clear();
	}
	
	/**
	 * Performs the entire world step- deals with movement, encounters, and reproduction
	 */
	public static void worldTimeStep() {	
		AllMove = false;
		for(Critter C : population){																					//initiate all doTimeStep functions
			C.doTimeStep();
		}
		AllMove = true;
		for(int i = 0; i < population.size(); i++){																		//kill off whoever died from that
			Critter C = population.get(i);
			if(C.getEnergy() <= 0){
				population.remove(i);
				i--;
			}
		}
		for(Critter A : population){																					//resolve fights 
			for(Critter B : population){
				if(!(population.indexOf(A) == population.indexOf(B)) && A.energy > 0 && B.energy > 0){
					boolean Afight = false;
					boolean Bfight = false;
					if(A.x_coord == B.x_coord && A.y_coord == B.y_coord){
						Afight = A.fight(B.toString());
						Bfight = B.fight(A.toString());
					}
					if(A.x_coord == B.x_coord && A.y_coord == B.y_coord && A.energy > 0 && B.energy > 0){				//fight if still in same position
						int Aroll = 0;
						int Broll = 0;
						if(Afight)
							Aroll = Critter.getRandomInt(A.energy);														//determine critter rolls
						if(Bfight)
							Broll = Critter.getRandomInt(B.energy);
						if(Aroll >= Broll){																				//give winner half energy
							A.energy += B.energy/2;
							B.energy = 0;
						}
						else if(Aroll < Broll){
							B.energy += A.energy/2;
							A.energy = 0;
						}
					}
				}
			}
		}
		for(Critter C: population){																						//deduct rest energy cost
			C.energy-= Params.rest_energy_cost;
		}
		
		for(int i = 0; i < population.size(); i++){																		//kill off whoever died from that
			Critter C = population.get(i);
			if(C.getEnergy() <= 0){
				population.remove(i);
				i--;
			}
		}
		for(int i = 0; i < Params.refresh_algae_count; i++){															//generate algae
			try{
			Critter.makeCritter("Algae");
			}
			catch(InvalidCritterException e){
				e.toString();
			}
		}
		for(Critter B: babies){																							//add in babies from reproduction
			population.add(B);	
		}
		babies.clear();																									//empty babies list
		for(int i = 0; i < population.size(); i++){																		//kill off whoever died from that
			Critter C = population.get(i);
			if(C.getEnergy() <= 0){
				population.remove(i);
				i--;
			}
		}	
		Moved.clear();																									//empty clear list
		updateWorld();																									//update world
	}
	/**
	 * updates worldMatrix array to the current state of the world
	 */
	public static void updateWorld(){
		for(int i = 0; i < Params.world_height; i++){
			for (int j = 0; j < Params.world_width; j++){
				worldMatrix[i][j] = " ";																				//reset world to blank spaces
			}
		}
		for(Critter C: population){
			worldMatrix[C.x_coord][C.y_coord] = C.toString();															//put critter tostrings wherever they are
		}
	}
	/**
	 * displays the world in ASCII format
	 */
	public static void displayWorld() {
		updateWorld();																									//make sure world is updated
		String[][] displayMatrix = new String[Params.world_height + 2][Params.world_width + 2];
		
		for(int i = 0; i < Params.world_width + 2; i++){																//make the frame
			if(i == 0){
				displayMatrix[0][0] = "+";
				displayMatrix[Params.world_height + 1][0] = "+";
			}
			else if(i == Params.world_width + 1){
				displayMatrix[0][Params.world_width + 1] = "+";
				displayMatrix[Params.world_height + 1][Params.world_width + 1] = "+";				
			}
			else if(!(i == 0) && !(i == Params.world_width + 1)){
				displayMatrix[0][i] = "-";
				displayMatrix[Params.world_height + 1][i] = "-";
			}
		}
		
		for(int i = 1; i < Params.world_height + 1; i++){
			displayMatrix[i][0] = "|";
			displayMatrix[i][Params.world_width + 1] = "|";
		}
		
		for(int i = 1; i < Params.world_height + 1; i++){
			for(int j = 1; j < Params.world_width + 1; j++){
				displayMatrix[i][j] = " ";
			}
		}
		for(int i = 0; i < Params.world_height; i++){																//put critters in their spaces
			for(int j = 0; j < Params.world_width; j++){
				displayMatrix[i+1][j+1] = worldMatrix[i][j];
			}
		}
		for(int i = 0; i < Params.world_height + 2; i++){															//print world
			for(int j = 0; j < Params.world_width + 2; j++){
				if(j == Params.world_width + 1)
					System.out.println(displayMatrix[i][j]);
				else
					System.out.print(displayMatrix[i][j]);
			}
		}
	}
}
