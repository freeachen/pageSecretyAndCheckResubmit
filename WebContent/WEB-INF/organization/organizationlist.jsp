<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="/common/common.jspf"%>
<html>
<head>
<title></title>
<style type="text/css">
	.searchbox{margin:-3}
</style>

<script type="text/javascript">
	$(function(){
			$('#dg').datagrid({    
			    //url:'${proPath}/supplier/selectPage.action', //通过关键字查询
			    //支持多条件查询
			    url:'${proPath}/organization/selectPageUseDyc.action', 
			    fitColumns:true,
			    nowrapL:true, //同行显示数据
			    idField:'supId', //类似数据库的主键,改查用
			    rownumbers:true, //显示行号列
			    pagination:true, //底部加分页工具栏
			    pageSize:5,  //默认10条, 
			    pageNumber: 1, //当前页为1
			    pageList:[2,5,10,20],
			    queryParams: {
					org_name: '%%',
					org_area:'%%'			
				}, 
			    toolbar: [{
					iconCls: 'icon-add',
					text:'新增',
					handler: function(){
						//alert('新增按钮');
						parent.$('#win').window({    //创建一个窗口,本处在main.jsp中创建
							title :'添加供应商',						
						    width:600,    
						    height:400,    
						    modal:true,
						    content:"<iframe src='${proPath}/base/goURL/organization/insert.action' height='100%' width='100%' frameborder='0px' ></iframe>"  
						}); 
					}
				},'-',{
					iconCls: 'icon-edit',
					text:'修改',
					handler: function(){
						alert('修改按钮');
						//判断是否选中一行，并且只能选中一行进行修改
						var array = $('#dg').datagrid("getSelections");
						if(array.length != 1){
							alert("请选择需要修改的记录，并且只能选中一条！");
							return false;							
						}
						//打开修改窗口
						parent.$('#win').window({    
							title :'修改机构',
						    width:600,    
						    height:400,    
						    modal:true,
						    content:"<iframe src='${proPath}/base/goURL/organization/update.action' height='100%' width='100%' frameborder='0px' ></iframe>"  
						}); 
					}
				},'-',{
					iconCls: 'icon-remove',
					text:'删除',
					handler: function(){
						var array = $('#dg').datagrid("getSelections");
						if(array.length > 0){ //选中行
						   //定义数组，通过下边的用来存储选中记录的Id
						   //var ids = new Array();
						   var ids = [];
						   for (i = 0; i < array.length; i++) {
							  ids[i] = array[i].org_id;
							  //alert(ids[i]); 每一行的供应商编号
						   }
						  alert("ids" + ids);
						  //如果需要锁整个页面，前面加parent. 否则值锁定了系统管理界面,其他的头部,左侧菜单都能点击
						 parent.$.messager.confirm('删除对话框', '您确认要删除吗？', function(r) {
							if (r) { //true
								//alert(r);
								$.ajax({
								  url: "${proPath}/organization/deleteList.action",
								  type:"POST",
								  dataType:'json',
								  traditional:true,  //设置为传统方式传送参数 pks:3 pks:5 不加则是 pks[]:3  pks[]:5
								  //data:{pks : ids},
								  data:{"pks" : ids},  //个人觉得带双引号标准点
								  success: function(html){
									  if(html > 0){
									  	alert("恭喜您 ，删除成功，共删除了"+html+"条记录");
									  }else{
									  	alert("对不起 ，删除失败");
									  }
								      //重新刷新页面
								      $("#dg").datagrid("reload");
								      //勾选删除后,把勾选的删除行的id也删除掉,否则还会存在idField中, 否则也会带到后台,那样删除越多带入后台的id也越多
								      $("#dg").datagrid("clearSelections");
								   },
								  error: function (XMLHttpRequest, textStatus, errorThrown) {
									    $.messager.alert('删除错误','请联系管理员！','error');
								  },
								}); //ajax()结束
							 } //if (r)结束
							}); //function(r)结束
						}else{ //未选中行
							alert("请选择需要删除的记录！");
						}
					}
				},'-',{
					text:"名称：<input type='text' id='org_name' name='org_name'/>",					
				}
				,'-',{
					text:"地址：<input type='text' id='org_area' name='org_area'/>",					
				}],		
				
			columns : [ [{
				checkbox:true, //true 显示复选框, false无复选框
			}, {
				field : 'org_id',
				title : '机构编号'
			}, {
				field : 'org_name',
				title : '机构名称',
				width : 100
			}, {
				field : 'org_type',
				title : '机构类型',
				width : 50
			}, {
				field : 'org_area',
				title : '区域',
				width : 100
			}, {
				field : 'org_contact',
				title : '联系人',
				width : 50
			}, {
				field : 'org_phone',
				title : '联系电话',
				width : 50,
			}, {
				field : 'org_post',
				title : '邮编',
				width : 50
			}, {
				field : 'org_address',
				title : '地址',
				width : 50,
				align : 'right'
			} ] ]
		});
		
		//通过关键字查询 供应商名称或地址
			$('#org_area').searchbox({ 
				searcher:function(value, name){ //value框里面输入的文字, name就是input name属性supAddress
					alert("org_name:"+value); 
					alert("org_area:"+ $('#org_area').val());
					$('#dg').datagrid('load',{
						org_name: '%'+ $('#org_name').val() +'%',
						org_area:'%'+ value +'%'		
					});					
			}, 
			prompt:'请输入机构区域'//默认的提示文字 
		});
		
	});
</script>
</head>

<body>
	<table id="dg"></table>
</body>
</html>