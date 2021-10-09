import java.util.ArrayList;

public class Student extends Users implements StudentInterface, java.io.Serializable {
	
	private ArrayList<Course> myCourses = new ArrayList<Course> ();
	
	public Student() {
		super();
	}
	
	public Student (String firstName, String lastName, String username, String password, ArrayList<Course> courses) {
		super (username, password, firstName, lastName);
		myCourses = courses;
		
	}
	
	
	public void studentViewAllCourses(ArrayList<Course> list) {
		
		for (int i = 0; i < list.size(); i++) {
			Course temp = list.get(i);
			
			System.out.println("Course name: " + temp.getCourseName() + ", Section #: " + temp.getCourseSectionNumber() + ", Instructor: " + temp.getCourseInstructor() + ", Location: " + temp.getCourseLocation());
			
		}
		
	}

	@Override
	public void allCoursesNotFull(ArrayList<Course> list) {
		System.out.println("Available courses (not full): ");
		
		for (int i = 0; i < list.size(); i++) {
			
			Course temp = list.get(i);
			
			if (temp.getCurrentStudents() < temp.getMaxStudents()) {
				System.out.println("Course name: " + temp.getCourseName() + ", Section #: " + temp.getCourseSectionNumber() + ", Instructor: " + temp.getCourseInstructor() + ", Location: " + temp.getCourseLocation());
			}
		}
	}

	@Override
	public void register(ArrayList<Course> courseList, ArrayList<Student> studentList, String firstName, String lastName, String courseID, int section) {
		
		Course tempCourse = new Course();
		for (int i = 0; i < courseList.size(); i++) {
			tempCourse = courseList.get(i);
			if (tempCourse.getCourseID().equals(courseID) && tempCourse.getCourseSectionNumber() == section) {
				String fullName = firstName + " " + lastName;
				tempCourse.getListNames().add(fullName);
				int newStuAmt = tempCourse.getCurrentStudents() + 1;
				tempCourse.setCurrentStudents(newStuAmt);
				break;
			}
			
		}
		for (int j = 0; j < studentList.size(); j++) {
			Student tempStudent = studentList.get(j);
			Course newCourse = new Course(tempCourse.getCourseID(), tempCourse.getCourseSectionNumber());
			if (tempStudent.getFirstName().equals(firstName) && tempStudent.getLastName().equals(lastName)) {
				tempStudent.getMyCourses().add(newCourse);
				System.out.println("Successfully added " + courseID + " to your courses.");
				return;
			}
		}
		
	}

	@Override
	public void withdraw(ArrayList<Course> courseList, ArrayList<Student> studentList, String firstName, String lastName, String courseID, int section) {
		
		for (int i = 0; i < courseList.size(); i++) {
			Course tempCourse = courseList.get(i);
			if (tempCourse.getCourseID().equals(courseID) && tempCourse.getCourseSectionNumber() == section) {
				String fullName = firstName + " " + lastName;
				ArrayList<String> stuList = tempCourse.getListNames();
				for (int j = 0; j < stuList.size(); j++) {
					String tempStudent = stuList.get(j);
					if (tempStudent.equals(fullName)) {
						stuList.remove(j);
						tempCourse.setCurrentStudents(tempCourse.getCurrentStudents() - 1);
					}
				}
				break;
			}
		}
		
		for (int i = 0; i < studentList.size(); i++) {
			Student tempStu = studentList.get(i);
			if (tempStu.getFirstName().equals(firstName) && tempStu.getLastName().equals(lastName)) {
				studentList.remove(i);
				
				System.out.println("Successfully removed " + courseID + " from your courses");
				break;
			}
		}
		
	}

	@Override
	public void currentCourses(ArrayList<Student> list, String firstName, String lastName) {
		for (int i = 0; i < list.size(); i++) {
			Student tempStudent = list.get(i);
			if (tempStudent.getFirstName().equals(firstName) && tempStudent.getLastName().equals(lastName)) {
				ArrayList<Course> courseList = tempStudent.getMyCourses();
				System.out.println("Your current courses:");
				for (int j = 0; j < courseList.size(); j++) {
					System.out.println("Course ID: " + courseList.get(j).getCourseID() + "\tSection #:" + courseList.get(j).getCourseSectionNumber());
				}
			}
		}
		
	}

	public ArrayList<Course> getMyCourses() {
		return myCourses;
	}

	public void setMyCourses(ArrayList<Course> myCourses) {
		this.myCourses = myCourses;
	}
	
	

}
