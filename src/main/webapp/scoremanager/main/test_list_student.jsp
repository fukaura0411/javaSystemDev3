<%--深浦 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title" value="成績一覧（学生）" />
    <c:param name="content">

        <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
            成績一覧（学生）
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
                                <option value="${year}">
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
                                <option value="${cls}">
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
                                <option value="${subject.cd}">
                                    ${subject.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div>
                        <button type="submit" name="event" value="31" class="btn btn-secondary">
                            検索
                        </button>
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
                        <input
                            type="text"
                            name="f4"
                            value="${f4}"
                            placeholder="学生番号を入力してください"
                            maxlength="10"
                            required
                        />
                    </div>

                    <div>
                        <button type="submit" name="event" value="32" class="btn btn-secondary">
                            検索
                        </button>
                    </div>

                </div>
                <input type="hidden" name="f" value="st" />
            </form>

        </div>

        <%-- 氏名表示・成績一覧 --%>
        <c:choose>

            <c:when test="${student == null}">
                <p>成績情報が存在しませんでした</p>
            </c:when>
            
            <c:when test="${empty testList}">
            	<p>氏名：${student.name}（${student.no}）<br>
                成績情報が存在しませんでした</p>
            </c:when>

            <c:otherwise>

                <%-- 氏名表示 --%>
                <div class="mt-3">
                    氏名：${student.name}（${student.no}）
                </div>

                <%-- 成績一覧テーブル --%>
                <div class="border p-3 mt-2">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>科目名</th>
                                <th>科目コード</th>
                                <th>回数</th>
                                <th>点数</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="test" items="${testList}">
                                <tr>
                                    <td>${test.subjectName}</td>
                                    <td>${test.subjectCd}</td>
                                    <td>${test.no}</td>
                                    <td>${test.point}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>

            </c:otherwise>

        </c:choose>

    </c:param>
</c:import>