package scoremanager.main;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tool.Action;

public class StudentCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Teacher teacher = (Teacher)session.getAttribute("teacher");
		Student student = new Student();
		student.setEntYear((int) Integer.parseInt(request.getParameter("ent_year")));
		student.setNo((String) request.getParameter("no"));
		student.setName((String) request.getParameter("name"));
		student.setClassNum((String) request.getParameter("class_num"));
		student.setAttend(true);
		student.setSchool(teacher.getSchool());
		
		StudentDao sDao = new StudentDao();
		boolean flg = sDao.insert(student);
		
		request.setAttribute("flg", flg);
		request.getRequestDispatcher("student_create_done.jsp").forward(request, response);
	}
}