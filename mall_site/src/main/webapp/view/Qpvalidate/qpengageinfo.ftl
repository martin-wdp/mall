<!DOCTYPE html>
<html>
<head>
<#assign basePath=request.contextPath>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="renderer" content="webkit">
    <title>企业认证</title>
</head>


<script src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.11.1/jquery.min.js"></script>

<script type="text/javascript" src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://cdn.staticfile.org/twitter-bootstrap/3.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript"
        src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript"
        src="http://cdn.bootcss.com/bootstrap-datepicker/1.4.0/locales/bootstrap-datepicker.zh-CN.min.js"></script>

<script>

    $(document).ready(function () {
        loadProvice();
    });
    /**
     * 加载省份
     */
    function loadProvice() {
        //var options = "<option value='' >"+"请选择"+"</option>";
        $.ajax({
            type: 'post',
            url: 'getQPAllProvince.html',
            async: false,
            success: function (data) {
                var options = "";
                options += "<option value=''>" + "请选择" + "</option>";
                for (var i = 0; i < data.length; i++) {
                    var province = data[i];
                    options += "<option value='" + province.provinceId + "'>" + province.provinceName + "</option>";
                }
                $(".province").html(options);
            }
        });
    }
    ;

    /**
     * 加载城市
     * @param pid 省份编号
     */
    function loadCity(pid) {
        var paramStr = "provinceId=" + pid;
        $.ajax({
            type: 'post',
            url: 'getQPAllCityByPid.html',
            data: paramStr,
            async: false,
            success: function (data) {
                if (data.length != 0) {
                    var options = "";
                    options += "<option value=''>" + "请选择" + "</option>";

                    for (var i = 0; i < data.length; i++) {
                        var city = data[i];
                        options += "<option value='" + city.cityId + "'>" + city.cityName + "</option>";
                    }
                    $(".city").html(options);
                } else {
                    $(".city").html("<option value='' >" + "请选择" + "</option>");
                    $(".district").html("<option value='' >" + "请选择" + "</option>");
                    /*$("#street").html("<option value='' >"+"请选择"+"</option>");*/
                }
            }
    });
    }

    function loadDistrict(pid) {
        var paramStr = "cityId=" + pid;
        $.ajax({
            type: 'post',
            url: 'getQPAllDistrictByCid.html',
            data: paramStr,
            async: false,
            success: function (data) {
                if (data.length != 0) {
                    var options = "";
                    options += "<option value=''>" + "请选择" + "</option>";

                    for (var i = 0; i < data.length; i++) {
                        var district = data[i];
                        options += "<option value='" + district.districtId + "'>" + district.districtName + "</option>";
                    }
                    $('.district').html(options);
                } else {
                    $(".district").html("<option value='' >" + "请选择" + "</option>");
                }
            }
        });
    }


</script>

