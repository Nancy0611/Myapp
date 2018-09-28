package Myapp;

import java.security.spec.PSSParameterSpec;
import java.util.Stack;

import jdk.nashorn.internal.ir.BreakableNode;


public class Tree {
	
	Node root;//��
	Tree lChild;//������
	Tree rChild;//������
	
//	public Tree(Tree lChild, Tree rChild,Node root) {//����һ����
		// TODO Auto-generated constructor stub
//		this.lChild = lChild;
//		this.rChild = rChild;
//		this.root = root;
//	}
//	
//	public Tree(Node node) {//����Ҷ��
//		// TODO Auto-generated constructor stub
//		this.lChild = null;
//		this.rChild = null;
//		this.root = node;
//	}
	

	
	/**
	 * 
	 * @param posfixStack  ��׺���ʽ
	 * @return �� 
	 */
	public static Node Builtree(Stack<Node> posfixStack) {
		//����һ����ʱ�洢�ڵ��ջ
		Stack<Node> stackTree = new Stack<>();
		Node node = new Node();
		Node T1;//������
		Node T2;//������
		Node pStack_Top;//��׺���ʽջջ��
		
		while(!posfixStack.isEmpty()){//������׺���ʽջ
			pStack_Top = posfixStack.pop();
			if (!pStack_Top.isOperator) {
				//ɨ���׺���ʽ����Ϊ����������ֱ�ӽ�ջ
				stackTree.push(pStack_Top);
			}
			else if(pStack_Top.isOperator&&stackTree.size()>1){
				//Ϊ��������򵯳�������T1,T2(�ȵ�T1)
				T1 = stackTree.pop();
				T2 = stackTree.pop();
				if(!T1.isOperator&&!T2.isOperator){//T1��T2��Ϊ���������Ƚ����С�������Ϊ������
					switch (node.compareFraction(T1, T2)) {
					case 2://T1��
						 pStack_Top.lChild = T1;
						 pStack_Top.rChild = T2;
						break;
					case 1://���
						pStack_Top.lChild = T1;
						pStack_Top.rChild = T2;
						break;
					case -1://T2��
						pStack_Top.lChild = T2;
						pStack_Top.rChild = T1;
						break;
					case 0://�ǲ�����
						System.out.println("�ǲ��������޷��Ƚ�!");
						break;
					default:
						break;
					}
				}//end��Ϊ������
				else if (T1.isOperator&&T2.isOperator) {//��Ϊ�����
					switch (node.compareOperator(T1, T2)) {
					case 2://1����2
						pStack_Top.lChild = T1;
						pStack_Top.rChild = T2;
						break;
					case 1://������������ȼ���ȣ��Ƚ�����������ֵ��������Ϊ�ϴ�ֵ
						
						break;
					case -1://1С��2
						pStack_Top.lChild = T2;
						pStack_Top.rChild = T1;
						break;
					case 0:
						System.out.println("����������޷��Ƚϣ�");
						break;
					default:
						break;
					}
				}//end��Ϊ�����
				else if (T1.isOperator&&!T2.isOperator) {//T1Ϊ����� T2Ϊ�������������Ϊ������
					pStack_Top.lChild = T1;
					pStack_Top.rChild = T2;
				}//end//T1Ϊ����� T2Ϊ������
				else if(!T1.isOperator&&T2.isOperator) {//T2Ϊ����� T1Ϊ������
					pStack_Top.lChild = T2;
					pStack_Top.rChild = T1;
				}//endT2Ϊ����� T1Ϊ������
			}//end��׺���ʽջ��Ϊ�����
			else {//��׺���ʽջ���������Ҳ�ǲ�����
				System.out.println("��׺���ʽ����");
			}
		}
		
		return stackTree.pop();
		
	}

}
