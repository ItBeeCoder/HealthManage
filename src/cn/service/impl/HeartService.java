package cn.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import cn.dao.IHeartDao;
import cn.dao.IOldManDao;
import cn.entity.Alarm;
import cn.entity.Heart;
import cn.entity.Oldman;
import cn.service.IHeartService;

public class HeartService implements IHeartService {

	private IHeartDao heartDao;
	private IOldManDao oldManDao;
	
	
	public IOldManDao getOldManDao() {
		return oldManDao;
	}

	public void setOldManDao(IOldManDao oldManDao) {
		this.oldManDao = oldManDao;
	}

	public IHeartDao getHeartDao() {
		return heartDao;
	}

	public void setHeartDao(IHeartDao heartDao) {
		this.heartDao = heartDao;
	}

	public void save(Heart heart) {
		heartDao.save(heart);
	}

	public void update(Heart heart) {
		heartDao.update(heart);
	}

	public Heart findById(int id) {
		return heartDao.findById(id);
	}

	public List<Heart> getAll() {
		return heartDao.getAll();
	}

	public List<Heart> getAll(String heartDatetime) {
		return heartDao.getAll(heartDatetime);
	}

	public void delete(int id) {
		heartDao.delete(id);
	}

	public void deleteMany(int[] ids) {
		if (ids != null && ids.length > 0) {
			for (int id : ids) {
				delete(id);
			}
		}
	}

	public int getMaxHeart(String dayStr) {
		// 获取给定日期下的所有的心率数据
		List<Heart> listHeart = getAll(dayStr);
		return getMaxHeartFun(listHeart);
	}

	public int getMinHeart(String dayStr) {
		// 获取给定日期下的所有的心率数据
		List<Heart> listHeart = getAll(dayStr);
		return getMinHeartFun(listHeart);
	}

	public double getMeanHeart(String dayStr) {
		// 获取给定日期下的所有的心率数据
		List<Heart> listHeart = getAll(dayStr);
		return getMeanHeartFun(listHeart);
	}

	public int getMaxHeart(Oldman oldMan, String heartDateTime) {
		List<Heart> listHeart = heartDao.getAllByOldAndDay(oldMan,
				heartDateTime);
		return getMaxHeartFun(listHeart);
	}

	public int getMinHeart(Oldman oldMan, String heartDateTime) {
		List<Heart> listHeart = heartDao.getAllByOldAndDay(oldMan,
				heartDateTime);
		return getMinHeartFun(listHeart);
	}

	public double getMeanHeart(Oldman oldMan, String heartDateTime) {
		List<Heart> listHeart = heartDao.getAllByOldAndDay(oldMan,
				heartDateTime);
		return getMeanHeartFun(listHeart);
	}

	public int getMaxHeartBySE(Oldman oldMan, String start, String end) {
		List<Heart> listHeart = heartDao
				.getAllByOldStartEnd(oldMan, start, end);
		return getMaxHeartFun(listHeart);

	}

	public int getMinHeartBySE(Oldman oldMan, String start, String end) {
		List<Heart> listHeart = heartDao
				.getAllByOldStartEnd(oldMan, start, end);
		return getMinHeartFun(listHeart);

	}

	public double getMeanHeartBySE(Oldman oldMan, String start, String end) {
		List<Heart> listHeart = heartDao
				.getAllByOldStartEnd(oldMan, start, end);
		return getMeanHeartFun(listHeart);

	}

	public int getMinHeartFun(List<Heart> listHeart) {
		TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
		for (Heart heart : listHeart) {
			// 提取心率时间值的前10个字符，包含到小时 2017072017 2017-07-20-17：点
			String heartMinute = heart.getHeartDateTime().substring(0, 12);

			if (tm.containsKey(heartMinute)) {
				Integer heartValue = tm.get(heartMinute);
				heartValue = heartValue + 1;
				tm.put(heartMinute, heartValue);
			} else {
				tm.put(heartMinute, 1);
			}
		}

		Collection<Integer> set = tm.values();
		return Collections.min(set);
	}

