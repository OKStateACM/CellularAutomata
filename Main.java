public class Main {
	public static void main(String[] args) {
		char[][] map = new Parser().load("test.txt");
		Map mapymap = new Map("test.txt");
		System.out.println(mapymap);
		mapymap.save("savefile.txt");
	}
}
