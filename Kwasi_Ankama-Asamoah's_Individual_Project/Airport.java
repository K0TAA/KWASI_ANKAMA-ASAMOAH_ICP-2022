
/**
 * 
 * @author 
 *
 * The Airport class to store information about an airport
 */
public class Airport {
	
	private int id;
	private String name;
	private String city;
	private String country;
	private String IATACode;
	private String ICAOCode;
	private double latitude;
	private double longitude;
	private double altitude;
	private double timezone;
	private String DST;
	private String tzDBTimezone;
	private String type;
	private String dataSource;
	
	public Airport(int id, String name, String city, String country, String iATACode, String iCAOCode, double latitude,
			double longitude, double altitude, double timezone, String dST, String tzDBTimezone, String type,
			String dataSource) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.country = country;
		IATACode = iATACode;
		ICAOCode = iCAOCode;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.timezone = timezone;
		DST = dST;
		this.tzDBTimezone = tzDBTimezone;
		this.type = type;
		this.dataSource = dataSource;
	}
	
	/*
	 * Getters for all the data members
	 */

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public String getCountry() {
		return country;
	}

	public String getIATACode() {
		return IATACode;
	}

	public String getICAOCode() {
		return ICAOCode;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getAltitude() {
		return altitude;
	}

	public double getTimezone() {
		return timezone;
	}

	public String getDST() {
		return DST;
	}

	public String getTzDBTimezone() {
		return tzDBTimezone;
	}

	public String getType() {
		return type;
	}

	public String getDataSource() {
		return dataSource;
	}
	
}
