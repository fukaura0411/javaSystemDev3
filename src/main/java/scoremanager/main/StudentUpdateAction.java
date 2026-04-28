//澤村
package scoremanager.main;

import java.util.List;

import bean.Student;
import bean.Teacher;
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
    	Teacher teacher = (Teacher)session.getAttribute("teacher");
    	
		String studentNo = request.getParameter("no");
		StudentDao sDao = new StudentDao();
		Student student = new Student();
		student = sDao.get(studentNo);
		ClassNumDao cNDao = new ClassNumDao();
		List<String> list = cNDao.filter(teacher.getSchool());
		
		request.setAttribute("class_num_set", list);
		request.setAttribute("student", student);
		request.getRequestDispatcher("student_update.jsp").forward(request, response);
	}
}
