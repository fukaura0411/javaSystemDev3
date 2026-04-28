//上地
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
        String name = request.getParameter("name");
        SubjectDao subjectDao = new SubjectDao();

        // GETの場合（初回表示）
        if (name == null) {
            Subject subject = subjectDao.get(cd);
            if (subject == null) {
                request.setAttribute("errorMessage", "科目が存在しません");
            }
            request.setAttribute("subject", subject);
            request.setAttribute("cd", cd);
            request.getRequestDispatcher("subject_update.jsp").forward(request, response);
            return;
        }

        // バリデーション
        Subject subject = subjectDao.get(cd);
        if (subject == null) {
            request.setAttribute("errorMessage", "科目が存在しません");
            request.setAttribute("cd", cd);
            request.setAttribute("name", name);
            request.getRequestDispatcher("subject_update.jsp").forward(request, response);
            return;
        }

        if (name.isEmpty()) {
			/*request.setAttribute("errorMessage", "科目名を入力してください");*/
            request.setAttribute("subject", subject);
            request.setAttribute("cd", cd);
            request.getRequestDispatcher("subject_update.jsp").forward(request, response);
            return;
        }

        // DB更新
        subject.setName(name);
        subjectDao.update(subject);

        request.getRequestDispatcher("subject_update_done.jsp").forward(request, response);
    }
}