import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
	
	// the list of vertices
	// each vertex is an airport 
	private ArrayList<Airport> vertices;
	
	// the list of edges - each flight route is an edge
	private ArrayList<Route> edges;
	
	// the adjacency list
	private HashMap<Airport, ArrayList<Airport>> adjList;
	
	public Graph(ArrayList<Airport> airports, ArrayList<Route> routes) {
		this.vertices = airports;
		this.edges = routes;
		createAdjList();
	}
	
	private void createAdjList() {
		adjList = new HashMap<>();
		for( Airport vertex : vertices ) {
			adjList.put(vertex, new ArrayList<>());
		}
		for( Route edge : edges ) {
			Airport vertex = findAirportById(Integer.parseInt(edge.getSourceAirportID()));
			if( vertex==null ) {
				continue;
			}
			if( findAirportById(Integer.parseInt(edge.getDestinationAirportID())) != null ) {
				adjList.get(vertex).add(findAirportById(Integer.parseInt(edge.getDestinationAirportID())));
			}
		}
	}
	
	public Airport findAirportById(int id) {
		for( Airport vertex : vertices ) {
			if( vertex.getId() == id ) {
				return vertex;
			}
		}
		return null;
	}
	
	public int getDistance(Airport source, Airport destination) {
        double latDiff = (destination.getLatitude() - source.getLatitude()) *
                      Math.PI / 180.0;
        double lonDiff = (destination.getLongitude() - source.getLongitude()) *
        		Math.PI / 180.0;
 
        double lat1 = (source.getLatitude()) * Math.PI / 180.0;
        double lat2 = (destination.getLatitude()) * Math.PI / 180.0;
 
        double a = Math.pow(Math.sin(latDiff / 2), 2) +
        		Math.pow(Math.sin(lonDiff / 2), 2) *
        		Math.cos(lat1) * Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return (int)(rad * c);
	}
	
	private Route getRoute(Airport source, Airport destination) {
		for( Route edge : edges ) {
			if( edge.getSourceAirportID().equals("" + source.getId()) && edge.getDestinationAirportID().equals("" + destination.getId())) {
				return edge;
			}
		}
		return null;
	}
	
	// to find the path between source and destination airport
	public ArrayList<Route> findPath(Airport source, Airport destination) {
		ArrayList<Route> route = new ArrayList<>();
		
		// to indicate if a vertex is visited
		// all vertices are unvisited right now
		HashMap<Airport, Boolean> visited = new HashMap<>();
		// to store shortest distances
		HashMap<Airport, Integer> shortestDistances = new HashMap<>();
		for( Airport vertex : vertices ) {
			visited.put(vertex, false);
			shortestDistances.put(vertex, Integer.MAX_VALUE);
		}
		// to store the parent of each vertex
		HashMap<Airport, Airport> parent = new HashMap<>();
		
		// start with the source
		shortestDistances.put(source, 0);
		parent.put(source, null);
		
		for( int i=1; i<vertices.size(); i++ ) {
			
			// find nearest vertex
			Airport nearestVertex = null;
			int shortestDistance = Integer.MAX_VALUE;
			for( Airport airport : vertices ) {
				if( !visited.get(airport) && shortestDistances.get(airport)<shortestDistance ) { 
					nearestVertex = airport;
					shortestDistance = shortestDistances.get(airport);
				}
			}
			
			visited.put(nearestVertex, true);
			
			if( adjList.get(nearestVertex) == null ) {
				continue;
			}
			
			// update distances of all neighbors
			for( Airport v : adjList.get(nearestVertex) ) {
				if( (shortestDistance + getDistance(nearestVertex, v)) < shortestDistances.get(v) ) {
					parent.put(v, nearestVertex);
					shortestDistances.put(v, shortestDistance + getDistance(nearestVertex, v));
				}
			}
		}
		
		Airport curr = destination;
		while( parent.get(curr) != null ) {
			route.add(0, getRoute(parent.get(curr), curr));
			curr = parent.get(curr);
		}
		
		return route;
	}

}
