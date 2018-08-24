package cn.dataprocess;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

public class ComRead implements Runnable {


		static CommPortIdentifier portId;  
		static Enumeration portList;//枚举类  
	    InputStream inputStream;  
	    SerialPort serialPort;  
	    Thread readThread;  
//	    StringToHex sHex;
	    static SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
	    static String date = df.format(new Date());// new Date()为获取当前系统时间
	    public static ArrayList<Integer> list = new ArrayList<Integer>();
	    public static String[][] arr=new String[1500][2];
	    public static int row=0,col1=0,col2=0;
	    
	    
	    public void start(){  
	    	
		   portList=CommPortIdentifier.getPortIdentifiers();//利用枚举类型得到所有通信端口  
	    	while(portList.hasMoreElements()){  
	    	portId=(CommPortIdentifier)portList.nextElement();  
	    	if(portId.getPortType()==CommPortIdentifier.PORT_SERIAL){//如果为串口的话  
	    	///System.out.println(portId.getName());  
	    	if(portId.getName().equals("COM4")){//如果是COM3，这里需要你自己设定指定的串口  
	    	try{  
	        //打开串口  
	        serialPort=(SerialPort)portId.open("Main",0);  
	    	}catch(PortInUseException e){}  
	    	try {  
	            inputStream = serialPort.getInputStream();/*获取端口的输入流对象*/  
	        } catch (IOException e) {}  
	    	}// end if  
	    	}// end if  
	    	}// end while  
	    	try{  
	    	readThread = new Thread(this); 
			readThread.start();  
	    	}  
	    	catch (Exception e) { }  
	     }  
	    
	    public void run(){
	    	while (true) {
	    	try{
	    		serialPort.setSerialPortParams(115200,  
	    				SerialPort.DATABITS_8,  
	    				SerialPort.STOPBITS_1,  
	    				SerialPort.PARITY_NONE);  
	    	}catch (UnsupportedCommOperationException e) {
	    		
	    	}  
	    		byte[] readB=new byte[200];  
	    		int nBytes=0;   
	    		try{  
	    			nBytes = inputStream.read(readB); 
	    			while(nBytes >0){ 
	    			printHexString(readB,nBytes);
	    			nBytes = inputStream.read(readB); 
	    		}  
//	    		sHex.printHexString(readB);//将读出的字符数组数据，直接转换成十六进制。
	    		}catch(IOException e){  
	    		System.err.println(e.toString());  
	    		}  
	    	
	    	}  
	    }
	    
	    public static void printHexString( byte[] b,int len) {
			
			SimpleDateFormat format=new SimpleDateFormat();
	        int tempmiddledata=-1,count=0;
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
			        		   System.out.println("list.get(i)=="+list.get(j));
			        		   count=count*10+list.get(j);
			        		}
			        		String msg="";  
					         Date date = new Date();  
					         SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");  
					         msg+=sdf.format(date);  
			        	 String str=count+"	"+msg+ "\r\n";
//			        	 if(row<1500){
//			        		 arr[row][col1]=String.valueOf(count);
//			        		 arr[row][col2]=msg;
//			        		 row++;
//			        	 
//			        	 }
//			        	 if(row==1499){
//			        	 }
			        	 System.out.println("len=="+len+" "+msg); 
				         writeMethod2(str);
				         count=0;
				         list.clear();
			         } 
			   }  	
			}
		public static void writeMethod2(String str){
	        String fileName="E:/pillow/"+date+".txt";
	        try {
	           //使用这个构造函数时，如果存在kuka.txt文件，
	           //则直接往kuka.txt中追加字符串
	           FileWriter writer=new FileWriter(fileName,true);
	           writer.write(str);
	           //System.out.println(str);  
	           writer.close();
	        } catch (IOException e){
	           e.printStackTrace();
	        }
		}
	    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			ComRead comread=new ComRead();  
			comread.start();  
	}
}
