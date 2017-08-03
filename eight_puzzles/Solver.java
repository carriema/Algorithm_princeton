import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;


public class Solver {
	
	private class BoardWrapper {
		Board board;
		int move;
		public BoardWrapper (Board board, int move) {
			this.board = board;
			this.move = move;
		}
		Board getBoard() {
			return board;
		}
		int getMove() {
			return move;
		}
	}
	private int move = 0;
	private PriorityQueue<BoardWrapper> pq;
	private boolean solvable = true;
	private ArrayList<Board> solution;
	
	private Comparator<BoardWrapper> compare = new Comparator<BoardWrapper>() {
		public int compare(BoardWrapper b1, BoardWrapper b2) {
			return b1.getBoard().manhattan() + b1.getMove() - b2.getBoard().manhattan() - b2.getMove();
		}
	};
	
	public Solver(Board initial) {
		// find a solution to the initial board (using the A* algorithm)
		solution = new ArrayList<>();
		pq = new PriorityQueue<BoardWrapper>(initial.dimension() * initial.dimension(), compare);
		pq.add(new BoardWrapper(initial, move));
		boolean success = false;
		while (!pq.isEmpty()) {
//			System.out.println(move);
			BoardWrapper pop = pq.poll();
			System.out.println(pop.getBoard().isGoal());
			
			if (pop.getBoard().isGoal()) {
				success = true;
				break;
			}
			Iterator<Board> b = pop.getBoard().neighbors().iterator();
			
			move = pop.getMove() + 1;
			
			solution.add(pop.getBoard());
			
			while (b.hasNext()) {
				Board temp = b.next();
				if (temp.hamming() == 2) {
					solvable = false;
					break;
				}
				pq.add(new BoardWrapper(b.next(), move));
			}
			if (!solvable || success) break;
			
		}
	}
    public boolean isSolvable() {
    	// is the initial board solvable?
    	return solvable;
    }
    public int moves() {
    	// min number of moves to solve initial board; -1 if unsolvable
    	if (!isSolvable()) {
    		return -1;
    	}
    	return move;
    }
    public Iterable<Board> solution() {
    	// sequence of boards in a shortest solution; null if unsolvable
    	if (!isSolvable()) {
    		return null;
    	}
    	return solution;
    }
    public static void main(String[] args) {
    	// solve a slider puzzle (given below)
    }
}
