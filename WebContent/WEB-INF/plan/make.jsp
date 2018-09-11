<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
	<title>制定计划</title>
	<script type="text/javascript">
		$(function(){
			
			$("button[id^='save']").click(function(){
				var id = $(this).attr("id");
				id = id.split("-")[1];
				var todo = $("#todo-" + id).val();
				
				var url = "/crm/plan/make-ajax";
				var args = {"id":id, "todo":todo};
				$.post(url, args, function(data){
					$.post(url, args, function(data){
						if(data == "1"){
							alert("修改成功!");
						}else{
							alert("修改失败!");
						}
					});
				});
				
				return false;
			});		
			
			$("button[id^='delete']").click(function(){
				var id = $(this).attr("id");
				id = id.split("-")[1];
				
				var url = "/crm/plan/delete-ajax";
				var args = {"id":id};
				$.post(url, args, function(data){
					if(data == "1"){
						$("#plan-" + id).remove();
						alert("删除成功!");
					}else{
						alert("删除失败!");
					}
				});
				
				return false;
			});	
			
			$("#execute").click(function(){
				var id = $(":hidden[name='chance.id']").val();
				window.location.href = "/crm/plan/execution?id=" + id;
				return false;
			});
			
			$("#new").click(function(){
				var date=$("#date").val();
				var todo=$("#todo").val();
				var url="/crm/plan/add";
				var params={
						"date":date,
						"todo":todo,
						"date00":new Date()
				};
				$.post(url,params,function(data){
					
				})
			});
		})
	</script>
</head>

<body class="main">
	<span class="page_title">制定计划</span>
	<div class="button_bar">
		<button class="common_button" id="execute">
			执行计划
		</button>
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
	</div>
	
		<form action="${ctx }/plan/make" method="post">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					编号
				</th>

				<td>
					${saleschance.id }
				</td>
				<th>
					机会来源
				</th>

				<td>
					${saleschance.source }
				</td>
			</tr>
			<tr>
				<th>
					客户名称
				</th>
				<td>
					${saleschance.custName }
				</td>
				<th>
					成功机率（%）
				</th>

				<td>
					${saleschance.rate }
				</td>
			</tr>
			<tr>
				<th>
				   概要
				</th>
				<td colspan="3">
					${saleschance.title }
				</td>
			</tr>
			<tr>
				<th>
					联系人
				</th>

				<td>
					${saleschance.contact}
				</td>
				<th>
					联系人电话
				</th>

				<td>
					${saleschance.contactTel }
				</td>
			</tr>
			<tr>
				<th>
					机会描述
				</th>
				<td colspan="3">
					${saleschance.description }
				</td>
			</tr>
			<tr>
				<th>
					创建人
				</th>
				<td>
					
				</td>
				<th>
					创建时间
				</th>
				<td>
					 <fmt:formatDate value="${saleschance.createDate }" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<th>
					指派给
				</th>
				<td>
					${saleschance.createBy.name }
				</td>

			</tr>
		</table>

		<br />
		
		<table class="data_list_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th width="200px">
					日期
				</th>
				<th>
					计划项
				</th>
			</tr>	
					
					<tr id="plan-41">
						<td class="list_data_text">
							2018-08-08
							&nbsp;
						</td>
						<td class="list_data_ltext">
							
								<input type="text" size="50"
									value="吃饭" id="todo-41"/>
								<button class="common_button" id="save-41">
									保存
								</button>
								<button class="common_button" id="delete-41">
									删除
								</button>					
						</td>
					</tr>				
			
		</table>

		<div class="button_bar">
			<button class="common_button" id="new">
				新建
			</button>
		</div>
		<input type="hidden" name="chance.id" value="${saleschance.id }" />
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					日期
					<br />
					(格式: yyyy-mm-dd)
				</th>
				<td>
					<input type="text" name="date" id="date" />
					&nbsp;
				</td>
				<th>
					计划项
				</th>
				<td>
					<input type="text" name="todo" size="50" id="todo" />
					&nbsp;
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
