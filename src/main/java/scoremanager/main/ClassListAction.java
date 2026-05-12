package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassListAction extends Action {

    public void execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");

        if (teacher == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        School school = teacher.getSchool();

        // クラス番号リストを取得
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumList = classNumDao.filter(school);

        // 学校の全生徒を取得（在学中のみ）
//        StudentDao studentDao = new StudentDao();
//        List<Student> studentList = studentDao.filter(school, true);

        request.setAttribute("school", school);
        request.setAttribute("classNumList", classNumList);
//        request.setAttribute("studentList", studentList);
        request.getRequestDispatcher("classlist.jsp").forward(request, response);
    }
}