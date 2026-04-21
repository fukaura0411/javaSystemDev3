<%-- 成績管理JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>

            <%-- 検索フォーム --%>
            <form method="get" class="px-4 mb-3">
                <div class="row align-items-end">
                    <div class="col-2">
                        <label class="form-label">入学年度</label>
                        <select class="form-select" name="entYear">
                            <option value="0">---------</option>
                            <c:forEach var="y" items="${entYearSet}">
                                <option value="${y}" <c:if test="${y==entYear}">selected</c:if>>${y}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2">
                        <label class="form-label">クラス</label>
                        <select class="form-select" name="classNum">
                            <option value="0">---------</option>
                            <c:forEach var="c" items="${classNumSet}">
                                <option value="${c}" <c:if test="${c==classNum}">selected</c:if>>${c}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-3">
                        <label class="form-label">科目</label>
                        <select class="form-select" name="subjectCd">
                            <option value="0">---------</option>
                            <c:forEach var="sub" items="${subjectSet}">
                                <option value="${sub.cd}" <c:if test="${sub.cd==subjectCd}">selected</c:if>>${sub.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-2">
                        <label class="form-label">回数</label>
                        <select class="form-select" name="no">
                            <option value="0">---------</option>
                            <c:forEach var="n" items="${noSet}">
                                <option value="${n}" <c:if test="${n==no}">selected</c:if>>${n}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-1">
                        <button class="btn btn-secondary" type="submit">検索</button>
                    </div>
                </div>
            </form>

            <%-- 検索結果 --%>
            <c:if test="${tests != null}">
                <div class="px-4">科目：${subject.name}（${no}回）</div>
                <form method="post" action="TestRegistExecute.action" class="px-4">
                    <input type="hidden" name="subjectCd" value="${subjectCd}">
                    <input type="hidden" name="classNum" value="${classNum}">
                    <input type="hidden" name="no" value="${no}">
                    <table class="table table-hover mt-2">
                        <tr>
                            <th>入学年度</th>
                            <th>クラス</th>
                            <th>学生番号</th>
                            <th>氏名</th>
                            <th>点数</th>
                        </tr>
                        <c:forEach var="test" items="${tests}" varStatus="status">
                            <tr>
                                <td>${entYear}</td>
                                <td>${classNum}</td>
                                <td>${test.studentNo}</td>
                                <td>${students[status.index].name}</td>
                                <td>
                                    <input type="hidden" name="studentNo" value="${test.studentNo}">
                                    <input class="form-control" type="number" name="point"
                                        value="${test.point}" min="0" max="100">
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <button class="btn btn-primary" type="submit">登録して終了</button>
                </form>
            </c:if>
        </section>
    </c:param>
</c:import>