<%-- 深浦 --%>

<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">
    <c:param name="title" value="クラス管理" />
    <c:param name="content">

        <%-- 画面タイトル --%>
        <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">クラス管理</h2>

        <%-- クラス絞り込みフォーム --%>
        <form action="ClassListSearch.action" method="get">
            <select name="p1">
                <option value="">-----</option>
                <c:forEach var="classNum" items="${classNumList}">
                    <option value="${classNum}"
                        <c:if test="${classNum == selectedClass}">selected</c:if>>
                        ${classNum}組
                    </option>
                </c:forEach>
            </select>
            <button type="submit">絞り込み</button>
        </form>

        <%-- 全校生徒表示 --%>
        <form action="ClassListSearch.action" method="get">
            <button type="submit" name="p2" value="all">全校生徒を表示</button>
        </form>

    </c:param>
</c:import>