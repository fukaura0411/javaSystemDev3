<%--深浦 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title" value="成績一覧（科目）" />
    <c:param name="content">

        <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
            成績一覧（科目）
        </h2>

        <div class="border p-3">

            <%-- 科目情報フォーム --%>
            <form action="${pageContext.request.contextPath}/scoremanager/main/TestListSubjectExecute.action" method="post">
                <div class="d-flex align-items-end mb-2">
                    <div class="me-3">
                        <p class="mb-0">科目情報</p>
                    </div>

                    <div class="me-2">
                        <label>入学年度</label><br />
                        <select name="f1">
                            <option value="">--------</option>
                            <c:forEach var="year" items="${yearList}">
                                <option value="${year}" ${year == f1 ? 'selected' : ''}>
                                    ${year}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="me-2">
                        <label>クラス</label><br />
                        <select name="f2">
                            <option value="">--------</option>
                            <c:forEach var="cls" items="${classList}">
                                <option value="${cls}" ${cls == f2 ? 'selected' : ''}>
                                    ${cls}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="me-2">
                        <label>科目</label><br />
                        <select name="f3">
                            <option value="">--------</option>
                            <c:forEach var="subject" items="${subjectList}">
                                <option value="${subject.cd}" ${subject.cd == f3 ? 'selected' : ''}>
                                    ${subject.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div>
                        <button type="submit" class="btn btn-secondary">検索</button>
                    </div>
                </div>
            </form>

            <hr class="text-muted" />

            <%-- 学生情報フォーム --%>
            <form action="${pageContext.request.contextPath}/scoremanager/main/TestListStudentExecute.action" method="post">
                <div class="d-flex align-items-end mb-2">
                    <div class="me-3">
                        <p class="mb-0">学生情報</p>
                    </div>

                    <div class="me-2">
                        <label>学生番号</label><br />
                        <input type="text" name="f4" value="${f4}"
                               placeholder="学生番号を入力してください" maxlength="10" />
                    </div>

                    <div>
                        <button type="submit" class="btn btn-secondary">検索</button>
                    </div>
                </div>
            </form>
        </div>

        <%-- 成績一覧表示 --%>
        <c:if test="${searched}">
            <div class="mt-3">

                <%-- データなし --%>
                <c:if test="${empty testList}">
                    <p>学生情報が存在しませんでした。</p>
                </c:if>

                <%-- データあり --%>
                <c:if test="${not empty testList}">
                    <p>科目：${subjectName}</p>

                    <table class="table border">
                        <thead>
                            <tr>
                                <th>入学年度</th>
                                <th>クラス</th>
                                <th>学生番号</th>
                                <th>氏名</th>
                                <th>1回</th>
                                <th>2回</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="test" items="${testList}">
                                <c:if test="${test.no == 1}">
                                    <tr>
                                        <td>${test.entYear}</td>
                                        <td>${test.classNum}</td>
                                        <td>${test.studentNo}</td>
                                        <td>${test.studentName}</td>
                                        <td>${test.point}</td>
                                        <td>
                                            <c:set var="point2" value="-" />
                                            <c:forEach var="t2" items="${testList}">
                                                <c:if test="${t2.studentNo == test.studentNo && t2.no == 2}">
                                                    <c:set var="point2" value="${t2.point}" />
                                                </c:if>
                                            </c:forEach>
                                            ${point2}
                                        </td>
                                    </tr>
                                </c:if>
                            </c:forEach>
                        </tbody>
                    </table>

                </c:if>
            </div>
        </c:if>

    </c:param>
</c:import>