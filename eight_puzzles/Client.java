
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
public class Client {
	public static void main(String[] args) {

	    // create initial board from file
	    
	    int[][] blocks = new int[3][3];
	    blocks[0][0] = 0;
	    blocks[0][1] = 1;
	    blocks[0][2] = 3;
	    blocks[1][0] = 4;
	    blocks[1][1] = 2;
	    blocks[1][2] = 5;
	    blocks[2][0] = 7;
	    blocks[2][1] = 8;
	    blocks[2][2] = 6;
	    Board initial = new Board(blocks);

	    // solve the puzzle
	    Solver solver = new Solver(initial);

	    // print solution to standard output
	    if (!solver.isSolvable())
	        StdOut.println("No solution possible");
	    else {
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }
	}
}
