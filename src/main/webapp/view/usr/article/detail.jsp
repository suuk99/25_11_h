<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ include file="/view/usr/common/header.jsp" %>

<script>
$(function() {
    $('.like').click(function() {
        const btn = $(this);
        
        if (!btn.data('id')) {
        	alert('로그인이 필요한 기능입니다.');
        	return;
        }
        
        const articleId = btn.data('id');

        $.ajax({
            url: '/usr/article/goodPoint',
            type: 'GET',
            data: { id: articleId },
            success: function(data) {
            	
            	if (data.result == 'notLogin') {
            		alert('로그인이 필요한 기능입니다.');
            		return;
            	}
            	
                if (data.result === 'add') {
                    btn.addClass('liked');
                    btn.find('.heart').text('♥');
                } else {
                    btn.removeClass('liked');
                    btn.find('.heart').text('♡');
                }

                btn.closest('.btn').find('.goodCnt').text(data.goodCnt);
            }
        });
    });
});
</script>

<c:set var="pageTitle" value="상세"/>

<nav class="list">
    <div class="top_name">상세보기</div>

    <table border="1">
        <tr>
            <th>번호</th>
            <td>${article.getId()}</td>
        </tr>
        <tr>
            <th>작성일</th>
            <td>${article.getRegDate()}</td>
        </tr>
        <tr>
            <th>수정일</th>
            <td>${article.getUpdateDate()}</td>
        </tr>
        <tr>
            <th>작성자</th>
            <td>${article.getWriterName()}</td>
        </tr>
        <tr>
            <th>제목</th>
            <td>${article.getTitle()}</td>
        </tr>
        <tr>
            <th>내용</th>
            <td>${article.getContent()}</td>
        </tr>
    </table>

    <div class="btn">
        <button class="like <c:if test='${isGood > 0}'>liked</c:if>'" 
       		 data-id="${article.id}" 
        	<c:if test='${sessionScope.loginMemberName == null}'>disabled</c:if>>
   		 <span class="heart">
        	<c:choose>
            	<c:when test="${isGood > 0}">♥</c:when>
            	<c:otherwise>♡</c:otherwise>
        	</c:choose>
   		</span>
    	좋아요
	  </button>

        <div class="goodCnt">${goodPointCnt}</div>

        <button onclick="history.back();">뒤로가기</button>

        <c:if test="${sessionScope.loginMemberName == article.writerName}">
            <a href="/usr/article/modify?id=${article.getId()}">수정</a>
            <a href="/usr/article/delete?id=${article.getId()}" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">삭제</a>
        </c:if>
    </div>
</nav>

<%@ include file="/view/usr/common/footer.jsp" %>