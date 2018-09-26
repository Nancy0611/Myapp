package Myapp;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class utilClass {
	public static char[] operate={'+','-','*','÷'};
	
	/**
	 * 获取输入
	 * @return 
	 */
	public static String getScanner(){
		Scanner scan=new Scanner(System.in);
		String input=scan.nextLine();
		scan.close();
		return input;
	}
	
	/**
	 * 随机获取运算符+ - * ÷
	 * @return
	 */
	public static char getOperate(){
		Random rand=new Random();
		int operateNum=rand.nextInt(4);
		//System.out.println(operate[operateNum]);
		return operate[operateNum];
	}
	
	/**
	 * 获取操作数
	 * @param range 数值范围
	 * @return
	 */
	public static String getNumber(int range){
		Random rand=new Random();
		int index=rand.nextInt(3);
		String num="";
		
		/**
		 * 随机获取数字，0获取自然数，1获取真分数，2获取带分数
		 */
		if(index==0){//自然数
			Random rand0=new Random();
			num=rand0.nextInt(range-1)+1+"";
		}
		if(index==1){//真分数
			Random rand1=new Random();
			int denominator=rand1.nextInt(range-2)+2;//分母[2,range)
			int numerator=rand1.nextInt(denominator-1)+1;//分子
			num=numerator+"/"+denominator+"";
			/*System.out.println(denominator);
			System.out.println(numerator);
			System.out.println(num);*/
		}
		if(index==2){//带分数
			Random rand2=new Random();
			int leftNum=rand2.nextInt(range-1)+1;//左整数部分
			int rightDeno=rand2.nextInt(range-2)+2;//右真分数部分-分母[2,range)
			int rightNume=rand2.nextInt(rightDeno-1)+1;//右真分数部分-分子
			num=leftNum+"'"+rightNume+"/"+rightDeno+"";
		}
		//System.out.println(num);
		return num;
	}
	
	/**
	 * 去表达式最前和最后的括号
	 * @param str表达式
	 * @return
	 */
	public static String deleteParen(String str){
		if((str.substring(0, 1).equals("(")) && (str.substring(str.length()-1).equals(")"))){
			str=str.substring(1, str.length()-1);
		}
		return str;
	}
	
	/**
	 * 生成四则运算表达式
	 * range题目中操作数的范围
	 */
	public static ArrayList<String> creatAc(int range){
		Random rand4=new Random();
		ArrayList<String> list=new ArrayList<String>();//存放每个表达式中的运算符和操作数
		boolean openParen=false;//左括号
		boolean closeParen=false;//右括号
		boolean tem=false;
		String str="";//四则运算表达式
		int operateNum=rand4.nextInt(3)+1;//每个表达式运算符个数1-3个
		
		//------------开始生成--------------
		for(int j=0;j<operateNum;j++){
			//决定是否加入左括号
			if(!openParen && rand4.nextBoolean()){
				str+="(";
				list.add("(");
				openParen=true;
			}
			String num1=getNumber(range);
			str+=num1;
			list.add(num1);
			//决定是否加入右括号
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
		str=deleteParen(str);//去掉开头和结尾均为括号
		//------------结束生成--------------
		
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
//		System.out.println("分子" + result.peek().numerator);
//		System.out.println(result.peek().numerator+"/"+result.peek().denominator);
		
//		for(int p=0;p<totalList.size();p++){
//			System.out.println(totalList.get(p));
//		}
	}
	
	
}
