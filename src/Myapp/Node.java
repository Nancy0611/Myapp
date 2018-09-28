package Myapp;
import java.util.HashMap;
import java.util.Stack;

import org.jcp.xml.dsig.internal.MacOutputStream;

import com.sun.javafx.collections.MappingChange.Map;
import com.sun.org.apache.regexp.internal.recompile;

public class Node {
	
	private static final int String = 0;
	Integer numerator;//����
	Integer denominator;//��ĸ
	boolean isOperator;
	String operator;
	Node lChild;
	Node rChild;
	
	
	//��������췽�� 
	public Node(boolean isOperator,String stackElement) {
		// TODO Auto-generated constructor stub
		if(isOperator == true){//Ϊ�����
			this.isOperator = true;
			this.operator = stackElement;	
		}
		else if (isOperator == false && stackElement.contains("'")){//Ϊ������
//			System.out.println(stackElement);
			String[] split1 = stackElement.split("'");
			String[] split2 = split1[1].split("\\/");
			this.numerator = Integer.parseInt(split1[0])*Integer.parseInt(split2[1]) 
					+ Integer.parseInt(split2[0]);
			this.denominator = Integer.parseInt(split2[1]);
//			System.out.println(numerator);
//			System.out.println(denominator);
		}
		else if(isOperator == false && (!stackElement.contains("'"))){//Ϊ����
//			System.out.println(stackElement);
			String[] s = stackElement.split("\\/");
			this.numerator = Integer.parseInt(s[0]);
//			System.out.println(numerator+"feafa");
			this.denominator = Integer.parseInt(s[1]);
//			System.out.println(denominator);
		}
	}
	
	public Node(boolean isOperator,Integer num,Integer den) {
		// TODO Auto-generated constructor stub
		this.isOperator = isOperator;
		this.denominator = den;
		this.numerator = num;
		
	}
	
	public Node() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * ���ݺ�׺���ʽ�����ӷ�ĸ��ʽ������
	 * ��������������stack2��
	 */
	public Stack<Node> calculate(Stack<Node> stack){
		Stack<Node> stack2 = new Stack<>();
		Node calculator;
		while(!stack.isEmpty()){
			if(!(calculator = stack.pop()).isOperator){//������ֱ����ջ
//				System.out.println("??"+calculator.numerator + "/"+ calculator.denominator);
				stack2.push(calculator);
			}
			else  if(calculator.isOperator){//��Ϊ�����
				
				//ÿ��ȥ��ջ������Ԫ��
				Node calculator1 = stack2.pop();
				Node calculator2 = stack2.pop();
				
				switch (calculator.operator) {
				case "+":
					stack2.push(calculator.add(calculator2, calculator1));
					break;
					
				case "-":
					Node res=calculator.sub(calculator2, calculator1);
					if(res.numerator>=0){
						stack2.push(res);
					}else{
						res.operator="#";
						stack2.push(res);
						return stack2;
					}
					stack2.push(calculator.sub(calculator2, calculator1));
					break;
					
				case "*":
					stack2.push(calculator.mul(calculator2, calculator1));
					break;
					
				case "��":
					stack2.push(calculator.div(calculator2, calculator1));
					break;
	
				default:
					break;
				}
				
			}
		}
		return stack2;
	}
	
	//�ӷ�
	public Node add(Node calculator1,Node calculator2) {
		Integer num = calculator1.numerator*calculator2.denominator +
				calculator2.numerator*calculator1.denominator;
				Integer den = calculator1.denominator*calculator2.denominator;
				int g = gcd(num, den);
				Node calculator = new Node(false, num/g, den/g);
				return calculator;
	}
	
	//����
	public Node sub(Node calculator1,Node calculator2) {
		Integer num = calculator1.numerator*calculator2.denominator -
				calculator2.numerator*calculator1.denominator;
		Integer den = calculator1.denominator*calculator2.denominator;
		int g = gcd(num, den);
		Node calculator = new Node(false, num/g, den/g);
		return calculator;
	}
	
	//�˷�
	public Node mul(Node calculator1,Node calculator2) {
		Integer num = calculator1.numerator*calculator2.numerator;
		Integer den = calculator1.denominator*calculator2.denominator;
		int g = gcd(num, den);
		Node calculator = new Node(false, num/g, den/g);
		return calculator;
	}
	
	//����
	public Node div(Node calculator1,Node calculator2) {
		Integer num = calculator1.numerator*calculator2.denominator;
		Integer den = calculator1.denominator*calculator2.numerator;
		int g = gcd(num, den);
		Node calculator = new Node(false, num/g, den/g);
		return calculator;
	}
	
	//���Լ��
	public int gcd(int a, int b){
		int m = Math.max(Math.abs(a), Math.abs(b));
		int n = Math.min(Math.abs(a), Math.abs(b));
		int r;
		while(n!=0){
			r = m % n;
			m = n;
			n = r;
		}
		return m;
	}
	
	//�Ƚ�������������С
	/**
	 * 
	 * @param f1
	 * @param f2
	 * @return 	f1 > f2 ���� 2
	 * 			f1 = f2����1
	 * 			f1 < f2����-1
	 * 			���� ����0
	 */
	public int compareFraction(Node f1,Node f2) {
		Node node = new Node();
				
		if (f1.isOperator||f2.isOperator) {
			System.out.println("���������ֽ��бȽϣ�");
			return 0;
		}
		Node compare = node.sub(f1, f2);//f1 - f2 ���Ϊnode����
		int result = compare.numerator/compare.denominator;//f1 - f2�Ľ��
		if (result == 0) {
			return 1;
		}
		else if (result > 0) {
			return 2;
		}
		else if (result < 0) {
			return -1;
		} 
			
		return 0;
	}
	
	/**
	 * 
	 * @param o1
	 * @param o2
	 * @return 	o1 > o2 return 2
	 * 			o1 = o2 return 1
	 * 			o1 < o2 return -1
	 * 			����                 return 0
	 */
	public int  compareOperator(Node o1,Node o2) {
		if (!o1.isOperator||!o2.isOperator) {
			System.out.println("��������ȷ�������");
			return 0;
		}
		HashMap<String, Integer> priMap = new HashMap<>();
		priMap.put("+", 0);
		priMap.put("-", 0);
		priMap.put("*", 1);
		priMap.put("��", 1);
		
		if (priMap.get(o1.operator) > priMap.get(o2.operator)) {
			//o1 ���� o2
			return 2;
		}
		else if (priMap.get(o1.operator) == priMap.get(o2.operator)) {
			//o1 ����o2
			return 1;
		}
		else if (priMap.get(o1.operator) < priMap.get(o2.operator)) {
			//o1����o2
			return 1;
		}
		return 0;
	}
	
	
	
	
}
