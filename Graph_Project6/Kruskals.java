import java.io.*;
import java.util.*;

public class Kruskals {

	static Map<String, Integer> stringToInt = new HashMap<>();
	static Map<Integer, String> intToString = new HashMap<>();

	static int numVertices = 0;

	public static void main(String[] args) {

		ArrayList<Edge> edgeList = new ArrayList<Edge>();

		ArrayList<Edge> kruskal_edges = new ArrayList<Edge>();
		
		// Read data file
		File link_data = new File("assn9_data.csv");
		FileReader fileReader = null;
		BufferedReader br = null;
		
		try {
			fileReader = new FileReader(link_data);
			br = new BufferedReader(fileReader);
			String line = "";
			String lines = "";
			String lineArray[];

			
			int i = 1;

			while ((line = br.readLine()) != null) {
				lineArray = line.split(",");
				stringToInt.put(lineArray[0], i);
				intToString.put(i, lineArray[0]);
				lines += line + "\n";
				i++;
				numVertices++;

			}
			lineArray = lines.split("\n");

			ArrayList<Edge> singleLineEdge;

			for (i = 1; i < lineArray.length; i++) {
				singleLineEdge = new ArrayList<Edge>();
				singleLineEdge = addEdge(lineArray[i]);

				for (int j = 0; j < singleLineEdge.size(); j++) {
					edgeList.add(singleLineEdge.get(j));
				}

			}
			br.close();

		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		kruskal_edges = kruskal(edgeList, numVertices);

		// Print edges
		int sumOfAllDistance = 0;
		for (int i = 0; i < kruskal_edges.size(); i++) {
			String vertex1 = intToString.get((kruskal_edges.get(i).getSource()));
			String vertex2 = intToString.get((kruskal_edges.get(i).getDestination()));
			int distance = kruskal_edges.get(i).getDistance();
			sumOfAllDistance += distance;
			System.out.println(vertex1 + " , " + vertex2 + " ==== " + distance);
		}
		System.out.println("Sum of the distances in the Tree: " + sumOfAllDistance);
	}

	/**
	 * Adding edges to the array list, source, destination, distance
	 */
	private static ArrayList<Edge> addEdge(String line) {
		String[] data = line.split(",");
		// The first parameter in each line corresponds to city
		int source = stringToInt.get(data[0]);
		
		ArrayList<Edge> anEdge = new ArrayList<Edge>();
		for (int i = 1; i < data.length; i = i + 2) {

			int destn = stringToInt.get(data[i]);
			int distance = Integer.parseInt(data[i + 1]);
			Edge edges = new Edge();

			edges.setSource(source);
			edges.setDestination(destn);
			edges.setDistance(distance);
			anEdge.add(edges);
		}

		return anEdge;
	}
	
	
	/**
	 * Using text book algorithm
	 */
	static ArrayList<Edge> kruskal(List<Edge> edges, int numVertices) {

		DisjSets ds = new DisjSets(edges.size());
		
		Comparator<Edge> comparator = new CompareDistance();
		
		PriorityQueue<Edge> q = new PriorityQueue<>(edges.size(), comparator);
		ArrayList<Edge> mst = new ArrayList<>();

		//Add all the edges to the priority queue
		for (int i = 0; i < edges.size(); i++) {
			q.add(edges.get(i));
		}

		while (mst.size() != numVertices - 1) {
			Edge e = q.remove(); // Edge e = (u, v)
			int uset = ds.find(e.getSource());
			int vset = ds.find(e.getDestination());
			if (uset != vset) {
				// Accept the edge
				mst.add(e);
				ds.union(uset, vset);
			}
		}
		return mst;
	}

}
