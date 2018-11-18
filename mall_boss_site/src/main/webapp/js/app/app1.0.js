var UNINSTALLED = 1;
var INSTALLED = 2;
var RESOLVED = 4;
var STARTING = 8;
var STOPPING = 16;
var ACTIVE = 32

var shouldShow = true;
$(function() {
    loadApps();
});
function loadApps(pageNo) {
    $("#freeAppsBtn").click();
    var installedApps;
    $.ajax({
        url:'queryAllInstallApps.htm?CSRFToken='+$("#CSRFToken").val(),
        async:false,
        success:function(data) {
            installedApps = data;
            appendInstalledApps(data);
        }
    });
    if(pageNo==undefined) pageNo=1;
    $.ajax({
        url:'appclient.htm?CSRFToken='+$("#CSRFToken").val()+'&pageNo='+pageNo+'&appName='+$("#appName").val(),
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success:function(result) {
            var data = JSON.parse(result);
            var appList = data.list;
            var html = '';
            for(var i=0;i<appList.length;i++) {
                var app = appList[i];
                html +=
                    '<li>' +
                        '<a href="javascript:;" data-toggle="modal" data-target="#appDetail" onclick="showAppDetail('+app.id+')">' +
                            '<img class="app-icon" src="'+app.appLogo+'" alt="">' +
                        '</a>' +
                        '<h4 class="app-name">' +
                            '<a href="javascript:;" data-toggle="modal" data-target="#appDetail" onclick="showAppDetail('+app.id+')">' + app.appName + '</a>' +
                        '</h4>' +
                        '<p class="app-version">版本：' + app.appVersion + '</p>';
                        var appFlag = getAppFlag(installedApps,app.id);
                        html += '<div class="progressbar'+app.id+'">';
                        if(appFlag=="undownload") {
                            html +=
                                '<div class="btn-group btngroup'+app.id+'">' +
                                '   <button class="btn btn-primary btn-xs" type="button" onclick="downloadApp('+app.id+')">立即安装</button>'+
                                '</div>';
                        } else if(appFlag=="resolved") {
                            html +=
                                '<div class="btn-group btngroup'+app.id+'">' +
                                '   <button class="btn btn-warning btn-xs" type="button">未启用</button>'+
                                '   <button type="button" class="btn btn-xs btn-warning dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' +
                                '       <span class="caret"></span>' +
                                '       <span class="sr-only">Toggle Dropdown</span>' +
                                '   </button>' +
                                '   <ul class="dropdown-menu">' +
                                '       <li><a href="javascript:;" onclick="startApp('+app.id+')">启用</a></li>' +
                                '       <li><a href="javascript:;" onclick="uninstallApp('+app.id+')">卸载</a></li>' +
                                '   </ul>' +
                                '</div>';
                        } else if(appFlag=="active") {
                            html +=
                                '<div class="btn-group btngroup'+app.id+'">' +
                                '   <button class="btn btn-success btn-xs" type="button">已启用</button>'+
                                '   <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' +
                                '       <span class="caret"></span>' +
                                '       <span class="sr-only">Toggle Dropdown</span>' +
                                '   </button>' +
                                '   <ul class="dropdown-menu">' +
                                '       <li><a href="javascript:;" onclick="stopApp('+app.id+')">停用</a></li>' +
                                '       <li><a href="javascript:;" onclick="uninstallApp('+app.id+')">卸载</a></li>' +
                                '   </ul>' +
                                '</div>';
                        } else if(appFlag=="uninstalled") {
                            html +=
                                '<div class="btn-group btngroup'+app.id+'">' +
                                '   <button class="btn btn-primary btn-xs" type="button" onclick="downloadApp('+app.id+')">立即安装</button>'+
                                '</div>';
                        }
                        html += '</div>';
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

function appendInstalledApps(installedApps) {
    var html = '';
    for(var i=0;i<installedApps.length;i++) {
        var app = installedApps[i];
        html +=
            '<li>' +
            '<a href="javascript:;" data-toggle="modal" data-target="#appDetail" onclick="showAppDetail('+app.appId+')">' +
            '<img class="app-icon" src="'+app.appLogo+'" alt="">' +
            '</a>' +
            '<h4 class="app-name">' +
            '<a href="javascript:;" data-toggle="modal" data-target="#appDetail" onclick="showAppDetail('+app.appId+')">' + app.appName + '</a>' +
            '</h4>' +
            '<p class="app-version">版本：' + app.installVersion + '</p>';
        html += '<div class="progressbar'+app.appId+'">';
        if(app.installStatus=="4") {
            html +=
                '<div class="btn-group btngroup'+app.appId+'">' +
                '   <button class="btn btn-primary btn-xs" type="button" onclick="downloadApp('+app.appId+')">立即安装</button>'+
                '</div>';
        } else if(app.installStatus=="2") {
            html +=
                '<div class="btn-group btngroup'+app.appId+'">' +
                '   <button class="btn btn-warning btn-xs" type="button">未启用</button>'+
                '   <button type="button" class="btn btn-xs btn-warning dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' +
                '       <span class="caret"></span>' +
                '       <span class="sr-only">Toggle Dropdown</span>' +
                '   </button>' +
                '   <ul class="dropdown-menu">' +
                '       <li><a href="javascript:;" onclick="startApp('+app.appId+')">启用</a></li>' +
                '       <li><a href="javascript:;" onclick="uninstallApp('+app.appId+')">卸载</a></li>' +
                '   </ul>' +
                '</div>';
        } else if(app.installStatus=="3") {
            html +=
                '<div class="btn-group btngroup'+app.appId+'">' +
                '   <button class="btn btn-success btn-xs" type="button">已启用</button>'+
                '   <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' +
                '       <span class="caret"></span>' +
                '       <span class="sr-only">Toggle Dropdown</span>' +
                '   </button>' +
                '   <ul class="dropdown-menu">' +
                '       <li><a href="javascript:;" onclick="stopApp('+app.appId+')">停用</a></li>' +
                '       <li><a href="javascript:;" onclick="uninstallApp('+app.appId+')">卸载</a></li>' +
                '   </ul>' +
                '</div>';
        }
        html += '</div>';
        '</li>';
    }
    $("#installedAppsUL").html(html);

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
    shouldShow = true;
    showProgress(id,30,INSTALLED);
    $.ajax({
        url:'downloadapp.htm?CSRFToken='+$("#CSRFToken").val()+'&id='+id,
        success:function(data) {
            if(data==1) {
                showTipAlert("正在安装应用，请稍后。请勿关闭或刷新页面，以免安装失败。");
            } else if(data==-1) {
                shouldShow = false;
                showTipAlert("安装失败，请检查您录入的每个网站地址信息，确保每个地址都可以正常访问。");
                refreshBtnGroup(id,UNINSTALLED);
            } else if(data==-2) {
                showTipAlert("该应用正在安装。");
            }
        }
    });
}

function showAppDetail(id) {
    $.ajax({
        url:'queryAppDetail.htm?id='+id+"&CSRFToken="+$("#CSRFToken").val(),
        success:function(data) {
            $("#appDetailLogo").attr("src",data.appLogo);
            $("#appDetailName").html(data.appName);
            $("#appDetailDesc").html(data.appDesc);
            $("#appDetailVersion").html("最新版本："+data.appVersion);
            $("#appMemory").html("所需内存："+data.needMemory+" M");
            $("#appDetailIntroduction").html("应用简介："+data.appIntroduction);
        }
    });
}

function startApp(id) {
    shouldShow = true;
    //查询并显示开启进度
    showProgress(id,30,ACTIVE);
	$.ajax({
		url:'startapp.htm?id='+id+"&CSRFToken="+$("#CSRFToken").val(),
		success:function(data) {
            if (data == 1) {
                showTipAlert("应用已正在启动，请稍后。请勿关闭或刷新页面，以免启动失败。");
            } else if (data == -1) {
                shouldShow = false;
                showTipAlert("启动失败，请检查您录入的每个网站地址信息，确保每个地址都可以正常访问。");
                refreshBtnGroup(id, INSTALLED);
            }
        }
	});
}
function stopApp(id) {
    shouldShow = true;
    //查询并显示停止进度
    showProgress(id,0,RESOLVED);
    $.ajax({
        url:'stopapp.htm?id='+id+"&CSRFToken="+$("#CSRFToken").val(),
        success:function(data) {
            if (data == 1) {
                showTipAlert("功能正在停用，请稍后。请勿关闭或刷新页面，以免启动失败。");
            } else if (data == -1) {
                shouldShow = false;
                showTipAlert("停用失败，请检查您录入的每个网站地址信息，确保每个地址都可以正常访问。");
                refreshBtnGroup(id, ACTIVE);
            }
        }
    });
}

function installApp(id) {
    shouldShow = true;
    //查询并显示安装进度
    showProgress(id,0,INSTALLED);
    $.ajax({
        url:'installapp.htm?id='+id+"&CSRFToken="+$("#CSRFToken").val(),
        success:function(data) {
            if (data == 1) {
                showTipAlert("功能正在安装,请稍后。请勿关闭或刷新页面，以免安装失败。");
            } else if (data == -1) {
                shouldShow = false;
                showTipAlert("安装失败，请检查您录入的每个网站地址信息，确保每个地址都可以正常访问。");
                refreshBtnGroup(id, INSTALLED);
            }
        }
    });
}

function uninstallApp(id) {
    shouldShow = true;
    //查询并显示卸载进度
    showProgress(id,0,UNINSTALLED);
    $.ajax({
        url:'uninstallapp.htm?id='+id+"&CSRFToken="+$("#CSRFToken").val(),
        success:function(data) {
            if (data == 1) {
                showTipAlert("功能正在卸载，请稍后。请勿关闭或刷新页面，以免卸载失败。");
            } else if (data == -1) {
                shouldShow = false;
                showTipAlert("卸载失败，请检查您录入的每个网站地址信息，确保每个地址都可以正常访问。");
                refreshBtnGroup(id, RESOLVED);
            }

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
function showProgress(id,progress,state) {
    if(!shouldShow) return;
    $(".btngroup"+id).hide();
    if(progress==undefined||progress==0) progress=30;

    if(progress>=100) {
        if(state==ACTIVE) {
            refreshBtnGroup(id,ACTIVE);
            showConfirmAlert("应用已经启用，刷新页面后即可使用。","refreshPage()");
        } else if(state==RESOLVED) {
            refreshBtnGroup(id,RESOLVED);
            showConfirmAlert("应用已经停用。","refreshPage()");
        } else if(state==UNINSTALLED) {
            refreshBtnGroup(id,UNINSTALLED);
            showConfirmAlert("应用已经卸载。","refreshPage()");
        } else if(state==INSTALLED) {
            refreshBtnGroup(id,INSTALLED);
            showTipAlert("应用已经成功安装。");
        }
        $(".progressbar"+id).removeClass().addClass("progressbar"+id);
        $(".progressbar"+id+" .ui-progressbar-value").hide();
        return;
    }
    $(".progressbar"+id).addClass("process-bar");
    $(".progressbar"+id+" .ui-progressbar-value").show();
    $(".progressbar"+id).each(function() {
        $(this).progressbar({
            value: progress
        });
    });
    $.ajax({
        url:'queryappstate.htm?CSRFToken='+$("#CSRFToken").val()+'&id='+id,
        async:false,
        success:function(data) {

            if(data==state) {
                //如果状态已是active，进度最低从70开始
                if(state==ACTIVE) {
                    $.ajax({
                        url:'isBundleAccessable.htm?CSRFToken='+$("#CSRFToken").val()+'&id='+id,
                        async:false,
                        success:function(result) {
                            if(result==1||result=='1') {
                                progress = 100;
                            } else {
                                progress=progress<85?progress:85;
                            }
                        }
                    });
                } else if(state==RESOLVED||state==UNINSTALLED||state==INSTALLED) {
                    progress=progress>96?progress:96;
                }

            }
            setTimeout("showProgress("+id+","+(progress+4)+","+state+")", 3000);
        }
    });
}

function refreshBtnGroup(id,state) {
    if(state==ACTIVE) {
        $(".btngroup"+id).html(
            '   <button class="btn btn-success btn-xs" type="button">已启用</button>'+
            '   <button type="button" class="btn btn-success btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' +
            '       <span class="caret"></span>' +
            '       <span class="sr-only">Toggle Dropdown</span>' +
            '   </button>' +
            '   <ul class="dropdown-menu">' +
            '       <li><a href="javascript:;" onclick="stopApp('+id+')">停用</a></li>' +
            '       <li><a href="javascript:;" onclick="uninstallApp('+id+')">卸载</a></li>' +
            '   </ul>');
    } else if(state==RESOLVED||state==INSTALLED) {
        $(".btngroup"+id).html(
            '   <button class="btn btn-warning btn-xs" type="button">未启用</button>'+
            '   <button type="button" class="btn btn-warning btn-xs dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">' +
            '       <span class="caret"></span>' +
            '       <span class="sr-only">Toggle Dropdown</span>' +
            '   </button>' +
            '   <ul class="dropdown-menu">' +
            '       <li><a href="javascript:;" onclick="startApp('+id+')">启用</a></li>' +
            '       <li><a href="javascript:;" onclick="uninstallApp('+id+')">卸载</a></li>' +
            '   </ul>');
    } else if(state==UNINSTALLED) {
        $(".btngroup"+id).html( '<button class="btn btn-primary btn-xs" type="button" onclick="installApp('+id+')">立即安装</button>');
    }
    $(".btngroup"+id).show();
}

function refreshPage() {
    location.reload();
}
