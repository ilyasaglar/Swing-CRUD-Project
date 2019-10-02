
public class Countries {

	String country_id;
	String country_name;
	int region_id;
	
	
	
	public Countries() {
		super();
	}
	public Countries(String country_id, String country_name, int region_id) {
		super();
		this.country_id = country_id;
		this.country_name = country_name;
		this.region_id = region_id;
	}
	public String getCountry_id() {
		return country_id;
	}
	public void setCountry_id(String country_id) {
		this.country_id = country_id;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	public int getRegion_id() {
		return region_id;
	}
	public void setRegion_id(int region_id) {
		this.region_id = region_id;
	}
	@Override
	public String toString() {
		return "Countries [country_id=" + country_id + ", country_name=" + country_name + ", region_id=" + region_id
				+ "]";
	}
	
	
}
