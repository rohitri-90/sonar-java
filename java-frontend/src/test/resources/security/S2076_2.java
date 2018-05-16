import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class S2076_2 extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public static String getTheParameter(HttpServletRequest request, String ... parameters) {
    for (String p : parameters) {
      String result = request.getParameter(p);
      if (result != null) {
        return p;
      }
    }
    return request.getParameter("default");
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doPost(request, response);
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");

    String param = getTheParameter(request, "command", "altCommand");
    if (param == null) {
      param = "rm file.tmp";
    }

    // executing command from request without verification
    Process process = Runtime.getRuntime().exec(param); // Noncompliant

    if (process.exitValue() != 0) {
      throw new ServletException(String.format("unable to run command %s.", param));
    }
  }
}