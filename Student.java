import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student with a list of module grades.
 * Demonstrates ArrayLists, calculations, and data modelling.
 */
public class Student {

    private String studentId;
    private String name;
    private List<Module> modules;

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name      = name;
        this.modules   = new ArrayList<>();
    }

    public void addModule(Module module) {
        modules.add(module);
    }

    public double calculateAverage() {
        if (modules.isEmpty()) return 0;
        double total = 0;
        for (Module module : modules) {
            total += module.getMark();
        }
        return total / modules.size();
    }

    public double calculateWeightedAverage() {
        if (modules.isEmpty()) return 0;
        double totalWeighted = 0;
        double totalCredits  = 0;
        for (Module module : modules) {
            totalWeighted += module.getMark() * module.getCredits();
            totalCredits  += module.getCredits();
        }
        return totalCredits == 0 ? 0 : totalWeighted / totalCredits;
    }

    public String getGrade(double average) {
        if (average >= 75) return "DISTINCTION";
        if (average >= 65) return "MERIT";
        if (average >= 50) return "PASS";
        return "FAIL";
    }

    public Module getHighestModule() {
        if (modules.isEmpty()) return null;
        Module highest = modules.get(0);
        for (Module m : modules) {
            if (m.getMark() > highest.getMark()) highest = m;
        }
        return highest;
    }

    public Module getLowestModule() {
        if (modules.isEmpty()) return null;
        Module lowest = modules.get(0);
        for (Module m : modules) {
            if (m.getMark() < lowest.getMark()) lowest = m;
        }
        return lowest;
    }

    public List<Module> getFailedModules() {
        List<Module> failed = new ArrayList<>();
        for (Module m : modules) {
            if (m.getMark() < 50) failed.add(m);
        }
        return failed;
    }

    public void printReport() {
        System.out.println("==========================================");
        System.out.println("         STUDENT GRADE REPORT            ");
        System.out.println("==========================================");
        System.out.println("Student ID : " + studentId);
        System.out.println("Name       : " + name);
        System.out.println("------------------------------------------");
        System.out.printf("%-25s %-8s %-8s%n", "Module", "Credits", "Mark (%)");
        System.out.println("------------------------------------------");
        for (Module m : modules) {
            System.out.printf("%-25s %-8d %-8.1f%n", m.getName(), m.getCredits(), m.getMark());
        }
        System.out.println("------------------------------------------");
        double avg = calculateAverage();
        double weighted = calculateWeightedAverage();
        System.out.printf("Simple Average     : %.2f%%%n", avg);
        System.out.printf("Weighted Average   : %.2f%%%n", weighted);
        System.out.println("Overall Grade      : " + getGrade(weighted));

        if (getHighestModule() != null) {
            System.out.println("Best Module        : " + getHighestModule().getName()
                + " (" + getHighestModule().getMark() + "%)");
        }
        if (getLowestModule() != null) {
            System.out.println("Needs Attention    : " + getLowestModule().getName()
                + " (" + getLowestModule().getMark() + "%)");
        }

        List<Module> failed = getFailedModules();
        if (!failed.isEmpty()) {
            System.out.println("Failed Modules     :");
            for (Module m : failed) {
                System.out.println("  - " + m.getName() + " (" + m.getMark() + "%)");
            }
        }
        System.out.println("==========================================");
    }

    // Getters
    public String getStudentId() { return studentId; }
    public String getName()      { return name; }
    public List<Module> getModules() { return modules; }
}
