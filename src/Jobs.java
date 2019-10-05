
public class Jobs {
	String job_id;
	String job_title;
	Integer min_salary;
	Integer max_salary;
	
	public Jobs() {
		super();
	}
	public Jobs(String job_id, String job_title) {
		super();
		this.job_id = job_id;
		this.job_title = job_title;
	}
	public Jobs(String job_id, String job_title, Integer min_salary, Integer max_salary) {
		super();
		this.job_id = job_id;
		this.job_title = job_title;
		this.min_salary = min_salary;
		this.max_salary = max_salary;
	}
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	public String getJob_title() {
		return job_title;
	}
	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}
	public Integer getMin_salary() {
		return min_salary;
	}
	public void setMin_salary(Integer min_salary) {
		this.min_salary = min_salary;
	}
	public Integer getMax_salary() {
		return max_salary;
	}
	public void setMax_salary(Integer max_salary) {
		this.max_salary = max_salary;
	}
	@Override
	public String toString() {
		return "Jobs [job_id=" + job_id + ", job_title=" + job_title + ", min_salary=" + min_salary + ", max_salary="
				+ max_salary + "]";
	}
	
	

}
