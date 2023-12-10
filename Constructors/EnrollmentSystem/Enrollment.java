public class Enrollment {
    public Section section;
    public Student student;

    Enrollment() {
        this.section = null;
        this.student = null;
        System.out.println("Empty enrollment");
    }

    Enrollment(Section section, Student student) {
        this.section = section;
        this.student = student;
    }
}
