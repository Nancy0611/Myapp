package Myapp;
import java.util.ArrayList;
import java.util.Stack;

/**
 * ���췽�������ɲ��ر��ʽ
 * @author LHY
 *
 */
public class Repeat {
	/**
	 * @param profixStack ��׺���ʽջ
	 */
	public Stack<Node> checkRepeat(Stack<Node> profixStack) {
		// TODO Auto-generated constructor stub
		Stack<Node> numberStack = new Stack<>(); //����һ���м�ջ���������
		Stack<Node> checkStack = new Stack<>(); //��Ų��ر��ʽջ
				
//		System.out.println(1);
		Node bookNode = new Node(true, 0,0);
//		System.out.println(2);
		Node node1 = new Node();
		Node node2 = new Node();
		while(!profixStack.isEmpty()){//ɨ���׺���ʽջֱ����Ϊ��
			Node proStack_top = profixStack.pop();//��ʼɨ���һ��
			if (!proStack_top.isOperator&&!(proStack_top.numerator==0&&proStack_top.denominator==0)) {//����׺���ʽջ��Ԫ��Ϊ���֡�����#���numberStack
					numberStack.push(proStack_top);
//					System.out.println(proStack_top);
			}
//			else if (proStack_top.isOperator&&proStack_top.numerator==0&&proStack_top.denominator==0) {
//				numberStack.pop();
//			}
			else if (proStack_top.isOperator) {//��׺���ʽջ��Ϊ�����,���checkStack,��pop��������,����#ѹ������
				checkStack.push(proStack_top);
				if (numberStack.size() > 1) {
					if (!(node1=numberStack.pop()).isOperator&&!(node1.numerator==0)&&!(node1.denominator==0)) {//��#
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
		//ת�ɲ��ر��ʽ
		ArrayList<Node> temp=new ArrayList<Node>();//�м���ջ1
		Node tempNode=new Node();//�м佻�����
		Stack<Node> checkRepeat1=repeat.checkRepeat(exp1);
		Stack<Node> checkRepeat2=repeat.checkRepeat(exp2);
		Stack<Node> newStack=new Stack<Node>();//���������ջ
		int lengthRe1=checkRepeat1.size();
		int lengthRe2=checkRepeat2.size();
		System.out.println(1);
		if(lengthRe1!=lengthRe2) return false;//�����Ȳ���ȣ�����ʽһ����ͬ
		System.out.println(2);
		for(Node n:checkRepeat1){
			temp.add(n);
		}
		if (this.isEqual(checkRepeat1, checkRepeat2)) {//��ȫһ���򷵻�true
			return true;
		}

		if(temp.get(0).operator.equals("+")||temp.get(0).operator.equals("*")){//ֻ�мӻ�˵�����ſ��ܳ��� �������Ҳ����������ظ��ı��ʽ
			tempNode=temp.get(1);
			temp.set(1, temp.get(2));
			temp.set(2, tempNode);
		}
		for(Node p:temp){
			newStack.push(p);
		}
		if(this.isEqual(newStack, checkRepeat2)) return true;//��������Ҳ������ظ�
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
			if (s1_top.isOperator) {//��s1ջ��Ϊ�����
				if (s2_top.isOperator&&(!s2_top.operator.equals(s1_top.operator))) {//s2��Ϊ�������s1������s2
					return false;
				}
				else if (!s2_top.isOperator) {//s1Ϊ�������s2�������
					return false;
				}
			}
			else if (!s1_top.isOperator) {//��s1������
				if (s2_top.isOperator) {//s2Ϊ�����
					return false;
				}
				else if (!s2_top.isOperator&&(s2_top.compareFraction(s1_top, s2_top)!=1)) {//s2Ϊ��������������s1
				return false;	
				}
			}
			
		}		
		return true;
	}
}
