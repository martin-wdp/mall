/**
 * 宁派电子商务平台前台会员部分 时间选项js文件 
 * @author 	NINGPAI-zhangqiang
 * @since 	2014年4月11日16:23:39
 * @version 0.0.1版
 */
function DateSelector(selYear, selMonth, selDay){
    this.selYear = selYear;
    this.selMonth = selMonth;
    this.selDay = selDay;
    this.InitYearSelect();
    this.InitMonthSelect();
}
/**
 * 设置最大年份
 */
DateSelector.prototype.MinYear = 1900;
/**
 * 获取当前年份
 */
DateSelector.prototype.MaxYear = (new Date()).getFullYear();
DateSelector.prototype.MaxMonth = (new Date()).getMonth()+1;

/**
 * 初始化年份控件
 */
DateSelector.prototype.InitYearSelect = function(){
    for(var i = this.MaxYear; i >= this.MinYear; i--){
        var op = window.document.createElement("OPTION");
        op.value = i;
        op.innerHTML = i;
        this.selYear.appendChild(op);
    }
};

/**
 * 初始化月份控件
 */
DateSelector.prototype.InitMonthSelect = function(){
	
    for(var i = 1; i < 13; i++){
        var op = window.document.createElement("OPTION");
        op.value = i;
        op.innerHTML = i;
        this.selMonth.appendChild(op);
    }
    loadMonthDay(this.selYear[0].value,1);
};
/**
 * 根据年份与月份获取当月的天数
 * @param year 年份
 * @param month 有份
 * @returns 当月的天数
 */
DateSelector.DaysInMonth = function(year, month)
{
    var date = new Date(year, month, 0);
    return date.getDate();
};
/**
 * 加载每月天数
 * @param year 选定年份
 * @param month 选定月份
 */
function loadMonthDay(year,month){
	while(this.selDay.lastChild){
		this.selDay.removeChild(this.selDay.lastChild);
	}
	for(var i = 1; i <= DateSelector.DaysInMonth(year,month); i++){
        var op = window.document.createElement("OPTION");
        op.value = i;
        op.innerHTML = i;
        this.selDay.appendChild(op);
    }
}