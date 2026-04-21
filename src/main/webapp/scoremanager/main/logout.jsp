<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:import url="/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="mx-auto col-md-12 mt-4">
           
            <h2 class="h3 mb-1 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                ログアウト
            </h2>
            
            <p class="alert alert-success py-1 mb-1 rounded-0 text-center" 
               style="background-color:#8bc98b; border:none; color:white;">
                ログアウトしました
            </p>
            
      
            <div class="mx-4 mt-2">
                <a href="${pageContext.request.contextPath}/scoremanager/main/Login.action">
                    ログイン
                </a>
            </div>
            
        </section>
    </c:param>
</c:import>