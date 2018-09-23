package Myapp;

import java.util.LinkedList;

public class stack {
	private LinkedList<Object> list=new LinkedList<Object>();
	public int top=-1;
	
	public void push(Object value){//入栈
		top++;
		list.addFirst(value);
	}
	
	public Object pop(){//出栈
		Object temp=list.getFirst();
		top--;
		list.removeFirst();
		return temp;
	}
	
	public Object top(){//获取栈顶元素
		return list.getFirst();
	}
}
