package Myapp;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class utilClass {
	public static char[] operate={'+','-','*','��'};
	
	/**
	 * ��ȡ����
	 * @return 
	 */
	public static String getScanner(){
		Scanner scan=new Scanner(System.in);
		String input=scan.nextLine();
		scan.close();
		return input;
	}
	
	/**
	 * �����ȡ�����+ - * ��
	 * @return
	 */
	public static char getOperate(){
		Random rand=new Random();
		int operateNum=rand.nextInt(4);
		//System.out.println(operate[operateNum]);
		return operate[operateNum];
	}
	
	/**
	 * ��ȡ������
	 * @param range ��ֵ��Χ
	 * @return
	 */
	public static String getNumber(int range){
		Random rand=new Random();
		int index=rand.nextInt(3);
		String num="";
		
		/**
		 * �����ȡ���֣�0��ȡ��Ȼ����1��ȡ�������2��ȡ������
		 */
		if(index==0){//��Ȼ��
			Random rand0=new Random();
			num=rand0.nextInt(range-1)+1+"";
		}
		if(index==1){//�����
			Random rand1=new Random();
			int denominator=rand1.nextInt(range-2)+2;//��ĸ[2,range)
			int numerator=rand1.nextInt(denominator-1)+1;//����
			num=numerator+"/"+denominator+"";
			/*System.out.println(denominator);
			System.out.println(numerator);
			System.out.println(num);*/
		}
		if(index==2){//������
			Random rand2=new Random();
			int leftNum=rand2.nextInt(range-1)+1;//����������
			int rightDeno=rand2.nextInt(range-2)+2;//�����������-��ĸ[2,range)
			int rightNume=rand2.nextInt(rightDeno-1)+1;//�����������-����
			num=leftNum+"'"+rightNume+"/"+rightDeno+"";
		}
		//System.out.println(num);
		return num;
	}
	
	/**
	 * ȥ���ʽ��ǰ����������
	 * @param str���ʽ
	 * @return
	 */
	public static String deleteParen(String str){
		if((str.substring(0, 1).equals("(")) && (str.substring(str.length()-1).equals(")"))){
			str=str.substring(1, str.length()-1);
		}
		return str;
	}
	
	/**
	 * ��������������ʽ
	 * range��Ŀ�в������ķ�Χ
	 */
	public static ArrayList<String> creatAc(int range){
		Random rand4=new Random();
		ArrayList<String> list=new ArrayList<String>();//���ÿ�����ʽ�е�������Ͳ�����
		boolean openParen=false;//������
		boolean closeParen=false;//������
		boolean tem=false;
		String str="";//����������ʽ
		int operateNum=rand4.nextInt(3)+1;//ÿ�����ʽ���������1-3��
		
		//------------��ʼ����--------------
		for(int j=0;j<operateNum;j++){
			//�����Ƿ����������
			if(!openParen && rand4.nextBoolean()){
				str+="(";
				list.add("(");
				openParen=true;
			}
			String num1=getNumber(range);
			str+=num1;
			list.add(num1);
			//�����Ƿ����������
			if(openParen && !closeParen && tem){
				if(rand4.nextBoolean()){
					str+=")";
					list.add(")");
					closeParen=true;
				}
			}
			char char1=getOperate();
			str+=char1;
			list.add(char1+"");
			if(openParen){
				tem=true;
			}
		}
		String num2=getNumber(range);
		str+=num2;
		list.add(num2);
		if(openParen && !closeParen){
			str+=")";
			list.add(")");
		}
		str=deleteParen(str);//ȥ����ͷ�ͽ�β��Ϊ����
		//------------��������--------------
		
		System.out.print(str+" "+"="+" ");
		return list;
		
//		Stack<String> toRpn = RPN.toRPN(list);
//		System.out.println(toRpn.peek());
//		handleStack handleStack=new handleStack(toRpn);
//		
//		
//		Calculator calculator = new Calculator();
//		Stack<Calculator> result =calculator.calculate(handleStack.calculatorStack);
//
//		System.out.println("����" + result.peek().numerator);
//		System.out.println(result.peek().numerator+"/"+result.peek().denominator);
		
//		for(int p=0;p<totalList.size();p++){
//			System.out.println(totalList.get(p));
//		}
	}
	
	
}
