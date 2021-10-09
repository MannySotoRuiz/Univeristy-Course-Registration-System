import java.util.ArrayList;

public interface AdminInterface {

	//Courses Management
	public void newCourse(ArrayList<Course> list, Course add);
	public void deleteCourse(ArrayList<Course> list, String name, int section);
	public void editCourse(ArrayList<Course> list, String name, int courseSection, String newLocation);
	public void displayInfo(ArrayList<Course> list, String courseID);
	public void registerStudent(ArrayList<Student> studentList, Student addStudent);
		
	//Reports
	public void adminViewAllCourses(ArrayList<Course> list);
	public ArrayList<Course> viewFullCourses(ArrayList<Course> list);
	public void fileCoursesFull(ArrayList<Course> list);
	public void studentListCourse(ArrayList<Course> list, String courseID, int section);
	public void studentCoursesRegistered(ArrayList<Student> list, String firstName, String lastName);
	public void sortCourses(ArrayList<Course> list);
}
