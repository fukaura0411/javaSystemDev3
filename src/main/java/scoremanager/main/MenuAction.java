//深浦

package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class MenuAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();

        if (session.getAttribute("teacher") == null) {
            response.sendRedirect(request.getContextPath() + "/scoremanager/main/Login.action");
            return;
        }

        request.getRequestDispatcher("/scoremanager/main/menu.jsp")
               .forward(request, response);
    }
}