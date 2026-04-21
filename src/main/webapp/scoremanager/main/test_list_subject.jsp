<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title" value="成績一覧（科目）" />
    <c:param name="content">

        <h2>成績一覧（科目）</h2>

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
                                <option value="${year}" ${year == f1 ? 'selected' : ''}>${year}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="me-2">
                        <label>クラス</label><br />
                        <select name="f2">
                            <option value="">--------</option>
                            <c:forEach var="cls" items="${classList}">
                                <option value="${cls}" ${cls == f2 ? 'selected' : ''}>${cls}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="me-2">
                        <label>科目</label><br />
                        <select name="f3">
                            <option value="">--------</option>
                            <c:forEach var="subject" items="${subjectList}">
                                <option value="${subject.cd}" ${subject.cd == f3 ? 'selected' : ''}>${subject.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div>
                        <button type="submit" name="event" value="31" class="btn btn-secondary">検索</button>
                    </div>

                </div>
                <input type="hidden" name="f" value="sj" />
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
                        <input type="text" name="f4" value="${f4}" placeholder="学生番号を入力してください" maxlength="10" required />
                    </div>

                    <div>
                        <button type="submit" name="event" value="32" class="btn btn-secondary">検索</button>
                    </div>

                </div>
                <input type="hidden" name="f" value="st" />
            </form>

        </div>

        <%-- 成績一覧テーブル --%>
        <div class="border p-3 mt-3">
            <table class="table">
                <thead>
                    <tr>
                        <th>学生番号</th>
                        <th>氏名</th>
                        <th>クラス</th>
                        <th>科目名</th>
                        <th>科目コード</th>
                        <th>回数</th>
                        <th>点数</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="test" items="${testList}">
                        <tr>
                            <td>${test.studentNo}</td>
                            <td>${test.studentName}</td>
                            <td>${test.classNum}</td>
                            <td>${test.subjectName}</td>
                            <td>${test.subjectCd}</td>
                            <td>${test.no}</td>
                            <td>${test.point}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </c:param>
</c:import>