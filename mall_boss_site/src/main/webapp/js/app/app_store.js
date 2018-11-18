var UNINSTALLED = 1;
var INSTALLED = 2;
var RESOLVED = 4;
var STARTING = 8;
var STOPPING = 16;
var ACTIVE = 32
$(function() {
    loadApps();
});

function loadApps(pageNo) {
	var installedApps;
	$.ajax({
		url:'queryAllInstallApps.htm?CSRFToken='+$("#CSRFToken").val(),
		async:false,
		success:function(data) {
			installedApps = data;
		}
	});
    if(pageNo==undefined) pageNo=1;
    $.ajax({
        url:'appclient.htm?pageNo='+pageNo+'&appName='+$("#appName").val(),
        contentType: "application/x-www-form-urlencoded; charset=utf-8", 
        success:function(result) {
        	var data = JSON.parse(result);
            var appList = data.list;
            var html = '';
            for(var i=0;i<appList.length;i++) {
                var app = appList[i];
                html += '<li>'+
                '<div class="app-icon"><img alt="" src="'+app.appLogo+'"/></div>'+
                '<div style="text-align: center;"><div id="progressbar'+app.id+'" style="text-align: left;"></div></div>'+
                '<p class="app-name">'+app.appName+'</p>';

                var appFlag = getAppFlag(installedApps,app.id);
                html += '<div id="appStatus'+app.id+'">';
                if(appFlag=="resolved") {
                    html += '<span style="background-color: #ee1a15;color: #fff;padding: 3px 5px;border-radius: 3px;">未开启</span>';
                } else if(appFlag=="active") {
                    html += '<span style="background-color: #3FED3E;color: #fff;padding: 3px 5px;border-radius: 3px;">已开启</span>';
                } else if(appFlag=="uninstalled") {
                    html += '<span style="background-color: #7a7874;color: #fff;padding: 3px 5px;border-radius: 3px;">已卸载</span>';
                }
                html += "</div>";

                html +=
                '<div class="app-cont" id="appcont'+app.id+'">'+
                '   <p class="full-name"><span>应用名称:</span>'+app.appName+'</p>'+
                '   <p class="app-version"><span>应用版本:</span>'+app.appVersion+'</p>'+
                //'   <p class="app-status"><span>Status:</span>Active</p>'+
                '   <p class="app-detail"><button class="btn btn-default btn-xs" type="button" data-toggle="modal" data-target="#appDetail" onclick="showAppDetail('+app.id+')">查看详情</button></p>'+
                '   <div class="app-operation">';
                if(appFlag=="undownload") {
                	html += '<button class="btn btn-default btn-xs btn-app" type="button" onclick="downloadApp('+app.id+')" id="installBtn'+app.id+'">安装</button>';
                    html += '<button class="btn btn-default btn-xs btn-app" type="button" onclick="startApp('+app.id+')" id="startBtn'+app.id+'" style="display:none;">开启</button>';
                    html += '<button class="btn btn-default btn-xs btn-app" type="button" onclick="stopApp('+app.id+')" id="stopBtn'+app.id+'" style="display:none;">停止</button>';
                    html += '<button class="btn btn-default btn-xs btn-app" type="button" onclick="uninstallApp('+app.id+')" id="uninstallBtn'+app.id+'" style="display:none;">卸载</button>';
                } else if(appFlag=="resolved") {
                    html += '<button class="btn btn-default btn-xs btn-app" type="button" onclick="downloadApp('+app.id+')" id="installBtn'+app.id+'" style="display:none;">安装</button>';
                    html += '<button class="btn btn-default btn-xs btn-app" type="button" onclick="startApp('+app.id+')" id="startBtn'+app.id+'">开启</button>';
                    html += '<button class="btn btn-default btn-xs btn-app" type="button" onclick="stopApp('+app.id+')"  style="display:none;" id="stopBtn'+app.id+'">停止</button>';
                    html += '<button class="btn btn-default btn-xs btn-app" type="button" onclick="uninstallApp('+app.id+')" id="uninstallBtn'+app.id+'">卸载</button>';
                } else if(appFlag=="active") {
                    html += '<button class="btn btn-default btn-xs btn-app" type="button" onclick="downloadApp('+app.id+')" id="installBtn'+app.id+'" style="display:none;">安装</button>';
                    html += '<button class="btn btn-default btn-xs btn-app" type="button" onclick="startApp('+app.id+')" style="display:none;" id="startBtn'+app.id+'">开启</button>';
                    html += '<button class="btn btn-default btn-xs btn-app" type="button" onclick="stopApp('+app.id+')" id="stopBtn'+app.id+'">停止</button>';
                    html += '<button class="btn btn-default btn-xs btn-app" type="button" onclick="uninstallApp('+app.id+')" id="uninstallBtn'+app.id+'">卸载</button>';
                } else if(appFlag=="uninstalled") {
                    html += '<button class="btn btn-default btn-xs btn-app" type="button" onclick="installApp('+app.id+')">安装</button>';
                    html += '<button class="btn btn-default btn-xs btn-app" type="button" onclick="startApp('+app.id+')" style="display:none;" id="startBtn'+app.id+'">开启</button>';
                    html += '<button class="btn btn-default btn-xs btn-app" type="button" onclick="stopApp('+app.id+')" id="stopBtn'+app.id+'" style="display:none;">停止</button>';
                    html += '<button class="btn btn-default btn-xs btn-app" type="button" onclick="uninstallApp('+app.id+')" style="display:none;">卸载</button>';
                }
            	
                
                html += 
                //'      <a href="javascript:;" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-refresh"></span></a>'+
                //'      <a href="javascript:;" class="ui-state-default ui-corner-all"><span class="ui-icon ui-icon-transferthick-e-w"></span></a>'+
                '   </div>'+
                '</div>'+
                '</li>';
            }
            $("#appListUL").html(html);


           // 添加页脚
            $("#pageFoot").html("");
            var foot =  "";
            if(data.pageNo==1) {
                foot += '<span class="disabled"> 上一页 </span>';
            } else {
                foot += "<a href='javascript:void(0)' onclick='loadApps(" + data.prePageNo + ")'>上一页</a>";
            }
            var i = 1;
            if(data.startNo>1){
                foot = foot + "<a onclick='loadApps(1)' href='javascript:void(0)'>1</a>";
                foot = foot + '<span class="current">... </span>';
            }
            for (var l = data.startNo; l <= data.endNo; l++)
            {
                if ((i - 1 + data.startNo) == data.pageNo)
                {
                    foot = foot + '<span class="current"> '+data.pageNo+' </span>';
                }
                else
                {
                    foot = foot + "<a onclick='loadApps(" + (i - 1 + data.startNo) + ")' href='javascript:void(0)'>" + (i - 1 + data.startNo) + "</a>";
                }
                i++;
            }
            if(data.endNo<data.totalPages){
                foot = foot + '<span class="current">... </span>';
                foot = foot + "<a onclick='loadApps("+data.totalPages+")' href='javascript:void(0)'>"+data.totalPages+"</a>";
            }
            foot = foot + "<a onclick='loadApps(" + data.nextPageNo +  ")' href='javascript:void(0)'>下一页</a>" ;
            //添加tfoot分页信息
            $("#pageFoot").html(foot);
        }
    });
}

