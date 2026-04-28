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
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報更新</h2>
            <div class="alert alert-success mt-3" role="alert" style="background-color: #d1e7dd; border-color: #badbcc; color: #0f5132;">
                変更が完了しました
            </div>
            <div class="mt-4">
                <a href="StudentList.action">学生一覧</a>
            </div>
        </section>
    </c:param>
</c:import>
 