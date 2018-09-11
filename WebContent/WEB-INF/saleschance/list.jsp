<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>销售机会管理</title>
	<script type="text/javascript">
		$(function(){
			$("img[name=delete]").click(function(){
				var requestUrl=$(this).attr("id");
				$("#ddd").val("delete");
				$("#form").attr("action",requestUrl).submit();
			});	
			
			$("#find").click(function(){
				/* window.location.href="${ctx}/saleschance/list?search_LIKE_custName="+$("input[name=search_LIKE_custName]").val(); */
				window.location.href="${ctx}/chance/toadd";
				return false;
			})
			
			/* $("#new").click(function(){
					window.location.href="/crm" + "/saleschance/toadd";
					return false;
			}); */
		});
	</script> 
</head>

<body class="main">
    <form id="form" action="" method="post">
       <input type="hidden"  name="_method" value="" id="ddd">
    </form>
    
	<form id="command" action="${ctx }/chance/list" method="get">
		<div class="page_title">
			销售机会管理
		</div>
		<div class="button_bar">
			<button class="common_button" id="find">
				新建
			</button>
			<button class="common_button"  >
				查询
			</button>
		</div>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title">
					客户名称
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_custName" />
				</td>
				<th class="input_title">
					概要
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_title" />
				</td>
				<th class="input_title">
					联系人
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_contact" />
				</td>
			</tr>
		</table>
	</form>
		<!-- 列表数据 -->
		<br />
		
		
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th>
						编号
					</th>
					<th>
						客户名称
					</th>
					<th>
						概要
					</th>
					<th>
						联系人
					</th>
					<th>
						联系人电话
					</th>
					<th>
						创建时间
					</th>
					<th>
						操作
					</th>
				</tr>
				<c:forEach items="${page.list }" var="saleschance">
				    <tr>
						<td class="list_data_number">${saleschance.id }</td>
						<td class="list_data_text">${saleschance.custName }</td>
						<td class="list_data_text">${saleschance.title }</td>
						<td class="list_data_text">${saleschance.contact }</td>
						<td class="list_data_text">${saleschance.contactTel }</td>
						<td class="list_data_text">
						    <fmt:formatDate value="${saleschance.createDate }" pattern="yyyy-MM-dd"/>
							
						</td>
						<td class="list_data_op">
							<img onclick="window.location.href='${ctx }/chance/toassign?id=${saleschance.id }'" 
								title="指派" src="${ctx }/static/images/bt_linkman.gif" class="op_button" />
							<img onclick="window.location.href='${ctx }/chance/toupdate?id=${saleschance.id }'" 
								title="编辑" src="${ctx }/static/images/bt_edit.gif" class="op_button" />
							<img name="delete" id="${ctx }/chance/delete?id=${saleschance.id }" 
								title="删除" src="${ctx }/static/images/bt_del.gif" class="op_button" />
						</td>
					</tr>
				</c:forEach>										
			</table>

<%@ include file="/WEB-INF/page/page.jsp" %>
	
	
	
</body>
</html>
