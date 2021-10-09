import java.util.ArrayList;

public interface StudentInterface {

	//Course Management
	public void studentViewAllCourses(ArrayList<Course> List);
	public void allCoursesNotFull(ArrayList<Course> list);
	public void register(ArrayList<Course> courseList, ArrayList<Student> studentList, String firstName, String lastName, String courseID, int section);
	public void withdraw(ArrayList<Course> courseList, ArrayList<Student> studentList, String firstName, String lastName, String courseID, int section);
	public void currentCourses(ArrayList<Student> list, String firstName, String lastName);
}
