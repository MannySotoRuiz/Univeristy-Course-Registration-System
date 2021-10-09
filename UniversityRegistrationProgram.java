import java.io.*;
import java.util.*;

public class UniversityRegistrationProgram {
	
	public static ArrayList<Course> courseList = new ArrayList<Course> ();
	public static ArrayList<Student> studentList = new ArrayList<Student> ();

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		Admin admin = new Admin();
		Student student = new Student();
		
		File fileName = new File("CoursesArray.ser");
		File fileName2 = new File("Student.ser");
		
		
		if (!fileName.exists() && !fileName2.exists()) {
			Data d = new Data();
			ArrayList<Course> hold = d.LoadCoursesData();
			
			courseList = hold;
			
		} else {
			doDeserialization();
			System.out.println(courseList);
			System.out.println(studentList);
		}
		
		int choice;
		while (true) {
			System.out.println("----------------------------------------");
			System.out.println("                Welcome                 ");
			System.out.println("                to the                  ");
			System.out.println("     University Registration Program    ");
			System.out.println("----------------------------------------");
			System.out.println("Press '1' if you are an Admin");
			System.out.println("Press '2' if you are a Student");
			System.out.println("Press '3' if you want to Exit");
			choice = input.nextInt();
			System.out.println();
			boolean check = true;
			
			if (choice != 1 && choice != 2 && choice != 3) {
				System.out.println("Sorry, incorrect input. Try again");
				System.out.println();
			} else {
				
				//user is a admin
				if (choice == 1) {
					while(check) {
						System.out.println("Admin Login");
						System.out.print("Username: ");
						String username = input.next();
						System.out.print("Password: ");
						String password = input.next();
						
						if (username.equals(admin.getUsername()) && password.equals(admin.getPassword())) {
							System.out.println();
							System.out.println("Successfully logged in!");
							System.out.println();
							
							while (true) {
								System.out.println("Press '1' for Courses Management");
								System.out.println("Press '2' for Reports");
								System.out.println("Press '3' to Logout");
								int adminSelect = input.nextInt();
								
								if (adminSelect == 3) {
									System.out.println("Logged out");
									System.out.println();
									check = false;
									doSerialization();
									break;
								} else if (adminSelect == 1) {
									while (true) {
										System.out.println();
										AdminCoursesManagement();
										System.out.print("Select a number: ");
										int select = input.nextInt();
										
										//creating a course
										if (select == 1) {
											input.nextLine();
											System.out.print("Course Name: ");
											String courseName = input.nextLine();
											System.out.print("Course ID: ");
											String courseID = input.next();
											System.out.print("Max students: ");
											int maxStudents = input.nextInt();
											System.out.println("Current Students (set to 0 for new courses by default)");
											input.nextLine();
											System.out.print("Course Instructor: ");
											String courseInstructor = input.nextLine();
											System.out.print("Course Section #: ");
											int courseSection = input.nextInt();
											input.nextLine();
											System.out.print("Course Location: ");
											String courseLocation = input.nextLine();
											ArrayList<String> stuList = new ArrayList<String>();
											Course newClass = new Course(courseName, courseID, maxStudents, 0, stuList, courseInstructor, courseSection, courseLocation);
											admin.newCourse(courseList, newClass);
											
											//deleting a course
										} else if (select == 2) {
											System.out.print("Course ID: ");
											String courseID = input.next();
											System.out.print("Course Section #: ");
											int courseSection = input.nextInt();
											admin.deleteCourse(courseList, courseID, courseSection);
											
											//editing a course
										} else if (select == 3) {
											System.out.print("Course ID: ");
											String courseID = input.next();
											System.out.print("Course Section #: ");
											int courseSection = input.nextInt();
											input.nextLine();
											System.out.print("Enter the new location: ");
											String newLocation = input.nextLine();
											admin.editCourse(courseList, courseID, courseSection, newLocation);
											
											
											//display information for a given course
										} else if (select == 4) {
											input.nextLine();
											System.out.print("Course ID: ");
											String courseID = input.nextLine();
											admin.displayInfo(courseList, courseID);
											System.out.println();
											
											//register a student
											//username is firstname
											//password is lastname
										} else if (select == 5) {
											input.nextLine();
											System.out.print("Enter firstname: ");
											String firstName = input.nextLine();
											System.out.print("Enter lastname: ");
											String lastName = input.nextLine();
											String stuUsername = firstName;
											String stuPassword = lastName;
											ArrayList<Course> newList = new ArrayList<Course> ();
											Student newStu = new Student(firstName, lastName, stuUsername, stuPassword, newList);
											admin.registerStudent(studentList, newStu);
											System.out.println("Sucessfully added " + firstName + " " + lastName + " as a student");
											System.out.println();
											
											//user wants to exit
										} else if (select == 6) {
											break;
											
											//user entered invalid input
										} else {
											System.out.println();
											System.out.println("Sorry, invalid option. Try again");
											System.out.println();
										}
									}
								} else if (adminSelect == 2) {
									while (true) {
										System.out.println();
										AdminReports();
										System.out.print("Select a number: ");
										int select = input.nextInt();
										
										//view all courses
										if (select == 1) {
											System.out.println();
											admin.adminViewAllCourses(courseList);
											System.out.println();
											
											//view all courses FULL
										} else if (select == 2) {
											System.out.println();
											ArrayList<Course> hold = admin.viewFullCourses(courseList);
											for (int i = 0; i < hold.size(); i++) {
												Course temp = hold.get(i);
												System.out.println("Course name: " + temp.getCourseName() + ", Course ID: " + temp.getCourseID() + ", Maximum Students: " + temp.getMaxStudents() + ", Current Students: " + temp.getCurrentStudents() + ", Instructor: " + temp.getCourseInstructor() + ", Course Section: " + temp.getCourseSectionNumber() + ", Location: " + temp.getCourseLocation());
											}
											System.out.println();
											
											//write to a file the list of courses that are full
										} else if (select == 3) {
											System.out.println();
											admin.fileCoursesFull(courseList);
											System.out.println("Sucessfully wrote to file");
											System.out.println();
											
											//View the student list of a specific course
										} else if  (select == 4) {
											input.nextLine();
											System.out.print("CourseID: ");
											String courseID = input.nextLine();
											System.out.print("Section #: ");
											int courseSection = input.nextInt();
											
											Course temp = new Course();
											for (int i = 0; i < courseList.size(); i++) {
												if (courseList.get(i).getCourseID().equals(courseID) && courseList.get(i).getCourseSectionNumber() == courseSection) {
													temp = courseList.get(i);
													break;
												}
											}
											if (temp.getListNames().size() > 0) {
												admin.studentListCourse(courseList, courseID, courseSection);
												System.out.println();
											} else {
												System.out.println("Student list is empty");
												System.out.println();
											}
											
											//View the current courses of a specifc student
										} else if (select == 5) {
											if (studentList.size() > 0) {
												input.nextLine();
												System.out.print("Enter firstname: ");
												String firstName = input.nextLine();
												System.out.print("Enter lastname: ");
												String lastName = input.nextLine();
												admin.studentCoursesRegistered(studentList, firstName, lastName);
												System.out.println();
											} else {
												System.out.println("There are no students registered");
												System.out.println();
											}
											
											
											//Sort courses based on the current of student registers
										} else if (select == 6) {
											admin.sortCourses(courseList);
											System.out.println();
											
											//user wants to exit
										} else if (select == 7) {
											break;
											
											//user entered an invalid input
										} else {
											System.out.println();
											System.out.println("Sorry, invalid option. Try again");
											System.out.println();
										}
									}
								}
							}
							
							//user entered invalid username or password
						} else {
							System.out.println();
							System.out.println("Incorrect username or password");
							System.out.println();
							continue;
						}
					}
					
					//user is a student
				} else if (choice == 2) {
					if (studentList.size() == 0) {
						System.out.println("There are no student acounts created.");
						System.out.println("Ask an admin for help");
						System.out.println();
						
						//there is at least 1 student created so proceed with student login
					} else {
						while (check) {
							System.out.println("Student Login");
							System.out.print("Username: ");
							String user = input.next();
							System.out.print("Password: ");
							String pwd = input.next();
							boolean ifLogin = false;
							Student theStudent = new Student();
							for (int i = 0; i < studentList.size(); i++) {
								Student tempStu = studentList.get(i);
								if (tempStu.getUsername().equals(user) && tempStu.getPassword().equals(pwd)) {
									ifLogin = true;
									theStudent = tempStu;
								}
							}
							
							if (ifLogin) {
								while (true) {
									System.out.println();
									StudentMenu();
									int stuSelect = input.nextInt();
									
									//Exit (log out)"
									if (stuSelect == 6) {
										System.out.println("Logged out");
										System.out.println();
										check = false;
										doSerialization();
										break;
										
										//View all courses
									} else if (stuSelect == 1) {
										student.studentViewAllCourses(courseList);
										System.out.println();
										
										//View all courses that are NOT FULL
									} else if (stuSelect == 2) {
										student.allCoursesNotFull(courseList);
										
										//Register for a course
									} else if (stuSelect == 3) {
										input.nextLine();
										System.out.println("Course ID: ");
										String courseID = input.nextLine();
										System.out.println("Course Section #: ");
										int courseSection = input.nextInt();
										//student.register(courseList, studentList, theStudent.getFirstName(), theStudent.getLastName(), courseID, courseSection);
										//System.out.println();
										
										Course temp = new Course();
										for (int i = 0; i < courseList.size(); i++) {
											if (courseList.get(i).getCourseID().equals(courseID) && courseList.get(i).getCourseSectionNumber() == courseSection) {
												temp = courseList.get(i);
												break;
											}
										}
										
										if (temp.getCurrentStudents() == temp.getMaxStudents()) {
											System.out.println("Sorry but course is full");
											System.out.println();
										} else {
											student.register(courseList, studentList, theStudent.getFirstName(), theStudent.getLastName(), courseID, courseSection);
											System.out.println();
										} 
										
										//Withdraw from a course
									} else if (stuSelect == 4) {
										input.nextLine();
										System.out.print("Course ID: ");
										String courseID = input.next();
										System.out.print("Course Section #: ");
										int courseSection = input.nextInt();
										student.withdraw(courseList, studentList, theStudent.getFirstName(), theStudent.getLastName(), courseID, courseSection);
										System.out.println();
										
										//View all your current courses
									} else if (stuSelect == 5) {
										student.currentCourses(studentList, theStudent.getFirstName(), theStudent.getLastName());
										System.out.println();
										
										//user entered an invalid input
									} else {
										System.out.println();
										System.out.println("Sorry, invalid input. Try aagin ");
										System.out.println();
									}
								}

								//invalid credentials
							} else {
								System.out.println();
								System.out.println("Incorrect username or password. Or account does not exist.");
								System.out.println();
							}
							
						}
					}
				} else if (choice == 3) {
					System.out.println("Goodbye");
					//doSerialization();
					return;
				}
			}
		}
		
		
		
		
		
		
		
		

	}
	
	
	public static void AdminCoursesManagement() {
		System.out.println("1. Create a new course");
		System.out.println("2. Delete a course");
		System.out.println("3. Edit a course");
		System.out.println("4. Display information for a given course (by course ID)");
		System.out.println("5. Register a student");
		System.out.println("6. Exit");
	}
	
	public static void AdminReports() {
		System.out.println("1. View All courses");
		System.out.println("2. View all courses that are FULL");
		System.out.println("3. Write to a file the list of course that are FULL");
		System.out.println("4. View the student list of a specific course");
		System.out.println("5. View the current courses of a specifc student");
		System.out.println("6. Sort courses based on the current of student registers");
		System.out.println("7. Exit");
	}
	
	public static void StudentMenu() {
		System.out.println("1. View all courses");
		System.out.println("2. View all courses that are NOT FULL");
		System.out.println("3. Register for a course");
		System.out.println("4. Withdraw from a course");
		System.out.println("5. View all your current courses");
		System.out.println("6. Exit (log out)");
	}
	
	public static void doSerialization() {
		try {
			FileOutputStream fos = new FileOutputStream("CoursesArray.ser");
			FileOutputStream fos2 = new FileOutputStream("Student.ser");
			
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
			
			oos.writeObject(courseList);
			oos2.writeObject(studentList);
			oos.close();
			oos2.close();
			fos.close();
			fos2.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();;
		}
	}
	
	public static void doDeserialization() {
		try {
			//FileInputSystem recieves bytes from a file
			FileInputStream fis = new FileInputStream("CoursesArray.ser");
			FileInputStream fis2 = new FileInputStream("Student.ser");
				      
			//ObjectInputStream does the deserialization-- it reconstructs the data into an object
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			ObjectInputStream ois2 = new ObjectInputStream(fis2);
			
				      
			//Cast as Employee. readObject will take the object from ObjectInputStream
			courseList = (ArrayList<Course>)ois.readObject();
			studentList = (ArrayList<Student>)ois2.readObject();
			ois.close();
			ois2.close();
			fis.close();
			fis2.close();
			} catch(IOException ioe) {
				ioe.printStackTrace();
				return;
			} catch(ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
				return;
			}
	}
	

}
