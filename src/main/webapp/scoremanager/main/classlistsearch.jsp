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

        <c:choose>
            <c:when test="${not empty p1}">
                <%-- 検索結果件数 --%>
                <p>検索結果：${StudentList.size()}件</p>

                <%-- 絞り込み --%>
                <h2>生徒一覧</h2>
                <table style="width: 100%; border-collapse: collapse;">
                    <tr style="border-bottom: 2px solid #ccc;">
                        <th style="padding: 10px; text-align: left; font-weight: normal; color: #333;">学生番号</th>
                        <th style="padding: 10px; text-align: left; font-weight: normal; color: #333;">氏名</th>
                        <th style="padding: 10px; text-align: left; font-weight: normal; color: #333;">クラス</th>
                    </tr>
                    <c:forEach var="Student" items="${StudentList}">
                        <tr style="border-bottom: 1px solid #eee;">
                            <td style="padding: 10px;">${Student.no}</td>
                            <td style="padding: 10px;">${Student.name}</td>
                            <td style="padding: 10px;">${Student.classNum}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>

            <c:when test="${not empty p2}">
                <%-- 検索結果件数 --%>
                <p>検索結果：${StudentAllList.size()}件</p>

                <%-- 全校生徒 --%>
                <h2>生徒一覧</h2>
                <table style="width: 100%; border-collapse: collapse;">
                    <tr style="border-bottom: 2px solid #ccc;">
                        <th style="padding: 10px; text-align: left; font-weight: normal; color: #333;">学生番号</th>
                        <th style="padding: 10px; text-align: left; font-weight: normal; color: #333;">氏名</th>
                        <th style="padding: 10px; text-align: left; font-weight: normal; color: #333;">クラス</th>
                    </tr>
                    <c:forEach var="StudentAll" items="${StudentAllList}">
                        <tr style="border-bottom: 1px solid #eee;">
                            <td style="padding: 10px;">${StudentAll.no}</td>
                            <td style="padding: 10px;">${StudentAll.name}</td>
                            <td style="padding: 10px;">${StudentAll.classNum}</td>
                        </tr>
                    </c:forEach>
                </table>
            </c:when>

            <c:otherwise>
                <p>クラスを選択してください</p>
            </c:otherwise>
        </c:choose>

    </c:param>
</c:import>