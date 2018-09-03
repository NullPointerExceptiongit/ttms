
$(document).ready(function(){
	$("#modal-dialog").on('click','.ok',
			doSaveOrUpdate);
	doInitProjectIdAndNames();
});

//初始化项目id和名字列表
function doInitProjectIdAndNames(){
	var url="team/dofindProjIdAndNames.do"
	$.getJSON(url,function(result){
		if(result.state==1){
		    setProjectSelectOptions(result.data);
		}else{
			alert(result.message);
		}
	})
}
function setProjectSelectOptions(list){
	var selectObj=$("#selectId");
	var optionObj="<option value=[id]>[name]</option>"
	for(var i in list){//List<Map<String,Object>>
	selectObj.append(optionObj
			.replace("[id]",list[i].id)
			.replace("[name]",list[i].name));
	}
}

//填充select选项
function setProjectSelectOption(list) {
	var selectObj=$("#selectId");
	for(var i in list){
		selectObj.append(
				'<option value='+list[i].id+'>'+list[i].name+'</option>')
	}
}
//保存或修改数据

//保存或更新数据
function doSaveOrUpdate(){	
	var url="team/doSaveOrUpdate.do"
	if($("#editFormId").valid()){//required
	//1.获得表单数据
	var params=doGetEditFormData();
	//2.将数据提交到服务端
	//var id=$("#editFormId").data("id");
	var id=$("#modal-dialog").data("id");
	var url=id?"team/doUpdateProject.do":"team/doSaveProject.do"
	$.post(url,params,function(result){
		if(result.state==1){
			//1)隐藏模态框
			$("#modal-dialog").modal("hide");
			//2)重新查询列表数据
			doGetObjects();
		}else{
		   alert(result.message);
		}
	})
	}
}

function doGetEditFormData(){
	var params={
		//"id":$("#modal-dialog").data("id"),//更新时需要时
		"name":$("#nameId").val(),
		"projectId":$("#selectId").val(),
		"valid":$('input[name="valid"]:checked').val(),
		"note":$('#noteId').val()};
	//检测获得的结果
	console.log(JSON.stringify(params));
	return params;
}







