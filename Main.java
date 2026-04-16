import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Entry point for the Student Grade Calculator.
 * Supports multiple students and generates grade reports.
 */
public class Main {

    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("======================================");
        System.out.println("   Student Grade Calculator v1.0     ");
        System.out.println("======================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = getIntInput("Enter choice: ");

            switch (choice) {
                case 1: handleAddStudent();    break;
                case 2: handleAddModules();    break;
                case 3: handleViewReport();    break;
                case 4: handleListStudents();  break;
                case 5: handleClassSummary();  break;
                case 0:
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Add new student");
        System.out.println("2. Add modules to a student");
        System.out.println("3. View student grade report");
        System.out.println("4. List all students");
        System.out.println("5. Class summary (average, top student)");
        System.out.println("0. Exit");
    }

    private static void handleAddStudent() {
        System.out.print("Student ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Student name: ");
        String name = scanner.nextLine().trim();
        students.add(new Student(id, name));
        System.out.println("Student added: " + name);
    }

    private static void handleAddModules() {
        Student student = findStudent();
        if (student == null) return;

        int count = getIntInput("How many modules to add? ");
        for (int i = 0; i < count; i++) {
            System.out.print("Module name: ");
            String name = scanner.nextLine().trim();
            int credits  = getIntInput("Credits: ");
            double mark  = getDoubleInput("Mark (%): ");
            try {
                student.addModule(new Module(name, credits, mark));
                System.out.println("Module added: " + name);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                i--; // retry
            }
        }
    }

    private static void handleViewReport() {
        Student student = findStudent();
        if (student != null) student.printReport();
    }

    private static void handleListStudents() {
        if (students.isEmpty()) {
            System.out.println("No students added yet.");
            return;
        }
        System.out.println("\n=== Students ===");
        for (Student s : students) {
            System.out.println(s.getStudentId() + " | " + s.getName()
                + " | Modules: " + s.getModules().size());
        }
    }

    private static void handleClassSummary() {
        if (students.isEmpty()) {
            System.out.println("No students to summarise.");
            return;
        }
        double classTotal = 0;
        Student topStudent = null;
        double topAvg = -1;

        for (Student s : students) {
            if (s.getModules().isEmpty()) continue;
            double avg = s.calculateWeightedAverage();
            classTotal += avg;
            if (avg > topAvg) {
                topAvg = avg;
                topStudent = s;
            }
        }

        System.out.println("\n=== Class Summary ===");
        System.out.printf("Class average  : %.2f%%%n", classTotal / students.size());
        if (topStudent != null) {
            System.out.printf("Top student    : %s (%.2f%%)%n", topStudent.getName(), topAvg);
        }
    }

    private static Student findStudent() {
        System.out.print("Enter student ID or name: ");
        String input = scanner.nextLine().trim();
        for (Student s : students) {
            if (s.getStudentId().equalsIgnoreCase(input) ||
                s.getName().equalsIgnoreCase(input)) {
                return s;
            }
        }
        System.out.println("Student not found: " + input);
        return null;
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
