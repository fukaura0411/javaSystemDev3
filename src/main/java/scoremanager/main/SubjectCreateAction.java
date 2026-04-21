package scoremanager.main;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class SubjectCreateAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        School school = teacher.getSchool();

        String cd = request.getParameter("cd");
        String name = request.getParameter("name");

        // GETの場合（初回表示）
        if (cd == null) {
            request.getRequestDispatcher("subject_create.jsp").forward(request, response);
            return;
        }

        // バリデーション
        if (cd.isEmpty() || name.isEmpty()) {
            request.setAttribute("errorMessage", "科目コードと科目名を入力してください");
            request.getRequestDispatcher("subject_create.jsp").forward(request, response);
            return;
        }

        // DB保存
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(school);

        SubjectDao subjectDao = new SubjectDao();
        subjectDao.save(subject);

        request.getRequestDispatcher("subject_create_done.jsp").forward(request, response);
    }
}