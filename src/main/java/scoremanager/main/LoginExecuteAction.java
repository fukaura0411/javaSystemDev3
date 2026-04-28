//　清田ケンジ

package scoremanager.main;

import java.util.HashMap;

import bean.Teacher;
import dao.TeacherDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LoginExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        TeacherDao tDao = new TeacherDao();
        Teacher teacher = tDao.login(id, password);

        HashMap<String, String> errors = new HashMap<>();

        if (teacher == null) {
            // 認証失敗
            errors.put("login", "ログインに失敗しました。IDまたはパスワードが正しくありません。");
            request.setAttribute("errors", errors);
            request.setAttribute("id", id);
            request.getRequestDispatcher("/scoremanager/main//login.jsp").forward(request, response);
            return;
        }

        // 認証成功 セッションにユーザーデータを格納
        HttpSession session = request.getSession();
        session.setAttribute("teacher", teacher);

        // メニュー画面へリダイレクト
        response.sendRedirect(request.getContextPath() + "/scoremanager/main/Menu.action");
    }
}