function getAppFlag(installedApps,curAppId) {
    var downloaded = false;
    for(var i=0;i<installedApps.length;i++) {
        if(installedApps[i].appId==curAppId) {
            downloaded = true;
            if(installedApps[i].installStatus=='1') {
                return "resolved";
            } else if(installedApps[i].installStatus=='2') {
                return "resolved";
            } else if(installedApps[i].installStatus=='3') {
                return "active";
            } else if(installedApps[i].installStatus=='4') {
                return "uninstalled";
            }
        }
    }
    if(!downloaded) {
        return "undownload";
    }
}

function downloadApp(id) {
    //查询并显示开启进度
    startProgress(id,30,INSTALLED);
    $("#installBtn"+id).attr("disabled","disabled");
    $.ajax({
        url:'downloadappfromserver.htm?CSRFToken='+$("#CSRFToken").val()+'&id='+id,
        success:function(data) {
            showTipAlert("功能正在安装，请稍后");
            $("#installBtn"+id).hide();
            $("#startBtn"+id).show();
        }
    });
}

function showAppDetail(id) {
    $.ajax({
        url:'appdetail.htm?id='+id+"&CSRFToken="+$("#CSRFToken").val(),
        success:function(data) {
            $("#appNameDiv").html(data.appName);
            $("#appDescDiv").html('<p>'+data.appDesc+'</p>');
        }
    });
}

