import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;



public class Student {
	String name;
	
	ArrayList<String> classes = new ArrayList<String>();
	
	int[][] schedule = new int[6][6];
	

	
	public Student(String name) {
		this.name = name;
	}
	public void pop_class() {
		this.classes.remove(0);
	}
	
	public void set_class() throws IOException {
		int it = 0; //iterate classes
		boolean read=false;
		boolean already_created = true;
		//this.classes.add(className);
		//File classes = new File("subjectsandblocks.txt");
		File students = new File("out.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(students));
		String string;
		
		while((string = br.readLine()) != null) {
			if(string.equals(this.name)) {
				read = true;
			}
			if(read && !string.equals(this.name)) {
				this.classes.add(string.substring(1)); // Substring(1) is for removing the blank space in the start of the string
				

				Block block1 = new  Block(string.substring(1));

				if(it % 2==0) {					
					block1.set_max_students(15);
				}else {
					block1.set_max_students(10);
				}
				block1.set_periods(it);
				for(int f=0;f<Main.blocks.size();f++) {
					if(Main.blocks.get(f).class_name.equals(string.substring(1))) {
						already_created = true;
						break;
					}
				}
				if(!already_created) {					
					Main.blocks.add(block1);
				}
				already_created=false;
				it++;
				if(it>=8) { // maybe !string.charAt(0).equals(" ") 
					it = 0;
					read = false;
				}
				
			}
		}	
	}
	public void create_schedule() throws IOException {
		File classes = new File("subjectsandblocks.txt");
		
		BufferedReader br = new BufferedReader(new FileReader(classes));
		String string;
		while((string = br.readLine()) != null) {
			for(int i=0;i<6;i++) { // Certo for(int i=0;i<this.classes.size();i++) {
				//System.out.println(this.classes.get(i));
				if(string.contains(this.classes.get(i))) {
					for(int a=0;a<Main.blocks.size();a++) {
						if(Main.blocks.get(a).class_name.equals(this.classes.get(i))) {
							String temp = string.substring(string.indexOf('-') + 1); // Get days
							temp = temp.replaceAll("\\s", ""); //Get rid of blank spaces
							for(int j=0;j<temp.length();j++) {
								switch(temp.charAt(j)) {
								case 'A':
									Main.blocks.get(i).set_days('A');
									this.schedule[i][0] = 2;
									break;
								case 'B':
									Main.blocks.get(i).set_days('B');
									this.schedule[i][1] = 2;
									break;
								case 'C':
									Main.blocks.get(i).set_days('C');
									this.schedule[i][2] = 2;
									break;
								case 'D':
									Main.blocks.get(i).set_days('D');
									this.schedule[i][3] = 2;
									break;
									
								case 'E':
									Main.blocks.get(i).set_days('E');
									this.schedule[i][4] = 2;
									break;
								case 'F':
									Main.blocks.get(i).set_days('F');
									this.schedule[i][5] = 2;
									break;
								}
							}
						}
					}
				}
			}
		}	
	}


}
