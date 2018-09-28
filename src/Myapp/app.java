package Myapp;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.org.apache.regexp.internal.recompile;
import com.sun.xml.internal.bind.v2.model.impl.ModelBuilder;

import sun.nio.cs.ext.ISCII91;
public class app {
	
	public static void main(String[] args) {
		Scanner scan1;
		Scanner scan2;
		System.out.println("使用 -n 参数控制生成题目的个数，例如：Myapp.exe -n 10");
		scan1 = new Scanner(System.in);
		String commend1=scan1.nextLine();
		System.out.println(checkInput(commend1));
		
		int count=0;
		int num=10;//预定义表达式数量
		ArrayList<String> finalExp=new ArrayList<>();
		ArrayList<Node> finalRes=new ArrayList<>();
		do {
			Stack<String> ac=new Stack<String>();
			ac=RPN.toRPN(utilClass.creatAc(50));//没带分母的后缀表达式
			handleStack handleStack=new handleStack(ac);//带分母的后缀表达式
			Node node=new Node();
			Stack<Node> stack=new Stack<Node>();
			stack=node.calculate(handleStack.posfixStack);//计算结果
			if(stack.peek().operator!="#"){
				finalExp.add(utilClass.final_str);
				finalRes.add(stack.peek());
				count++;
			}
		} while (count!=num);
		for(String n:finalExp){
			System.out.println(n);
		}
		for(Node m:finalRes){
			System.out.println(m.numerator+"/"+m.denominator);
		}
//		for(int i=0;i<2;i++){
//			Stack<String> list=new Stack<String>();
//			list=RPN.toRPN(utilClass.creatAc(10));
//			handleStack handleStack = new handleStack(list);
//			Node node = new Node();
//			Stack<Node> posfixStack = handleStack.posfixStack;
//			Stack<Node> poStack =(Stack<Node>) posfixStack.clone();
////			System.out.println(8);
//			Repeat repeat = new Repeat();
////			System.out.println(9);
////			System.out.println(000);
////			for(Node node1:checkStack){
////				System.out.println(000);
////				System.out.print(node1);
////			}
////			Tree tree = new Tree(posfixStack);
//			
//			Stack<Node> resultStack =node.calculate(posfixStack);
//			System.out.println("结果" + resultStack.peek().numerator + "/" + resultStack.peek().denominator);
//			Stack<Node> checkStack = repeat.checkRepeat(poStack);
//			System.out.println();
////		}
//		Stack<Node> exp1 = new Stack<>();
//		Stack<Node> exp2 = new Stack<>();
//		Node node1 = new Node(false, 3, 1);
//		Node node2 = new Node(false, 333, 1);
//		Node node3 = new Node(true, "+");
//		Node node4 = new Node(false, 5, 1);
//		Node node5 = new Node(true, "+");
//		exp1.push(node5);
//		exp1.push(node4);
//		exp1.push(node3);
//		exp1.push(node2);
//		exp1.push(node1);
//		
//		Node node21 = new Node(false, 333, 1);
//		Node node22 = new Node(false, 2, 1);
//		Node node23 = new Node(true, "+");
//		Node node24 = new Node(false, 5, 1);
//		Node node25 = new Node(true, "+");
//		exp2.push(node25);
//		exp2.push(node24);
//		exp2.push(node23);
//		exp2.push(node22);
//		exp2.push(node21);
//		Stack<Integer> exp1 = new Stack<>();
//		exp1.push(1);
//		Stack<Integer> exp2 = exp1;
//		exp1.push(1);
//		if(exp1==exp2) System.out.println(1111111111);
//		Repeat repeat = new Repeat();
//		if (repeat.isEqual(exp1, exp2)) {
//			System.out.println("yes");
//		}
//		if (repeat.IsRepeat(exp1, exp2)) {
//			System.out.println("yes");
//		}
//		else{
//			System.out.println("no");
//		}
	}
	public static boolean checkInput(String input){//正则表达式校验输入命令行
		boolean flag=false;
		try{
			String pattern="^Myapp\\s+(\\-[nr]\\s+){1,2}\\s[0-9]*[1-9][0-9]*$";
			Pattern  regex=Pattern.compile(pattern);
			Matcher matcher=regex.matcher(input);
			flag=matcher.matches();
		}catch(Exception e){
			flag=false;
		}
		return flag;
	}
	
	
	

}
