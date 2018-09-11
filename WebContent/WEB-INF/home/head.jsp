<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title></title>
	<link rel="stylesheet" type="text/css" href="styles.css" >
	<script type="text/javascript">
		$(function(){
			$("#logout").click(function(){
				window.parent.parent.location.href = this.href;
			});
		})
	</script>
  </head>
  <body style="border-bottom:solid 1px #666;">
	<TABLE style="width:100%;">
	<TR >
		<td ><img src="${ctx}/static/images/logo.jpg"></td>
		<td style="font-family:黑体;font-size:33px;font-weight:bold;"> 客户关系管理系统</td>	
		<td width="25%" align="right" style="font-size:12px;" valign="bottom">
			<b>当前登录用户:${user.name }&nbsp;&nbsp;角色:[${user.role.name }]</b>&nbsp;&nbsp;&nbsp;&nbsp;  [<a href="${ctx}/user/logout" id="logout">注销</a>]&nbsp;&nbsp;<br />
		 </td>
	</tr>
	</table>
  </body>
</html>
