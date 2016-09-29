public class Main {
	public static void main(String[] args) {
		char[][] map = new Parser().load("test.txt");
		Map mapymap = new Map("test.txt");
		Automata system = new Automata(mapymap);
		System.out.println(system);
		system.step();
		System.out.println(system);
		system.step();
		System.out.println(system);
	}
}
