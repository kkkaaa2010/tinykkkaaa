/*
(function($) {…})(jQuery);
1）、原理：
这实际上是匿名函数，如下：
function(arg){…}
这就定义了一个匿名函数，参数为arg
而调用函数时，是在函数后面写上括号和实参的，由于操作符的优先级，函数本身也需要用括号，即：
(function(arg){…})(param)
这就相当于定义了一个参数为arg的匿名函数，并且将param作为参数来调用这个匿名函数
而(function($){…})(jQuery)则是一样的，之所以只在形参使用$，是为了不与其他库冲突，所以实参用jQuery
相当于funtion output(s){…};output(jQuery);或者var fn=function(s){…};fn(jQuery);
2）、作用（非常有用）：
这种写法的最大好处是形成闭包。在(function($) {…})(jQuery)在内部定义的函数和变量只能在此范围内有效。
*/
(function($){
$.fn.changeText = function(options) {
	var defaults = {
		'color' : 'blue',
		'fontSize' : '12px'
	};
	var settings = $.extend({},defaults, options);
	return this.css({
		'color' : settings.color,
		'fontSize' : settings.fontSize
	});
	
	/*
	this.css('color','blue');
	this.each(function(){
		$(this).append(' ' + $(this).attr('href'));
	});
	*/
};
})(jQuery);