import java.io.File;
import java.io.IOException;

public class PathValidation {
    public static void main(String[] args) {
        try {
            File file = new File("../somePath/someFile.txt");
            String canonicalPath = file.getCanonicalPath();  // Canonicalize the path
            if (!canonicalPath.startsWith("/trusted/base/path")) {
                throw new SecurityException("Invalid path: " + canonicalPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
