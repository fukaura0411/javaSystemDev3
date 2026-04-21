package scoremanager.main;

import bean.Student;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class StudentUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Student student = new Student();
		student.setName((String) request.getParameter("name"));
		student.setClassNum((String) request.getParameter("num"));
		String isAttendStr = request.getParameter("is_attend");
		boolean isAttend=false;
		if (isAttendStr != null) {
			isAttend = true;
		}
		student.setAttend(isAttend);
		student.setNo((String) request.getParameter("no"));
		StudentDao sDao = new StudentDao();
		sDao.save(student);
		
		request.getRequestDispatcher("student_update_done.jsp").forward(request, response);
	}
}