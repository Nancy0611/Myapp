package Myapp;
import java.util.Random;
import java.util.Scanner;


public class utilClass {
	public static char[] operate={'+','-','*','÷'};
	
	public static String getScanner(){//获取输入
		Scanner scan=new Scanner(System.in);
		String input=scan.nextLine();
		scan.close();
		return input;
	}
	
	public static char getOperate(){//随机获取运算符
		Random rand=new Random();
		int operateNum=rand.nextInt(4);
		//System.out.println(operate[operateNum]);
		return operate[operateNum];
	}
	
	public static String getNumber(int range){//获取操作数
		Random rand=new Random();
		int index=rand.nextInt(3);
		String num="";
		
		/* 随机获取数字，0获取自然数，1获取真分数，2获取带分数*/
		if(index==0){//自然数
			Random rand0=new Random();
			num=rand0.nextInt(range-1)+1+"";
		}
		if(index==1){//真分数
			Random rand1=new Random();
			int denominator=rand1.nextInt(range-1)+1;//分母
			int numerator=rand1.nextInt(denominator-1)+1;//分子
			num=numerator+"/"+denominator+"";
			/*System.out.println(denominator);
			System.out.println(numerator);
			System.out.println(num);*/
		}
		if(index==2){//带分数
			Random rand2=new Random();
			int leftNum=rand2.nextInt(range-1)+1;//左整数部分
			int rightDeno=rand2.nextInt(range-1)+1;//右真分数部分-分母
			int rightNume=rand2.nextInt(rightDeno-1)+1;//右真分数部分-分子
			num=leftNum+"'"+rightNume+"/"+rightDeno+"";
		}
		//System.out.println(num);
		return num;
	}
	
	/*生成四则运算表达式
	 * acNum生成题目的个数
	 * range题目中操作数的范围*/
	public static void creatAc(int acNum,int range){
		Random rand4=new Random();
		
		for(int i=0;i<acNum;i++){
			boolean openParen=false;//左括号
			boolean closeParen=false;//右括号
			boolean tem=false;
			String str="";//四则运算表达式
			int operateNum=rand4.nextInt(3)+1;//每个表达式运算符个数1-3个
			for(int j=0;j<operateNum;j++){
				//决定是否加入左括号
				if(!openParen && rand4.nextBoolean()){
					str+="(";
					openParen=true;
				}
				str+=getNumber(range).toString();
				//决定是否加入右括号
				if(openParen && !closeParen && tem){
					if(rand4.nextBoolean()){
						str+=")";
						closeParen=true;
					}
				}
				str+=getOperate();
				if(openParen){
					tem=true;
				}
			}
			str+=getNumber(range).toString();
			if(openParen && !closeParen){
				str+=")";
			}
		System.out.println(str);	
		}
		
	}
}
