/*    This program was made to show the security rule  IDS01-J. Name: Normalize
     strings before validating them */

import java.util.Date;

public final class StopDontAllowSensitiveClassesToBeCopied { // final class allows it to not be copied
    // Private and final fields for immutability
    private final String ssn;
    private final Date dob;

    // Constructor to initialize the fields
    public StopDontAllowSensitiveClassesToBeCopied(String ssn, Date dob) {
        this.ssn = ssn;
        // Defensive copying to protect the internal Date field
        this.dob = new Date(dob.getTime());
    }

    // Prevent cloning by overriding clone()
    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning is not allowed for this class.");
    }
}
