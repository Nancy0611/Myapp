package Myapp;

import java.util.LinkedList;

public class stack {
	private LinkedList<Object> list=new LinkedList<Object>();
	public int top=-1;
	
	public void push(Object value){//��ջ
		top++;
		list.addFirst(value);
	}
	
	public Object pop(){//��ջ
		Object temp=list.getFirst();
		top--;
		list.removeFirst();
		return temp;
	}
	
	public Object top(){//��ȡջ��Ԫ��
		return list.getFirst();
	}
}
