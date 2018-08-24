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

import cn.dao.IOldManDao;
import cn.entity.Alarm;
import cn.entity.Breath;
import cn.entity.Oldman;
import cn.impl.BreathDao;
import cn.service.IBreathService;

public class BreathService implements IBreathService {

	BreathDao breathDao;
	private IOldManDao oldManDao;
	
	

	public IOldManDao getOldManDao() {
		return oldManDao;
	}

	public void setOldManDao(IOldManDao oldManDao) {
		this.oldManDao = oldManDao;
	}

	// 设置注入
	public BreathDao getBreathDao() {
		return breathDao;
	}

	public void setBreathDao(BreathDao breathDao) {
		this.breathDao = breathDao;
	}

	public void save(Breath breath) {
		breathDao.save(breath);
	}

	public void update(Breath breath) {
		breathDao.update(breath);
	}

	public Breath findById(int id) {
		return breathDao.findById(id);
	}

	public List<Breath> getAll() {
		return breathDao.getAll();
	}

	public List<Breath> getAll(String breathDatetime) {

		return breathDao.getAll(breathDatetime);
	}

	public void delete(int id) {
		breathDao.delete(id);
	}

	public void deleteMany(int[] ids) {
		if (ids != null && ids.length > 0) {
			for (int id : ids) {
				delete(id);
			}
		}
	}

	//9.6  
	// 通过给定某一天，返回这一天的每一小时的平均呼吸次数[123,456,123,456,147,258,369,456]一共24个数据
	public List<Integer> getDetailedBreathByDay(Oldman oldMan, String dayStr) {

		List<Breath> listBreath = breathDao.getAllByOldAndDay(oldMan, dayStr);
		List<Integer> listCount = getDetailedBreathFun(listBreath);

		return listCount;
	}

	public List<Integer> getDetailedBreathFun(List<Breath> listBreath) {

		TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
		List<Integer> listCount = new ArrayList<Integer>();

		// 给tm设置24个值，默认设置为0
		for (int i = 1; i <= 24; i++) {
			String key = null;

			if (i <= 9) {
				key = "0" + i;
			} else {
				key = i + "";
			}
			if (key != null && key.trim().length() > 0) {
				tm.put(key, 0);
			}
		}

		for (Breath breath : listBreath) {

			// 时间的格式 2017 09 05 12 23 56，中间没有空格
			// 提取呼吸时间值的前10个字符，包含到小时 2017072017 2017-07-20-17：点
			String breathHour = breath.getBreathDateTime().substring(8, 10);
			if (tm.containsKey(breathHour)) {
				Integer breathValue = tm.get(breathHour);
				breathValue = breathValue + 1;
				tm.put(breathHour, breathValue);
			}
		}

		for (int i = 1; i <= 24; i++) {
			String key = null;
			if (i <= 9) {
				key = "0" + i;
			} else {
				key = i + "";
			}

			if (key != null && key.length() > 0) {
				if (tm.containsKey(key)) {
					listCount.add(tm.get(key));
				}
			}
		}
		
		return listCount;
	}

	
	//9.5  待完善
	public List<Integer> findByOldIdAndDate(Oldman oldMan,String breathDateTime){
		return null;
		
	}

	/**
	 * 获取每小时最大的呼吸值
	 */
	public int getMaxBreath(String dayStr) {
		// 获取给定日期下的所有的呼吸数据
		List<Breath> listBreath = getAll(dayStr);
		return getMaxBreathFun(listBreath);
	}

	/**
	 * 获取每小时的最大的呼吸值
	 */
	public int getMinBreath(String dayStr) {
		// 获取给定日期下的所有的呼吸数据
		List<Breath> listBreath = getAll(dayStr);
		return getMinBreathFun(listBreath);
	}

	// 获取所有老人的每分钟的平均呼吸值
	public double getMeanBreath(String dayStr) {
		// 获取给定日期下的所有的呼吸数据
		List<Breath> listBreath = getAll(dayStr);
		return getMeanBreathFun(listBreath);
	}

	// 获取指定老人的该天的最大呼吸值
	public int getMaxBreath(Oldman oldMan, String dayStr) {
		List<Breath> listBreath = breathDao.getAllByOldAndDay(oldMan, dayStr);

		return getMaxBreathFun(listBreath);
	}

	// 获取指定老人的该天的最小呼吸值
	public int getMinBreath(Oldman oldMan, String dayStr) {

		List<Breath> listBreath = breathDao.getAllByOldAndDay(oldMan, dayStr);

		return getMinBreathFun(listBreath);
	}

	// 获取指定老人的该天的平均呼吸值
	public double getMeanBreath(Oldman oldMan, String dayStr) {
		// 首先获取所有指定老人id和日期的呼吸数据
		List<Breath> listBreath = breathDao.getAllByOldAndDay(oldMan, dayStr);
		return getMeanBreathFun(listBreath);
	}

	public int getMaxBreathBySE(Oldman oldMan, String start, String end) {

		List<Breath> listBreath = breathDao
				.getByOldStartEnd(oldMan, start, end);
		return getMaxBreathFun(listBreath);
	}

