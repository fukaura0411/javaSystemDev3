//上地
package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
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

public class TestRegistAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        School school = teacher.getSchool();

        // 年度リスト
        int year = LocalDate.now().getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i < year + 1; i++) {
            entYearSet.add(i);
        }

        // クラスリスト
        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNumSet = classNumDao.filter(school);

        // 科目リスト
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectSet = subjectDao.filter(school);

        // 回数リスト（1〜2）
        List<Integer> noSet = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            noSet.add(i);
        }

        // パラメーター取得
        String entYearStr = request.getParameter("entYear");
        String classNum = request.getParameter("classNum");
        String subjectCd = request.getParameter("subjectCd");
        String noStr = request.getParameter("no");

        List<Test> tests = null;
        Subject subject = null;

        // 検索処理
        if (entYearStr != null && classNum != null && subjectCd != null && noStr != null
            && !entYearStr.equals("0") && !classNum.equals("0")
            && !subjectCd.equals("0") && !noStr.equals("0")) {

            int entYear = Integer.parseInt(entYearStr);
            int no = Integer.parseInt(noStr);

            // 学生一覧取得
            StudentDao studentDao = new StudentDao();
            List<Student> students = studentDao.filter(school, entYear, classNum, false);

            // テスト一覧取得
            TestDao testDao = new TestDao();
            List<Test> existingTests = testDao.filter(school.getCd(), entYear, classNum, subjectCd, no);

            // 学生ごとにtestを作成
            tests = new ArrayList<>();
            for (Student s : students) {
                Test test = new Test();
                test.setStudentNo(s.getNo());
                test.setSubjectCd(subjectCd);
                test.setSchoolCd(school.getCd());
                test.setNo(no);
                test.setClassNum(classNum);

                // 既存の点数があればセット
                for (Test existing : existingTests) {
                    if (existing.getStudentNo().equals(s.getNo())) {
                        test.setPoint(existing.getPoint());
                        break;
                    }
                }
                tests.add(test);
            }

            // 科目名取得
            for (Subject sub : subjectSet) {
                if (sub.getCd().equals(subjectCd)) {
                    subject = sub;
                    break;
                }
            }

            request.setAttribute("students", students);
            request.setAttribute("tests", tests);
            request.setAttribute("subject", subject);
            request.setAttribute("entYear", entYearStr);
            request.setAttribute("classNum", classNum);
            request.setAttribute("subjectCd", subjectCd);
            request.setAttribute("no", noStr);
        }

        request.setAttribute("entYearSet", entYearSet);
        request.setAttribute("classNumSet", classNumSet);
        request.setAttribute("subjectSet", subjectSet);
        request.setAttribute("noSet", noSet);
        request.setAttribute("entYear", entYearStr);
        request.setAttribute("classNum", classNum);
        request.setAttribute("subjectCd", subjectCd);
        request.setAttribute("no", noStr);

        // 修正：絶対パスに変更
        request.getRequestDispatcher("/scoremanager/main/test_regist.jsp").forward(request, response);
    }
}