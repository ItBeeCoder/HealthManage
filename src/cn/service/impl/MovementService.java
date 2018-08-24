package cn.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import cn.dao.IMovementDao;
import cn.entity.Movement;
import cn.entity.Oldman;
import cn.service.IMovementService;

public class MovementService implements IMovementService {

	private IMovementDao movementDao;

	public IMovementDao getMovementDao() {
		return movementDao;
	}

	public void setMovementDao(IMovementDao movementDao) {
		this.movementDao = movementDao;
	}

	public void save(Movement movement) {
		movementDao.save(movement);
	}

	public void delete(int id) {
		movementDao.delete(id);
	}

	public void deleteMany(int[] ids) {
		if (ids != null && ids.length > 0) {
			for (int id : ids) {
				delete(id);
			}
		}
	}

	public void update(Movement movement) {
		movementDao.update(movement);
	}

	public Movement findById(int id) {
		return movementDao.findById(id);
	}

	public List<Movement> getAll() {

		return movementDao.getAll();
	}

	public List<Movement> getAll(String dayStr) {

		return movementDao.getAllByTime(dayStr);

	}

	public List<Movement> getAll(Oldman oldMan) {

		return movementDao.getAllByOld(oldMan);
	}

	public int getMovementAccount(Oldman oldMan, String dayStr) {
		List<Movement> listMovement = movementDao.getAllByOldAndDay(oldMan,
				dayStr);
		return listMovement.size();
	}

	public int getAllMovementDuration(Oldman oldMan, String dayStr) {
		List<Movement> listMovement = movementDao.getAllByOldAndDay(oldMan,
				dayStr);

		long sum = 0;
		for (Movement movement : listMovement) {
			long duration = 0;
			String stop = movement.getStop();
			String start = movement.getStart();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			try {
				Date t1 = (Date) sdf.parse(stop);
				Date t2 = (Date) sdf.parse(start);
				duration = t1.getTime() - t2.getTime();
				sum += duration;
			} catch (ParseException e) {
				System.out.println("日期转换异常");
				e.printStackTrace();
			}
		}

		// 获取该天下体动总分钟数
		return (int) (sum / 1000 / 60);
	}

	public void uploadMovementData(Oldman oldMan, String filePath) {
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
					// 将读取到的数据保存到数据库
					movement.setMovementDateTime(lineTxt);
					// 设置老人
					movement.setOldman(oldMan);
					// 将体动保存到数据库中
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
