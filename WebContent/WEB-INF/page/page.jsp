<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div style="text-align:right; padding:6px 6px 0 0;">   
   <c:choose>
     <c:when test="${page.totalNum<=5 }">
        <c:set var="begin" value="1"></c:set>
        <c:set var="end" value="${page.totalNum }"></c:set>
     </c:when>
     <c:when test="${page.pageNo>3 }">
        <c:set var="begin" value="${page.pageNo-2 }"></c:set>
        <c:set var="end" value="${page.pageNo+2 }"></c:set>
        <c:if test="${end>page.totalNum }">
            <c:set var="end" value="${page.totalNum }"></c:set>
            <c:set var="begin" value="${end-4 }"></c:set>
        </c:if>
     </c:when>
     <c:when test="${page.pageNo<=3 }">
        <c:set var="begin" value="1"></c:set>
        <c:set var="end" value="5"></c:set>
     </c:when>
   </c:choose>
   
   
           共${page.totalRecord }条记录，共${page.totalNum }页
    <a href="${page.path }?pageNo=1${page.queryString}">首页</a>
    <a href="${page.path }?pageNo=${page.pageNo-1}${page.queryString}">上一页</a>   
    <c:forEach begin="${begin }" end="${end}" var="index">
      <c:if test="${index==page.pageNo }">
        <span style="color: red">【${index }】</span>
      </c:if>
      <c:if test="${index!=page.pageNo }">
        <a href="${page.path }?pageNo=${index}${page.queryString}">${index}</a>
      </c:if>      
    </c:forEach>
     <a href="${page.path }?pageNo=${page.pageNo+1}${page.queryString}">下一页</a>
     &nbsp;&nbsp;        
     <a href="${page.path }?pageNo=${page.totalNum}${page.queryString}">末页</a>
            跳转到<input type="text" id="pageNo" size="1">页
          <input type="hidden" id="qs" value="${page.queryString}">  
    
</div>

<script type="text/javascript" src="${ctx }/static/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript">

	$(function(){
		$("#pageNo").change(function(){
			var pageNo=$(this).val();
			var queryStr=$("#qs").val();
			var reg = /^[1-9]\d*$/;
			if(!reg.test(pageNo)){
				alert("请输入正确的页码格式");
				return;
			}
			window.location.href="${page.path }?pageNo="+pageNo+queryStr;
		});
	})
</script>
</html>