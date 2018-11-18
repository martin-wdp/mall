<!doctype html>
<html>
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <title>${topmap.seo.mete!''}</title>
    <meta name="description" content="${(topmap.seo.meteDes)!''}">
    <meta name="keywords" content="">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${basePath}/index_nine/css/header.css"/>
    <link rel="stylesheet" href="${basePath}/index_nine/css/style.css"/>
    <style>
        .dropdown-menu {display:block!important;}
        
        .customer-box {
        position: absolute;
		background: #efefef;
		width: 146px;
		min-height: 150px;
		border: 1px solid #ccc;
		padding: 9px;
		right: 55px;
		display: none;
		}
		.sideBar .customer-box a {width:auto; height:auto; background-color:transparent; border:none; line-height:normal;}
		.close-cs {
		position: absolute!important;
		top: 10px;
		right: 10px;
		background: url(${basePath}/index_nine/images/agree_close1.gif) no-repeat!important;
		width: 10px!important;
		height: 10px!important;
		display: block;
		}
		.customer-box p {overflow:hidden; margin-top:10px;}
		.customer-box p .qq_name {float:right;}
		.customer-box p .qq_img {float:left;}
		.sideBar a span {font-size:12px!important; padding:2px 5px; width:28px!important; line-height:normal!important;}
    </style>
</head>
<body>
<#include "newheader9.ftl"/>
<#include "index9_ftl.ftl"/>
<#include "newbottom.ftl"/>
</body>