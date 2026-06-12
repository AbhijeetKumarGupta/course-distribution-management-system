package CourseDistributor.src.model;

import java.util.ArrayList;

public class Convener extends FacultyMember {
	private int conId;
	private ArrayList<Course> course;
	private ArrayList<Lecturers> lecturer;
	private ArrayList<TAs> ta;

	public Convener() {}

	public Convener(int conId, ArrayList<Course> course, ArrayList<Lecturers> lecturer, ArrayList<TAs> ta) {
		super();
		this.conId = conId;
		this.course = course;
		this.lecturer = lecturer;
		this.ta = ta;
	}

	public int getConId() {
		return conId;
	}

	public void setConId(int conId) {
		this.conId = conId;
	}

	public ArrayList<Course> getCourseNotAssigned() {
		ArrayList<Course> tempCourse = new ArrayList<Course>();
		boolean allocate = false;


        for (Course value : this.course) {
            for (Lecturers lecturers : this.lecturer) {
                for (int k = 0; k < lecturers.getAssignedCourses().size(); k++) {
                    if (value.getCourseCode() == lecturers.getAssignedCourses().get(k)
                            .getCourseCode()) {
                        allocate = true;
                        break;
                    }

                }
            }
            for (TAs tAs : this.ta) {
                for (int kk = 0; kk < tAs.getAssignedCourses().size(); kk++) {
                    if (value.getCourseCode() == tAs.getAssignedCourses().get(kk).getCourseCode()) {
                        allocate = true;
                        break;
                    }
                }
            }

            if (!allocate) {
                tempCourse.add(value);
            }
            allocate = false;
        }
		return tempCourse;
	}
	
	public ArrayList<Course> getCourseAssigned() {
		ArrayList<Course> tempCourse = new ArrayList<Course>();
		boolean allocate = true;

        for (Course value : this.course) {
            for (Lecturers lecturers : this.lecturer) {
                for (int k = 0; k < lecturers.getAssignedCourses().size(); k++) {
                    if (value.getCourseCode() != lecturers.getAssignedCourses().get(k)
                            .getCourseCode()) {
                        allocate = false;
                        break;
                    }

                }
            }
            for (TAs tAs : this.ta) {
                for (int kk = 0; kk < tAs.getAssignedCourses().size(); kk++) {
                    if (value.getCourseCode() != tAs.getAssignedCourses().get(kk).getCourseCode()) {
                        allocate = false;
                        break;
                    }
                }
            }

            if (allocate) {
                tempCourse.add(value);
            }
            allocate = true;
        }

		return tempCourse;
	}

	public ArrayList<Course> getCourse(){
		
		return this.course;
	}
	public void setCourse(ArrayList<Course> course) {
		this.course = course;
	}

	public ArrayList<Lecturers> getLecturer() {
		return lecturer;
	}

	public void setLecturer(ArrayList<Lecturers> lecturer) {
		this.lecturer = lecturer;
	}

	public ArrayList<TAs> getTa() {
		return ta;
	}

	public void setTa(ArrayList<TAs> ta) {
		this.ta = ta;
	}

	public void allocateCourse(int courseId, int lectId) {
		this.getLecturer().get(lectId-1).setAssignedCourses(this.course.get(courseId-1));
	}

	public void dropCourse(int courseCode) {
		this.course.remove(courseCode-1);
	}

}
