package Myapp;

import java.util.ArrayList;
import java.util.Stack;

public class RPN {
	
	/**
     *  将中序表达式转换成后序表达式
     * @param str 生成的中序表达式
     */
	public static Stack<String> toRPN(ArrayList<String> list){
		Stack<String> stack=new Stack<String>();//栈
		Stack<String> right=new Stack<String>();//右序表达式
		String operate;//运算符
			
		for(int i=0;i<list.size();i++){
			String ch=list.get(i);
//			System.out.println(ch);
			if(isOperator(ch)){//当前字符为运算符
				if(stack.empty()==true || ch=="("){//栈为空或者为（直接入栈
					stack.push(ch);
//					System.out.println("栈为空或者为（直接入栈" + ch);
//					System.out.println(stack.peek());
				}else{//非栈空、非左括号
//					System.out.println("非栈空、非左括号"+ch);
					if(ch==")"){//如果为）
						while(true){//将（后的运算符出栈并加到后续表达式中
//							System.out.println("111");
							if((!stack.empty()) && (!stack.peek().equals("("))){
//								System.out.println("111111");
								operate=stack.pop();
//								System.out.println(operate);
								right.push(operate);
							}else{
								if(!stack.empty())//如果栈顶元素为（
									stack.pop();
								break;
							}
						}
					}else{//非栈空、非左括号、非右括号
//						System.out.println(0);
						while(true){//栈不为空，优先级低
//							System.out.println(2);
							if(!stack.empty() && priority(ch,stack.peek())){
								operate=stack.pop()+"";
//								System.out.println(22);
//								System.out.println(operate);
								if(!operate.equals("(")){
									right.push(operate);
								}
							}else{
								break;
							}
						}
						stack.push(ch+"");
					}
				}
				
			}else{
				right.push(ch+"");//操作数
			}
		}
		while(!stack.empty()){
			operate=stack.pop()+"";
			if(!operate.equals("("))
				right.push(operate);
		}
		
//		Stack<String> newRight = new Stack<>();
//		while (!right.isEmpty()) {
//			System.out.print(right.peek());
//			newRight.push(right.pop());
//		}
//		System.out.println();
//		System.out.println(right.peek());
//		System.out.println();
		return right;
	}
	
	/**
	 * 判断是否为运算符
	 */
	public static boolean isOperator(String ch){
		if((ch.equals("+"))||(ch.equals("-"))||(ch.equals("*"))||(ch.equals("÷"))||(ch.equals("("))||(ch.equals(")")))
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
