package cn.itc.servlet;

public class Thread1 {

	/**
	 * author:shaozq
	 * 2017-12-26上午11:37:46
	 * TODO
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		final Business business=new Business();
		Thread a=new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				business.SubThread();
			}
		});
		Thread b=new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				business.MainThread();
			}
		});
		a.start();
		b.start();
	}
	
}
	
	class Business{
		private static Object LOCK=new Object();
		volatile boolean bShouldSub=true;
		
		public void MainThread(){
			while(true){
			for(int i=0;i<1;i++){
				synchronized (LOCK) {
					
						System.out.println(Thread.currentThread().getName()+" MainThread:i="+i+" j=");
					
					if(bShouldSub){
						bShouldSub=false;
						LOCK.notify();
					
					}
				}
			}
			}
		}
		
		public void SubThread(){
			while(true){
			for(int i=0;i<1;i++){
				synchronized (LOCK) {
						System.out.println(Thread.currentThread().getName()+" SubThread:i="+i+" j=");
					
					if(!bShouldSub){
						bShouldSub=true;
						LOCK.notify();
						
					}
				}
			}
		}
		}

}
