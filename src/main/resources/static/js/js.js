// sidebar 高度设置
$(function(){
	var $h = $(document).height();
	var $screenW = $(window).width();
	if($screenW >= 768) {
		$('.sidebar').eq(0).css('height',$h);
	} else {
		$('.sidebar').eq(0).css('height',64 + "px");
	}
})
//限制输入框只能输入正整数或者两位小数
function clearNoNum(obj) {
        //先把非数字的都替换掉，除了数字和.
        obj.value = obj.value.replace(/[^\d.]/g,"");
        //必须保证第一个为数字而不是.
        obj.value = obj.value.replace(/^\./g,"");
        //保证只有出现一个.而没有多个.
        obj.value = obj.value.replace(/\.{2,}/g,".");
        //保证.只出现一次，而不能出现两次以上
        obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
        obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3'); //只能输入两个小数
}
//限制输入框只能输入正整数
function setInteger(obj) {

    if(obj.value.length==1) {
        obj.value=obj.value.replace(/[^1-9]/g,'');
    } else {
        obj.value=obj.value.replace(/\D/g,'');
    }
}