<%-- システムエラーJSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
 
<c:import url="/common/base.jsp">
    <c:param name="title">
        エラーページ
    </c:param>
 
    <%-- メニューバーを非表示にする --%>
    <c:param name="show_nav">false</c:param>
 
    <c:param name="scripts"></c:param>
 
    <c:param name="content">
        <%-- 中央寄せでメッセージを表示 --%>
        <div class="mt-5 text-center">
            <p class="small" style="color: #666;">エラーが発生しました</p>
        </div>
    </c:param>
</c:import>