import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
	
	private Point[] points;
	private ArrayList<LineSegment> ls;
	
	public BruteCollinearPoints(Point[] points) {
		if (points == null) throw new NullPointerException();
		for (Point p : points) {
			if (p == null) throw new NullPointerException();
		}
		for (int i = 0; i < points.length - 1; i++) {
			for (int j = i + 1; j < points.length; j++) {
				if (points[i].equals(points[j])) throw new IllegalArgumentException();
			}
		}
		this.points = Arrays.copyOf(points, points.length);
		this.ls = new ArrayList<>();
		Arrays.sort(this.points);
		generateSegArr();
	}
	
	public int numberOfSegments() {
		return ls.size();
	}
	
	public LineSegment[] segments() {
		return ls.toArray(new LineSegment[ls.size()]);
	}
	
	private boolean sameSlope(Point p1, Point p2, Point p3, Point p4) {
		if (p1.slopeTo(p2) != p2.slopeTo(p3)) return false;
		if (p2.slopeTo(p3) != p3.slopeTo(p4)) return false;
		return true;
	}
	
	private void generateSegArr() {
		for (int i = 0; i < points.length - 3; i++) {
			for (int j = i + 1; j < points.length - 2; j++) {
				for (int m = j + 1; m < points.length - 1; m++) {
					for (int n = m + 1; n < points.length; n++) {
						if (sameSlope(points[i], points[j], points[m], points[n])) {
							ls.add(new LineSegment(points[i], points[n]));
						}
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		BufferedReader br = null;
		FileReader fr = null;
		String FILENAME = "/Users/cma/Downloads/collinear/horizontal100.txt";
		ArrayList<Point> pointArr = new ArrayList<Point>();
		try {
			
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			int k = Integer.parseInt(br.readLine());
			while (k-- > 0) {
				String[] pos = br.readLine().split(" ");
				pointArr.add(new Point(Integer.parseInt(pos[0]), Integer.parseInt(pos[1])));
			}
		}catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		Point[] points = pointArr.toArray(new Point[pointArr.size()]);
		BruteCollinearPoints bp = new BruteCollinearPoints(points);
		System.out.println(bp.numberOfSegments());
		
	}

}
