import java.math.BigDecimal;

public final class Student extends Person {
    private final String major;
    private BigDecimal flexDollars;

    // Constructor with flex dollars
    public Student(String name, int id, String major, BigDecimal flexDollars) {
        super(name, id); // Call to Person constructor
        if (major == null || major.isEmpty()) {
            throw new IllegalArgumentException("Major cannot be null or empty.");
        }
        if (flexDollars == null || flexDollars.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Flex dollars cannot be null or negative.");
        }
        this.major = major;
        this.flexDollars = flexDollars;
    }

    public String getMajor() {
        return major;
    }

    public BigDecimal getFlexDollars() {
        return flexDollars;
    }

    public void addFlexDollars(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Cannot add null or negative flex dollars.");
        }
        this.flexDollars = this.flexDollars.add(amount);
    }

    public void spendFlexDollars(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Cannot spend null or negative flex dollars.");
        }
        if (amount.compareTo(this.flexDollars) > 0) {
            throw new IllegalArgumentException("Insufficient flex dollars.");
        }
        this.flexDollars = this.flexDollars.subtract(amount);
    }

    /**
     * Sets a new flex dollars amount for the student.
     * Calculates and returns the difference between the new and old amounts.
     *
     * @param newAmount The new flex dollars amount.
     * @return The difference (newAmount - oldAmount).
     */
    public BigDecimal setFlexDollars(BigDecimal newAmount) {
        if (newAmount == null || newAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Flex dollars cannot be null or negative.");
        }
        BigDecimal difference = newAmount.subtract(this.flexDollars);
        this.flexDollars = newAmount;
        return difference;
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String toString() {
        return getRole() + "[ID: " + getId() + ", Name: " + getName() + ", Major: " + major + ", Flex Dollars: $" + flexDollars + "]";
    }
}