function startApp(id) {
    //查询并显示开启进度
    startProgress(id,0,ACTIVE);
	$.ajax({
		url:'startapp.htm?id='+id+"&CSRFToken="+$("#CSRFToken").val(),
		success:function(data) {
            showTipAlert("应用已正在启动，请稍后");
            $("#stopBtn"+id).show();
            $("#startBtn"+id).hide();
		}
	});
}
function stopApp(id) {
    //查询并显示停止进度
    startProgress(id,0,RESOLVED);
    $.ajax({
        url:'stopapp.htm?id='+id+"&CSRFToken="+$("#CSRFToken").val(),
        success:function(data) {
            showTipAlert("功能正在停用，请稍后");
            $("#stopBtn"+id).hide();
            $("#startBtn"+id).show();
        }
    });
}

function installApp(id) {
    //查询并显示安装进度
    startProgress(id,0,INSTALLED);
    $.ajax({
        url:'installapp.htm?id='+id+"&CSRFToken="+$("#CSRFToken").val(),
        success:function(data) {
            showTipAlert("功能正在安装,请稍后");
            $("#uninstallBtn"+id).hide();
            $("#startBtn"+id).show();
        }
    });
}

function uninstallApp(id) {
    //查询并显示卸载进度
    startProgress(id,0,UNINSTALLED);
    $.ajax({
        url:'uninstallapp.htm?id='+id+"&CSRFToken="+$("#CSRFToken").val(),
        success:function(data) {
            showTipAlert("功能正在卸载，请稍后");
            $("#uninstallBtn"+id).hide();
            $("#installBtn"+id).show();
        }
    });
}

/*
根据状态查询并显示进度
state:
int UNINSTALLED = 1;
 int INSTALLED = 2;
 int RESOLVED = 4;
 int STARTING = 8;
 int STOPPING = 16;
 int ACTIVE = 32;*/
function startProgress(id,progress,state) {
    if(progress==undefined) progress=30;
    if(progress>=100) {
        if(state==ACTIVE) {
            $("#appStatus"+id).html('<span style="background-color: #3FED3E;color: #fff;padding: 3px 5px;border-radius: 3px;">已开启</span>');
            showConfirmAlert("应用已经安装，刷新页面后即可使用。","refreshPage()");
        } else if(state==RESOLVED) {
            $("#appStatus"+id).html('<span style="background-color: #ee1a15;color: #fff;padding: 3px 5px;border-radius: 3px;">未开启</span>');
            showTipAlert("应用已经停止。");
        } else if(state==UNINSTALLED) {
            $("#appStatus"+id).html( '<span style="background-color: #7a7874;color: #fff;padding: 3px 5px;border-radius: 3px;">已卸载</span>');
            showConfirmAlert("应用已经卸载。","refreshPage()");
        }
        $("#progressbar"+id).hide();
        return;
    }
    $("#progressbar"+id).progressbar({
        value: progress
    });
    $.ajax({
        url:'queryappstate.htm?id='+id,
        async:false,
        success:function(data) {

            if(data==state) {
                //如果状态已是active，进度最低从70开始
                progress=progress>70?progress:70;
            }
            setTimeout("startProgress("+id+","+(progress+4)+","+state+")", 3000);
        }
    });
}

function refreshPage() {
    location.reload();
}
