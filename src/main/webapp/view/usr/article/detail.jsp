<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="상세"/>

<%@ include file="/view/usr/common/header.jsp" %>

<script>
$(document).ready(function() {
	
	$('#likeBtn').click(function(){
		const articleId = '${articleId}';
		
		$.ajax({
			url: '/usr/article/toggleLike',
			type: 'POST',
			data: {articleId: articleId},
			xhrFields: { withCredentials: true },
			success: function (data) {
				console.log(data); 
				if (data.status == 'fail') {
					alert('로그인 후 이용가능한 기능입니다.');
					return;
				}
				
				if (data.liked) {
					$('#likeBtn').find('.heart').text('♥');
				} else {
					$('#likeBtn').find('.heart').text('♡');
				}
				
				
				$('#likeCnt').text(data.likeCount);
			}
		});
	});
});
</script>
	<nav class="list">
		<div class="top_name">상세보기</div>
		
		<table border="1">
			<tr>
				<th>번호</th>
				<td>${article.getId() }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${article.getRegDate() }</td>
			</tr>
			<tr>
				<th>수정일</th>
				<td>${article.getUpdateDate() }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${article.getWriterName() }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${article.getTitle() }</td>
			</tr>
			<tr>
				<th>내용</th>
				<td class="content">${article.getContent() }</td>
			</tr>
		</table>
		
		<div class="btn">
			<button id="likeBtn" >
				<span class="heart">
					<c:choose>
						<c:when test="${hasLiked }">♥</c:when>
						<c:otherwise>♡</c:otherwise>
					</c:choose>
				</span> 좋아요
			</button>
			<div id="likeCnt">${likeCount}</div>
			<button onclick="history.back();">뒤로가기</button>
			<c:if test="${sessionScope.loginMemberName == article.writerName}">
				<a href="/usr/article/modify?id=${article.getId() }">수정</a>
				<a href="/usr/article/delete?id=${article.getId() }&boardId=${article.getBoardId()}" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
			</c:if>
		</div>
	</nav>
<%@ include file="/view/usr/common/footer.jsp" %>