import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class Admin extends Users implements AdminInterface, Serializable {
	
	//private static final long serialVersionUID = 1L;
	public Admin () {
		super();
		this.username = "admin";
		this.password = "admin001";
	}

	//static ArrayList<Course> listOfStudents = new ArrayList<Course> ();

	@Override
	public void newCourse(ArrayList<Course> list, Course add) {
		
		Course newCourse = add;
		list.add(newCourse);
		System.out.println();
		System.out.println("Course was added");
		System.out.println();		
		
	}
	

	@Override
	public void deleteCourse(ArrayList<Course> list, String id, int section) {
		// TODO Auto-generated method stub
		//Course temp = new Course();
		boolean check = false;
		
		for (int i = 0; i < list.size(); i++) {
			Course current = list.get(i);
			if (current.getCourseID().equals(id) && current.getCourseSectionNumber() == section) {
				list.remove(current);
				check = true;
				break;
			}
		}
		
		if (check) {
			System.out.println();
			System.out.println("Course was deleted");
			System.out.println();
		} else {
			System.out.println();
			System.out.println("Course does not exist");
			System.out.println();
		}
		
	}

	@Override
	public void editCourse(ArrayList<Course> list, String courseID, int courseSection, String newLocation) {
		// TODO Auto-generated method stub
		boolean check = false;
		
		for (int i = 0; i < list.size(); i++) {
			Course current = list.get(i);
			if (current.getCourseID().equals(courseID) && current.getCourseSectionNumber() == courseSection) {
				current.setCourseLocation(newLocation);
				check = true;	
				break;
			}
		}
		
		if (check) {
			System.out.println();
			System.out.println("Course was edited");
			System.out.println();
		} else {
			System.out.println();
			System.out.println("Course does not exist");
			System.out.println();
		}
	}

	@Override
	public void displayInfo(ArrayList<Course> list, String courseID) {
		for (int i = 0; i < list.size(); i++) {
			Course temp = list.get(i);
			if (temp.getCourseID().equals(courseID)) {
				System.out.println("Course name: " + temp.getCourseName() + ", Course ID: " + temp.getCourseID() + ", Maximum Students: " + temp.getMaxStudents() + ", Current Students: " + temp.getCurrentStudents() + ", Instructor: " + temp.getCourseInstructor() + ", Course Section: " + temp.getCourseSectionNumber() + ", Location: " + temp.getCourseLocation());
			}
		}
	}

	@Override
	public void registerStudent(ArrayList<Student> studentList, Student addStudent) {
		studentList.add(addStudent);
		
	}

	@Override
	public void adminViewAllCourses(ArrayList<Course> list) {
		
		for (int i = 0; i < list.size(); i++) {
			Course temp = new Course();
			temp = list.get(i);
			
			System.out.println("Course name: " + temp.getCourseName() + ", Course ID: " + temp.getCourseID() + ", Maximum Students: " + temp.getMaxStudents() + ", Current Students: " + temp.getCurrentStudents() + ", Instructor: " + temp.getCourseInstructor() + ", Course Section: " + temp.getCourseSectionNumber() + ", Location: " + temp.getCourseLocation());
			
		}
		
	}

	@Override
	public ArrayList<Course> viewFullCourses(ArrayList<Course> list) {
		// TODO Auto-generated method stub
		ArrayList<Course> fullCourses = new ArrayList<Course>();
		for (int i = 0; i < list.size(); i++) {
			Course temp = new Course();
			temp = list.get(i);
			
			if (temp.getCurrentStudents() == temp.getMaxStudents()) {
				fullCourses.add(temp);
			} 
		}
		return fullCourses;
	}

	@Override
	public void fileCoursesFull(ArrayList<Course> list) {
		String fileName = "FullCourses.txt";
		Scanner scan = new Scanner(System.in);
		
		try {
			FileWriter fileWriter = new FileWriter(fileName);
			
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			for (int i = 0; i < list.size(); i++) {
				Course temp = list.get(i);
				if (temp.getCurrentStudents() == temp.getMaxStudents()) {
					bufferedWriter.write(temp.getCourseID() + ", Section #");
					bufferedWriter.write(temp.getCourseSectionNumber());
					bufferedWriter.newLine();
				}
			}
			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file '" + fileName + "'");
		}
		scan.close();
	}

	@Override
	public void studentListCourse(ArrayList<Course> list, String courseID, int section) {
		for (int i = 0; i < list.size(); i++) {
			Course temp = new Course();
			if (temp.getCourseID().equals(courseID) && temp.getCourseSectionNumber() == section) {
				ArrayList<String> holdList = new ArrayList<String>();
				holdList = temp.getListNames();
				for (int j = 0; j < holdList.size(); i++) {
					System.out.print(holdList.get(i) + ", ");
				}
				break;
			}
		}
		
	}

	@Override
	public void studentCoursesRegistered(ArrayList<Student> list, String firstName, String lastName) {
		for (int i = 0; i < list.size(); i++) {
			Student temp = new Student();
			temp = list.get(i);
			if (temp.getFirstName().equals(firstName) && temp.getLastName().equals(lastName)) {
				ArrayList<Course> holdCoursesList = new ArrayList<Course>();
				holdCoursesList = temp.getMyCourses();
				System.out.println(firstName + " " + lastName + " is currently enrolled in:");
				for (int j = 0; j < holdCoursesList.size(); j++) {
					Course currentCourse = new Course();
					currentCourse = holdCoursesList.get(j);
					System.out.println("Course ID: " + currentCourse.getCourseID() + ", Section #: " + currentCourse.getCourseSectionNumber());
				}
			}
		}
	}
	
	public void sortCourses(ArrayList<Course> list) {
		for (int i = 0; i < list.size(); i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(i).getCurrentStudents() > list.get(j).getCurrentStudents()) {
					Course temp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
		System.out.println();
		adminViewAllCourses(list);
	}
}
