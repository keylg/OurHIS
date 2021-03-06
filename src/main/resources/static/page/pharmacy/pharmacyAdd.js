layui.use(['form','layer'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;
    
    
    $("input[name=mname]").css({
		"position":"relative"
	});
	
	$("#lns").css({
		"border":"1px #ccc solid",
		"width":"230px",
		"position":"absolute",
		"top":"37px",
		"left":"0px",
		"display":"none",
		"z-index":"999"
	});
	
	//删除左右两端空格的方法
	 function trim(str){ //删除左右两端的空格
	　　     return str.replace(/(^\s*)|(\s*$)/g, "");
	　　 }
	
	// 键盘松开的时候Ajax请求
	$(".mname").keyup(function(){
		var mname = $(this).val();
		if(mname != ""){
			$.ajax({
				url:"/pharmacykc/selectMname",
				type:"post",
				data:{"mname":mname},
				dataType:"html",
				async:true,
				success:function(result){
					$("#lns").show();
					$("#lns").html(result);
					
					// 点击模糊列表的值，并且赋值
					$(".choose").click(function(){
						$("input[name=mname]").val(trim($(this).text()));
						$(".msize").val($(this).attr("a"));
						$(".manufacturer").val($(this).attr("b"));
						$(".mprice").val($(this).attr("c"));
						$(".munit").val($(this).attr("d"));
						$("input[name=mname]").focus();
						$("#lns").hide();
					});
					$("body").click(function(){
						$("#lns").hide();
						
					});
				}
			});
		}else{
			$("#lns").html("");
			$("#lns").hide();
		}
	});
    
    form.on("submit(addpharmacy)",function(data){
        //弹出loading
    	 var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
         $.post(
        	"/pharmacy/addpharmacy",
        		 {
             mname : $(".mname").val(),  //药品名
             msize : $(".msize").val(),
             manufacturer : $(".manufacturer").val(),
             mprice : $(".mprice").val(),
             munit : $(".munit").val(),
             yfmxnum : $(".yfmxnum").val(),  //数量
             hidnum : $(".hidnum").val(),
        		 },
         function(res){
        	if(res==1){
        		top.layer.msg("添加成功！");
        	}else{
        		top.layer.msg("添加失败！");
        	}
         });
        setTimeout(function(){
            top.layer.close(index);
            layer.closeAll("iframe");
            //刷新父页面
            parent.location.reload();
        },1000);
        return false;
    })

})