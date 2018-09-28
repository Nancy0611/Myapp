package Myapp;
import java.util.Stack;

/*
 * 该类将后续表达式stack转化为有分子分母的后续表达式
 * 存于handleStack对象的calculatorStack中
 */
public class handleStack {
Stack<Node> posfixStack;
	
	public handleStack(Stack<String> stack) {
		// TODO Auto-generated constructor stub
		Stack<Node> stack1 = new Stack<>();//中间栈
		Stack<Node> stack2 = new Stack<>();//中间栈
		//String s;
		while(!stack.isEmpty()){
			String string = stack.pop();
			//运算符直接进栈
			if(string.equals("+")||string.equals("-")||string.equals("*")||string.equals("÷")){
				Node node = new Node(true,string);
				stack1.push(node);
			}
			else if(!string.contains("/")){
				string = string + "/1";
				Node node = new Node(false,string);
				stack1.push(node);
			}
			else {
				Node calculator = new Node(false,string);
				stack1.push(calculator);
			}
		}
		for(Node c:stack1){
			stack2.push(c);
		}
		this.posfixStack = stack2;
//		for(Calculator c:this.calculatorStack){
//			System.out.println(c.operator + c.numerator + "/" + c.denominator);
//		}
	}
}
