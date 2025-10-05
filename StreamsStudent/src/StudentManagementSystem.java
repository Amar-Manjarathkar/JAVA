import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class StudentManagementSystem {

    public static void main(String[] args) {
        // Sample Student Data
        List<Student> students = Arrays.asList(
                new Student("PRN001", "Alice", 21, "Computer Science", LocalDate.of(2022, 8, 15), 3.8, "New York"),
                new Student("PRN002", "Bob", 22, "Mechanical Engineering", LocalDate.of(2021, 9, 1), 3.5, "Los Angeles"),
                new Student("PRN003", "Charlie", 20, "Computer Science", LocalDate.of(2022, 8, 15), 3.9, "New York"),
                new Student("PRN004", "Diana", 23, "Physics", LocalDate.of(2020, 7, 20), 3.2, "Chicago"),
                new Student("PRN005", "Eve", 21, "Physics", LocalDate.of(2022, 8, 20), 3.6, "Chicago"),
                new Student("PRN006", "Frank", 22, "Mechanical Engineering", LocalDate.of(2021, 9, 1), 3.95, "Los Angeles")
        );

        // --- Method Demonstrations ---

        System.out.println("1. Students in Computer Science: " + getStudentNamesByCourse(students, "Computer Science"));
        System.out.println("\n2. Students with GPA > 3.7: " + getStudentsWithHighGPA(students, 3.7));
        System.out.println("\n3. Unique student cities: " + getAllCities(students));
        System.out.println("\n4. Student count by course: " + getStudentCountByCourse(students));
        System.out.println("\n5. Average GPA of all students: " + String.format("%.2f", getAverageGPA(students)));
        System.out.println("\n6. Top 3 students by GPA: " + getTopStudentsByGPA(students, 3));
        System.out.println("\n7. Students sorted by name: " + getStudentsSortedByName(students));
        System.out.println("\n8. Students grouped by enrollment year: " + getStudentsByEnrollmentYear(students));
    }

    // 1. Return names of all students from the specified course
    public static List<String> getStudentNamesByCourse(List<Student> students, String course) {
        return students.stream()
                .filter(s -> s.getCourse().equalsIgnoreCase(course))
                .map(Student::getName)
                .collect(Collectors.toList());
    }

    // 2. Return students with GPA above the given threshold
    public static List<Student> getStudentsWithHighGPA(List<Student> students, double minGpa) {
        return students.stream()
                .filter(s -> s.getGpa() > minGpa)
                .collect(Collectors.toList());
    }

    // 3. Return unique cities where students are from
    public static Set<String> getAllCities(List<Student> students) {
        return students.stream()
                .map(Student::getCity)
                .collect(Collectors.toSet());
    }

    // 4. Group students by course and count how many in each
    public static Map<String, Long> getStudentCountByCourse(List<Student> students) {
        return students.stream()
                .collect(Collectors.groupingBy(Student::getCourse, Collectors.counting()));
    }

    // 5. Calculate the average GPA of all students
    public static double getAverageGPA(List<Student> students) {
        return students.stream()
                .mapToDouble(Student::getGpa)
                .average()
                .orElse(0.0);
    }

    // 6. Return top N students with highest GPA
    public static List<Student> getTopStudentsByGPA(List<Student> students, int limit) {
        return students.stream()
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .limit(limit)
                .collect(Collectors.toList());
    }

    // 7. Return all students sorted alphabetically by name
    public static List<Student> getStudentsSortedByName(List<Student> students) {
        return students.stream()
                .sorted(Comparator.comparing(Student::getName))
                .collect(Collectors.toList());
    }

    // 8. Group students by their enrollment year
    public static Map<Integer, List<Student>> getStudentsByEnrollmentYear(List<Student> students) {
        return students.stream()
                .collect(Collectors.groupingBy(s -> s.getEnrollmentDate().getYear()));
    }
}