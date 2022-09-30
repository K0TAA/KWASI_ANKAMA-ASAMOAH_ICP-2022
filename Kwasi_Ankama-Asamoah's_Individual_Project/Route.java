
/**
 * 
 * @author 
 *
 * The Route class to store information about a route
 */
public class Route {
	
	private String airlineCode;
	private String airlineID;
	private String sourceAirportCode;
	private String sourceAirportID;
	private String destinationAirportCode;
	private String destinationAirportID;
	private String codeShare;
	private int stops;
	private String equipment;
	
	public Route(String airlineCode, String airlineID, String sourceAirportCode, String sourceAirportID,
			String destinationAirportCode, String destinationAirportID, String codeShare, int stops, String equipment) {
		this.airlineCode = airlineCode;
		this.airlineID = airlineID;
		this.sourceAirportCode = sourceAirportCode;
		this.sourceAirportID = sourceAirportID;
		this.destinationAirportCode = destinationAirportCode;
		this.destinationAirportID = destinationAirportID;
		this.codeShare = codeShare;
		this.stops = stops;
		this.equipment = equipment;
	}

	public String getAirlineCode() {
		return airlineCode;
	}

	public String getAirlineID() {
		return airlineID;
	}

	public String getSourceAirportCode() {
		return sourceAirportCode;
	}

	public String getSourceAirportID() {
		return sourceAirportID;
	}

	public String getDestinationAirportCode() {
		return destinationAirportCode;
	}

	public String getDestinationAirportID() {
		return destinationAirportID;
	}

	public String getCodeShare() {
		return codeShare;
	}

	public int getStops() {
		return stops;
	}

	public String getEquipment() {
		return equipment;
	}
	
}
