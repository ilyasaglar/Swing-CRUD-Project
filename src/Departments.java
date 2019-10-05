
public class Departments {
	Integer department_id;
	String department_name;
	Integer manager_id;
	Integer location_id;

	public Departments() {
		super();
	}
	public Departments(Integer department_id, String department_name, Integer manager_id, Integer location_id) {
		super();
		this.department_id = department_id;
		this.department_name = department_name;
		this.manager_id = manager_id;
		this.location_id = location_id;
	}
	public Departments(Integer department_id, String department_name) {
		super();
		this.department_id = department_id;
		this.department_name = department_name;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(Integer manager_id) {
		this.manager_id = manager_id;
	}
	public int getLocation_id() {
		return location_id;
	}
	public void setLocation_id(Integer location_id) {
		this.location_id = location_id;
	}
	@Override
	public String toString() {
		return "Departments [department_id=" + department_id + ", department_name=" + department_name + ", manager_id="
				+ manager_id + ", location_id=" + location_id + "]";
	}
	

	
}
