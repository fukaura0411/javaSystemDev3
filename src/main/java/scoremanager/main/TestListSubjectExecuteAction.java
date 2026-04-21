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

        // リクエストパラメータ取得
        String f1 = request.getParameter("f1");
        String f2 = request.getParameter("f2");
        String f3 = request.getParameter("f3");

        // バリデーション：全て未選択の場合のみエラー
        if ((f1 == null || f1.isEmpty()) && (f2 == null || f2.isEmpty()) && (f3 == null || f3.isEmpty())) {
            request.setAttribute("message", "入学年度・クラス・科目のいずれかを選択してください");
            request.getRequestDispatcher("/scoremanager/main/test_list.jsp").forward(request, response);
            return;
        }

        // 入学年度はIntegerに変換（未選択はnull）
        Integer entYear = (f1 != null && !f1.isEmpty()) ? Integer.parseInt(f1) : null;

        // 成績データ取得
        TestDao tDao = new TestDao();
        List<Test> testList = tDao.filterBySubject(school.getCd(), entYear, f2, f3);

        // 0件の場合
        if (testList.isEmpty()) {
            request.setAttribute("message", "該当する成績情報が存在しませんでした");
            request.getRequestDispatcher("/scoremanager/main/test_list.jsp").forward(request, response);
            return;
        }

        request.setAttribute("testList", testList);
        request.getRequestDispatcher("/scoremanager/main/test_list_subject.jsp").forward(request, response);
    }
}