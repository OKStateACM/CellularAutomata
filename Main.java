public class Main {
	public static void main(String[] args) {
		char[][] map = new Parser().load("test.txt");
		Map mapymap = new Map(map);
		System.out.println(mapymap);
	}
}
