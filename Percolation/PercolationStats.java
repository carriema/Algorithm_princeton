import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.*;
public class PercolationStats {
	int n;
	int trials;
	double[] times;
	Stopwatch sw;
	double time_consume;
	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException("n and trials should be positive.");
		}
		this.n = n;
		this.trials = trials;
		times = new double[trials];
		sw = new Stopwatch();
		doTrials();
		time_consume = sw.elapsedTime();
	}
	private void doTrials() {
		for (int i = 0; i < trials; i++) {
			double count = 0;
			Percolation p = new Percolation(n);
			while (!p.percolates()) {
				int row = StdRandom.uniform(n);
				int col = StdRandom.uniform(n);
				if (p.isOpen(row, col)) {
					continue;
				}
				p.open(row, col);
				count++;
			}
			times[i] = count/(n * n);
		}
	}
	public double mean() {
		return StdStats.mean(times);
	}
	public double stddev() {
		return StdStats.stddev(times);
	}
	public double confidenceLo() {
		return mean() - 1.96 * stddev()/Math.sqrt(trials);
	}
	public double confidenceHi() {
		return mean() + 1.96*stddev()/Math.sqrt(trials);
	}
	public static void main(String[] args) {
		int n = 0, trials = 0;
		while (StdIn.hasNextLine()) {
			n = StdIn.readInt();
			trials = StdIn.readInt();
		}
		PercolationStats ps= new PercolationStats(n, trials);
		//PercolationStats ps= new PercolationStats(200, 100);
		System.out.println("Time Used: " + ps.time_consume);
		System.out.println("Mean: " + ps.mean());
		System.out.println("Stddev: " + ps.stddev());
		System.out.println("Confidence Low: " + ps.confidenceLo());
		System.out.println("Confidence Hi: " + ps.confidenceHi());
	}
}
