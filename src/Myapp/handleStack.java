package Myapp;
import java.util.Stack;

/*
 * ���ཫ�������ʽstackת��Ϊ�з��ӷ�ĸ�ĺ������ʽ
 * ����handleStack�����calculatorStack��
 */
public class handleStack {
Stack<Node> posfixStack;
	
	public handleStack(Stack<String> stack) {
		// TODO Auto-generated constructor stub
		Stack<Node> stack1 = new Stack<>();//�м�ջ
		Stack<Node> stack2 = new Stack<>();//�м�ջ
		//String s;
		while(!stack.isEmpty()){
			String string = stack.pop();
			//�����ֱ�ӽ�ջ
			if(string.equals("+")||string.equals("-")||string.equals("*")||string.equals("��")){
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
