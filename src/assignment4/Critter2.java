package assignment4;

public class Critter2  extends Critter {
	@Override
	public String toString() { return "2"; }
	
	private static final int GENE_TOTAL = 24;
	private int[] genes = new int[8];
	private int dir;
	
	public Critter2() {
		for (int k = 0; k < 8; k += 1) {
			genes[k] = GENE_TOTAL / 8;
		}
		dir = Critter.getRandomInt(8);
	}
	
	public boolean fight(String not_used) { 
		run(dir);
		Critter2 aa = new Critter2();
		reproduce(aa, dir);
		reproduce(aa, dir);
		reproduce(aa, dir);
		return true; 
	}
	
	@Override
	public void doTimeStep() {
		/* takes three steps forward */
		walk(dir);
		if(dir < 2){
			Critter2 bb = new Critter2();
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
	public static void runStats(java.util.List<Critter> crit2) {
		int total_straight = 0;
		int total_left = 0;
		int total_right = 0;
		int total_back = 0;
		for (Object obj : crit2) {
			Critter2 c = (Critter2) obj;
			total_straight += c.genes[0];
			total_right += c.genes[1] + c.genes[2] + c.genes[3];
			total_back += c.genes[4];
			total_left += c.genes[5] + c.genes[6] + c.genes[7];
		}
		System.out.print("" + crit2.size() + " total Critters1    ");
		System.out.print("" + total_straight / (GENE_TOTAL * 0.01 * crit2.size()) + "% straight   ");
		System.out.print("" + total_back / (GENE_TOTAL * 0.01 * crit2.size()) + "% back   ");
		System.out.print("" + total_right / (GENE_TOTAL * 0.01 * crit2.size()) + "% right   ");
		System.out.print("" + total_left / (GENE_TOTAL * 0.01 * crit2.size()) + "% left   ");
		System.out.println();
	}
	
}

