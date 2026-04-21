package scoremanager.main;
	 
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import bean.School;
import bean.Student;
import dao.ClassNumDao;
import dao.StudentDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;
	 
	public class StudentListAction extends Action {
	 
	    @Override
	    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	 
	    	School s = new School();
	    	s.setCd("oom");
	 
	        // リクエストパラメーターの取得
	        String entYearStr = request.getParameter("f1");
	        String classNum = request.getParameter("f2");
	        String isAttendStr = request.getParameter("f3");
	        int entYear = 0;
	        boolean isAttend = false;
	        List<Student> students = null;
	        LocalDate todaysDate = LocalDate.now();
	        int year = todaysDate.getYear();
	        ClassNumDao cNDao = new ClassNumDao();
	        StudentDao sDao = new StudentDao();
	        HashMap<String, String> errors = new HashMap<>();
	        
	        // ビジネスロジック
	        if (entYearStr != null) {
	            // 数値に変換
	            entYear = Integer.parseInt(entYearStr);
	        }
	        // リストを初期化
	        List<Integer> entYearSet = new ArrayList<>();
	        // 10年前から1年後まで年をリストに追加
	        for (int i = year - 10; i < year + 1; i++) {
	            entYearSet.add(i);
	        }
	 
	        // DBからデータ取得
	        // ログインユーザーの学校コードをもとにクラス番号の一覧を取得
	         List<String> list = cNDao.filter(s);
	        if (entYear != 0 && !classNum.equals("0")) {
	            // 入学年度とクラスを指定
	            students = sDao.filter(s, entYear, classNum, isAttend);
	        } else if (entYear != 0 && classNum.equals("0")) {
	            // 入学年度のみの場合
	            students = sDao.filter(s, entYear, isAttend);
	        } else if (entYear == 0 && classNum == null || entYear == 0 && classNum.equals("0")) {
	            // 指定なしの場合
	            // 学生情報を取得
	            students = sDao.filter(s, isAttend);
	        } else {
	            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
	            request.setAttribute("errors", errors);
	            // 学生情報を取得
	            students = sDao.filter(s, isAttend);
	        }
	 
	        // レスポンス値をセット
	        // リクエストに入学年度をセット
	        request.setAttribute("f1", entYearStr);
	        // リクエストにクラス番号をセット
	        request.setAttribute("f2", classNum);
	        // 在学フラグが指定されていない場合
	        if (isAttendStr == null) {
	            // 在学フラグをtrue
	            isAttend = true;
	        } else {
	            isAttend = Boolean.parseBoolean(isAttendStr);
	        }
	        request.setAttribute("f3", isAttendStr);
	 
	        // リクエストに学生リストをセット
	        request.setAttribute("students", students);
	        // リクエストにクラスデータをセット
	        request.setAttribute("class_num_set", list);
	        // リクエストに年度データをセット
	        request.setAttribute("ent_year_set", entYearSet);
	 
	        // JSPへフォワード
	        request.getRequestDispatcher("student_list.jsp").forward(request, response);
	    }
	}