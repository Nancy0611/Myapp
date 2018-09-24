package Myapp;

import Myapp.stack;

public class RPN {
	
	/**
     *  将中序表达式转换成后序表达式
     * @param str 生成的中序表达式
     */
	public static String toRPN(String str){
		stack stack=new stack();//栈
		String rpn=" ";//后缀表达式
		String operate;//运算符
		for(int i=0;i<str.length();i++){
			char ch=str.charAt(i);
			if(isOperator(ch)){//当前字符为运算符
				if(stack.top==-1 || ch=='('){//栈为空或者为（直接入栈
					stack.push(ch);
				}else{
					if(ch==')'){//如果为）
						while(true){//将（后的运算符出栈并加到后续表达式中
							if(stack.top!=-1 && (!stack.top().toString().equals("("))){
								operate=stack.pop().toString();
//								System.out.println(operate);
								rpn+=operate;
							}else{
								if(stack.top!=-1)//如果栈顶元素为（
									stack.pop();
								break;
							}
						}
					}else{
						while(true){//栈不为空，优先级低
							if(stack.top!=-1 && priority(ch+"",stack.top()+"")){
								operate=stack.pop()+"";
//								System.out.println(operate);
								if(!operate.equals("(")){
									rpn+=operate;
								}
							}else{
								break;
							}
						}
						stack.push(ch);
					}
				}
				
			}else{
				rpn+=ch;//操作数
			}
		}
		while(stack.top!=-1){
			operate=stack.pop()+"";
			if(!operate.equals("("))
				rpn+=operate;
		}
		System.out.println(rpn);
		return rpn;
	}
	
	/**
	 * 判断是否为运算符
	 */
	public static boolean isOperator(char ch){
		if((ch=='+')||(ch=='-')||(ch=='*')||(ch=='÷')||(ch=='(')||(ch==')'))
			return true;
		else 
			return false;
	}

	/**
	 * 设置运算符的优先级别
	 * @param operatorout当前中序表达式字符
	 * @param operatorin栈中字符
	 * @return
	 */
	public static boolean priority(String operatorout, String operatorin) {
		int m = -1, n = -1;
		String addop[][] = { { "+", "-", "*", "÷", "(", ")" },
				{ "+", "-", "*", "÷", "(", ")" } };
		int first[][] = { { 1, 1, 2, 2, 2, 0 }, { 1, 1, 2, 2, 2, 0 },
				{ 1, 1, 1, 1, 2, 0 }, { 1, 1, 1, 1, 2, 0 },
				{ 2, 2, 2, 2, 2, 0 }, { 2, 2, 2, 2, 2, 2 } };
		for (int i = 0; i < 6; i++) {
			if (operatorin.equalsIgnoreCase(addop[0][i]))
				m = i;
		}
		for (int i = 0; i < 6; i++) {
			if (operatorout.equalsIgnoreCase(addop[1][i]))
				n = i;
		}
		if (m == -1 && n == -1)
			return false;
		else if (m == -1 && n != -1)
			return false;
		else if (m != -1 && n == -1)
			return true;
		else if (first[m][n] == 1) {
			return true;
		} else
			return false;
	}
}
