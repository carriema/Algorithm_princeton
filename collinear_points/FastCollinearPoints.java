import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class FastCollinearPoints {
	
	private HashMap<Double, List<Point>> datastore = new HashMap<>();
	private Point[] points;
	private ArrayList<LineSegment> ls;
	
	public FastCollinearPoints(Point[] points) {
		if (points == null) throw new NullPointerException();
		for (Point p : points) {
			if (p == null) throw new NullPointerException();
		}
		for (int i = 0; i < points.length - 1; i++) {
			for (int j = i + 1; j < points.length; j++) {
				if (points[i].equals(points[j])) throw new IllegalArgumentException();
			}
		}
		this.ls = new ArrayList<>();
		this.points = Arrays.copyOf(points, points.length);
		generateLS();
	}
	
	public int numberOfSegments() {
		return ls.size();
	}
	
	public LineSegment[] segments() {
		return ls.toArray(new LineSegment[ls.size()]);
	}
	
	private void generateLS() {
		for (int i = 0; i < points.length - 1; i++) {
			Arrays.sort(points, points[i].slopeOrder());
			findAppropriatePoint(points);
		}
	}
	
	private void findAppropriatePoint(Point[] points) {
		int i = 1;
		while(i < points.length - 2) {
			double slope = points[0].slopeTo(points[i]);
			if (points[0].slopeTo(points[i + 2]) != slope) {
				i++;
			} else {
				ArrayList<Point> temp = new ArrayList<Point>();
				temp.add(points[0]);
				while (i<points.length && (points[0].slopeTo(points[i]) == slope)) {
					temp.add(points[i++]);
				}
				Collections.sort(temp);
				Point end = temp.get(temp.size() - 1);
				Point start = temp.get(0);
				LineSegment newLs = new LineSegment(start, end);
				if (!datastore.containsKey(slope)) {
					datastore.put(slope, new ArrayList<Point>());
				}
				if (!datastore.get(slope).contains(start)) {
					ls.add(newLs);
					datastore.get(slope).add(start);
				}
//				for (Point p : datastore.get(slope)) {
//					if (p.compareTo(start) != 0) {
//						
//					}
//				}
			}
		}
	}
	
	public static void main(String[] args) {
		BufferedReader br = null;
		FileReader fr = null;
		String FILENAME = "/Users/cma/Downloads/collinear/horizontal25.txt";
		ArrayList<Point> pointArr = new ArrayList<Point>();
		try {
			
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
			int k = Integer.parseInt(br.readLine());
			while (k-- > 0) {
				String s = br.readLine().trim();
				String[] pos = s.split("\\s+");
				
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
