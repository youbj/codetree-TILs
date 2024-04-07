import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	private static StringTokenizer st;
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();

	private static final int UP = 0;
	private static final int DOWN = 1;
	private static final int RIGHT = 2;
	private static final int LEFT = 3;

	private static int map[][];
	private static int size;
	private static int[] move = new int[5];
	private static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		init();
		makeperm(0);
		System.out.println(max);
	}

	public static void init() throws IOException {
		size = Integer.parseInt(br.readLine());
		map = new int[size][size];

		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		return;
	}

	public static void makeperm(int idx) {

		if (idx == 5) {
			execute();
			return;
		}

		for (int i = 0; i < 4; i++) {

			move[idx] = i;
			makeperm(idx + 1);
		}
	}

	public static void execute() {
		int[][] clonemap = new int[size][size];
		for (int i = 0; i < size; i++) {
			clonemap[i] = map[i].clone();
		}

		for (int idx = 0; idx < 5; idx++) {
			switch (move[idx]) {
			case UP:
				moveUP(clonemap);
				break;
			case DOWN:
				moveDOWN(clonemap);
				break;
			case RIGHT:
				moveRIGHT(clonemap);
				break;
			case LEFT:
				moveLEFT(clonemap);
				break;
			}

		}

		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				max = Math.max(max, clonemap[r][c]);
			}
		}
	}

	public static void moveUP(int[][] clonemap) {

		for (int c = 0; c < size; c++) {
			Stack<Integer> stack = new Stack<Integer>();
			for (int r = 0; r < size; r++) {
				if (clonemap[r][c] == 0) {
					continue;
				}

				if (stack.isEmpty() || stack.peek() != clonemap[r][c]) {
					stack.add(clonemap[r][c]);
					continue;
				} else {
					int num = stack.pop();
					stack.add(num + 1);
					continue;
				}
			}
			if (stack.size() == size)
				continue;
			else {
				int[] buf = new int[size];
				int index = stack.size() - 1;

				while (!stack.isEmpty()) {
					int num = stack.pop();
					if (num % 2 != 0) {
						buf[index] = (num - 1) * 2;
					} else {
						buf[index] = num;
					}
					index--;
				}
				for (int r = 0; r < size; r++) {
					clonemap[r][c] = buf[r];
				}
				
			}
		}

	}

	public static void moveDOWN(int[][] clonemap) {
		for (int c = 0; c < size; c++) {
			Stack<Integer> stack = new Stack<Integer>();
			for (int r = size - 1; r >= 0; r--) {
				if (clonemap[r][c] == 0) {
					continue;
				}

				if (stack.isEmpty() || stack.peek() != clonemap[r][c]) {
					stack.add(clonemap[r][c]);
				} else {
					int num = stack.pop();
					stack.add(num + 1);
				}
			}

			if (stack.size() == size)
				continue;

			else {
				int[] buf = new int[size];
				int index = stack.size() - 1;

				while (!stack.isEmpty()) {
					int num = stack.pop();
					if (num % 2 != 0) {
						buf[index] = (num - 1) * 2;
					} else {
						buf[index] = num;
					}
					index--;
				}
				int i = 0;
				for (int r = size - 1; r >= 0; r--, i++) {
					clonemap[r][c] = buf[i];
				}
			}
		}

	}

	public static void moveRIGHT(int[][] clonemap) {
		for (int r = 0; r < size; r++) {
			Stack<Integer> stack = new Stack<Integer>();
			for (int c = size - 1; c >= 0; c--) {
				if (clonemap[r][c] == 0) {
					continue;
				}

				if (stack.isEmpty() || stack.peek() != clonemap[r][c]) {
					stack.add(clonemap[r][c]);
				} else {
					int num = stack.pop();
					stack.add(num + 1);
				}
			}

			if (stack.size() == size)
				continue;

			else {
				int[] buf = new int[size];
				int index = 0;

				while (!stack.isEmpty()) {
					int num = stack.pop();
					if (num % 2 != 0) {
						buf[index] = (num - 1) * 2;
					} else {
						buf[index] = num;
					}
					index++;
				}
				for (int c = 0; c < size; c++) {
					clonemap[r][size - 1 - c] = buf[c];
				}
			}
		}
	}

	public static void moveLEFT(int[][] clonemap) {
		for (int r = 0; r < size; r++) {
			Stack<Integer> stack = new Stack<Integer>();
			for (int c = 0; c < size; c++) {
				if (clonemap[r][c] == 0) {
					continue;
				}

				if (stack.isEmpty() || stack.peek() != clonemap[r][c]) {
					stack.add(clonemap[r][c]);
				} else {
					int num = stack.pop();
					stack.add(num + 1);
				}
			}

			if (stack.size() == size)
				continue;

			else {
				int[] buf = new int[size];
				int index = stack.size() - 1;

				while (!stack.isEmpty()) {
					int num = stack.pop();
					if (num % 2 != 0) {
						buf[index] = (num - 1) * 2;
					} else {
						buf[index] = num;
					}
					index--;
				}
				for (int c = 0; c < size; c++) {
					clonemap[r][c] = buf[c];
				}
			}
		}
	}
}