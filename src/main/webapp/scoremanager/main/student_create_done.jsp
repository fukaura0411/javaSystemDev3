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
            <c:choose>
            	<c:when test="${flg == true }">
		            <div class="alert alert-success mt-3" role="alert" style="background-color: #d1e7dd; border-color: #badbcc; color: #0f5132;">
		                登録が完了しました
		            </div>
	            </c:when>
	            <c:otherwise>
	            	<div class="alert alert-danger mt-3" role="alert" style="background-color: #f8d7da; border-color: #f5c2c7; color: #842029;">
		                登録する学生番号はすでに存在しています
		            </div>
	            </c:otherwise>
	        </c:choose>
            <div class="mt-4">
                <p><a href="StudentList.action">学生一覧</a>&emsp;&emsp;&emsp;&emsp;
                <a href="StudentCreate.action">戻る</a>
                </p>
            </div>
        </section>
    </c:param>
</c:import>
 