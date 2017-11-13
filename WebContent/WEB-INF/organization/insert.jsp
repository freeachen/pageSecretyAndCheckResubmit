<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<%@ include file="/common/common.jspf"%>
<title>My JSP</title>
</head>
<body>
	<form id="ff" method="post">
	    <div> <label for="org_id">机构编号:</label> <input type="text" name="org_id"/> </div>
		<div> <label for="org_name">机构名称:</label> <input type="text" name="org_name"/> </div>
		<div> <label for="org_type">机构类型:</label> <input type="text" name="org_type" value="总行" readonly="readonly" /> </div>
		<div> <label for="org_area">区域:</label> <input type="text" name="org_area" /> </div>
		<div> <label for="org_contact">联系人:</label> <input type="text" name="org_contact" /> </div>
		<div><label for="org_post">邮编:</label><textarea name="org_post"></textarea></div>
		<div><label for="org_phone">电话:</label><textarea name="org_phone"></textarea></div>
		<div><label for="org_address">地址:</label><textarea name="org_address"></textarea></div>
		<div><input id="btn" type="button" value="提交" /></div>
	</form>

	<script type="text/javascript">
		$(function() {
			var win = parent.$("iframe[title='机构管理']").get(0).contentWindow;//返回ifram页面窗体对象（window), 在main.jsp中找到!
			$("[name='org_name']").validatebox({
				required : true,
				missingMessage : '请填写机构名！'
			});
			$("[name='org_contact']").validatebox({
				required : true,
				missingMessage : '请填写出联系人！'
			});
			$("[name='org_phone']").validatebox({
				required : true,
				missingMessage : '请填写联系电话！'
			});
			//禁用验证
			$("#ff").form("disableValidation");

			$("#btn").click(function() { //点击保存提交按钮的时候才触发验证
				$("#ff").form("enableValidation");
				if ($("#ff").form("validate")) { //ajax form提交
					$('#ff').form('submit', {
						url : '${proPath}/organization/insert.action',
						onSubmit : function() {
							return true; //一定要提交  或 return $("#ff").form("enableValidation"); 提交的时候验证
						},
						success : function(count) {							
								//可以定义为对应消息框
								if(count > 0){
									alert("添加成功！");									
								}else{
									alert("添加失败！");
								}
								parent.$("#win").window("close");
								win.$("#dg").datagrid("reload"); //	成功后,让供应商管理里面的datagrid重新加载, reload会保持在当前页面, load会保持在第一页						
						}
					});
				}
			});
		});
	</script>
</body>
</html>
