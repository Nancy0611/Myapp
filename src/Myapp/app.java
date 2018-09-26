package Myapp;
import java.util.Stack;
public class app {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String string1 = "1";
//		String string2 = "4/5";
//		String string3 = "+";
//		String string4 ="1'2/3";
//		String string5 ="4";
//		String string6 ="+";
//		String string7 ="-";
//		Stack<String> stack = new Stack<>();
//		stack.push(string1);
//		stack.push(string2);
//		stack.push(string3);	
//		stack.push(string4);
//		stack.push(string5);
//		stack.push(string6);
//		stack.push(string7);
//		handleStack handleStack = new handleStack(stack);
//		Calculator calculator = new Calculator();
//		Stack<Calculator> stack2 =calculator.calculate(handleStack.calculatorStack);
////		System.out.println("lll");
//		System.out.println(stack2.peek().numerator + "/" + stack2.peek().denominator);
		for(int i=0;i<10;i++){
			Stack<String> list=new Stack<String>();
			list=RPN.toRPN(utilClass.creatAc(10));
			handleStack handleStack = new handleStack(list);
			Calculator calculator = new Calculator();
			Stack<Calculator> stack2 =calculator.calculate(handleStack.calculatorStack);
			System.out.println(stack2.peek().numerator+"/"+stack2.peek().denominator);
		}
	}

}
