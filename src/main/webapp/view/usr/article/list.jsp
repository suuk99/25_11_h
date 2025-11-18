<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="pageTitle" value="${boardName }"/>

<%@ include file="/view/usr/common/header.jsp" %>
	<nav class="list">
		<div class="top_name">${boardName}</div>
		
<<<<<<< HEAD
<<<<<<< HEAD
		<form id = "searchForm" action="list" method="get">
		<select name="boardId">
    		<c:forEach items="${boards}" var="board">
        		<option value="${board.id}" ${board.id == boardId ? "selected" : ""}>${board.name}</option>
   			</c:forEach>
		</select>
		<div class="search">
				<select name="searchPart" id="searchPart">
					<option value="title">제목</option>
					<option value="content">내용</option>
					<option value="titleAndContent">제목 + 내용</option>
				</select>
				<input name="keyword" id="keyword" type="text" placeholder="검색어를 입력해주세요"/>
				<a href="#" id="searchBtn">검색</a>
			</div>
		</form>
		
		<script>
			$(function() {
				$('#searchBtn').click(function(e) {
					e.preventDefault();
					
					const keyword = $('#keyword').val().trim();
					const searchPart = $('#searchPart').val();
					const boardId = "${boardId}";
					
					if (!keyword) {
						alert('검색어를 입력해 주세요.');
						return;
					}
					$("#searchForm").submit();
				});
			});
		</script>
		
=======
		<div class="searchBox">
			<select name="searchSelect" id="">
				<option value="0">제목</option>
				<option value="1">내용</option>
				<option value="2">제목+내용</option>
			</select>
			<input name="searchInput" type="text" placeholder=" 검색어를 입력하세요." />
			<a href="#">검색</a>
		</div>
>>>>>>> paging
=======
		<form action="/usr/article/list" method="get">
			<div class="searchBox">
				<input type="hidden" name="boardId" value="${boardId }" />
				
				<select name="searchType" id="searchSelect">
					<option value="title" ${searchType == 'title' ? 'selected' : ''}>제목</option>
					<option value="content" ${searchType == 'content' ? 'selected' : ''}>내용</option>
					<option value="titleAndContent" ${searchType == 'titleAndContent' ? 'selected' : ''}>제목+내용</option>
				</select>
				
				<input name="keyword" id="keyword" type="text" value="${keyword}"placeholder=" 검색어를 입력하세요." />
				<button type="submit">검색</button>
			</div>
		</form>
>>>>>>> temp
		<table border="1">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
			<c:forEach items="${articles }" var="article">
				<tr>
					<td>${article.getId() }</td>
					<td><a href="/usr/article/detail?id=${article.getId() }">${article.getTitle() }</a></td>
					<td>${article.getWriterName() }</td>
					<td>${article.getRegDate() }</td>
				</tr>
			</c:forEach>
		</table>
		
		<div class="pagination">
    <c:if test="${paging.startPage > 1}">
        <a href="/usr/article/list?boardId=${boardId}&page=${paging.startPage - 1}">&laquo; 이전</a>
    </c:if>

    <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="i">
        <c:choose>
            <c:when test="${i == paging.page}">
                <span>${i}</span>
            </c:when>
            <c:otherwise>
                <a href="/usr/article/list?boardId=${boardId}&page=${i}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${paging.endPage < paging.totalPage}">
        <a href="/usr/article/list?boardId=${boardId}&page=${paging.endPage + 1}">다음 &raquo;</a>
    </c:if>
</div>
		
		<div class="btn">
			<button onclick="history.back();">뒤로가기</button>
			<c:if test="${sessionScope.loginMemberId != null }">
				<a href="/usr/article/write">글쓰기</a>
			</c:if>
		</div>
		<div class="flex justify-center">
				<div class="join">
					<c:set var="queryString" value="?boardId=${param.boardId }&keyword=${keyword}&searchType=${searchType }" />
					
					<c:if test="${begin != 1 }">
						<a class="join-item btn btn-sm" href="${queryString }&cPage=1"><i class="fa-solid fa-angles-left"></i></a>
						<a class="join-item btn btn-sm" href="${queryString }&cPage=${begin - 1 }"><i class="fa-solid fa-caret-left"></i></a>
					</c:if>
					<c:forEach begin="${begin }" end="${end }" var="i">
						<a class="join-item btn btn-sm ${cPage == i ? 'btn-active' : '' }" href="${queryString }&cPage=${i }">${i }</a>
					</c:forEach>
					<c:if test="${end != totalPagesCnt }">
						<a class="join-item btn btn-sm" href="${queryString }&cPage=${end + 1 }"><i class="fa-solid fa-caret-right"></i></a>
						<a class="join-item btn btn-sm" href="${queryString }&cPage=${totalPagesCnt }"><i class="fa-solid fa-angles-right"></i></a>
					</c:if>
				</div>
			</div>
	</nav>
	
<%@ include file="/view/usr/common/footer.jsp" %>