	public int getMaxHeartFun(List<Heart> listHeart) {
		TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
		for (Heart heart : listHeart) {
			// 提取心率时间值的前10个字符，包含到小时 2017072017 2017-07-20-17：点
			String heartMinute = heart.getHeartDateTime().substring(0, 12);

			if (tm.containsKey(heartMinute)) {
				Integer heartValue = tm.get(heartMinute);
				heartValue = heartValue + 1;
				tm.put(heartMinute, heartValue);
			} else {
				tm.put(heartMinute, 1);
			}
		}

		Collection<Integer> set = tm.values();
		return Collections.max(set);
	}

	public double getMeanHeartFun(List<Heart> listHeart) {
		TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
		for (Heart heart : listHeart) {
			// 提取心率时间值的前10个字符，包含到小时 2017072017 2017-07-20-17：点
			String heartMinute = heart.getHeartDateTime().substring(0, 12);

			if (tm.containsKey(heartMinute)) {
				Integer heartValue = tm.get(heartMinute);
				heartValue = heartValue + 1;
				tm.put(heartMinute, heartValue);
			} else {
				tm.put(heartMinute, 1);
			}
		}

		// 获取所有键的集合
		Collection<String> setKey = tm.keySet();
		// 获取所有键的长度，为了获取一共有多少个分钟的数据
		int keyCount = setKey.size();
		int sum = 0;
		Set<Entry<String, Integer>> keyValue = tm.entrySet();

		for (Entry<String, Integer> entry : keyValue) {
			sum += entry.getValue();
		}
		// 使用总的心跳次数除以分钟个数
		return sum * 1.0 / keyCount;
	}

	public void uploadHeartData(Oldman oldMan, String filePath) {
		try {
			String encoding = "GBK";
			File file = new File(filePath);

			if (file.isFile() && file.exists()) { // 判断文件是否存在

				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					// 创建心跳对象

					Heart heart = new Heart();
					heart.setOldman(oldMan);
					heart.setHeartDateTime(lineTxt);
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

	/**
	 * 传入的参数为date，表示在现在的时间之前的三分钟内的所有上传的数据，都取出来，然后根据老人的id进行检测异常
	 * 步骤是：1.首先获取每个老人的三分钟数据， 2.根据每个老人的数据，检测心率值是否正常，如果不正常，将不正常的数据放入到Alarm中
	 */
	public List<Alarm> detectHeartAbnormal(String date) {

		// 集合中存放异常报警信息
		List<Alarm> alarmList = null;

		// 获取指定时间下的所有老人的数据
		List<Oldman> oldManList = oldManDao.getAll();
		System.out.println(oldManList);
		System.out.println(date);
		for (Oldman oldMan : oldManList) {

			List<Heart> heartList = heartDao.getOldmanBefore3Minute(oldMan,
					date);
			System.out.println(heartList.size());
			int isAbnormal = detect(heartList);
			if (isAbnormal == 0) {
				break;

			} else if (isAbnormal == 1) {
				Alarm alarm = new Alarm();
				alarm.setAlarmTime(date);
				alarm.setOldman(oldMan);
				alarm.setType("心率过快");
				alarmList = new ArrayList<Alarm>();
				alarmList.add(alarm);

			} else if (isAbnormal == 2) {
				Alarm alarm = new Alarm();
				alarm.setAlarmTime(date);
				alarm.setOldman(oldMan);
				alarm.setType("心率过慢");
				alarmList = new ArrayList<Alarm>();
				alarmList.add(alarm);
			}
		}

		return alarmList;
	}

	public int detect(List<Heart> listHeart) {

		int TYPE = 0;

		// 心率的正常值为60-100，如果最大心率值超过110，则报出异常，类型为1，
		// 如果最小心率值为50，则报出异常，类型为2，表示心率过慢
		if (listHeart.size() < 1) {
			TYPE = 0;
		} else {

			if (this.getMaxHeartFun(listHeart) > 110) {
				TYPE = 1;

			} else if (this.getMinHeartFun(listHeart) < 50) {
				TYPE = 2;
			}
		}
		return TYPE;
	}

}
