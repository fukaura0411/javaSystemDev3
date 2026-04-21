<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts">
        <script>
            $(function() {
                // パスワード表示切替機能
                $('#chk_d_ps').on('change', function() {
                    if ($(this).is(':checked')) {
                        $('#password').attr('type', 'text');
                    } else {
                        $('#password').attr('type', 'password');
                    }
                });
            });
        </script>
    </c:param>

    <c:param name="content">

        <section class="mx-auto col-md-6 border p-4 mt-5 bg-white shadow-sm">
            
            <%-- 画面タイトル --%>
            <h2 class="h3 mb-4 fw-normal bg-secondary bg-opacity-10 py-2 px-4 text-center">ログイン</h2>
            
            <form method="post" action="LoginExecute.action">
                
                <%-- 認証エラーメッセージ表示 (赤色を廃止し、画像に合わせた灰色に修正) --%>
                <c:if test="${!empty errors}">
                    <div class="mb-3 small">
                        <ul class="list-unstyled text-center" style="margin-bottom: 0; color: #666;">
                            <c:forEach var="error" items="${errors}">
                                <li>・${error.value}</li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>
			
                <%-- ID入力欄 --%>
                <div class="mb-3">
                    <label class="form-label" for="id">ID</label>
                    <input class="form-control" type="text" id="id" name="id" 
                           value="${id}" required maxlength="10" 
                           placeholder="半角でご入力ください" />
                </div>
                
                <%-- パスワード入力欄 --%>
                <div class="mb-3">
                    <label class="form-label" for="password">パスワード</label>
                    <input class="form-control" type="password" id="password" name="password" 
                           required maxlength="30" 
                           placeholder="30文字以内の半角英数でご入力ください" />
                </div>
                
                <%-- パスワード表示チェックボックス --%>
                <div class="mb-3 text-center">
                    <input type="checkbox" id="chk_d_ps" name="chk_d_ps" />
                    <label for="chk_d_ps">パスワードを表示</label>
                </div>

                <%-- ログインボタン --%>
                <div class="text-center">
                    <button class="btn btn-primary px-5" type="submit" name="login" value="ログイン">ログイン</button>
                </div>
            </form>
        </section>
    </c:param>
</c:import>