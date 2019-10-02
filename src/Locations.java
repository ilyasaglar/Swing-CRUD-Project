
public class Locations {
	Integer location_id;
	String street_address;
	String postal_code;
	String city;
	String state_province;
	char country_id;
	
	
	
	public Locations() {
		super();
	}

	public Locations(Integer location_id, String street_address, String postal_code, String city, String state_province,
			char country_id) {
		super();
		this.location_id = location_id;
		this.street_address = street_address;
		this.postal_code = postal_code;
		this.city = city;
		this.state_province = state_province;
		this.country_id = country_id;
	}

	public Integer getLocation_id() {
		return location_id;
	}

	public void setLocation_id(Integer location_id) {
		this.location_id = location_id;
	}

	public String getStreet_address() {
		return street_address;
	}

	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState_province() {
		return state_province;
	}

	public void setState_province(String state_province) {
		this.state_province = state_province;
	}

	public char getCountry_id() {
		return country_id;
	}

	public void setCountry_id(char country_id) {
		this.country_id = country_id;
	}

	@Override
	public String toString() {
		return "Locations [location_id=" + location_id + ", street_address=" + street_address + ", postal_code="
				+ postal_code + ", city=" + city + ", state_province=" + state_province + ", country_id=" + country_id
				+ "]";
	}

	
	
}

