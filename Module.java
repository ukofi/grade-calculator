/**
 * Represents a single academic module with a name, credits, and mark.
 */
public class Module {

    private String name;
    private int credits;
    private double mark;

    public Module(String name, int credits, double mark) {
        if (mark < 0 || mark > 100) {
            throw new IllegalArgumentException("Mark must be between 0 and 100.");
        }
        if (credits <= 0) {
            throw new IllegalArgumentException("Credits must be greater than zero.");
        }
        this.name    = name.trim();
        this.credits = credits;
        this.mark    = mark;
    }

    public String getStatus() {
        if (mark >= 75) return "Distinction";
        if (mark >= 65) return "Merit";
        if (mark >= 50) return "Pass";
        return "Fail";
    }

    // Getters
    public String getName()    { return name; }
    public int getCredits()    { return credits; }
    public double getMark()    { return mark; }

    @Override
    public String toString() {
        return name + " | " + credits + " credits | " + mark + "% | " + getStatus();
    }
}
