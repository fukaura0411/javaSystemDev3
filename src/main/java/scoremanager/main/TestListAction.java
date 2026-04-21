package scoremanager.main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestListAction extends Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        School school = teacher.getSchool();

        // クラスデータ取得
        ClassNumDao cnDao = new ClassNumDao();
        List<String> classList = cnDao.filter(school);

        // 科目データ取得
        SubjectDao sDao = new SubjectDao();
        List<Subject> subjectList = sDao.filter(school);

        // 入学年度リスト生成
        List<Integer> yearList = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = currentYear; i >= currentYear - 10; i--) {
            yearList.add(i);
        }

        // リクエストに設定
        request.setAttribute("classList", classList);
        request.setAttribute("subjectList", subjectList);
        request.setAttribute("yearList", yearList);

        // test_list.jspにフォワード
        request.getRequestDispatcher("test_list.jsp").forward(request, response);
    }
}