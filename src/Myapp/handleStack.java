package Myapp;
import java.util.Stack;

/*
 * ���ཫ�������ʽstackת��Ϊ�з��ӷ�ĸ�ĺ������ʽ
 * ����handleStack�����calculatorStack��
 */
public class handleStack {
Stack<Calculator> calculatorStack;
	
	public handleStack(Stack<String> stack) {
		// TODO Auto-generated constructor stub
		Stack<Calculator> stack1 = new Stack<>();//�м�ջ
		Stack<Calculator> stack2 = new Stack<>();//�м�ջ
		//String s;
		while(!stack.isEmpty()){
			String string = stack.pop();
			//�����ֱ�ӽ�ջ
			if(string.equals("+")||string.equals("-")||string.equals("*")||string.equals("��")){
				Calculator calculator = new Calculator(true,string);
				stack1.push(calculator);
			}
			else if(!string.contains("/")){
				string = string + "/1";
				Calculator calculator = new Calculator(false,string);
				stack1.push(calculator);
			}
			else {
				Calculator calculator = new Calculator(false,string);
				stack1.push(calculator);
			}
		}
		for(Calculator c:stack1){
			stack2.push(c);
		}
		this.calculatorStack = stack2;
//		for(Calculator c:this.calculatorStack){
//			System.out.println(c.operator + c.numerator + "/" + c.denominator);
//		}
	}
}
