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
		Map nextGeneration = new Map(new char[this.map.x][this.map.y])
		for (int xi=0; xi<this.map.x; xi++){
			for (int yi=0; yi<this.map.y; yi++){
				count = neighbors(xi, yi);
				char currentState = this.map.get(xi,
				char newState;
				boolean isAlive = currentState==this.alive
				if (isAlive && count<2){
					newState = this.dead;
				} else if (isDead && count>3){
					newState = this.alive;
				} else {
					newState = currentState;
				}
				nextGeneration.set(xi, yi, newState);
			}
		}
		this.map = nextGeneration;
	}
	/** Print friendly string
	 */
	public String toString(){
		 return this.map.toString();
	}
}
