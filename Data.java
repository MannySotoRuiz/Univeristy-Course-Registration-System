import java.util.*;
import java.io.*;


public class Data implements Serializable{
	
	public static ArrayList<Course> LoadCoursesData() {
		
		//Scanner input = new Scanner(System.in);
		
		ArrayList<String> courseList = new ArrayList<String> ();
		ArrayList<Course> list = new ArrayList<Course> ();
		
		boolean check = false;
		
		String fileName= "MyUniversityCourses.csv";
		String line = null;
		
		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) != null) {
				courseList.add(line);
			}
			
			courseList.remove(courseList.get(0));
			 
			for (int i = 0; i < courseList.size(); i++) {
				String current = courseList.get(i);
				String [] clean = current.split(",");
				String courseName = clean[0];
				String courseID = clean[1];
				int maxStudents = Integer.parseInt(clean[2]);
				int currentStudents = Integer.parseInt(clean[3]);
				ArrayList<String> namesList = new ArrayList<String> ();
				String courseInstructor = clean[5];
				int courseSectionNumber = Integer.parseInt(clean[6]);
				String courseLocation = clean[7];
				
				Course temp = new Course(courseName, courseID, maxStudents, currentStudents, namesList, courseInstructor, courseSectionNumber, courseLocation);
				list.add(temp);
			}
			
			/*
			System.out.println();
			System.out.println("Data has been loaded");
			System.out.println(); */
			check = true;
			
			/*
			Course temp = new Course();
			for (int i = 0; i < list.size(); i++) {
				Course display = list.get(i);
				temp.displayCourse(display);
			}
			System.out.println();
			*/
			
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}
		
		return list;
		
	}
	

}
