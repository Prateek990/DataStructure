import java.util.Comparator;

/*
 * A comparable class to compare distances
 */
public class CompareDistance implements Comparator<Edge> {

	public int compare(Edge firstEdge, Edge secondEdge) {
		if(firstEdge.getDistance()> secondEdge.getDistance()){
			return 1;
		} else if(firstEdge.getDistance() < secondEdge.getDistance()) {
			return -1;
		}
		return 0;
	}

}
