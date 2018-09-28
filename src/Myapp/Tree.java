package Myapp;

import java.security.spec.PSSParameterSpec;
import java.util.Stack;

import jdk.nashorn.internal.ir.BreakableNode;


public class Tree {
	
	Node root;//根
	Tree lChild;//左子树
	Tree rChild;//右子数
	
//	public Tree(Tree lChild, Tree rChild,Node root) {//构造一棵树
		// TODO Auto-generated constructor stub
//		this.lChild = lChild;
//		this.rChild = rChild;
//		this.root = root;
//	}
//	
//	public Tree(Node node) {//构造叶子
//		// TODO Auto-generated constructor stub
//		this.lChild = null;
//		this.rChild = null;
//		this.root = node;
//	}
	

	
	/**
	 * 
	 * @param posfixStack  后缀表达式
	 * @return 树 
	 */
	public static Node Builtree(Stack<Node> posfixStack) {
		//构造一个临时存储节点的栈
		Stack<Node> stackTree = new Stack<>();
		Node node = new Node();
		Node T1;//右子树
		Node T2;//左子树
		Node pStack_Top;//后缀表达式栈栈顶
		
		while(!posfixStack.isEmpty()){//遍历后缀表达式栈
			pStack_Top = posfixStack.pop();
			if (!pStack_Top.isOperator) {
				//扫描后缀表达式，若为操作数，则直接进栈
				stackTree.push(pStack_Top);
			}
			else if(pStack_Top.isOperator&&stackTree.size()>1){
				//为运算符，则弹出两棵树T1,T2(先弹T1)
				T1 = stackTree.pop();
				T2 = stackTree.pop();
				if(!T1.isOperator&&!T2.isOperator){//T1、T2都为操作数，比较其大小，大的作为左子树
					switch (node.compareFraction(T1, T2)) {
					case 2://T1大
						 pStack_Top.lChild = T1;
						 pStack_Top.rChild = T2;
						break;
					case 1://相等
						pStack_Top.lChild = T1;
						pStack_Top.rChild = T2;
						break;
					case -1://T2大
						pStack_Top.lChild = T2;
						pStack_Top.rChild = T1;
						break;
					case 0://非操作数
						System.out.println("非操作数！无法比较!");
						break;
					default:
						break;
					}
				}//end都为操作数
				else if (T1.isOperator&&T2.isOperator) {//都为运算符
					switch (node.compareOperator(T1, T2)) {
					case 2://1大于2
						pStack_Top.lChild = T1;
						pStack_Top.rChild = T2;
						break;
					case 1://两个运算符优先级相等，比较左右子树的值，左子树为较大值
						
						break;
					case -1://1小于2
						pStack_Top.lChild = T2;
						pStack_Top.rChild = T1;
						break;
					case 0:
						System.out.println("非运算符！无法比较！");
						break;
					default:
						break;
					}
				}//end都为运算符
				else if (T1.isOperator&&!T2.isOperator) {//T1为运算符 T2为操作数，运算符为左子树
					pStack_Top.lChild = T1;
					pStack_Top.rChild = T2;
				}//end//T1为运算符 T2为操作数
				else if(!T1.isOperator&&T2.isOperator) {//T2为运算符 T1为操作数
					pStack_Top.lChild = T2;
					pStack_Top.rChild = T1;
				}//endT2为运算符 T1为操作数
			}//end后缀表达式栈顶为运算符
			else {//后缀表达式栈顶非运算符也非操作数
				System.out.println("后缀表达式错误！");
			}
		}
		
		return stackTree.pop();
		
	}

}
