/**
 * 2017-11-4下午05:20:30
	author:shaozq
 */
package cn.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.transaction.annotation.Transactional;

import processaldata.ProcThreeData;

import cn.entity.Oldman;
import cn.itc.servlet.LogViewTest;
import cn.service.IBreathService;
import cn.service.IOldManService;
import cn.service.impl.OldManService;

import com.itc.util.SpringContextUtil;
import com.mathworks.toolbox.javabuilder.MWException;
@Transactional
public class InitServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
		int RecNumCount=0;
	    boolean flag=true;
	    IOldManService oldManService ;
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式这里决定存储数据的文件名格式是按照年月日时分还是年月日
	    String date = df.format(new Date());// new Date()为获取当前系统时间
	    LogViewTest view = new LogViewTest();
	    private long lastTimeFileSize = 0;
	    private static int firsttimeTxtData2Sql = 0;
	    public ArrayList<Integer> list = new ArrayList<Integer>();
	    volatile boolean bShouldSub=true;
	    private int tnum = 1;// 线程编号,Thread Number
		private ReentrantLock lock = new ReentrantLock();
		private Condition redCon = lock.newCondition();
		private Condition greenCon = lock.newCondition();
	    
	public InitServlet() {
		// TODO Auto-generated constructor stub
	}
	
	public void init(ServletConfig config){
		try {
			super.init(config);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Servlet自动测试");
		
		oldManService = (IOldManService) SpringContextUtil
				.getBean("oldManService");
		 
		new ListenPort().start();

		System.out.println("串口调用结束");

	}

	class ListenPort extends Thread{
			ServerSocket ss ;
			Socket socket=null;
			InputStream inputStream; 
		 
		  public void run(){
			  try {
				System.out.println("监听端口8086之前");  
				ss = new ServerSocket(8086);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 while (true) {
				  try {
					socket = ss.accept();
					System.out.println("进入while循环1");
					inputStream = socket.getInputStream();
					byte[] readB=new byte[200];  
		    		int nBytes=0; 
		    		int firstflag = 0;
		    			nBytes = inputStream.read(readB);
						//System.out.println("进入while循环2，nBytes=="+nBytes);
		    			while(nBytes >0){
		    				if(firstflag == 0)
		    					System.out.println("firstflag == 0");
		    			printHexString(readB,nBytes,firstflag);
//		    			System.out.println("进入while循环4，调用printHexString(readB,nBytes)结束");
		    			nBytes = inputStream.read(readB);
		    			firstflag=1;
		    			}  
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		  }
	}
	
	 public void printHexString( byte[] b,int len,int firstflag) throws IOException {
			
	        int tempmiddledata=-1,count=0;
	        String fileName="E:/pillow/"+date+".txt";
	       
			  for (int i = 0; i < len; i++) {
			         String hex = Integer.toHexString(b[i] & 0xFF)+"";  
			         if (hex.length() == 1) {
			        	 hex = '0' + hex;  
			         }         
			         int tempnum=Integer.parseInt(hex.toUpperCase(),16);
			         if(tempnum<=57&&tempnum>=48){
			        	 tempnum-=48;
			        	list.add(tempnum);
			         }else if (tempnum==13){
			        	 tempmiddledata=tempnum;
			         }else if ((tempnum==10)&&(tempmiddledata==13)){
			        	 for(int j = 0;j < list.size(); j ++){
//			        		   System.out.println("list.get(i)=="+list.get(j));
			        		   count=count*10+list.get(j);
			        		}
			        		String msg="";  
					         Date date1 = new Date();  
					         SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");  
					         msg+=sdf.format(date1);  
			        	 String str=count+"	"+msg+ "\r\n";
			        	 //System.out.println("len=="+len+" "+msg); 
			        	 
				         writeMethod2(fileName,str);
				         RecNumCount++;
				         //System.out.println("printHexString() firstflag==="+firstflag); 
				         if((RecNumCount==1)&&(firstflag==0)){//如果是第一次接收满1000个数据
				        	 firsttimeTxtData2Sql = 1;
				        	System.out.println("开始执行if(RecNumCount==1000)RecNumCount=="+RecNumCount); 
				        	new read3AnotherTxt().start();
				        	new ProthreekinddataThread(date).start();
				
//				        	new ProthreekinddataThread(date).start();//想法：处理程序用这个线程，把这行代码放到init()中去
				        	RecNumCount=0;
				        	firstflag = 1;
				         }
				         count=0;
				         list.clear();
			         }
			   }
			}
	
	 	public void writeMethod2(String fileName,String str){
//	        String fileName="E:/pillow/"+date+".txt";
	        try {
	           FileWriter writer=new FileWriter(fileName,true);
	           writer.write(str);
	           writer.close();
	        } catch (IOException e){
	           e.printStackTrace();
	        }
		}
		
		class read3AnotherTxt extends Thread {

				String sourceFile = "E:/pillow/"+date+".txt";
		        final String targetFile = "E:/pillow/"+date+"111.txt";
		    	//指定文件可读可写  
		        final File file=new File(targetFile);
		        //启动一个线程每10秒钟读取新增的日志信息  
		        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);  
		       
		            public void run() {
		            	while (true) {
					         try {
						            lock.lock();
						             while (tnum != 1) {// 判断是否该自己执行了[采用while不是if是为了防止死锁]
						                redCon.await();
						             }
						             RandomAccessFile randomAccessFile = null;
										try {
											randomAccessFile = new RandomAccessFile(sourceFile, "rw");
										} catch (FileNotFoundException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}  
						                try {
						                	System.out.println("file.exists()===="+file.exists()+"  "+file.getPath());
						                	if(file.exists()){
						                		System.out.println("开始执行file.delete()");
						        	        	file.delete();
						        	        }
						        	        if(!file.exists()){
						        	        	System.out.println("开始执行file.createNewFile()");
						        	        	file.createNewFile();
						        	        }
						                    //获得变化部分的  
						                    randomAccessFile.seek(lastTimeFileSize);  
						                    String tmp = "";  
						                    System.out.println("这是新增的文件内容");
						                    while ((tmp = randomAccessFile.readLine())!= null) {
						                        // 输出文件内容 
//						                        System.out.println(new String(tmp.getBytes("ISO8859-1")));
						                        write2TxtFile(file.toString(),new String(tmp.getBytes("ISO8859-1")));
						                        lastTimeFileSize = randomAccessFile.length();
						                        tmp = "";
						                    }
						                   // processTxtThreadEndFlag = true;
						                    System.out.println("开始执行new ProthreekinddataThread(date).start()");
//						                    new ProthreekinddataThread(date).start();
						                }catch (IOException e) {  
						                    e.printStackTrace();  
						                }
					            tnum = 2;
						        greenCon.signal();
					         }catch (InterruptedException e) {
						         e.printStackTrace();
					          } finally {
						          lock.unlock();
						    }
						}
		          }
	}
		 public void write2TxtFile(String fileName,String str){
		        try {
		          /*
		           * public FileWriter(File file, boolean append)
		           * 如果append参数为 true，则将字节写入文件末尾处,相当于追加信息
		           * 如果append参数为false, 则写入文件开始处
		           */
		           FileWriter fw=new FileWriter(fileName,true);//这里改成false的话也不对，我觉得可能是这里出问题了
		           fw.write(str);
		           //System.out.println(str);  
		           fw.close();
		        } catch (IOException e){
		           e.printStackTrace();
		        }
			}
		 
		class ProthreekinddataThread extends Thread{
			String locaFilePath=null;
//		  new ProthreekinddataThread("E:/pillow/"+date+"111.txt").start();
			public ProthreekinddataThread(String date){
				locaFilePath = date;
			}
			public void run(){
				 while (true) {
	                 try {
	                    lock.lock();
	                     while (tnum != 2) {
	                        greenCon.await();
	                     }
	                 	int n = 9;
	    			    ProcThreeData test=null;
	    			    String str="E:/pillow/"+locaFilePath+".txt"; 
	    			    //String str = locaFilePath;
	    			    try {
	    			    	System.out.println("开始测试");
	    			    	test=new ProcThreeData();
	    			    	test.processall(str,n);
	    			    	System.out.println("测试完成");
	    			    } catch (MWException e) {
	    				// TODO Auto-generated catch block
	    			    	e.printStackTrace();
	    			    }finally{
	    			    	if (test != null)
	    		        	 test.dispose();
	    			    }
	    				 System.out.println("开始执行new read3AnotherTxt().start()");
	    				 if(firsttimeTxtData2Sql==1){//如果是第一次
	    					 try {
	    						System.out.println("firsttimeTxtData2Sql==="+firsttimeTxtData2Sql);
	    						txtData2Sql();
	    						firsttimeTxtData2Sql=0;
	    					} catch (IOException e) {
	    						// TODO Auto-generated catch block
	    						e.printStackTrace();
	    					}
	    				 }
	                    tnum = 1;
	                   redCon.signal();
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                } finally {
	                     lock.unlock();
	                 }
	            }
				//new read3AnotherTxt().start();
			}
	}
		
	    public void txtData2Sql() throws IOException {
	       
	    	final String brfilePath="E:\\pillow\\Br_data.txt";
	        final String hrfilePath="E:\\pillow\\hr_data.txt";
	        final String bodymovefilePath="E:\\pillow\\move_data.txt";
	        //启动一个线程每2秒钟向日志文件写一次数据  
	        ScheduledExecutorService exec =  Executors.newScheduledThreadPool(1);  
	        exec.scheduleWithFixedDelay(new Runnable(){ 
	            @SuppressWarnings("unused")
				public void run() {
	              //oldManService.uploadBreathData(filePath);
	            System.out.println("开始执行txtData2Sql()中的run()方法");
	             Oldman oldMan=new Oldman();
	             oldMan.setOldManId(19);
                // System.out.println("oldMan.getOldManId()===="+oldMan.getOldManId());
                System.out.println("开始调用oldManService.uploadBreathDataByOldmanfilePath=="+brfilePath);
         		
	            oldManService.uploadBreathDataByOldman(brfilePath,oldMan);
         		oldManService.uploadHeartDataByOldman(hrfilePath, oldMan);
         		oldManService.uploadMovementDataByOldman(bodymovefilePath, oldMan);
         		
	            }
	        }, 0, 20, TimeUnit.SECONDS);  
	    }
		
	    public void Recthreekinddata(String date){
		    int n = 9;   
		    ProcThreeData test=null;
		    String str="E:/pillow/"+date+".txt";  
		    try {
		    	System.out.println("开始测试");
		    	System.out.println(System.getProperty("java.library.path"));
		    	test=new ProcThreeData();
		    	test.processall(str,n);
		    	System.out.println("测试完成");
		    } catch (MWException e) {
			// TODO Auto-generated catch block
		    	e.printStackTrace();
		    }finally{
		    	if (test != null)
	        	 test.dispose();
		    }
		}
	    
		/*
		 * 获得指定日期的前一天
		 * @param specifiedDay
		 * @return
		 * @throws Exception
		 */
		public static String getSpecifiedDayBefore(String specifiedDay){
			Calendar c=Calendar.getInstance();
			Date date= null;
			try {
				date=new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			c.setTime(date);
			int day=c.get(Calendar.DATE);
			c.set(Calendar.DATE, day-1);
			String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
			return dayBefore;
		}
		
}
