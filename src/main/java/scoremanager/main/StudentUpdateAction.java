package scoremanager.main;

import java.util.List;

import bean.School;
import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String studentNo = request.getParameter("no");
		StudentDao sDao = new StudentDao();
		Student student = new Student();
		student = sDao.get(studentNo);
		ClassNumDao cNDao = new ClassNumDao();
		School school = new School();
		school.setCd("oom");
		List<String> list = cNDao.filter(school);
		
		request.setAttribute("class_num_set", list);
		request.setAttribute("student", student);
		request.getRequestDispatcher("student_update.jsp").forward(request, response);
	}
}
