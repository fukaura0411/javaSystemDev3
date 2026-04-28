//上地
package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String cd = request.getParameter("cd");
        SubjectDao subjectDao = new SubjectDao();

        // GETの場合（確認画面表示）
        if (request.getMethod().equals("GET")) {
            Subject subject = subjectDao.get(cd);
            request.setAttribute("subject", subject);
            request.getRequestDispatcher("subject_delete.jsp").forward(request, response);
            return;
        }

        // DB削除
        subjectDao.delete(cd);
        request.getRequestDispatcher("subject_delete_done.jsp").forward(request, response);
    }
}