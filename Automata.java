public class Automata {
	Map map;

	/** Constructor
	 */
	public Automata( Map map ){
		this.map = map;
	}
	/** Count the number of live neighbors
	 * @param x x position in the map of where we are centered
	 * @param y y position in the map of where we are centered
	 */
	private neighbors(int x, int y){
		int count = 0;
		for (int xi=x-1; xi<=x+1; xi++){
			for (int yi=y-1; yi<=y+1; yi++){
				if (this.map.get(xi, yi) == this.alive){
					count++;
				}
			}
		}
		if (this.map.get(x,y) == this.alive){
			count--;
		}
	}
	/** Simulate one round of the automata
	 */
	public void step(){
	}
	/** Print friendly string
	 */
	public String toString(){
		 return this.map.toString();
	}
}
