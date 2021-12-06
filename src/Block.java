import java.util.ArrayList;

public class Block {
	String class_name;
	ArrayList<Character> days = new ArrayList<Character>();
	public int period;
	
	int[][] enroled_students = new int[6][6];
	int max_students;


	
	public Block(String class_name) {
		this.class_name = class_name;
	}
	public void add_student(int period, int day) {
		this.enroled_students[period][day]++;
	}
	public void set_days(char day) {
		this.days.add(day);
	}
	public void set_periods(int p) {
		this.period = p;
	}
	
	public boolean is_full(int period, int day) {
		if(enroled_students[period][day] >= max_students) {
			return true;
		} else {
			return false;
		}
	}
	public void set_max_students(int max) {
		this.max_students = max;
	}
}
