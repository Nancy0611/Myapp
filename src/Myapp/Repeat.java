package Myapp;
import java.util.ArrayList;
import java.util.Stack;

/**
 * 构造方法：生成查重表达式
 * @author LHY
 *
 */
public class Repeat {
	/**
	 * @param profixStack 后缀表达式栈
	 */
	public Stack<Node> checkRepeat(Stack<Node> profixStack) {
		// TODO Auto-generated constructor stub
		Stack<Node> numberStack = new Stack<>(); //构造一个中间栈，存放数字
		Stack<Node> checkStack = new Stack<>(); //存放查重表达式栈
				
//		System.out.println(1);
		Node bookNode = new Node(true, 0,0);
//		System.out.println(2);
		Node node1 = new Node();
		Node node2 = new Node();
		while(!profixStack.isEmpty()){//扫描后缀表达式栈直至其为空
			Node proStack_top = profixStack.pop();//开始扫描第一个
			if (!proStack_top.isOperator&&!(proStack_top.numerator==0&&proStack_top.denominator==0)) {//若后缀表达式栈顶元素为数字。若非#则进numberStack
					numberStack.push(proStack_top);
//					System.out.println(proStack_top);
			}
//			else if (proStack_top.isOperator&&proStack_top.numerator==0&&proStack_top.denominator==0) {
//				numberStack.pop();
//			}
			else if (proStack_top.isOperator) {//后缀表达式栈顶为运算符,则进checkStack,再pop两个数字,并把#压进数字
				checkStack.push(proStack_top);
				if (numberStack.size() > 1) {
					if (!(node1=numberStack.pop()).isOperator&&!(node1.numerator==0)&&!(node1.denominator==0)) {//非#
						checkStack.push(node1);
					}
					if (!(node2=numberStack.pop()).isOperator&&!(node2.numerator==0)&&!(node2.denominator==0)) {
						checkStack.push(node2);
					}
				}
				numberStack.push(bookNode);
			}
			
		}//end while
		System.out.println("size"+checkStack.size());
		for(Node node:checkStack){
			if (node.isOperator) {
				System.out.print(node.operator + " ");
			}else if(!node.isOperator){
				System.out.print(node.numerator + "/" + node.denominator + " ");
			}
		}
		return checkStack;
		
	}
	
	public  boolean IsRepeat(Stack<Node> exp1,Stack<Node> exp2){
		Repeat repeat=new Repeat();
		//转成查重表达式
		ArrayList<Node> temp=new ArrayList<Node>();//中间存放栈1
		Node tempNode=new Node();//中间交换结点
		Stack<Node> checkRepeat1=repeat.checkRepeat(exp1);
		Stack<Node> checkRepeat2=repeat.checkRepeat(exp2);
		Stack<Node> newStack=new Stack<Node>();//交换后的新栈
		int lengthRe1=checkRepeat1.size();
		int lengthRe2=checkRepeat2.size();
		System.out.println(1);
		if(lengthRe1!=lengthRe2) return false;//若长度不相等，则表达式一定不同
		System.out.println(2);
		for(Node n:checkRepeat1){
			temp.add(n);
		}
		if (this.isEqual(checkRepeat1, checkRepeat2)) {//完全一样则返回true
			return true;
		}

		if(temp.get(0).operator.equals("+")||temp.get(0).operator.equals("*")){//只有加或乘的情况才可能出现 交换左右操作数当做重复的表达式
			tempNode=temp.get(1);
			temp.set(1, temp.get(2));
			temp.set(2, tempNode);
		}
		for(Node p:temp){
			newStack.push(p);
		}
		if(this.isEqual(newStack, checkRepeat2)) return true;//若交换后也相等则重复
		System.out.println(3);
		return false;
	}
	
	public boolean  isEqual(Stack<Node> stack1,Stack<Node> stack2) {
		Stack<Node> s1 = new Stack<>();
		Stack<Node> s2 = new Stack<>();
		
		Node s1_top;
		Node s2_top;
		for(Node node1:stack1){
			s1.push(node1);
		}
		for(Node node2:stack2){
			s2.push(node2);
		}
		
		while (!s1.isEmpty()&&!s2.isEmpty()) {
			s1_top = s1.pop();
			s2_top = s2.pop();
			if (s1_top.isOperator) {//若s1栈顶为运算符
				if (s2_top.isOperator&&(!s2_top.operator.equals(s1_top.operator))) {//s2都为运算符但s1不等于s2
					return false;
				}
				else if (!s2_top.isOperator) {//s1为运算符，s2非运算符
					return false;
				}
			}
			else if (!s1_top.isOperator) {//若s1操作数
				if (s2_top.isOperator) {//s2为运算符
					return false;
				}
				else if (!s2_top.isOperator&&(s2_top.compareFraction(s1_top, s2_top)!=1)) {//s2为操作数但不等于s1
				return false;	
				}
			}
			
		}		
		return true;
	}
}
