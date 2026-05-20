<%-- 科目情報変更JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">

        <section class="me-4">

            <%-- ① タイトル --%>
            <h2 class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                科目情報変更
            </h2>

            <form method="post" action="SubjectUpdateExecute.action" class="px-4">

                <%-- cdをhiddenで送る --%>
                <input type="hidden" name="cd" value="${subject.cd}">

                <%-- ②③ 科目コード --%>
                <div class="mb-3">
                    <label class="form-label">科目コード</label><br>
                    <span>${subject.cd}</span>
                </div>

                <%-- ④⑤ 科目名 --%>
                <div class="mb-3">
                    <label class="form-label" for="name">科目名</label>
                    <input class="form-control" type="text" id="name" name="name"
                        value="${subject.name}" required>
                </div>

                <%-- ⑥⑦ ボタンと戻る --%>
                <div>
                    <button class="btn btn-primary mb-2" type="submit">
                        変更
                    </button><br>
                    <a href="SubjectList.action">戻る</a>
                </div>

            </form>

        </section>

    </c:param>
</c:import>
