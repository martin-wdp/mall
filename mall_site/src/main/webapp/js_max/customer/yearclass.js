/**
 * 宁派电子商务平台前台会员部分 时间选项js文件 
 * @author 	NINGPAI-zhangqiang
 * @since 	2014年4月11日16:23:39
 * @version 0.0.1版
 */
function YearSelector(comeYear){
    this.comeYear = comeYear;
    this.InitYearSelect();
}
/**
 * 设置最大年份
 */
YearSelector.prototype.MinYear = 1900;
/**
 * 获取当前年份
 */
YearSelector.prototype.MaxYear = (new Date()).getFullYear();
YearSelector.prototype.MaxMonth = (new Date()).getMonth()+1;

/**
 * 初始化年份控件
 */
YearSelector.prototype.InitYearSelect = function(){
    for(var i = this.MaxYear; i >= this.MinYear; i--){
        var op = window.document.createElement("OPTION");
        op.value = i;
        op.innerHTML = i;
        this.comeYear.appendChild(op);
    }
};
