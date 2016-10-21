package assignment4;

public class Critter4 extends Critter{
	@Override
	public String toString() { return "4"; }
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	
	
	public Critter4() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}
	public boolean fight(String not_used) { 
		return dir > 6;                 //only if the critter moves down right diagonally, it will want to fight
	}
	@Override
	public void doTimeStep() {
		/* if direction is greater than 4, then we take 1 step and if less than that, then we take 4 steps */
		if(dir > 4) {
			walk(dir);
		}
		else{
			run(dir);
			run(dir);
		}
		if(dir > 0){
                                            //if the direction is is not moving to the right, then we create
			Critter4 bb = new Critter4();       //a new critter. The Critter reproduces
			reproduce(bb, dir);
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
	
	public static void runStats(java.util.List<Critter> crit4) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : crit4) {
			Critter4 c = (Critter4) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		System.out.print("" + crit4.size() + " total Critters4    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * crit4.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * crit4.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * crit4.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * crit4.size()) + "% left   ");
		System.out.println();
	}
}
