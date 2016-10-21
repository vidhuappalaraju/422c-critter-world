package assignment4;

public class Critter3 extends Critter{
		
	@Override
	public String toString() { return "3"; }
	
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	
	public Critter3() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}
	
	public boolean fight(String not_used) { 
		return dir > 2;                     //This Critter will fight if its direction is greater than 2.
	}
	
	@Override
	public void doTimeStep() {
		/* takes three steps forward */
		walk(dir);
		walk(dir);
		walk(dir);
		
		if(dir > 0){
            
			Critter3 bb = new Critter3();           //if the critter goes in any direction other than right,
			reproduce(bb, dir);                      //then it will reproduce. (0 = Critter moving to the right)
		}
		
		/* pick a new direction based on our genes */
		int roll = Critter.getRandomInt(GENE_TOTAL);
		int turn = 0;
		while (genes[turn] <= roll) {
			roll = roll - genes[turn];
			turn = turn + 1;
		}
		assert(turn < 8);
		
		dir = (dir + turn) % 8;
	}
	public static void runStats(java.util.List<Critter> crit3) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : crit3) {
			Critter3 c = (Critter3) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		System.out.print("" + crit3.size() + " total Critters3    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * crit3.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * crit3.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * crit3.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * crit3.size()) + "% left   ");
		System.out.println();
	}
	
}
