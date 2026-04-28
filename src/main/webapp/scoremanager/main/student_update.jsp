<%--澤村 --%>
<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>
 
    <c:param name="scripts"></c:param>
 
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報更新</h2>
            
            <form method="get" action="StudentUpdateExecute.action">
            	<div class="col-4"
		            <label class="form-label">入学年度</label>
		           	<p><input type="text" name="ent_year" value="&emsp;${student.entYear }" readonly style="border:none; outline:none;"></p>
		        </div>
		        <div class="col-4">
		            <label class="form-label">学生番号</label>
		            <p><input type="text" name="no" value="&emsp;${student.no }" readonly style="border:none; outline:none;"></p>
		        </div>
		        <label class="form-label">氏名</label>
		            <p><input type="text" name="name" value="${student.name }" maxlength="30" required class="form-control"></p>
		        <label class="form-label">クラス</label>
		        	<select name="class_num" class="form-select">
		        		<c:forEach var="num" items="${class_num_set}">
						    <option value="${num}" ${fn:trim(student.classNum) == fn:trim(num) ? 'selected' : ''}>
						        ${num}
						    </option>
						</c:forEach>
		        	</select>
		        <label class="form-label">在学中
		        	<c:choose>
		        		<c:when test="${student.attend == true }">
		        			<input type="checkbox" name="is_attend" value="${student.attend }" checked>
		        		</c:when>
		        		<c:otherwise>
		        			<input type="checkbox" name="is_attend" value="${student.attend }">
		        		</c:otherwise>
		           	</c:choose>
		        </label>
		        <br>
		        <button class="btn btn-primary" type="submit">変更</button>
		        <p><a href="StudentList.action">戻る</a></p>
	            
	        </form>
        </section>
    </c:param>
</c:import>
 