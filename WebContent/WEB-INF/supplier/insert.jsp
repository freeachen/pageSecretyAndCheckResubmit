<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<%@ include file="/common/common.jspf"%>
<title>My JSP</title>
</head>
<body>
	<form id="ff" method="post">
	    <div> <label for="supId">供应商编号:</label> <input type="text" name="supId"/> </div>
		<div> <label for="supName">供应商:</label> <input type="text" name="supName"/> </div>
		<div> <label for="supLinkman">联系人:</label> <input type="text" name="supLinkman" /> </div>
		<div> <label for="supPhone">联系电话:</label> <input type="text" name="supPhone" /> </div>
		<div> <label for="supAddress">联系地址:</label> <input type="text" name="supAddress" /> </div>
		<div> <label for="supPay">期初应付(元):</label> <input type="text" name="supPay" /> </div>
		<div> <label for="supType">供应商类型：</label> 									
			<select id="cc" class="easyui-combobox" name="supType" style="width:200px;">   
				<c:forEach items="${applicationScope.sysParam.supType}" var="supType">
					<option value="${supType.key}">${supType.value}</option>   
				</c:forEach>   
			</select>  
		</div>
		<div><label for="supRemark">备注:</label><textarea name="supRemark"></textarea></div>
		<div><input id="btn" type="button" value="提交" /></div>
	</form>

	<script type="text/javascript">
		$(function() {
			var win = parent.$("iframe[title='供应商管理']").get(0).contentWindow;//返回ifram页面窗体对象（window), 在main.jsp中找到!
			$("[name='supName']").validatebox({
				required : true,
				missingMessage : '请填写供应商！'
			});
			$("[name='supLinkman']").validatebox({
				required : true,
				missingMessage : '请填写出联系人！'
			});
			$("[name='supPhone']").validatebox({
				required : true,
				missingMessage : '请填写联系电话！'
			});
			//禁用验证
			$("#ff").form("disableValidation");

			$("#btn").click(function() { //点击保存提交按钮的时候才触发验证
				$("#ff").form("enableValidation");
				if ($("#ff").form("validate")) { //ajax form提交
					$('#ff').form('submit', {
						url : '${proPath}/supplier/insert.action',
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
