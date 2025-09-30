public class StudentMain {
    public static void main(String[] args) {
        Student s1 = new Student("Amar", 78);
        System.out.println(s1.toString());
        Student s2 = new Student("Amar1", 65);
        System.out.println(s2.toString());
        Student s3 = new Student("Amar2", 56);
        System.out.println(s3.toString());

        Student[] csDeptStudents = {s1, s2, s3};

        Department csdept = new Department("CS DEPT", csDeptStudents);
        double avg = csdept.calculateAverage();

        System.out.println("The average is:" + avg);
        csdept.calculatePercentage();
    }
}

/*1. Loop/conditions : Write program for checking prime number, calculating factorial (Iterative)

2. Create a Student class with name and rollno, total marks
Implement default as well as parameterized constructor
Implement getter - setter and toString method

Note : The rollno should be auto generated based on number of student objects created
Display the count of total students using displyStudentCount method

3. Create a Department class with Student array
1. Calculate Average marks for all student
2. Calculate percentage

Additional - Binary Search
Any sort algorithm*/
