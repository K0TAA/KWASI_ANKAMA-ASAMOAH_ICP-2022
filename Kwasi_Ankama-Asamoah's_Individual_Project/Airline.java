
/**
 * 
 * @author 
 *
 * The Airline class to store information about an airline
 */
public class Airline {
	
	private int id;
	private String name;
	private String alias;
	private String IATACode;
	private String ICAOCode;
	private String callSign;
	private String country;
	private String active;
	
	public Airline(int id, String name, String alias, String iATACode, String iCAOCode, String callSign, String country,
			String active) {
		this.id = id;
		this.name = name;
		this.alias = alias;
		IATACode = iATACode;
		ICAOCode = iCAOCode;
		this.callSign = callSign;
		this.country = country;
		this.active = active;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getAlias() {
		return alias;
	}

	public String getIATACode() {
		return IATACode;
	}

	public String getICAOCode() {
		return ICAOCode;
	}

	public String getCallSign() {
		return callSign;
	}

	public String getCountry() {
		return country;
	}

	public String getActive() {
		return active;
	}
}
