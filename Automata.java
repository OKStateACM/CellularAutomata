public class Automata {

	//declare class variables below. these can be acessed [optionally] using
	//'this'. ex) `this.map=...`   or just    `map=...`
	//for clarity and simplicity, we use the `this` style for now.
	
	Map map; //stores the grid for the current generation

	//note the 'final' keyword. that means their value can *never* change
	//we use these type of 'constants' for many reasons:
	//	* even though their value is used in many places, it requires
	//		just one change should we need to.
	//	* the variable name gives a reader some insight. Imagine if I
	//		replaced every instance of these variables with 'o' or
	//		'.'. it might not make as much sense.
	//	* minimal optimizations.

	final char alive = 'o'; //character that represents a 'live' cell
	final char dead = '.';  //character that represents a 'dead' cell

	/** Constructor. this is called when we use 'new'
	 * @param map initial state
	 */
	public Automata( Map map ){
		this.map = map;
	}

	/** Count the number of live neighbors
	 * @param x x position in the map of where we are centered
	 * @param y y position in the map of where we are centered
	 */
	private int neighbors(int x, int y){
		int count = 0; //this will be a running total of 'alive'
		               //neighbors.
		//we want to check a 3x3 grid centered at (x,y).
		//	* x values range from [x-1, x+1]
		//	* y values range from [y-1, y+1]
		//note that includes (x,y) which we *don't* want to
		//include as a 'neighbor'. we will 'fix' this later.
		for (int xi=x-1; xi<=x+1; xi++){ //iterate over our x range
			for (int yi=y-1; yi<=y+1; yi++){ //iterate over our y
				if (this.map.get(xi, yi) == this.alive){
					count++; // if this neighbor is alive
					         // then count it!
				}
			}
		}
		//here is where we fix our error noted above.
		//if the cell at (x,y) is dead, it wasn't counted anywahs, so no change.
		//if it was alive, we 'overcounted' by exactly 1. so, remove it
		if (this.map.get(x,y) == this.alive){
			count--;
		}

		//and finally, return our final count.
		return count;
	}

	/** Simulate one round of the automaton
	 */
	public void step(){
		// we need to keep our current generation intact
		// while we calculate the next generation. So we will use
		// a new grid so we don't have to rewrite cella in our
		// current grid.
		Map nextGeneration = new Map(new char[this.map.x][this.map.y]);

		//we need to go over every single cell in the grid.
		//so we iterate over all the columns,
		//	and for each column, iterate over every row.
		//this ensures we get every (x,y) pair.
		for (int xi=0; xi<this.map.x; xi++){ //for *every* column
			for (int yi=0; yi<this.map.y; yi++){ //for *every* row
				//first step is to initialize some local varibles
				char currentState = this.map.get(xi, yi);
				char newState=' ';

				//note this boolean: it will make our
				//if statements much more readable later.
				boolean isAlive = currentState==this.alive;

				//we call the neighbors() method to get the
				//number of neighbors.
				// this pattern of breaking sub-problems
				// into seperate functions is key to good
				// readable code.
				//
				// this would be a mess if we had to also 
				// understand how neighbors() works to
				// understand how this function works.
				//
				// now we only have to trust that it *does*
				// work. we can get back to it later.
				int count = this.neighbors(xi, yi);

				// here are our rules:
				// this.alive/this.dead are constants.
				if (isAlive && count<2){ //underpopulation
					newState = this.dead;
				} else if (isAlive && count>3){ //over populated
					newState = this.dead;
				} else if (!isAlive && count>2){ //growth!
					newState = this.alive;
				} else { //stay alive/dead
					newState = currentState;
				}
				
				//and finally set the value of the new cell
				nextGeneration.set(xi, yi, newState);
			}
		}
		// now that we're finished, we can 'overwrite' the whole
		// grid with our new one.
		this.map = nextGeneration;
	}

	/** Print friendly string
	 */
	public String toString(){
		 return this.map.toString();
	}
}
