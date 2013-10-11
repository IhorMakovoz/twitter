package garbage;

import com.sun.net.httpserver.HttpServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        request.getRequestDispatcher("/WEB-INF//pages/registration-form-success.jsp").include(request, response);
    }
}

/*public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Registration registration = new Registration(request.getParameter("login"),
                                                     request.getParameter("password"));
        RegistrationTransaction rt = new RegistrationTransaction();

        try {
            rt.addRegistration(registration);
          //  RegistrationConsoleWriter.print(rt.addRegistration(registration));
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF//pages/registration-form-success.jsp").include(request, response);
    }
}*/
