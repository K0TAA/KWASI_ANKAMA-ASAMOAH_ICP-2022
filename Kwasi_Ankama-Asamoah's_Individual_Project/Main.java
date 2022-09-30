import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	// the file names
	public static final String AIRPORTS_INPUT_FILE = "airports.csv";
	public static final String AIRLINES_INPUT_FILE = "airlines.csv";
	public static final String ROUTES_INPUT_FILE = "routes.csv";
	
	// the helper method to read airport data from file
	private static ArrayList<Airport> readAirportData() {
		
		try {
			ArrayList<Airport> airports = new ArrayList<>();
			Scanner scanner = new Scanner(new File(AIRPORTS_INPUT_FILE));
			while( scanner.hasNextLine() ) {
				// read a line
				String line = scanner.nextLine();
				// extract data
				String tokens[] = line.split(",");
				// if invalid number of tokens, skip this row
				if( tokens.length != 14 ) {
					continue;
				}
				// if any of the tokens is a "\N", skip this row
				boolean invalidData = false;
				for( int j=0; j<tokens.length; j++ ) {
					if( tokens[j].equals("\\N")) {
						invalidData = true;
						break;
					}
				}
				if( invalidData ) {
					continue;
				}
				// create a new airport
				Airport airport = new Airport(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], 
						Double.parseDouble(tokens[6]), Double.parseDouble(tokens[7]), Double.parseDouble(tokens[8]), Double.parseDouble(tokens[9]),
						tokens[10], tokens[11], tokens[12], tokens[13]);
				airports.add(airport);
			}
			scanner.close();
			return airports;
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("Cannot open input file " + AIRPORTS_INPUT_FILE);
			return null;
		}
	}
	
	// the helper method to read airline data from file
	private static ArrayList<Airline> readAirlineData() {
		
		try {
			ArrayList<Airline> airlines = new ArrayList<>();
			Scanner scanner = new Scanner(new File(AIRLINES_INPUT_FILE));
			while( scanner.hasNextLine() ) {
				// read a line
				String line = scanner.nextLine();
				// extract data
				String tokens[] = line.split(",");
				// if invalid number of tokens, skip this row
				if( tokens.length != 8 ) {
					continue;
				}
				// if any of the tokens is a "\N", skip this row
				boolean invalidData = false;
				for( int j=0; j<tokens.length; j++ ) {
					if( tokens[j].equals("\\N")) {
						invalidData = true;
						break;
					}
				}
				if( invalidData ) {
					continue;
				}
				// create a new airline
				Airline airline = new Airline(Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], 
						tokens[6], tokens[7]);
				airlines.add(airline);
			}
			scanner.close();
			return airlines;
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("Cannot open input file " + AIRLINES_INPUT_FILE);
			return null;
		}
	}
	
	// the helper method to read route data from file
	private static ArrayList<Route> readRouteData() {
		
		try {
			ArrayList<Route> routes = new ArrayList<>();
			Scanner scanner = new Scanner(new File(ROUTES_INPUT_FILE));
			while( scanner.hasNextLine() ) {
				// read a line
				String line = scanner.nextLine();
				// extract data
				String tokens[] = line.split(",");
				// if invalid number of tokens, skip this row
				if( tokens.length != 9 ) {
					continue;
				}
				// if any of the tokens is a "\N", skip this row
				boolean invalidData = false;
				for( int j=0; j<tokens.length; j++ ) {
					if( tokens[j].equals("\\N")) {
						invalidData = true;
						break;
					}
				}
				if( invalidData ) {
					continue;
				}
				// create a new route
				Route route = new Route(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4], tokens[5], 
						tokens[6], Integer.parseInt(tokens[7]), tokens[8]);
				routes.add(route);
			}
			scanner.close();
			return routes;
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("Cannot open input file " + ROUTES_INPUT_FILE);
			return null;
		}
	}
	
	// helper method to get output file name
	private static String getOutputFileName(String inputFileName) {
		String name = inputFileName.substring(0, inputFileName.indexOf('.'));
		return name + "_output.txt";
	}
	
	// get airport for the given city and country
	private static Airport searchAirport(String s, ArrayList<Airport> airports) {
		String city = s.substring(0, s.indexOf(','));
		String country = s.substring(s.indexOf(',') + 1).trim();
		for( Airport airport : airports ) {
			if( airport.getCity().equalsIgnoreCase(city) && airport.getCountry().equalsIgnoreCase(country) ) {
				return airport;
			}
		}
		return null;
	}
	

	public static void main(String[] args) {
		ArrayList<Airport> airports = readAirportData();
		// System.out.println(airports.size());
		ArrayList<Airline> airlines = readAirlineData();
		// System.out.println(airlines.size());
		ArrayList<Route> routes = readRouteData();
		// System.out.println(routes.size());
		
		Scanner scanner = new Scanner(System.in);
		
		// ask for input file name
		System.out.print("Enter input file name: ");
		String inputFileName = scanner.nextLine();
		scanner.close();
		
		// try to read city names from the file
		try {
			scanner = new Scanner(new File(inputFileName));
			// get output file name
			String outputFileName = getOutputFileName(inputFileName);
			PrintWriter printWriter = new PrintWriter(new File(outputFileName));
			
			// read source and destination
			String sourceLocation = scanner.nextLine();
			String destinationLocation = scanner.nextLine();
			scanner.close();
			
			// find airports
			Airport sourceAirport = searchAirport(sourceLocation, airports);
			Airport destinationAirport = searchAirport(destinationLocation, airports);
			
			// if either of them is null
			if( sourceAirport == null ) {
				printWriter.println("Cannot find airport at " + sourceLocation);
			}
			else if( destinationAirport == null ) {
				printWriter.println("Cannot find airport at " + destinationLocation);
			}
			else {
				// create graph
				Graph graph = new Graph(airports, routes);
				// find path
				ArrayList<Route> path = graph.findPath(sourceAirport, destinationAirport);
				// print path
				int numAdditionalStops = 0;
				int i=0;
				int totalDistance = 0;
				for( Route route : path ) {
					printWriter.println("\t" + (++i) + ". " + route.getAirlineCode() + " from " + route.getSourceAirportCode() + " to " + route.getDestinationAirportCode() + " " + route.getStops() + " stops.");
					numAdditionalStops += route.getStops();
					totalDistance += graph.getDistance( graph.findAirportById(Integer.parseInt(route.getSourceAirportID())), 
							graph.findAirportById(Integer.parseInt(route.getDestinationAirportID())));
				}
				printWriter.println("Total flights: " + i);
				printWriter.println("Total additional stops: " + numAdditionalStops);
				printWriter.println("Total distance: " + totalDistance + "km");
			}
			
			printWriter.close();
		
		}
		catch(FileNotFoundException fnfe) {
			System.out.println("Cannot open input file " + inputFileName);
		}
		
	}

}
