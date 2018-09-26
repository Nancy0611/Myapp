package Myapp;

import java.util.ArrayList;
import java.util.Stack;

public class RPN {
	
	/**
     *  ��������ʽת���ɺ�����ʽ
     * @param str ���ɵ�������ʽ
     */
	public static Stack<String> toRPN(ArrayList<String> list){
		Stack<String> stack=new Stack<String>();//ջ
		Stack<String> right=new Stack<String>();//������ʽ
		String operate;//�����
			
		for(int i=0;i<list.size();i++){
			String ch=list.get(i);
//			System.out.println(ch);
			if(isOperator(ch)){//��ǰ�ַ�Ϊ�����
				if(stack.empty()==true || ch=="("){//ջΪ�ջ���Ϊ��ֱ����ջ
					stack.push(ch);
//					System.out.println("ջΪ�ջ���Ϊ��ֱ����ջ" + ch);
//					System.out.println(stack.peek());
				}else{//��ջ�ա���������
//					System.out.println("��ջ�ա���������"+ch);
					if(ch==")"){//���Ϊ��
						while(true){//��������������ջ���ӵ��������ʽ��
//							System.out.println("111");
							if((!stack.empty()) && (!stack.peek().equals("("))){
//								System.out.println("111111");
								operate=stack.pop();
//								System.out.println(operate);
								right.push(operate);
							}else{
								if(!stack.empty())//���ջ��Ԫ��Ϊ��
									stack.pop();
								break;
							}
						}
					}else{//��ջ�ա��������š���������
//						System.out.println(0);
						while(true){//ջ��Ϊ�գ����ȼ���
//							System.out.println(2);
							if(!stack.empty() && priority(ch,stack.peek())){
								operate=stack.pop()+"";
//								System.out.println(22);
//								System.out.println(operate);
								if(!operate.equals("(")){
									right.push(operate);
								}
							}else{
								break;
							}
						}
						stack.push(ch+"");
					}
				}
				
			}else{
				right.push(ch+"");//������
			}
		}
		while(!stack.empty()){
			operate=stack.pop()+"";
			if(!operate.equals("("))
				right.push(operate);
		}
		
//		Stack<String> newRight = new Stack<>();
//		while (!right.isEmpty()) {
//			System.out.print(right.peek());
//			newRight.push(right.pop());
//		}
//		System.out.println();
//		System.out.println(right.peek());
//		System.out.println();
		return right;
	}
	
	/**
	 * �ж��Ƿ�Ϊ�����
	 */
	public static boolean isOperator(String ch){
		if((ch.equals("+"))||(ch.equals("-"))||(ch.equals("*"))||(ch.equals("��"))||(ch.equals("("))||(ch.equals(")")))
			return true;
		else 
			return false;
	}

	/**
	 * ��������������ȼ���
	 * @param operatorout��ǰ������ʽ�ַ�
	 * @param operatorinջ���ַ�
	 * @return
	 */
	public static boolean priority(String operatorout, String operatorin) {
		int m = -1, n = -1;
		String addop[][] = { { "+", "-", "*", "��", "(", ")" },
				{ "+", "-", "*", "��", "(", ")" } };
		int first[][] = { { 1, 1, 2, 2, 2, 0 }, { 1, 1, 2, 2, 2, 0 },
				{ 1, 1, 1, 1, 2, 0 }, { 1, 1, 1, 1, 2, 0 },
				{ 2, 2, 2, 2, 2, 0 }, { 2, 2, 2, 2, 2, 2 } };
		for (int i = 0; i < 6; i++) {
			if (operatorin.equalsIgnoreCase(addop[0][i]))
				m = i;
		}
		for (int i = 0; i < 6; i++) {
			if (operatorout.equalsIgnoreCase(addop[1][i]))
				n = i;
		}
		if (m == -1 && n == -1)
			return false;
		else if (m == -1 && n != -1)
			return false;
		else if (m != -1 && n == -1)
			return true;
		else if (first[m][n] == 1) {
			return true;
		} else
			return false;
	}
}
