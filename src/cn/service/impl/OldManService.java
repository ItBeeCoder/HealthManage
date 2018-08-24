package cn.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.dao.IBreathDao;
import cn.dao.IHeartDao;
import cn.dao.IMovementDao;
import cn.dao.IOldManDao;
import cn.entity.Breath;
import cn.entity.Heart;
import cn.entity.Movement;
import cn.entity.Oldman;
import cn.entity.Pillow;
import cn.impl.BreathDao;
import cn.service.IOldManService;

public class OldManService implements IOldManService {

	private IOldManDao oldManDao;
	private static IBreathDao breathDao;
	private IHeartDao heartDao;
	private IMovementDao movementDao;
	private long lastTimeFileSize = 0;
	
	public void setOldManDao(IOldManDao oldManDao) {
		this.oldManDao = oldManDao;
	}

	public IBreathDao getBreathDao() {
		return breathDao;
	}

	@SuppressWarnings("static-access")
	public void setBreathDao(IBreathDao breathDao) {
		this.breathDao = breathDao;
	}

	public IHeartDao getHeartDao() {
		return heartDao;
	}

	public void setHeartDao(IHeartDao heartDao) {
		this.heartDao = heartDao;
	}

	public IMovementDao getMovementDao() {
		return movementDao;
	}

	public void setMovementDao(IMovementDao movementDao) {
		this.movementDao = movementDao;
	}

	public void save(Oldman oldMan) {
		oldManDao.save(oldMan);
	}

	/****************************** by shao ******************************/
	public void updateOldman(Oldman oldMan, String propertyName, Object value) {
		oldManDao.updateOldman(oldMan, propertyName, value);
		System.out.println("updateOldman Service finish");
	}

	public void updateOldmanpillowId(Pillow pillow, String propertyName,
			Object value) {
		oldManDao.updateOldmanpillowId(pillow, propertyName, value);
		// System.out.println("updatepillow Service finish");
	}

	// 9.4
	public void updateUserPassByAccount(Oldman oldMan, String propertyName,
			Object value) {
		oldManDao.updateUserPassByAccount(oldMan, propertyName, value);
	}

	/****************************** by shao ******************************/

	public void update(Oldman oldMan) {
		oldManDao.update(oldMan);
	}

	public Oldman findById(int id) {
		return oldManDao.findById(id);
	}

	public List<Oldman> getAll() {
		return oldManDao.getAll();
	}

	public List<Oldman> getAll(String oldManName) {
		return oldManDao.getAll(oldManName);
	}

	public List<Oldman> findByProperty(String propertyName, Object value) {
		return oldManDao.findByProperty(propertyName, value);
	}

	public void delete(int id) {
		oldManDao.delete(id);
	}

	public void deleteMany(int[] ids) {
		if (ids != null && ids.length > 0) {
			for (int id : ids) {
				delete(id);
			}
		}
	}

	// 此方法没有把老人id添加进去
	public void uploadBreathData(String filePath) {

		try {
			String encoding = "GBK";
			File file = new File(filePath);

			if (file.isFile() && file.exists()) { // 判断文件是否存在

				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					// 创建呼吸对象
					Breath b = new Breath();
					b.setBreathDateTime(lineTxt);
					breathDao.save(b);

					System.out.println(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

	}
	
	// 此方法将指定老人的呼吸数据导入到数据库中
	@SuppressWarnings("null")
	public void uploadBreathDataByOldman(String filePath, Oldman oldMan) {
//		breathDao = new BreathDao();
		System.out.println("开始调用uploadBreathDataByOldman(String filePath, Oldman oldMan)filePath=="+filePath);
		try {
			String encoding = "GBK";
			System.out.println("开始new File");
			File file = new File(filePath);
		//	 System.out.println("oldMan.getOldManId()===="+oldMan.getOldManId());
			// System.out.println("uploadBreathDataByOldman()==="+filePath);
			if (file.isFile() && file.exists()) {// 判断文件是否存在

				//InputStreamReader read = new InputStreamReader(
						//new FileInputStream(file), encoding);// 考虑到编码格式
				//BufferedReader bufferedReader = new BufferedReader(read);
				//String lineTxt = null;
				RandomAccessFile randomAccessFile = null;
				try {
					randomAccessFile = new RandomAccessFile(file, "rw");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}  
                try {
                    //获得变化部分的  
                    randomAccessFile.seek(lastTimeFileSize);  
                    String tmp = "";  
                    System.out.println("这是新增的文件内容");
                    while ((tmp = randomAccessFile.readLine())!= null) {
             
                        Breath b = new Breath();
    					b.setBreathDateTime(tmp);
    					b.setOldman(oldMan);
    					System.out.println("开始调用breathDao.save(b)");
    					System.out.println("b.setBreathDateTime===="+b.getBreathDateTime()+b.getOldman().getOldManId());
    				//	System.out.println("b==null的结果是"+(b==null)+"breathDao==null的结果是"+(breathDao==null));
    					if((tmp!=null)||!(tmp.equals(" "))){
    						breathDao.save(b);
    					}
                        lastTimeFileSize = randomAccessFile.length();  
                    }
				
				
			/*	12.24
			 * while ((lineTxt = bufferedReader.readLine()) != null) {
					// 创建呼吸对象
					Breath b = new Breath();
					b.setBreathDateTime(lineTxt);
					b.setOldman(oldMan);
					System.out.println("开始调用breathDao.save(b)");
					System.out.println("lineTxt===="+lineTxt);
					System.out.println("b.setBreathDateTime===="+b.getBreathDateTime()+b.getOldman().getOldManId());
				//	System.out.println("b==null的结果是"+(b==null)+"breathDao==null的结果是"+(breathDao==null));
					if((lineTxt!=null)||!(lineTxt.equals(" "))){
						breathDao.save(b);
					}
					System.out.println(lineTxt);
				}*/
			//	read.close();
                    /* }
			} else {
				System.out.println("找不到指定的文件");
			}*/
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

	}
		}catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
	}

	// 此方法将指定老人的心率数据导入到数据库中
	public void uploadHeartDataByOldman(String filePath,Oldman oldMan) {

			try {
				String encoding = "GBK";
				File file = new File(filePath);

				if (file.isFile() && file.exists()) { // 判断文件是否存在

					InputStreamReader read = new InputStreamReader(
							new FileInputStream(file), encoding);// 考虑到编码格式
					BufferedReader bufferedReader = new BufferedReader(read);
					String lineTxt = null;
					while ((lineTxt = bufferedReader.readLine()) != null) {
						//创建心跳对象
						Heart heart = new Heart();
						heart.setHeartDateTime(lineTxt);
						heart.setOldman(oldMan);
						
						heartDao.save(heart);
						
						System.out.println(lineTxt);
					}
					read.close();
				} else {
					System.out.println("找不到指定的文件");
				}
			} catch (Exception e) {
				System.out.println("读取文件内容出错");
				e.printStackTrace();
			}

		}

	// 此方法将指定老人的体动数据导入到数据库中
	public void uploadMovementDataByOldman(String filePath, Oldman oldMan) {

		try {
			String encoding = "GBK";
			File file = new File(filePath);

			if (file.isFile() && file.exists()) { // 判断文件是否存在

				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					// 创建体动对象
					Movement movement = new Movement();
					movement.setMovementDateTime(lineTxt);
					movement.setOldman(oldMan);
					
					movementDao.save(movement);

					System.out.println(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

	}

}
