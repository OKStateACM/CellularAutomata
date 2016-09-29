import java.io.*;

public class Map {
	char[][] map;
	int x;
	int y;

	public Map(char[][] map){
		if (map == null) {
			map = new char[1][1]; // make a dummy map, rather than crashing on null pointer exceptions.
		}
		this.map = map;
		this.x = map.length;
		this.y = map[0].length;
	}

	public Map(String filename){
		this( Parser.load(filename) ); //fallthrough to the normal constructor once we load the array
	}

	/** return the value in the cell given at x,y
	 * Wraps around when we go outside the bounds of the array
	 * (aka its a toroidal plane - see wikipedia entry on cellular automata)
	 * @param x x coordinate of the cell
	 * @param y y coordinate of the cwll
	 */
	public char get(int x, int y){
		x=x%this.x;
		y=y%this.y;
		if (x<0) {x=this.x+x;}
		if (y<0) {y=this.y+y;}
		return this.map[x][y];
	}
	/** Set a cell at x,y position to a certain value
	 * (does nothing for cells outside the bounds of our array)
	 */
	public void set(int x, int y, char cell){
		if (x>=0 && x<this.x && y>=0 && y<this.y){
			this.map[x][y] = cell;
		}
	}
	/** Save the map to a text file
	 * @param filename name of the file to save to
	 */
	public void save(String filename){
		FileOutputStream file = null;
		try {
			file = new FileOutputStream(filename);
			file.write( this.toString().getBytes() );
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			try {
				if (file != null){ file.close(); }
			} catch (IOException e){
				e.printStackTrace();
			}
		}
		
	}

	/*display this map as a 2d grid,
	 */
	public String toString(){
		StringBuilder str = new StringBuilder();
		for (int yi = 0; yi<this.y; yi++) {
			for (int xi = 0; xi<this.x; xi++) {
				char c = this.map[xi][yi];
				str.append(c);
			}
			str.append("\n");
		}
		return str.toString();
	}
}
