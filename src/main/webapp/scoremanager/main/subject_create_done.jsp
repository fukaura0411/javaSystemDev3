<%--上地 --%>
<%-- 科目情報登録完了JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
            <div class="alert alert-success mx-4">登録が完了しました</div>
            <div class="px-4">
                <a href="SubjectCreate.action">戻る</a>
                &nbsp;
                <a href="SubjectList.action">科目一覧</a>
            </div>
        </section>
    </c:param>
</c:import>