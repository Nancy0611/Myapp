package Myapp;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class utilClass {
	public static char[] operate={'+','-','*','��'};
	
	public static String getScanner(){//��ȡ����
		Scanner scan=new Scanner(System.in);
		String input=scan.nextLine();
		scan.close();
		return input;
	}
	
	public static char getOperate(){//�����ȡ�����
		Random rand=new Random();
		int operateNum=rand.nextInt(4);
		//System.out.println(operate[operateNum]);
		return operate[operateNum];
	}
	
	public static String getNumber(int range){//��ȡ������
		Random rand=new Random();
		int index=rand.nextInt(3);
		String num="";
		
		/* �����ȡ���֣�0��ȡ��Ȼ����1��ȡ�������2��ȡ������*/
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
	
	public static String deleteParen(String str){//ȥ���ʽ��ǰ����������
		if((str.substring(0, 1).equals("(")) && (str.substring(str.length()-1).equals(")"))){
			str=str.substring(1, str.length()-1);
		}
		return str;
	}
	
	/*��������������ʽ
	 * acNum������Ŀ�ĸ���
	 * range��Ŀ�в������ķ�Χ*/
	public static void creatAc(int acNum,int range){
		Random rand4=new Random();
		ArrayList<String> list=new ArrayList<String>();
		for(int i=0;i<acNum;i++){
			boolean openParen=false;//������
			boolean closeParen=false;//������
			boolean tem=false;
			String str="";//����������ʽ
			int operateNum=rand4.nextInt(3)+1;//ÿ�����ʽ���������1-3��
			for(int j=0;j<operateNum;j++){
				//�����Ƿ����������
				if(!openParen && rand4.nextBoolean()){
					str+="(";
					openParen=true;
				}
				str+=getNumber(range).toString();
				//�����Ƿ����������
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
		str=deleteParen(str);//ȥ����ͷ�ͽ�β��Ϊ����
		list.add(str);
//		for(String a:list){
//			System.out.println(a);
//		}
		}
		
	}
}
