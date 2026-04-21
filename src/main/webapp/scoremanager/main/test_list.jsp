<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title" value="成績参照" />
    <c:param name="content">

        <%-- 画面タイトル --%>
        <h2>成績参照</h2>

        <div class="border p-3">

            <%-- 科目情報行 --%>
            <form action="${pageContext.request.contextPath}/scoremanager/main/TestListSubjectExecute.action" method="post">
                <div class="d-flex align-items-end mb-2">

                    <%-- 科目情報 --%>
                    <div class="me-3">
                        <p class="mb-0">科目情報</p>
                    </div>

                    <%-- 入学年度 --%>
                    <div class="me-2">
                        <label>入学年度</label><br />
                        <select name="f1">
                            <option value="">--------</option>
                            <c:forEach var="year" items="${yearList}">
                                <option value="${year}">${year}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- クラス --%>
                    <div class="me-2">
                        <label>クラス</label><br />
                        <select name="f2">
                            <option value="">--------</option>
                            <c:forEach var="cls" items="${classList}">
                                <option value="${cls}">${cls}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- 科目 --%>
                    <div class="me-2">
                        <label>科目</label><br />
                        <select name="f3">
                            <option value="">--------</option>
                            <c:forEach var="subject" items="${subjectList}">
                                <option value="${subject.cd}">${subject.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <%-- 検索ボタン --%>
                    <div>
                        <button type="submit" name="event" value="31" class="btn btn-secondary">検索</button>
                    </div>

                </div>

                <%-- 科目情報識別コード --%>
                <input type="hidden" name="f" value="sj" />

            </form>

            <%-- 区切り線 --%>
            <hr class="text-muted" />

            <%-- 学生情報行 --%>
            <form action="${pageContext.request.contextPath}/scoremanager/main/TestListStudentExecute.action" method="post">
                <div class="d-flex align-items-end mb-2">

                    <%-- 学生情報ラベル --%>
                    <div class="me-3">
                        <p class="mb-0">学生情報</p>
                    </div>

                    <%-- 学生番号 --%>
                    <div class="me-2">
                        <label>学生番号</label><br />
                        <input type="text" name="f4" value="${f4}" placeholder="学生番号を入力してください" maxlength="10" required />
                    </div>

                    <%-- 検索ボタン --%>
                    <div>
                        <button type="submit" name="event" value="32" class="btn btn-secondary">検索</button>
                    </div>

                </div>

                <%-- 学生情報識別コード --%>
                <input type="hidden" name="f" value="st" />

            </form>
    
        </div>
        <%-- 利用方法メッセージ --%>
        <br>
        <p class="text-info">科目情報を選択または学生情報を入力して検索ボタンをクリックしてください</p>
        
    </c:param>
</c:import>
