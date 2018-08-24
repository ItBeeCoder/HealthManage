package cn.itc.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//import processaldata.ProcThreeData;

import com.mathworks.toolbox.javabuilder.MWException;

public class LogViewTest {

	/**
	 * author:shaozq
	 * 2017-12-14上午10:45:52
	 * TODO
	 */
	private long lastTimeFileSize = 0;  
	int RecNumCount=0;
    boolean flag=true;
    
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式这里决定存储数据的文件名格式是按照年月日时分还是年月日
    String date = df.format(new Date());// new Date()为获取当前系统时间
    
//    StringToHex sHex;
//    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
//    String date = df.format(new Date());// new Date()为获取当前系统时间
    public ArrayList<Integer> list = new ArrayList<Integer>();
	   
    /** 
     * 实时读取指定文件的内容 
     * @param logFile 
     * @throws FileNotFoundException 
     */  
    public void realtimeShowLog(File logFile) throws FileNotFoundException {
        //指定文件可读可写  
        final RandomAccessFile randomAccessFile = new RandomAccessFile(logFile, "rw");  
        //启动一个线程每10秒钟读取新增的日志信息  
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);  
        exec.scheduleWithFixedDelay(new Runnable() {  
            public void run() {  
                try {  
                    //获得变化部分的  
                    randomAccessFile.seek(lastTimeFileSize);  
                    String tmp = "";  
                    System.out.println("这是新增的文件内容");
                    while ( (tmp = randomAccessFile.readLine()) != null) {  
                        // 输出文件内容 
                        System.out.println(new String(tmp.getBytes("ISO8859-1"))); 
                        String filePath="E:\\pillow\\Br_data.txt";
//                        oldManService.uploadBreathData(filePath);
                        lastTimeFileSize = randomAccessFile.length();  
                    }
                } catch (IOException e) {  
                    e.printStackTrace();  
                } 
            } 
        }, 0, 10, TimeUnit.SECONDS);
    }
    
    /*
     * 作用： 把原始文件的内容每隔30秒读到一个新的目标文件，在写入目标文件的同时，覆盖原始文件的内容
     */
    public int read2AnotherTxt(String sourceFile,final String targetFile) throws FileNotFoundException {
        int endflag=0;
    	//指定文件可读可写  
        final RandomAccessFile randomAccessFile = new RandomAccessFile(sourceFile, "rw");  
        //启动一个线程每10秒钟读取新增的日志信息  
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);  
        exec.scheduleWithFixedDelay(new Runnable() {  
            public void run() {  
                try {  
                    //获得变化部分的  
                    randomAccessFile.seek(lastTimeFileSize);  
                    String tmp = "";  
                    System.out.println("这是新增的文件内容");
                    while ( (tmp = randomAccessFile.readLine()) != null) {  
                        // 输出文件内容 
                        System.out.println(new String(tmp.getBytes("ISO8859-1")));
                        write2TxtFile(targetFile,new String(tmp.getBytes("ISO8859-1")));
                        lastTimeFileSize = randomAccessFile.length();  
                    }
                } catch (IOException e) {  
                    e.printStackTrace();  
                } 
            } 
        }, 0, 20, TimeUnit.SECONDS);
        endflag=1;
        return endflag;
    }
   
    public void write2TxtFile(String fileName,String str){
        try {
          /*
           * public FileWriter(File file, boolean append)
           * 如果append参数为 true，则将字节写入文件末尾处,相当于追加信息
           * 如果append参数为false, 则写入文件开始处
           */
           FileWriter fw=new FileWriter(fileName,true);
           fw.write(str);
           //System.out.println(str);  
           fw.close();
        } catch (IOException e){
           e.printStackTrace();
        }
	}
    
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
    String msgInfo = "this is a message, 这是一条信息";  
    /** 
     * 实时写入日志到指定文件 
     * @throws IOException 
     */  
    public void writerLog() throws IOException {
        final File logFile = new File("F:/study/mock.txt");  
        if(!logFile.exists()) { 
            logFile.createNewFile();
        }
        //启动一个线程每2秒钟向日志文件写一次数据  
        ScheduledExecutorService exec =  Executors.newScheduledThreadPool(1);  
        exec.scheduleWithFixedDelay(new Runnable(){ 
            @SuppressWarnings("unused")
			public void run() { 
                try {  
                    if(logFile == null) {
                        throw new IllegalStateException("logFile can not be null!");  
                    }  
                    Writer txtWriter = new FileWriter(logFile,true);  
                    txtWriter.write(dateFormat.format(new Date()) +"\t"+ msgInfo +"\r\n");  
                    txtWriter.flush();  
                } catch (IOException e) {
                    throw new RuntimeException(e);  
                }  
            }  
        }, 0, 2, TimeUnit.SECONDS);  
    }  
   
    /** 
     * 实时监听接收数据线程
     * @throws IOException 
     */  
    
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		LogViewTest view = new LogViewTest();  
	    view.writerLog();  
	    final File tmpLogFile = new File("F:\\study\\mock.txt");  
	    view.realtimeShowLog(tmpLogFile); 
	}

}
