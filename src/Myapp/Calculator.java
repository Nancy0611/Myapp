package Myapp;
import java.util.Stack;

public class Calculator {
	
	Integer numerator;//����
	Integer denominator;//��ĸ
	boolean isOperator;
	String operator;
	
	
	//��������췽�� 
	public Calculator(boolean isOperator,String stackElement) {
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
	
	public Calculator(boolean isOperator,Integer num,Integer den) {
		// TODO Auto-generated constructor stub
		this.isOperator = isOperator;
		this.denominator = den;
		this.numerator = num;
		
	}
	
	public Calculator() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * ���ݺ�׺���ʽ�����ӷ�ĸ��ʽ������
	 * ��������������stack2��
	 */
	public Stack<Calculator> calculate(Stack<Calculator> stack){
		Stack<Calculator> stack2 = new Stack<>();
		Calculator calculator;
		while(!stack.isEmpty()){
			if(!(calculator = stack.pop()).isOperator){//������ֱ����ջ
//				System.out.println("??"+calculator.numerator + "/"+ calculator.denominator);
				stack2.push(calculator);
			}
			else  if(calculator.isOperator){//��Ϊ�����
				
				//ÿ��ȥ��ջ������Ԫ��
				Calculator calculator1 = stack2.pop();
				Calculator calculator2 = stack2.pop();
				
				switch (calculator.operator) {
				case "+":
					stack2.push(calculator.add(calculator2, calculator1));
					break;
					
				case "-":
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
	public Calculator add(Calculator calculator1,Calculator calculator2) {
		Integer num = calculator1.numerator*calculator2.denominator +
				calculator2.numerator*calculator1.denominator;
				Integer den = calculator1.denominator*calculator2.denominator;
				int g = gcd(num, den);
				Calculator calculator = new Calculator(false, num/g, den/g);
				return calculator;
	}
	
	//����
	public Calculator sub(Calculator calculator1,Calculator calculator2) {
		Integer num = calculator1.numerator*calculator2.denominator -
				calculator2.numerator*calculator1.denominator;
		Integer den = calculator1.denominator*calculator2.denominator;
		int g = gcd(num, den);
		Calculator calculator = new Calculator(false, num/g, den/g);
		return calculator;
	}
	
	//�˷�
	public Calculator mul(Calculator calculator1,Calculator calculator2) {
		Integer num = calculator1.numerator*calculator2.numerator;
		Integer den = calculator1.denominator*calculator2.denominator;
		int g = gcd(num, den);
		Calculator calculator = new Calculator(false, num/g, den/g);
		return calculator;
	}
	
	//����
	public Calculator div(Calculator calculator1,Calculator calculator2) {
		Integer num = calculator1.numerator*calculator2.denominator;
		Integer den = calculator1.denominator*calculator2.numerator;
		int g = gcd(num, den);
		Calculator calculator = new Calculator(false, num/g, den/g);
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
	
	
	
}