<body>
<div class="container">
    <div class="store">
        <div class="wrapper-app">
            <div id="header">

            <#include "../index/qptop.ftl">

            </div>

            <div id="content">
                <div class="create-wrapper">
                    <form method="post" id="aptitude_from" action="${basePath}/saveEnCertInfo.html"
                          enctype="multipart/form-data" >
                        <div class="form-item">
                            <h3>企业认证信息${basePath}</h3>

                            <div class="form-wp">
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>汽修厂名称：</label>

                                    <div class="controls col-xs-7">
                                        <input type="text" class="form-control" maxlength="15" name="company_name"
                                               id="company_name"/>

                                        <p id=company_name><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                        <p id=company_name><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>汽修厂类型：</label>


                                    <div class="controls col-xs-7">
                                        <input type="radio" name="company_type" value="0"/>维修厂
                                        <input type="radio" checked="checked" name="company_type" value="1"/>4s店
                                        <input type="radio"  name="company_type" value="2"/>快修连锁


                                </div>

                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>门头照片：</label>

                                    <div class="controls col-xs-7" style="margin-top:7px;">
                                        <input type="file" name="company_pic"
                                               id="company_pic"/><#--<a class="blu_bt" href="javascript:;">取消</a>-->
                                        <div class="alert">
                                            以下所需要上传电子版资质仅支持JPG、GIF、PNG格式的图片，大小不超过1M。请确保图片清晰，文字可辨并有清晰的红色公章
                                        </div>
                                    </div>
                                </div>
                                    <div class="form-group">
                                        <label class="control-label col-xs-3"><b>*</b>所属地区：</label>

                                        <div class="controls col-xs-7">
                                            <div class="row">
                                                <div class="col-xs-4">
                                                    <select name="enterprise_province" id="province"
                                                            class="form-control sm-sel province"
                                                            onchange="loadCity(this.value)"
                                                            >
                                                        <option value="">请选择</option>
                                                    </select>
                                                </div>
                                                <div class="col-xs-4">
                                                    <select name="enterprise_city" id="city"
                                                            onchange="loadDistrict(this.value)"
                                                            class="form-control sm-sel city" >
                                                        <option value="">请选择</option>
                                                    </select>
                                                </div>
                                                <div class="col-xs-4">
                                                    <select name="enterprise_county" id="district"
                                                            class="form-control sm-sel district" >
                                                        <option value="">请选择</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <p id=companyEmpNumTip class=hide><i></i><i
                                                    class=border></i><s></s><span></span></p>
                                        </div>
                                    </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>企业详细地址：</label>

                                    <div class="controls col-xs-7">
                                        <input type="text" class="form-control" maxlength="15" name="company_address" id="company_address"
                                               />

                                        <p id=bussIdTip><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>法人：</label>

                                    <div class="controls col-xs-7">
                                        <input maxlength="15" name="buss_legal_name" id="buss_legal_name"
                                                type="text" class="form-control"/>

                                        <p id=bussAddrTip><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>法人身份证号：</label>

                                    <div class="controls col-xs-7">
                                        <input class="form-control datepicker companyCreTime" type="text"
                                                name="buss_legal_card_id" id=""/>

                                        <p id=companyCreTimeTip><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>注册资本：</label>

                                    <div class="controls col-xs-7">
                                        <div class="row">
                                            <div class="col-xs-5">
                                                <input type="text"

                                                        name="company_capital" id=""/>
                                            </div>

                                        </div>
                                        <p id=bussDateTip><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>
                                    <div class="form-group">
                                        <label class="control-label col-xs-3"><b>*</b>经营品牌：</label>

                                        <div class="controls col-xs-7">
                                            <input class="form-control datepicker companyCreTime" type="text"
                                                    name="buss_brand" id=""/>

                                            <p id=companyCreTimeTip><i></i><i class=border></i><s></s><span></span></p>
                                        </div>
                                    </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>经营范围：</label>

                                    <div class="controls col-xs-7">
                                        <textarea name="company_pic" id="company_pic" }</textarea>

                                        <p id=bussRangeTip><i></i><i class=border></i><s></s><span></span></p>
                                    </div>
                                </div>
                                    <div class="form-group">
                                        <label class="control-label col-xs-3"><b>*</b>企业资质：</label>

                                        <div class="controls col-xs-7">
                                        <textarea name="enterprise_aptitude" id="enterprise_aptitude"
                                                  class="form-control">${(info.bussRange)!''}</textarea>

                                            <p id=bussRangeTip><i></i><i class=border></i><s></s><span></span></p>
                                        </div>
                                    </div>
                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>营业执照电子版：</label>

                                    <div class="controls col-xs-7" style="margin-top:7px;">
                                        <input type="file" name="buss_url" id="buss_url"/>

                                        <div class="alert">
                                            以下所需要上传电子版资质仅支持JPG、GIF、PNG格式的图片，大小不超过1M。请确保图片清晰，文字可辨并有清晰的红色公章
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-item">


                            <div class="form-wp">
                                <div class="form-group">

                                <div class="form-group">
                                    <label class="control-label col-xs-3"><b>*</b>组织机构代码证：</label>

                                    <div class="controls col-xs-7" style="margin-top:7px;">
                                        <input type="file" name="buss_dept_no" id="buss_dept_no"/>

                                        <div class="alert">
                                            以下所需要上传电子版资质仅支持JPG、GIF、PNG格式的图片，大小不超过1M。请确保图片清晰，文字可辨并有清晰的红色公章
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-item">


                            <div class="form-wp">
                                <div class="form-group">
                                    <label class="control-label col-xs-3">一般纳税人证明：</label>

                                    <div class="controls col-xs-7" style="margin-top:7px;">
                                        <input type="file" name="buss_tax_regist"/>

                                        <div class="alert">
                                            以下所需要上传电子版资质仅支持JPG、GIF、PNG格式的图片，大小不超过1M。请确保图片清晰，文字可辨并有清晰的红色公章
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                            <div class="form-item">
                                <h3>联系方式</h3>

                                <div class="form-wp">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3">联系人：</label>

                                        <div class="controls col-xs-7" style="margin-top:7px;">
                                            <input type="text" name="company_contact_name"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-wp">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3">联系电话：</label>

                                        <div class="controls col-xs-7" style="margin-top:7px;">
                                            <input type="text" name="company_contact_tel"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-wp">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3">email：</label>

                                        <div class="controls col-xs-7" style="margin-top:7px;">
                                            <input type="text" name="company_email"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-wp">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3">通讯地址：</label>

                                        <div class="controls col-xs-7" style="margin-top:7px;">
                                            <input type="text" name="company_contact_addr"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-wp">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3">固定电话：</label>

                                        <div class="controls col-xs-7" style="margin-top:7px;">
                                            <input type="text" name="company_contact_tel"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-wp">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3">传真：</label>

                                        <div class="controls col-xs-7" style="margin-top:7px;">
                                            <input type="text" name="company_contact_cz"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-wp">
                                    <div class="form-group">
                                        <label class="control-label col-xs-3">邮编：</label>

                                        <div class="controls col-xs-7" style="margin-top:7px;">
                                            <input type="text" name="company_contact_yb"/>
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="form-item">
                            <div class="form-wp">
                                <div class="form-group">
                                    <label class="col-xs-3"></label>

                                    <div class="controls col-xs-7">
                                        <button class="btn btn-large btn-primary" type="reset"
                                                >重置
                                        </button>
                                        <button class="btn btn-large btn-primary apti_btn" type="submit">提交认证信息</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="footer">
        <#include "../index/qpfoot.ftl">
        </div>
    </div>
</div>

</body>


</html>
