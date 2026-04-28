//深浦
package scoremanager.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        School school = teacher.getSchool();

        // ▼ 初期データ
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

        // ▼ パラメータ取得
        String f1 = request.getParameter("f1");
        String f2 = request.getParameter("f2");
        String f3 = request.getParameter("f3");

        request.setAttribute("f1", f1);
        request.setAttribute("f2", f2);
        request.setAttribute("f3", f3);

        // ▼ 未入力チェック
        if ((f1 == null || f1.isEmpty()) ||
            (f2 == null || f2.isEmpty()) ||
            (f3 == null || f3.isEmpty())) {

            request.setAttribute("message", "入学年度とクラスと科目を選択してください");
            request.getRequestDispatcher("/scoremanager/main/test_list.jsp").forward(request, response);
            return;
        }

        Integer entYear = Integer.parseInt(f1);

        // データ取得
        TestDao tDao = new TestDao();
        List<Test> testList = tDao.filterBySubject(school.getCd(), entYear, f2, f3);

        // ここが修正ポイント
        request.setAttribute("searched", true);
        request.setAttribute("testList", testList);

        // データがあるときだけ科目名セット
        if (!testList.isEmpty()) {
            request.setAttribute("subjectName", testList.get(0).getSubjectName());
        }

        // 常に同じ画面へ
        request.getRequestDispatcher("test_list_subject.jsp").forward(request, response);
    }
}