package cn.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.dao.IBreathDao;
import cn.entity.Breath;
import cn.entity.Oldman;

public class BreathDao extends BaseDao<Breath> implements IBreathDao {

	@SuppressWarnings("unchecked")
	/**
	 * 模糊查询指定日期字符串匹配的所有呼吸对象
	 */
	public List<Breath> getAll(String breathDatetime) {
		return getSessionFactory().getCurrentSession()//
				.createQuery("from Breath where breathDateTime like ?")//
				.setParameter(0, breathDatetime + "%")//
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Breath> getAllByOld(Oldman oldMan) {
		return getSessionFactory().getCurrentSession()//
				.createQuery("from Breath as b where b.oldman =?")//
				.setParameter(0, oldMan)//
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Breath> getAllByOldAndDay(Oldman oldMan, String dayStr) {
		return getSessionFactory().getCurrentSession()
				//
				.createQuery(
						"from Breath as b where b.oldman =? and b.breathDateTime like ?")//
				.setParameter(0, oldMan)//
				.setParameter(1, dayStr + "%")// 此处加了一个"%"
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Breath> getByOldStartEnd(Oldman oldMan, String start, String end) {
		// 此方法不对，有待改正…………………………………………………………………………………………………………
		// ============================================================================
		return getSessionFactory().getCurrentSession()
				//
				.createQuery(
						"from Breath as b where b.oldman =? and breathDateTime>? and breathDateTime<?")//
				.setParameter(0, oldMan)//
				.setParameter(1, start + "%")// 9.5此处加了一个"%"
				.setParameter(2, end)//
				.list();
		// ==================================================================================
	}

	// 获取从现在往前推三分钟的数据,指定老人的前三分钟的数据,便于后边的异常检测
	@SuppressWarnings("unchecked")
	public List<Breath> getOldmanBefore3Minute(Oldman oldMan, String dateStr) {
		// 获取请求时间下的前三分钟的时间，由于所请求的时间有可能才刚开始，采集的数据只有一条，因此
		// 获取在指定时间分钟下的前三个分钟的数据
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			System.out.println("日期转换异常");
			e.printStackTrace();
		}
		if (date == null) {
			Date now = new Date();
			String nowStr = sdf.format(now);
			try {
				date = sdf.parse(nowStr);
			} catch (ParseException e) {
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
		String[] queryTimeArr = new String[] { queryTime, queryTime1,
				queryTime2 };
		// 请求的数据为 201709271628，也就是2017-09-28 16:28分之前的三个分钟的数据
		// 也就是2017-09-28 16:27，2017-09-28 16:26，2017-09-28 16:25

		try {
			// 通过时间字符串匹配原则去查找所有的前三分钟的数据
			List<Breath> breathList;
			// 获取第一个时间的breathList
			breathList = getSessionFactory().getCurrentSession()
					.createQuery("from Breath as b where b.oldman =? "//
							+ " and breathDateTime like ? ")//
					.setParameter(0, oldMan)//
					.setParameter(1, queryTime + "%")//
					.list();
			// 获取第二个时间的breathList
			List<Breath> breathList1 = getSessionFactory().getCurrentSession()
					.createQuery("from Breath as b where b.oldman =? "//
							+ " and breathDateTime like ? ")//
					.setParameter(0, oldMan)//
					.setParameter(1, queryTime1 + "%")//
					.list();
			// 获取第三个时间的breathList
			List<Breath> breathList2 = getSessionFactory().getCurrentSession()
					.createQuery("from Breath as b where b.oldman =? "//
							+ " and breathDateTime like ? ")//
					.setParameter(0, oldMan)//
					.setParameter(1, queryTime2 + "%")//
					.list();
			// concat
			breathList.addAll(breathList1);
			breathList.addAll(breathList2);

			return breathList;
		} catch (Exception e) {
			System.out.println("sql语句出错");
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Breath> getOldmanDuring4Hours(Oldman oldMan, String detectHour) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhh");
		Date date = null;
		try {
			date = sdf.parse(detectHour);
		} catch (ParseException e) {
			System.out.println("日期转换异常");
			e.printStackTrace();
		}
		if (date == null) {
			Date now = new Date();
			String nowStr = sdf.format(now);
			try {
				date = sdf.parse(nowStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 查询时间的前一个小时
		Date d1 = new Date(date.getTime() - 60000 * 60);
		// 查询时间的前两个小时
		Date d2 = new Date(date.getTime() - 60000 * 60 * 2);
		// 查询时间的后一个小时
		Date d3 = new Date(date.getTime() + 60000 * 60);

		String queryTime = sdf.format(date);
		String queryTime1 = sdf.format(d1);
		String queryTime2 = sdf.format(d2);
		String queryTime3 = sdf.format(d3);

		try {
			// 通过时间字符串匹配原则去查找所有的前两个小时和后一个小时的数据
			List<Breath> breathList;
			// 获取当前小时的breathList
			breathList = getSessionFactory().getCurrentSession()
					.createQuery("from Breath as b where b.oldman =? "//
							+ " and breathDateTime like ? ")//
					.setParameter(0, oldMan)//
					.setParameter(1, queryTime + "%")//
					.list();
			// 获取前两个小时的breathList
			List<Breath> breathList1 = getSessionFactory().getCurrentSession()
					.createQuery("from Breath as b where b.oldman =? "//
							+ " and breathDateTime like ? ")//
					.setParameter(0, oldMan)//
					.setParameter(1, queryTime1 + "%")//
					.list();
			// 获取前一个小时的breathList
			List<Breath> breathList2 = getSessionFactory().getCurrentSession()
					.createQuery("from Breath as b where b.oldman =? "//
							+ " and breathDateTime like ? ")//
					.setParameter(0, oldMan)//
					.setParameter(1, queryTime2 + "%")//
					.list();

			// 获取前一个小时的breathList
			List<Breath> breathList3 = getSessionFactory().getCurrentSession()
					.createQuery("from Breath as b where b.oldman =? "//
							+ " and breathDateTime like ? ")//
					.setParameter(0, oldMan)//
					.setParameter(1, queryTime3 + "%")//
					.list();
			// concat
			breathList.addAll(breathList1);
			breathList.addAll(breathList2);
			breathList.addAll(breathList3);

			return breathList;

		} catch (Exception e) {
			System.out.println("sql语句出错");
			e.printStackTrace();
		}
		return null;
	}

}