	public int getMinBreathBySE(Oldman oldMan, String start, String end) {

		List<Breath> listBreath = breathDao
				.getByOldStartEnd(oldMan, start, end);

		return getMinBreathFun(listBreath);
	}

	public double getMeanBreathBySE(Oldman oldMan, String start, String end) {

		List<Breath> listBreath = breathDao
				.getByOldStartEnd(oldMan, start, end);

		return getMeanBreathFun(listBreath);
	}

	// 获取集合中的最大呼吸值的函数
	public int getMaxBreathFun(List<Breath> listBreath) {

		TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
		for (Breath breath : listBreath) {
			// 提取呼吸时间值的前10个字符，包含到小时 2017072017 2017-07-20-17：点
			String breathHour = breath.getBreathDateTime().substring(0, 10);
			if (tm.containsKey(breathHour)) {
				Integer breathValue = tm.get(breathHour);
				breathValue = breathValue + 1;
				tm.put(breathHour, breathValue);

			} else {
				tm.put(breathHour, 1);
			}
		}

		Collection<Integer> set = tm.values();
		
		System.out.println("getMaxBreathFun(List<Breath> listBreath)=="+Collections.max(set));
		return Collections.max(set);
		
	}

	public int getMinBreathFun(List<Breath> listBreath) {

		TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
		for (Breath breath : listBreath) {
			// 提取呼吸时间值的前10个字符，包含到小时 2017072017 2017-07-20-17：点
			String breathHour = breath.getBreathDateTime().substring(0, 10);
			if (tm.containsKey(breathHour)) {
				Integer breathValue = tm.get(breathHour);
				breathValue = breathValue + 1;
				tm.put(breathHour, breathValue);

			} else {
				tm.put(breathHour, 1);
			}
		}

		Collection<Integer> set = tm.values();
		return Collections.min(set);
	}

	public double getMeanBreathFun(List<Breath> listBreath) {
		// 首先获取所有指定老人id和日期的呼吸数据
		TreeMap<String, Integer> tm = new TreeMap<String, Integer>();

		// 通过遍历数据，将在同一个小时内的数据设置一个值，进行加，保存到集合中
		for (Breath breath : listBreath) {
			// 提取呼吸时间值的前10个字符，包含到小时 2017072017 2017-07-20-17：点
			String breathHour = breath.getBreathDateTime().substring(0, 10);

			if (tm.containsKey(breathHour)) {
				Integer breathValue = tm.get(breathHour);
				breathValue = breathValue + 1;
				tm.put(breathHour, breathValue);

			} else {
				tm.put(breathHour, 1);
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
		// 使用总的心跳次数除以小时个数
		return sum * 1.0 / keyCount;
	}

	public void uploadBreathData(Oldman oldMan, String filePath) {
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
					b.setOldman(oldMan);
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
	
	
	/**
	 * 传入的参数为date，表示在现在的时间之前的三分钟内的所有上传的数据，都取出来，然后根据老人的id进行检测异常
	 * 步骤是：1.首先获取每个老人的三分钟数据， 2.根据每个老人的数据，检测呼吸值是否正常，如果不正常，将不正常的数据放入到Alarm中
	 */
	public List<Alarm> detectAbnormal(String date) {

		// 集合中存放异常报警信息
		List<Alarm> alarmList = null;
				

		// 获取指定时间下的所有老人的数据
		List<Oldman> oldManList = oldManDao.getAll();
		System.out.println(oldManList);
		System.out.println(date);
		for (Oldman oldMan : oldManList) {

			List<Breath> breathList = breathDao.getOldmanBefore3Minute(oldMan,
					date);
			System.out.println(breathList.size());
			int isAbnormal = detect(breathList);
			if (isAbnormal == 0) {
				break;

			} else if (isAbnormal == 1) {
				Alarm alarm = new Alarm();
				alarm.setAlarmTime(date);
				alarm.setOldman(oldMan);
				alarm.setType("呼吸过快");
				alarmList = new ArrayList<Alarm>();
				alarmList.add(alarm);

			} else if (isAbnormal == 2) {
				Alarm alarm = new Alarm();
				alarm.setAlarmTime(date);
				alarm.setOldman(oldMan);
				alarm.setType("呼吸过慢");
				alarmList = new ArrayList<Alarm>();
				alarmList.add(alarm);
			}
		}

		return alarmList;
	}

	public int detect(List<Breath> listBreath) {

		int TYPE = 0;

		// 呼吸的正常值为10-37，如果最大呼吸值超过37，则报出异常，类型为1，
		// 如果最小呼吸值为10，则报出异常，类型为2，表示呼吸过慢
		if (listBreath.size() < 1) {
			TYPE = 0;
		} else {

			if (this.getMaxBreathFun(listBreath) > 37) {
				TYPE = 1;

			} else if (this.getMinBreathFun(listBreath) < 10) {
				TYPE = 2;
			}
		}
		return TYPE;
	}

}
