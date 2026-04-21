<%-- 共通テンプレート --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
 
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
 
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
 
<title>${param.title}</title>
 
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
${param.scripts}
</head>
 
<body>
<div class="container">
 
	<header class="py-3 mb-4 border-bottom bg-primary bg-opacity-10 d-flex justify-content-between align-items-center">
    <c:import url="/common/header.jsp" />
</header>
	<div class="row justify-content-center">
 
		<c:choose>
 
			<%-- ログイン済み --%>
			<c:when test="${not empty sessionScope.teacher}">
 
				<nav class="col-3">
					<c:import url="/common/navigation.jsp" />
				</nav>
 
				<main class="col-9 border-start ps-3">
					${param.content}
				</main>
 
			</c:when>
 
			<%-- 未ログイン --%>

			<c:otherwise>
    		<%-- col-6 を col-12 に変更 --%>
    			<main class="col-12">
        		${param.content}
    			</main>
			</c:otherwise>
 
		</c:choose>
 
	</div>
 
	<footer class="mt-4 border-top pt-2 text-center">
		<c:import url="/common/footer.jsp" />
	</footer>
 
</div>
</body>
</html>
 