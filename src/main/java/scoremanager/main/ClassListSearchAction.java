
package scoremanager.main;

import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassDao;
import dao.ClassNumDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class ClassListSearchAction extends Action {

    public void execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");


        School school = teacher.getSchool();

        // クラス番号リストを取得
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumList = classNumDao.filter(school);

//        // 学校の全生徒を取得（在学中のみ）
//        StudentDao studentDao = new StudentDao();
//        List<Student> studentList = studentDao.filter(school, true);

        
        // nullで初期化
        String p1 = null;
        String p2 = null;
        
        // 選択したクラスを受け取る
        p1 = request.getParameter("p1");
        
        // 全校生徒ver
        p2 = request.getParameter("p2");
        
        // 実体化
        ClassDao cDao = new ClassDao();
        
        // 全生徒を取得
        List<Student> StudentAllList = cDao.filter(school);
        
        // クラス指定で取得
        List<Student> StudentList = new ArrayList<>();
        if (p1 != null && !p1.isEmpty()) {
            StudentList = cDao.filter(school, p1);
        }
        
        
        request.setAttribute("school", school);
        request.setAttribute("classNumList", classNumList);
        request.setAttribute("StudentAllList", StudentAllList);
        request.setAttribute("StudentList", StudentList);
        request.setAttribute("p1", p1);
        request.setAttribute("p2", p2);
        
        
        request.getRequestDispatcher("classlistsearch.jsp").forward(request, response);
    }
}