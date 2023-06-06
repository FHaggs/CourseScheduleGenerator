import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class Main {
	public static FamList<Block> blocks = new FamList<Block>();
	public static FamList<Student> problem_students = new FamList<Student>(); 
	static boolean done=false;
	public static void main(String[] args) throws Exception{
	

		File students = new File("../students.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(students));
		String student_name;
		
		while((student_name = br.readLine()) != null ){
			System.out.println(student_name);
			Student student1 = new Student(student_name);
			student1.set_class();
			student1.create_schedule();
			done =false;
			
			for(int a=0;a<2;a++) {

				
				
				if(!solve(student1.schedule,0, student1.classes.subList(0, 6)) && !done) {
					student1.pop_class();
					student1.create_schedule();
					if(solve(student1.schedule,0, student1.classes.subList(0,6))) {
						break;
					}
				}

			}
			System.out.println("-------------------");
			if(!done) {
				problem_students.add(student1);
			}
		}
		System.out.println("\n\nStudents with schedule problems: " + problem_students.size());
		for(int i=0;i<problem_students.size(); i++) {
			
			System.out.println(problem_students.get(i).name);
			System.out.println(problem_students.get(i).classes);
			/*
			for(int x=0;x<6;x++) {
				for(int y=0;y<6;y++) {
					System.out.print(problem_students.get(i).schedule[x][y]);
				}
				System.out.print('\n');
			}*/
			
			for(int j=1;j<5;j++) {				
				if(solve(problem_students.get(i).schedule, j, problem_students.get(i).classes.subList(0, 6))) {
					break;
				}
			}
		}
	br.close();	
	}
	
	static private boolean isSafe(int[][] board, int row, int col) {
		if(!is_availible(row, col)) {
			return false;
		}
		if(board[row][col] != 2) {
			return false;
		}
		for(int i=0;i<6;i++) {
			if(board[row][i] == 1) {
				return false;
			}
		}
		for(int i=0;i<6;i++) {
			if(board[i][col] == 1) {
				return false;
			}
		}
		return true;
	}
	
	static private boolean solve(int[][] board, int row, FamList<String> classes) {
		if(row >= 6) {
			printcourse(board, classes);
			for(int i=0;i<6;i++) {
				for(int j=0;j<6;j++) {
					if(board[i][j] == 1) {
						add_to_block(i, j);
					}
				}
			}
			
			done=true;
			return true;
		}
		for(int i=0;i<6;i++) {
			if(isSafe(board, row, i)) {
				board[row][i] = 1;
		
				if(solve(board, row + 1, classes)) {
					done=true;
					break;
				}
				board[row][i]=0;
			}
		}
		return false;
	}
	
	static private void printcourse(int data[][], FamList<String> classes) {
		for(int i=0;i<6;i++) {
			for(int j=0;j<6;j++) {
                StringBuilder pretty_printer = new StringBuilder(100);
                if(j == 0){
                    int num_espacos = 40 - classes.get(i).length(); // used for formating
                    pretty_printer.append(classes.get(i)+ ":");
                    String espacos = String.format("%" + num_espacos + "s", "");
                    pretty_printer.append(espacos);
                }
				if(data[i][j] == 1){	
					pretty_printer.append(" X ");
				}else {
					pretty_printer.append(" O ");
				}
                System.out.print(pretty_printer);
			}
			System.out.print('\n');
		}

	}

	static boolean is_availible(int period, int day) {
		char char_day='Z';
		switch(day) {
			case 0:
				char_day = 'A';
				break;
			case 1:
				char_day = 'B';
				break;	
			case 2:
				char_day = 'C';
				break;	
			case 3:
				char_day = 'D';
				break;	
			case 4:
				char_day = 'E';
				break;	
			case 5:
				char_day = 'F';
				break;	
				
		}
		
		for(int i=0;i<blocks.size();i++) {
			Block temp_block = blocks.get(i);
			if(temp_block.period == period || temp_block.days.contains(char_day)) {
				if(temp_block.is_full(period, day)) {
					return false;
				}else {
					return true;
				}
			}
		}
		System.out.println("Deu merda");
		return false;
	}
	static void add_to_block(int period, int day) {
		char char_day='Z';
		switch(day) {
			case 0:
				char_day = 'A';
				break;
			case 1:
				char_day = 'B';
				break;	
			case 2:
				char_day = 'C';
				break;	
			case 3:
				char_day = 'D';
				break;	
			case 4:
				char_day = 'E';
				break;	
			case 5:
				char_day = 'F';
				break;	
				
		}
		
		for(int i=0;i<blocks.size();i++) {
			Block temp_block = blocks.get(i);
			if(temp_block.period == period || temp_block.days.contains(char_day)) {
				temp_block.add_student(period, day);
			}
		}
	}

	
}
