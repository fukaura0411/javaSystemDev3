<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>
 
    <c:param name="scripts"></c:param>
 
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>
            
            <form method="get" action="StudentCreateExecute.action">
		        <label class="form-label">入学年度</label>
		        <select name="ent_year" class="form-control">
		       		<c:forEach var="year" items="${ent_year_set }">
		       			<option value="${year }">${year }</option>
		       		</c:forEach>
		       	</select>
		        <label class="form-label">学生番号</label>
	            <p><input type="text" name="no" placeholder="学生番号を入力して下さい" maxlength="10" required class="form-control"></p>
		        <label class="form-label">氏名</label>
		        <p><input type="text" name="name" placeholder="氏名を入力して下さい" maxlength="30" required class="form-control"></p>
		        <label class="form-label">クラス</label>
		        	<select name="class_num">
		        		<c:forEach var="num" items="${class_num_set }">
		        			<option value="${num }" class="form-control">${num }</option>
		        		</c:forEach>
		        	</select>
		        <p><input type="submit" value="登録して終了"></p>
		        <p><a href="StudentList.action">戻る</a></p>
	        </form>
        </section>
    </c:param>
</c:import>
 