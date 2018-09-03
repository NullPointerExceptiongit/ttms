/*$(document).ready(function(){
	$("#uploadFormId")
	.on("click",".btn-upload",doupload);
});

function doupload(){
	debugger
	var url ="attach/doSaveObject.do";
	//异步提交表单
	$("uploadFormId").ajaxSubmit({
		url:url,
		type:"post",
	    dattaType:"json",
	    success:function(){
	    	alert("upload ok")
	    }
	});
}
*/

$(document).ready(function(){
	$("#uploadFormId")
	.on("click",".btn-upload",doUpload)
	$("#uploadFormId")
	.on("click",".btn-down",doDownload)
	doGetObject();
	
});
function doDownload(){
	var id=
	$(this).parent().parent().data("id");
	console.log("id="+id);
	var url="attach/doDownload.do?id="+id;
	document.location.href=url;
}


/*上传*/
function doUpload(){
	var vals=$("#upload").val();	
	  	   
    	   var url="attach/doSaveObject.do";
    		//异步提交表单(先确保jquery.form.js已经引入了)    		
    		$("#uploadFormId").ajaxSubmit({    			
    			type:'post',
    			url:url,
    			data:{"athType":1,"belongId":1},
    			dataType:'json',
    			success:function(result){
    				if(result.state==1){
    				alert("upload ok");
    				doGetObject();
    				}else{
    				alert(result.message);
    				}
    			}
    		});
       }	


function doGetObject() {
	
	var url="attach/doFindObjects.do";
	$.post(url,function(result){
		if(result.state==1){
			setTableRows(result.data);
		}else{
			alert(result.message);
		}
	})
}


function setTableRows(list){
	var tBody=$("#tbodyId");
	tBody.empty();
	var tds='<td><input type="checkbox" name="selectItem" value="[id]"></td>'
		+'<td>[title]</td>'
		+'<td>[name]</td>'
		+'<td>[contentType]</td>'
	    +'<td><input type="button" class="btn btn-success btn-down" value="下载"></td>';
	for(var i in list){
		var tr=$("<tr></tr>");
		tr.data("id",list[i].id);
		tr.append(
	    tds.replace("[id]",list[i].id)
	       .replace("[title]",list[i].title)
	       .replace("[name]",list[i].fileName)
	       .replace("[contentType]",list[i].contentType));
	    tBody.append(tr);
	}
}





