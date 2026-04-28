//　みんな

package tool;

import java.io.IOException;

import bean.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "*.action" })
public class FrontController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            // パスを取得
            String path = req.getServletPath().substring(1);
            // ファイル名をクラス名に変換 (例: scoremanager/main/Login.action -> scoremanager.main.LoginAction)
            String name = path.replace(".action", "Action").replace('/', '.');

            System.out.println("* servlet path -> " + req.getServletPath());
            System.out.println("* class name -> " + name);

            // ログインチェック不要なAction
            boolean isLoginAction = name.equals("scoremanager.main.LoginAction")
                    || name.equals("scoremanager.main.LoginExecuteAction");

            if (!isLoginAction) {
                // セッションチェック
                HttpSession session = req.getSession(false);
                Teacher teacher = (session != null) ? (Teacher) session.getAttribute("teacher") : null;

                if (teacher == null) {
                    // 未ログインの場合はログイン画面へ
                    res.sendRedirect(req.getContextPath() + "/scoremanager/main/Login.action");
                    return;  // 修正：__return__ → return;
                }
            }

            // アクションクラスのインスタンスを動的に生成
            Action action = (Action) Class.forName(name).getDeclaredConstructor().newInstance();

            // 実行
            action.execute(req, res);

        } catch (Exception e) {
            e.printStackTrace();
            // URLを強制的に "/common/error.jsp" に書き換えて移動させる
            	res.sendRedirect(req.getContextPath() + "/common/error.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}