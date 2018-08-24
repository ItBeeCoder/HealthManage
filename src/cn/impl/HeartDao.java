package cn.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.dao.IHeartDao;
import cn.entity.Heart;
import cn.entity.Oldman;

public class HeartDao extends BaseDao<Heart> implements IHeartDao {

	@SuppressWarnings("unchecked")
	public List<Heart> getAll(String heartDatetime) {
		return getSessionFactory().getCurrentSession()//
				.createQuery("from Heart where heartDateTime like ?")//
				.setParameter(0, heartDatetime + "%")//
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Heart> getAllByOld(Oldman oldMan) {
		return getSessionFactory().getCurrentSession()//
				.createQuery("from Heart as h where h.oldman = ?")//
				.setParameter(0, oldMan)//
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Heart> getAllByOldAndDay(Oldman oldMan, String heartDateTime) {
		return getSessionFactory().getCurrentSession()
				//
				.createQuery(
						"from Heart as h where h.oldman = ? and heartDateTime like ?")// 8.31
																						// oldman改过
				.setParameter(0, oldMan)//
				.setParameter(1, "%" + heartDateTime + "%")// 此处改动过
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Heart> getAllByOldStartEnd(Oldman oldMan, String start,
			String end) {
		// 下面的方法不正确
		// ===============================================================
		return getSessionFactory().getCurrentSession()
				//
				.createQuery(
						"from Heart as h where h.oldman = ? and heartDateTime>? and heartDateTime<?")//
				.setParameter(0, oldMan)//
				.setParameter(1, start)//
				.setParameter(2, end)//
				.list();
		// ==================================================================
	}

	// 获取从现在往前推三分钟的心率数据,指定老人的前三分钟的数据,便于后边的异常检测
	@SuppressWarnings("unchecked")
	public List<Heart> getOldmanBefore3Minute(Oldman oldMan, String dateStr) {
		// 获取请求时间下的前三分钟的时间，由于所请求的时间有可能才刚开始，采集的数据只有一条，因此
		// 获取在指定时间分钟下的前三个分钟的数据
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (Exception e) {
			System.out.println("日期转换异常");
			e.printStackTrace();
		}
		if (date == null) {
			Date now = new Date();
			String nowStr = sdf.format(now);
			try {
				date = sdf.parse(nowStr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Date d1 = new Date(date.getTime() - 60000);
		Date d2 = new Date(date.getTime() - 120000);
		Date d3 = new Date(date.getTime() - 180000);

		String queryTime = sdf.format(date).substring(0, 12);
		String queryTime1 = sdf.format(d1).substring(0, 12);
		String queryTime2 = sdf.format(d2).substring(0, 12);
		String queryTime3 = sdf.format(d3);

		// 请求的数据为 201709271628，也就是2017-09-28 16:28分之前的三个分钟的数据
		// 也就是2017-09-28 16:27，2017-09-28 16:26，2017-09-28 16:25

		try {
			// 通过时间字符串匹配原则去查找所有的前三分钟的数据
			List<Heart> heartList;
			// 获取第一个时间的breathList
			heartList = getSessionFactory().getCurrentSession()
					.createQuery("from Heart as h where h.oldman =? "//
							+ " and heartDateTime like ? ")//
					.setParameter(0, oldMan)//
					.setParameter(1, queryTime + "%")//
					.list();
			// 获取第二个时间的breathList
			List<Heart> heartList1 = getSessionFactory().getCurrentSession()
					.createQuery("from Heart as h where h.oldman =? "//
							+ " and heartDateTime like ? ")//
					.setParameter(0, oldMan)//
					.setParameter(1, queryTime1 + "%")//
					.list();
			// 获取第三个时间的breathList
			List<Heart> heartList2 = getSessionFactory().getCurrentSession()
					.createQuery("from Heart as h where h.oldman =? "//
							+ " and heartDateTime like ? ")//
					.setParameter(0, oldMan)//
					.setParameter(1, queryTime2 + "%")//
					.list();
			// concat
			heartList.addAll(heartList1);
			heartList.addAll(heartList2);

			return heartList;
		} catch (Exception e) {
			System.out.println("sql语句出错");
			e.printStackTrace();
		}
		return null;
	}
}
