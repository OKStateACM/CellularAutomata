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
