$(document).ready(function(){
	
	$("#queryFormId").on("click",".btn-search",doGetObjects);
	$("#queryFormId").on("click",".btn-add,.btn-update",showEditDialog);
	doGetObjects();//加载数据
});
function showEditDialog(){
	
	var url="team/editUI.do";
	var title;
	if($(this).hasClass("btn-add")){
		title="添加项目"
	}
	if($(this).hasClass("btn-update")){
		title="修改项目"
		//将要修改的记录的id值绑定到模态框上
		//目的时通过一个模块实现添加或更新操作
		$("#modal-dialog").data("id",$(this).parent().parent().data("id"));//
	}
	//在模态框的.moday-body位置异步加载url
	$("#modal-dialog .modal-body").load(url,
			function(){//加载完成执行此
		 $(".modal-title").html(title);
		 $("#modal-dialog").modal("show");
	})
	
}

function doGetObjects(){
	debugger
	//1.url
	var url="team/dofindPageObjects.do"
	//2.params
	var params=getQueryFormData();
	console.log("params.valid:"+params.valid)
	var pageCurrent=$("#pageId").data("pageCurrent");
	if(pageCurrent){
		params.pageCurrent=pageCurrent;
	}else{
		params.pageCurrent=1;
	}
	//3.ajax (post)                    
    $.post(url,params,function(result){
    	console.log(JSON.stringify(result));
    	
    	
    	if(result.state==1){//SUCCESS
    	//3.1 填充表格(setTableRows(result.data.list))
    	setTableRows(result.data.list)
    	//3.2 设置分页(setPagination(result.data.pageObject))
    	setPagination(result.data.pageObject);
    	
    	}else{
    	  alert(result.message);
    	}
    });
}



//获得查询表单中的数据
function getQueryFormData(){
	var params={
	"projectName":$("#searchNameId").val(),
	"valid":$("#searchValidId").val()
	}
	return params;
}


function setTableRows(list){
	//迭代list(此集合中现在存储的是多个map)
	var tds='<td><input type="checkbox" name="checkedItem" value="[id]"></td>'
	        +"<td>[name]</td>"
	        +"<td>[projectName]</td>"
	        +"<td>[valid]</td>"
	        +"<td>修改</td>";
	var tBody=$("#tbodyId");
	tBody.empty();
	for(var i in list){
		var tr=$("<tr></tr>");
		tr.append(
		tds.replace("[id]",list[i].id)
		   .replace("[name]",list[i].name)
		   .replace("[projectName]",list[i].projectName)
		   .replace("[valid]",list[i].valid?"启用":"禁用"));
	    tBody.append(tr);
	}
}











