//澤村
package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

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
		String entYearStr = request.getParameter("ent_year");
		int entYear = Integer.parseInt(entYearStr);
		String no = request.getParameter("no");
		Map<String, String> errors = new HashMap<>();
		
		student.setEntYear(entYear);
		student.setNo((String) request.getParameter("no"));
		student.setName((String) request.getParameter("name"));
		student.setClassNum((String) request.getParameter("class_num"));
		student.setAttend(true);
		student.setSchool(teacher.getSchool());
		
		if (entYear == 0) {
		    // 選択が「---------」（値が0）の場合にエラーメッセージをセット
		    errors.put("ent_year", "入学年度を選択してください");
		}

		if (!errors.isEmpty()) {
		    // エラーがある場合は入力画面に戻す
			request.setAttribute("student", student);
		    request.setAttribute("errors", errors);
		    request.getRequestDispatcher("StudentCreate.action").forward(request, response);
		    return;
		}
		
		StudentDao sDao = new StudentDao();
		boolean flg = sDao.insert(student);
		
		if (flg == false) {
		    // 選択が「---------」（値が0）の場合にエラーメッセージをセット
		    errors.put("no", "学生番号が重複しています");
		}

		if (!errors.isEmpty()) {
		    // エラーがある場合は入力画面に戻す
			
			request.setAttribute("student", student);
		    request.setAttribute("errors", errors);
		    request.getRequestDispatcher("StudentCreate.action").forward(request, response);
		    return;
		}
		
		request.getRequestDispatcher("student_create_done.jsp").forward(request, response);
	}
}