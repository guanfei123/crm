<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<title>客户开发计划</title>
</head>

<body class="main">
	<form action="${ctx }/plan/list" method="get">
		<div class="page_title">
			客户开发计划
		</div>
		<div class="button_bar">
			<button class="common_button" onclick="document.forms[0].submit();">
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
		<br />
	</form>	
		
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
						状态
					</th>
					<th>
						操作
					</th>
				</tr>				
					<c:forEach items="${page.list }" var="list">
					<tr>
						<td class="list_data_number">
							${list.id }
						</td>
						<td class="list_data_text">
							${list.custName }
						</td>
						<td class="list_data_text">
							${list.title }
						</td>
						<td class="list_data_text">
							${list.contact }
						</td>
						<td class="list_data_text">
							${list.contactTel }
						</td>
						<td class="list_data_text">
							 <fmt:formatDate value="${list.createDate }" pattern="yyyy-MM-dd"/>
						</td>
						<td class="list_data_text">
							<script type="text/javascript">
								switch('2')
								{
									case '2':
										document.write('开发中');
										break;
									case '3':
										document.write('开发成功');
										break;
									case '4':
									    document.write('开发失败');
									   	break;
								}
							</script>
						</td>
						<td class="list_data_op">
							
								<img
									onclick="window.location.href='${ctx }/plan/tomake?id=${list.id }'"
									title="制定计划" src="${ctx }/static/images/bt_plan.gif" class="op_button" />
								<img
									onclick="window.location.href='${ctx }/plan/execution?id=${list.id }'"
									title="执行计划" src="${ctx }/static/images/bt_feedback.gif" class="op_button" />
								<img 
									onclick="window.location.href='${ctx }/chance/finish?id=${list.id }'"
									title="开发成功" src="${ctx }/static/images/bt_yes.gif" class="op_button" />
							
							
						</td>
					</tr>
				</c:forEach>
			</table>
	
		
</body>
<%@ include file="/WEB-INF/page/page.jsp" %>
</html>
 