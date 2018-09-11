<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
  <head>
    <title>指派销售机会</title>
    <script type="text/javascript">
	    $(function(){
	    	var val = $("#designeeDate").val();

	    	if(val == null || val == ""){
	    		$("#designeeDate").val(new Date().format("yyyy-MM-dd"));
	    	}
	    	$("#save").click(function(){	    		
    			$("form")[0].submit();
	    	});
	    })
    </script>
  </head>

  <body class="main">
  <span class="page_title">指派销售机会</span>

  <div class="button_bar">
	<button class="common_button" onclick="javascript:history.go(-1);">返回</button>
	<button class="common_button" id="save">保存</button>
  </div>
  <form id="chance" action="${ctx }/chance/assign" method="post">
  	<input id="id" name="id" type="hidden" value="1"/>
		<input type="hidden" name="_method" value="put">
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>编号</th>
				<td>${saleschance.id }</td>
				<th>机会来源</th>
				<td>${saleschance.source }</td>
			</tr>
			<tr>
				<th>客户名称</th>
				<td>${saleschance.custName }</td>
				<th>成功机率%</th>
				<td>${saleschance.rate }</td>
			</tr>
			<tr>
				<th>概要</th>
				<td colspan="2">${saleschance.title }</td>
			</tr>
			<tr>
				<th>联系人</th>
				<td>${saleschance.contact }</td>
				<th>联系人电话</th>
				<td>${saleschance.contactTel }</td>
			</tr>
			<tr>
				<th>机会描述</th>
				<td colspan="3">${saleschance.description }</td>
			</tr>
			<tr>
				<th>创建人</th>
				<td>${saleschance.createBy.name }</td>
				<th>创建时间</th>
				<td> 
				<fmt:formatDate value="${saleschance.createDate }" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
		</table>
		<br />
		
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">				
			<tr>					
				<th>指派给</th>
				<td>
					<select id="designee.id" name="designee.id">
						<option value="">请选择</option>	
						<c:forEach items="${list}" var="user">
						  <option value="${user.id }" selected="selected">${user.name }</option>
						</c:forEach>
					<select>
					<span class="red_star">*</span>				  
				</td>
				<th>指派时间</th>
				<td>
					<input id="designeeDate" name="designeeDate" type="text"/><span class="red_star">*</span>
				</td>
			</tr>
		</table>
	 </form>
  </body>
</html>