import java.sql.Date;

public class Employees {
	Integer employee_id;
	String first_name;
	String last_name;
	String email;
	String phone_number;
	String hire_date;
	String job_id;
	Integer salary;
	Integer manager_id;
	Integer department_id;
	Integer commission_pct;


	public Employees(Integer employee_id, String first_name, String last_name, String email, String phone_number, String hire_date,
			 String job_id, Integer salary, Integer commission_pct, Integer manager_id, Integer department_id) {
		super();
		this.employee_id = employee_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone_number = phone_number;
		this.hire_date = hire_date;
		this.job_id = job_id;
		this.salary = salary;
		this.commission_pct = commission_pct;
		this.manager_id = manager_id;
		this.department_id = department_id;
	}

	public Employees() {
		super();
	}

	public Integer getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getJob_id() {
		return job_id;
	}

	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Integer getManager_id() {
		return manager_id;
	}

	public void setManager_id(Integer manager_id) {
		this.manager_id = manager_id;
	}

	public Integer getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Integer department_id) {
		this.department_id = department_id;
	}

	public Integer getCommission_pct() {
		return commission_pct;
	}

	public void setCommission_pct(Integer commission_pct) {
		this.commission_pct = commission_pct;
	}

	public String getHire_date() {
		return hire_date;
	}

	public void setHire_date(String date2) {
		this.hire_date = date2;
	}

	@Override
	public String toString() {
		return employee_id + " " + first_name + " " + last_name;
	}

}
