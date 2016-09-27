import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
	// note that this method is static, so we don't have to initialize a new Parser to use this function.
	public static char[][] load(String filename){
		File file = new File(filename);
		FileReader reader = null;
		char[] contents = null; //we don't know how big it well be yet, so just declare the variable, don't allocate.
		try {
			reader = new FileReader(file);
			contents = new char[(int) file.length()]; //allocate space for it.
			reader.read(contents);	// read the whole file. We don't need buffered readers, since we need the whole file in memory anyways
			reader.close();
		} catch (IOException e){
			e.printStackTrace();	// just tell us what went wrong. nothing really we can do.
			return new char[1][1]; //return something, NOT NULL!!!!!! NEVER RETURN NULL!
		} finally {
			try{ //note that closing a file can fail. so, we have to catch it....
				if(reader != null){reader.close();}
			} catch (IOException e){
				e.printStackTrace();	// just tell us what went wrong. nothing really we can do.
				return new char[1][1]; //return something, NOT NULL!!!!!! NEVER RETURN NULL!
			}
		}
		
		//now we need to format it into a 2d array.
		//first step is to figure out its size...
		int maxX = 0;
		int maxY = 0;
		int curX = 0;
		int size = contents.length;
		for(int i=0; i<size; i++){
			if(contents[i] == '\n'){
				maxY++;                      //we found a newline, so we have another row.
				maxX = Math.max(curX, maxX); //take the greater value of the two options for # of columns
				curX = 0;                    //need to reset to the first column
			} else if (contents[i] != '\r') {
				curX++;                      //for each character that is not a newline, increase the number of columns in this row
			}
		}
		//test if the last row ended with a newline. If it did not, then we need to add the last row.
		if( contents[ size-1 ] == '\n' || contents[ size-1 ] == '\r' ){
			maxY++;
		}

		//NOW we can allocate our 2d array
		char[][] map = new char[maxX][maxY];
		//now we need to copy to the 2d array
		int x=0; int y=0;
		for(int i=0; i<size; i++){
			char c = contents[i];
			if( c == '\n' ){	//don't copy newlines, but increment our row counter when we encounter them
				y++;
			} else if (c != '\r') {	//don't copy carriage returns because windows in stupid.
				map[x][y] = c;	// copy the value into the map
				x++;          	// and move to the next column
			}
		}

		return map;
	}
}
