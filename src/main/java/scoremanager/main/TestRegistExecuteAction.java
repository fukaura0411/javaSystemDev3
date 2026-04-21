package scoremanager.main;

import bean.School;
import bean.Teacher;
import bean.Test;
import dao.TestDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class TestRegistExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("teacher");
        School school = teacher.getSchool();

        String[] studentNos = request.getParameterValues("studentNo");
        String[] points = request.getParameterValues("point");
        String subjectCd = request.getParameter("subjectCd");
        String classNum = request.getParameter("classNum");
        String noStr = request.getParameter("no");
        int no = Integer.parseInt(noStr);

        TestDao testDao = new TestDao();

        for (int i = 0; i < studentNos.length; i++) {
            Test test = new Test();
            test.setStudentNo(studentNos[i]);
            test.setSubjectCd(subjectCd);
            test.setSchoolCd(school.getCd());
            test.setNo(no);
            test.setClassNum(classNum);
            if (points[i] != null && !points[i].isEmpty()) {
                test.setPoint(Integer.parseInt(points[i]));
            }
            testDao.save(test);
        }

        request.getRequestDispatcher("test_regist_done.jsp").forward(request, response);
    }
}