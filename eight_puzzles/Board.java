import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Board {
	private int[][] blocks;
	private int dimension;
	private Random random;
	int pos;
	int[][] values = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	private int[] valToPos(int val) {
		if (val == 0) {
			return new int[]{ dimension -1, dimension - 1};
		}
		int row = (val - 1) / dimension;
		int col = (val - 1) % dimension;
		return new int[]{row, col};
	}
	
	private int posToVal(int i, int j) {
		if (i == dimension - 1 && j == dimension - 1) {
			return 0;
		}
		return i * dimension + j + 1;
	}
	
	public Board(int[][] blocks) {
		this.blocks = blocks;
		random = new Random();
	}
	
	public int dimension() {
		this.dimension = blocks.length;
		return this.dimension;
	}
	
	public int hamming() {
		int ham = 0;
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				if (blocks[i][j] != posToVal(i, j)) {
					ham++;
				}
			}
		}
		return ham;
	}

	public int manhattan() {
		int manh = 0;
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				if (blocks[i][j] != posToVal(i, j)) {
					int[] pos = valToPos(blocks[i][j]);
					manh += Math.abs(pos[0] - i) + Math.abs(pos[1] - j);
				}
			}
		}
		return manh;
	}

	public boolean isGoal() {
		boolean isgoal = hamming() == 0;
		return isgoal;
	}
	
	private void swapTwoPos(int[] pos1, int[] pos2) {
		int val2 = blocks[pos1[0]][pos1[1]];
		int val1 = blocks[pos2[0]][pos2[1]];
		blocks[pos1[0]][pos1[1]] = val1;
		blocks[pos2[0]][pos1[0]] = val2;
	}

	public Board twin() {
		
		int[] pos1 = new int[]{ random.nextInt(dimension), random.nextInt(dimension)};
		int val2 = blocks[pos1[0]][pos1[1]];
		int[] pos2 = valToPos(val2);
		swapTwoPos(pos1, pos2);
		return new Board(blocks);
	}

	public boolean equals(Object y) {
		return this.toString().equals(y.toString());
		
	}

	public Iterable<Board> neighbors() {
		// all neighboring boards
		ArrayList<Board> res = new ArrayList<>();
		int[] pos = new int[2];
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				if (blocks[i][j] == 0) {
					pos[0] = i;
					pos[1] = j;
				}
			}
 		}
		for (int i = 0; i < values.length; i++) {
			int[] exchangePos = new int[] {pos[0] + values[i][0], pos[1] + values[i][1]};
			if (exchangePos[0] < dimension && exchangePos[0] > 0 && exchangePos[1] < dimension && exchangePos[1] > dimension) {
				swapTwoPos(pos, exchangePos);
				res.add(new Board(this.blocks));
				swapTwoPos(pos, exchangePos);
			}
		}
		return (Iterable<Board>) res;
	}

	public String toString() {
		// string representation of this board (in the output format specified below)
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				sb.append(blocks[i][j]);
				sb.append("\t");
			}
			sb.append("\n");
		}
		return sb.toString();
	}


	public static void main(String[] args) {
		// unit tests (not graded)
	}
}
