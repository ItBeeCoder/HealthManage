package cn.itc.servlet;

public class Jiaoti {
	
	public Object lock=new Object();
	public static void main(String[] args){
//		new Jiaoti().go();
	}

	/*public void go(){
		Thread1 t1=new Thread1();
		Thread2 t2=new Thread2();
		t1.start();
		t2.start();
	}
	
	public class Thread1 extends Thread {
		public void run(){
			while(true){
				synchronized(lock){
					lock.notify();
					System.out.println("Thread1在运行");
					lock.wait();
				}
			}
		}
	}*/
	
	/*public class Thread2 extends Thread {
		
		public void run(){
			while(true){
				synchronized(lock){
					lock.notify();
					System.out.println("Thread2在运行");
					lock.wait();
				}
			}
		}
	}*/
	}
