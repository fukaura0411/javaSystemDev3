package scoremanager.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListStudentExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // セッションからユーザー情報取得
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        School school = teacher.getSchool();

        // ドロップダウン用データを再取得
        ClassNumDao cnDao = new ClassNumDao();
        List<String> classList = cnDao.filter(school);

        SubjectDao sDao = new SubjectDao();
        List<Subject> subjectList = sDao.filter(school);

        List<Integer> yearList = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear; i >= currentYear - 10; i--) {
            yearList.add(i);
        }

        request.setAttribute("classList", classList);
        request.setAttribute("subjectList", subjectList);
        request.setAttribute("yearList", yearList);

        // 学生番号を取得
        String f4 = request.getParameter("f4");
        request.setAttribute("f4", f4);
        request.setAttribute("st", "st");

        // バリデーション：空チェック
        if (f4 == null || f4.trim().isEmpty()) {
            request.setAttribute("message", "学生番号を入力してください");
            request.getRequestDispatcher("/scoremanager/main/test_list.jsp").forward(request, response);
            return;
        }

        // 学生情報を検索
        StudentDao stDao = new StudentDao();
        Student student = stDao.get(f4);

        // 学生が存在しない場合
        if (student == null) {
            request.setAttribute("message", "該当する学生が見つかりませんでした");
            request.getRequestDispatcher("/scoremanager/main/test_list.jsp").forward(request, response);
            return;
        }

        // 成績一覧を取得
        TestDao tDao = new TestDao();
        List<Test> testList = tDao.filterByStudent(f4);

        request.setAttribute("student", student);
        request.setAttribute("testList", testList);
        request.getRequestDispatcher("/scoremanager/main/test_list_student.jsp").forward(request, response);
    }
}