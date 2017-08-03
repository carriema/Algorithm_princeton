
import edu.princeton.cs.algs4.*;

public class Percolation {
	WeightedQuickUnionUF uf;
	boolean[][] percolation;
	int num;
	public Percolation(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("n should be positive.");
		}
		uf = new WeightedQuickUnionUF(n * n + 1);
		percolation = new boolean[n][n];
		num = n;
		for (int i = 0; i < n; i++) {
			uf.union(i, n * n);
		}
	}
	private int rowToNum(int row, int col) {
		return row * num + col;
	}
	public void open(int row, int col) {
		percolation[row][col] = true;
		if (row - 1 >= 0 && percolation[row - 1][col]) {
			uf.union(rowToNum(row - 1, col), rowToNum(row, col));
		}
		if (row + 1 < num && percolation[row + 1][col]) {
			uf.union(rowToNum(row + 1, col), rowToNum(row, col));
		}
		if (col - 1 >= 0 && percolation[row][col - 1]) {
			uf.union(rowToNum(row,  col - 1), rowToNum(row, col));
		}
		if(col + 1 < num && percolation[row][col + 1]) {
			uf.union(rowToNum(row, col + 1),  rowToNum(row, col));
		}
	}
	public boolean isOpen(int row, int col) {
		return percolation[row][col];
	}
	public boolean isFull(int row, int col) {
		return uf.connected(rowToNum(row, col), (num*num));
	}
	public boolean percolates() {
		for (int i = 0; i < num; i++) {
			if (!isOpen(num - 1, i)){
				continue;
			}
			if (isFull(num - 1, i)) {
				return true;
			}
		}
		return false;
	}
	public static void main(String[] args) {
		
	}
	 
}
