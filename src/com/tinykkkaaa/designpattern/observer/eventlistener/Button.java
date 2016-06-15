package com.tinykkkaaa.designpattern.observer.eventlistener;

import com.tinykkkaaa.designpattern.observer.eventlistener.event.ClickEvent;
import com.tinykkkaaa.designpattern.observer.eventlistener.event.DblClickEvent;
import com.tinykkkaaa.designpattern.observer.eventlistener.event.MouseMoveEvent;
import com.tinykkkaaa.designpattern.observer.eventlistener.listener.ClickListener;
import com.tinykkkaaa.designpattern.observer.eventlistener.listener.DblClickListener;
import com.tinykkkaaa.designpattern.observer.eventlistener.listener.MouseMoveListener;

//我们模拟一个html页面的button元素，LZ只添加个别属性，其余属性同理
public class Button {
	private String id;//这相当于id属性
    private String value;//这相当于value属性
    private ClickListener onclick;//我们完全模拟原有的模型，这个其实相当于onclick属性
    private DblClickListener onDblClick;//同理，这个相当于双击属性
    private MouseMoveListener onMouseMove;//同理
    
    public void click(){
    	onclick.click(new ClickEvent(this));
    }
    public void dblClick(){
        onDblClick.dblClick(new DblClickEvent(this));
    }
    public void mouseMove(String x, String y){
    	onMouseMove.mouseMove(new MouseMoveEvent(this, x, y));
    }
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public ClickListener getOnclick() {
		return onclick;
	}
	public void setOnclick(ClickListener onclick) {
		this.onclick = onclick;
	}
	public DblClickListener getOnDblClick() {
		return onDblClick;
	}
	public void setOnDblClick(DblClickListener onDblClick) {
		this.onDblClick = onDblClick;
	}
	public MouseMoveListener getOnMouseMove() {
		return onMouseMove;
	}
	public void setOnMouseMove(MouseMoveListener onMouseMove) {
		this.onMouseMove = onMouseMove;
	}
}
