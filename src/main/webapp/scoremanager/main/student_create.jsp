<%--澤村 --%>
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
		        <div class="mb-3">
				    <label class="form-label">入学年度</label>
				    <select name="ent_year" class="form-select">
				        <%-- 初期値のvalueを0（または空文字）に設定 --%>
				        <option value="0" <c:if test="${ent_year == 0}">selected</c:if>>---------</option>
				        <c:forEach var="year" items="${ent_year_set}">
				            <option value="${year}" <c:if test="${year == ent_year}">selected</c:if>>${year}</option>
				        </c:forEach>
				    </select>
				
				    <%-- エラーがあれば表示 --%>
				    <c:if test="${not empty errors.ent_year}">
				        <div style="color: #ff9900; font-size: 0.85em; margin-top: 5px;">
				            ${errors.ent_year}
				        </div>
				    </c:if>
				</div>
				<div class="mb-3">
		        	<label class="form-label">学生番号</label>
	            	<p><input type="text" name="no" placeholder="学生番号を入力して下さい" maxlength="10" required class="form-control" value="${student.no }"></p>
	            	<%-- 重複エラーの表示 --%>
				    <c:if test="${not empty errors.no}">
				        <div style="color: #ff9900; font-size: 0.85em; margin-top: 5px;">
				            ${errors.no}
				        </div>
				    </c:if>
				</div>
		        <label class="form-label">氏名</label>
		        <p><input type="text" name="name" placeholder="氏名を入力して下さい" maxlength="30" required class="form-control" value="${student.name }"></p>
		        <label class="form-label">クラス</label>
		        	<select name="class_num" class="form-select">
		        		<c:forEach var="num" items="${class_num_set }">
		        			<option value="${num }">${num }</option>
		        		</c:forEach>
		        	</select>
		        	<br>
		        <button type="submit" class="btn btn-secondary">登録して終了</button>
		        <p><a href="StudentList.action">戻る</a></p>
	        </form>
        </section>
    </c:param>
</c:import>
 