package scoremanager.main;

import bean.Subject;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectUpdateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String cd = request.getParameter("cd");
        SubjectDao subjectDao = new SubjectDao();

        // DBから科目を取得して編集画面に渡す
        Subject subject = subjectDao.get(cd);
        if (subject == null) {
            request.setAttribute("errorMessage", "科目が存在しません");
        }
        request.setAttribute("subject", subject);
        request.setAttribute("cd", cd);
        request.getRequestDispatcher("subject_update.jsp").forward(request, response);
    }
}