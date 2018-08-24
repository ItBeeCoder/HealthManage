package com.itc.socket;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.annotation.Resource;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/newwebsocket/{userId}")
public class WebSocketServer {
	@Resource
	private WebSocketServer webcomment;

	// 用来记录当前用户的ID
	private String currentUserId;
	// 与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	// 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static int onlineCount = 0;

	// concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();
	// 线程安全的Map
	static ConcurrentHashMap<String, Session> webSocketMap = new ConcurrentHashMap<String, Session>();// 建立连接的方法

	
	public String getCurrentUserId() {
		return currentUserId;
	}

	/**
	 * 连接建立成功调用的方法
	 * 
	 * @param session
	 *            可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(@PathParam(value = "userId") String userId,
			Session session) {
		/*
		 * 获取从/websocket开始的整条链接，用于获取userId？***=***的参数 String uri =
		 * session.getRequestURI().toString();
		 */
		currentUserId = userId;
		this.session = session;
		webSocketMap.put(userId, session);// 加入到map中
		webSocketSet.add(this);// 加入set中

		addOnlineCount(); // 在线数加1

		System.out.println("从客户端传递过来的，user is " + currentUserId);
		System.out.println(userId + "进入聊天室");
		System.out.println("websocketServer 有新连接加入！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose(Session session) {

		Map<String, String> map = session.getPathParameters();

		webSocketMap.remove(map.get("userId")); // 从set中删除
		webSocketSet.remove(this);

		// 打印当前在线人数的id
		for (String user : webSocketMap.keySet()) {
			System.out.println("在线的userId为： " + user);
		}

		subOnlineCount(); // 在线数减1

		System.out.println("关闭连接的用户Id为： " + map.get("userId"));
		System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
	}

	/**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message
	 *            客户端发送过来的消息
	 * @param session
	 *            可选的参数
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("webSocketServer来自客户端的消息:" + message);
		// 获取用户ID
		Map<String, String> map = session.getPathParameters();
		String userId = map.get("userId");
		for (String user : webSocketMap.keySet()) {
			try {
				sendMessage(user + "你好，我是" + userId + "   " + message,
						webSocketMap.get(user));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 发生错误时调用
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("发生错误");
		error.printStackTrace();
	}

	public void sendMessage(String message, Session session) throws IOException {

		if (session.isOpen()) {
			session.getAsyncRemote().sendText(message);
		}

	}

	/**
	 * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
	 * 
	 * @param message
	 * @throws IOException
	 */
	public void sendMessage(String message) throws IOException {

		this.session.getBasicRemote().sendText(message);
	}

	public static synchronized int getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocketServer.onlineCount++;
	}

	public static synchronized void subOnlineCount() {
		WebSocketServer.onlineCount--;
	}
}
