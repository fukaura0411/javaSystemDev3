<%-- 科目情報登録JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
            <div class="text-danger px-4">${errorMessage}</div>
            <form method="post" class="px-4">
                <div class="mb-3">
                    <label class="form-label" for="cd">科目コード</label>
                    <input class="form-control" type="text" id="cd" name="cd"
                        placeholder="科目コードを入力してください" value="${cd}">
                </div>
                <div class="mb-3">
                    <label class="form-label" for="name">科目名</label>
                    <input class="form-control" type="text" id="name" name="name"
                        placeholder="科目名を入力してください" value="${name}">
                </div>
                <button class="btn btn-primary" type="submit">登録</button>
                <a href="SubjectList.action">戻る</a>
            </form>
        </section>
    </c:param>
</c:import>