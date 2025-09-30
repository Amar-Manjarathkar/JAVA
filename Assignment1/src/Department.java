public class Department {
    String departmentName;
    Student[] students;

    public Department(String departmentName, Student[] students) {
        this.departmentName = departmentName;
        this.students = students;
    }

    public double calculateAverage() {
        if (students == null || students.length == 0) {
            return 0.0;
        }
        double totalSum = 0;

        for (Student s : students) {
            totalSum += s.getMarks();

        }
        return totalSum / students.length;

    }

    public void calculatePercentage() {
        double percentage = calculateAverage();
        System.out.println("The percentage for " + departmentName + " is: " + percentage);


    }
}