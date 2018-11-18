<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html>
<html lang="zh-cn">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>选择促销</title>

    <!-- Bootstrap -->
    <link href="<%=basePath %>css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=basePath %>css/font-awesome.min.css">
    <link href="<%=basePath %>iconfont/iconfont.css" rel="stylesheet">
    <link href="<%=basePath %>css/summernote.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-select.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/zTreeStyle/zTreeStyle.css" rel="stylesheet">
    <link href="<%=basePath %>/css/select2.min.css" rel="stylesheet">
    <link href="<%=basePath %>css/style.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
       <!-- 引用头 -->
   <jsp:include page="../page/header.jsp"></jsp:include>
   
    <div class="page_body container-fluid">
      <div class="row">

 		 <jsp:include page="../page/left.jsp"></jsp:include>
 		 
        <div class="col-lg-20 col-md-19 col-sm-18 main">
          <div class="main_cont">
             <jsp:include page="../page/breadcrumbs.jsp"></jsp:include>

            <h2 class="main_title">${pageNameChild}</h2>

            <div class="common_data p20">

              <div class="marketing container-fluid">
             
             
             
                <div class="row">
                
	                  <div class="col-xs-4">
	                    <div class="marketing_item">
	                      <a href="javascript:;" class="danpincuxiao">
	                        <img alt="" src="${basePath }images/marketing/1.png">
	                        <span>单品促销</span>
	                      </a>
	                      <div class="arrow"></div>
	                    </div>
	                  </div>
	                
	                
	                  <div class="col-xs-4">
	                    <div class="marketing_item">
	                      <a href="javascript:;" class="danpincuxiao">
	                        <img alt="" src="${basePath }images/marketing/5.png">
	                        <span>满减促销</span>
	                      </a>
	                      <div class="arrow"></div>
	                    </div>
	                  </div>
	                  
	                     <div class="col-xs-4">
	                    <div class="marketing_item">
	                      <a href="javascript:;" class="danpincuxiao">
	                        <img alt="" src="${basePath }images/marketing/15.png">
	                        <span>折扣促销</span>
	                      </a>
	                      <div class="arrow"></div>
	                    </div>
	                  </div>
	                  
	                  
	                    
	                    <%--<div class="col-xs-4">--%>
	                    <%--<div class="marketing_item">--%>
	                      <%--<a href="javascript:;" class="danpincuxiao">--%>
	                        <%--<img alt="" src="${basePath }images/marketing/13.png">--%>
	                        <%--<span>满件促销</span>--%>
	                      <%--</a>--%>
	                      <%--<div class="arrow"></div>--%>
	                    <%--</div>--%>
	                  <%--</div>--%>
	                  
	                  
                      <div class="col-xs-4">
	                    <div class="marketing_item">
	                      <a href="javascript:;" class="danpincuxiao">
	                        <img alt="" src="${basePath }images/marketing/12.png">
	                        <span>包邮</span>
	                      </a>
	                      <div class="arrow"></div>
	                    </div>
	                  </div>
	                
	                  
                </div>
                <div class="row">
	                  <div class="col-xs-24">

			                    <div class="market_intro" style="display:none;">
			                      <h4>单品促销</h4>
			                      <p>可为商品设置直降促销</p>
			                      <button type="button" class="btn btn-info" onclick="createMarket('DP');">立即添加</button>
			                     <!--  <button type="button" class="btn btn-default">查看教程</button> -->
			                    </div>
			             
			           			  <div class="market_intro" style="display:none;">
			                      <h4>满减促销</h4>
			                      <p>可为商品设置满减促销和满折促销</p>
			                      <button type="button" class="btn btn-info" onclick="createMarket('MJO');">立即添加</button>
			                     <!--  <button type="button" class="btn btn-default">查看教程</button> -->
			                    </div>
			                    
			                       <div class="market_intro" style="display:none;">
			                      <h4>折扣</h4>
			                      <p>可为多个不同商品分别设置不同的折扣促销</p>
			                      <button type="button" class="btn btn-info" onclick="createMarket('ZK');">立即添加</button>
			                     <!--  <button type="button" class="btn btn-default">查看教程</button> -->
			                    </div>
			                    
			                       <%--<div class="market_intro" style="display:none;">--%>
			                      <%--<h4>满件</h4>--%>
			                      <%--<p>可为多个不同商品设置满够件数打折促销和满够件数多少钱促销</p>--%>
			                      <%--<button type="button" class="btn btn-info" onclick="createMarket('MJP');">立即添加</button>--%>
			                     <%--<!--  <button type="button" class="btn btn-default">查看教程</button> -->--%>
			                    <%--</div>--%>
			                    
                                <div class="market_intro" style="display:none;">
			                      <h4>包邮</h4>
			                      <p>可为商品设置包邮促销</p>
			                      <button type="button" class="btn btn-info" onclick="createMarket('BY');">立即添加</button>
			                     <!--  <button type="button" class="btn btn-default">查看教程</button> -->
			                    </div>
                  </div>
                </div>
               
                
                
                
                
                <div class="row">
	               
				                  <div class="col-xs-4">
				                    <div class="marketing_item">
				                      <a href="javascript:;">
				                        <img alt="" src="${basePath }images/marketing/10.png">
				                        <span>团购</span>
				                      </a>
				                      <div class="arrow"></div>
				                    </div>
				                  </div>
				           		<div class="col-xs-4">
				                    <div class="marketing_item">
				                      <a href="javascript:;">
				                        <img alt="" src="${basePath }images/marketing/11.png">
				                        <span>抢购</span>
				                      </a>
				                      <div class="arrow"></div>
				                    </div>
                                </div>

                </div>
                <div class="row">
                  <div class="col-xs-24">
                   
			                    <div class="market_intro" style="display:none;">
			                      <h4>团购</h4>
			                      <p>可为商品设置参加团购促销</p>
			                     <button type="button" class="btn btn-info" onclick="createMarket('TG');">立即添加</button>
			                      <!-- <button type="button" class="btn btn-default">查看教程</button> -->
			                    </div>
			            		 <div class="market_intro" style="display:none;">
			                      <h4>抢购</h4>
			                      <p>可为商品设置参加抢购促销</p>
			                     <button type="button" class="btn btn-info" onclick="createMarket('QG');">立即添加</button>
			                      <!-- <button type="button" class="btn btn-default">查看教程</button> -->
			                    </div>
                  </div>
                </div>
                
               
               
              </div>

            </div>

          </div>
        </div>
      </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="scanFocus"  role="dialog">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">联想手机S898t (耀世金)(黄金斗士S8)</h4>
          </div>
          <div class="modal-body">
            <table class="table table-striped table-hover">
              <thead>
              <tr>
                <th>Email</th>
                <th>会员用户名</th>
                <th>手机</th>
                <th>商品名称</th>
                <th>货号</th>
                <th>关注时间</th>
              </tr>
              </thead>
              <tbody>
              <tr>
                <td>694113006@qq.com</td>
                <td>ningpai</td>
                <td>13917732453</td>
                <td>Apple iPhone 6 16G 金 </td>
                <td>20140919111</td>
                <td>2014-10-27 14:46:43</td>
              </tr>
              <tr>
                <td>694113006@qq.com</td>
                <td>ningpai</td>
                <td>13917732453</td>
                <td>Apple iPhone 6 16G 金 </td>
                <td>20140919111</td>
                <td>2014-10-27 14:46:43</td>
              </tr>
              <tr>
                <td>694113006@qq.com</td>
                <td>ningpai</td>
                <td>13917732453</td>
                <td>Apple iPhone 6 16G 金 </td>
                <td>20140919111</td>
                <td>2014-10-27 14:46:43</td>
              </tr>
              <tr>
                <td>694113006@qq.com</td>
                <td>ningpai</td>
                <td>13917732453</td>
                <td>Apple iPhone 6 16G 金 </td>
                <td>20140919111</td>
                <td>2014-10-27 14:46:43</td>
              </tr>
              <tr>
                <td>694113006@qq.com</td>
                <td>ningpai</td>
                <td>13917732453</td>
                <td>Apple iPhone 6 16G 金 </td>
                <td>20140919111</td>
                <td>2014-10-27 14:46:43</td>
              </tr>
              </tbody>
            </table>
            <div class="table_foot">
              <div class="table_pagenav pull-right">
                <div class="meneame">
                  <span class="disabled"> 上一页 </span>
                  <span class="current"> 1 </span>
                  <a href="#?page=2"> 2 </a>
                  <a href="#?page=3"> 3 </a>
                  <a href="#?page=4"> 4 </a>
                  <a href="#?page=5"> 5 </a>
                  <a href="#?page=6"> 6 </a>
                  <a href="#?page=7"> 7 </a>
                  ...
                  <a href="#?page=199"> 199 </a>
                  <a href="#?page=200"> 200 </a>
                  <a href="#?page=2"> 下一页 </a>
                </div>
              </div>
              <div class="table_ctrl pull-left">
                <form role="form" class="form-horizontal">
                  <label class="control-label col-sm-12">每页显示：</label>
                  <div class="col-sm-12">
                    <select class="form-control">
                      <option>10</option>
                      <option>20</option>
                      <option>50</option>
                      <option>100</option>
                    </select>
                  </div>
                </form>
              </div>
              <div class="clr"></div>
            </div>
          </div>
        </div>
      </div>
    </div>



    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="<%=basePath %>js/bootstrap.min.js"></script>
    <script src="<%=basePath %>js/summernote.min.js"></script>
    <script src="<%=basePath %>js/language/summernote-zh-CN.js"></script>
    <script src="<%=basePath %>js/bootstrap-select.min.js"></script>
    <script src="<%=basePath %>js/common.js"></script>
    <script src="<%=basePath %>/js/select2.min.js"></script>
    <script>
      $(function(){

        /* 为选定的select下拉菜单开启搜索提示 */
        $('select[data-live-search="true"]').select2();
        /* 为选定的select下拉菜单开启搜索提示 END */

        /* 富文本编辑框 */
        $('.summernote').summernote({
          height: 300,
          tabsize: 2,
          lang: 'zh-CN'
        });

        /* 选择规格值 */
        $('.spec_set input').change(function(){
          if($(this).is(':checked')){
            $(this).parent().parent().next().slideDown('fast');
          }
          else {
            $(this).parent().parent().next().slideUp('fast');
          }
        });

        /* 下面是表单里面的填写项提示相关的 */
        $('.xiaoshoujia').popover({
          content : '此价格只用于显示，以商品定价为商品销售价',
          trigger : 'hover'
        });

        $('.marketing_item').click(function(){
          var theIndex = $(this).index('.marketing_item');
          if(!($('.market_intro').eq(theIndex).is(':visible'))){
            $('.market_intro').slideUp('fast').eq(theIndex).slideDown('fast');
            $('.marketing_item').removeClass('marketing_item_open');
            $(this).addClass('marketing_item_open');
          }
          else{
            $('.market_intro').slideUp();
            $(this).removeClass('marketing_item_open');
          }

        });

      });
      
      function createMarket(codexId){
          window.location.href="createallmarket.htm?CSRFToken=${token}&marketFlag="+codexId;
      }
    </script>
  </body>
</html>
