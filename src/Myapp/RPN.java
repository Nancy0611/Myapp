package Myapp;

import Myapp.stack;

public class RPN {
	
	/**
     *  ��������ʽת���ɺ�����ʽ
     * @param str ���ɵ�������ʽ
     */
	public static String toRPN(String str){
		stack stack=new stack();//ջ
		String rpn=" ";//��׺���ʽ
		String operate;//�����
		for(int i=0;i<str.length();i++){
			char ch=str.charAt(i);
			if(isOperator(ch)){//��ǰ�ַ�Ϊ�����
				if(stack.top==-1 || ch=='('){//ջΪ�ջ���Ϊ��ֱ����ջ
					stack.push(ch);
				}else{
					if(ch==')'){//���Ϊ��
						while(true){//��������������ջ���ӵ��������ʽ��
							if(stack.top!=-1 && (!stack.top().toString().equals("("))){
								operate=stack.pop().toString();
//								System.out.println(operate);
								rpn+=operate;
							}else{
								if(stack.top!=-1)//���ջ��Ԫ��Ϊ��
									stack.pop();
								break;
							}
						}
					}else{
						while(true){//ջ��Ϊ�գ����ȼ���
							if(stack.top!=-1 && priority(ch+"",stack.top()+"")){
								operate=stack.pop()+"";
//								System.out.println(operate);
								if(!operate.equals("(")){
									rpn+=operate;
								}
							}else{
								break;
							}
						}
						stack.push(ch);
					}
				}
				
			}else{
				rpn+=ch;//������
			}
		}
		while(stack.top!=-1){
			operate=stack.pop()+"";
			if(!operate.equals("("))
				rpn+=operate;
		}
		System.out.println(rpn);
		return rpn;
	}
	
	/**
	 * �ж��Ƿ�Ϊ�����
	 */
	public static boolean isOperator(char ch){
		if((ch=='+')||(ch=='-')||(ch=='*')||(ch=='��')||(ch=='(')||(ch==')'))
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
