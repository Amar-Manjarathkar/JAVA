public class Student {
    int rollno;
    String name;
    int marks;
    static int count = 0;

    public Student(String name, int marks) {
        count++;
        this.name = name;
        this.rollno = count;
        this.marks = marks;

    }

    public int getRollno() {
        return rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public static void displayTotalCount() {
        System.out.println("The total count of students is: " + count);
        ;
    }


    @Override
    public String toString() {
        return "Student{" +
                "rollno=" + rollno +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}
