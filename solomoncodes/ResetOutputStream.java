import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class MyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().write("Hello, World!");
 
        // Ensure no further modifications to the response after committing it
        // No further reset or modifications to the output stream are allowed after this point
    }
}
