//　清田ケンジ

package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class LogoutAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // セッションを破棄
        HttpSession session = request.getSession();
        session.invalidate();
        // ログアウト画面へフォワード
        request.getRequestDispatcher("/scoremanager/main/logout.jsp").forward(request, response);
    }
}