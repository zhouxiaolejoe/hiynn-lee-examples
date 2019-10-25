package com.hiynn.spring.quartz.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.BlobMessage;

import javax.jms.*;
import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileReciever {
	/**
	 * @param args
	 * @throws JMSException
	 */
	public static void main(String[] args) throws JMSException {
		// 获取 ConnectionFactory
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.238.106:61616");

		// 创建 Connection
		Connection connection = connectionFactory.createConnection();
		connection.start();
		// 创建 Session
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 创建 Destinatione
		Destination destination = session.createQueue("File.Transport");
		// 创建 Consumer
		MessageConsumer consumer = session.createConsumer(destination);
		// 注册消息监听器，当消息到达时被触发并处理消息
		consumer.setMessageListener(new MessageListener() {
			// 监听器中处理消息
			@Override
			public void onMessage(Message message) {
				if (message instanceof BlobMessage) {
					BlobMessage blobMessage = (BlobMessage) message;
					try {
						String fileName = blobMessage.getStringProperty("FILE.NAME");
						System.out.println("文件接收请求处理：" + fileName + "，文件大小：" + blobMessage.getLongProperty("FILE.SIZE")
								+ " 字节");
						JFileChooser fileChooser = new JFileChooser();
						fileChooser.setDialogTitle("请指定文件保存位置");
						fileChooser.setSelectedFile(new File(fileName));
						if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
							File file = fileChooser.getSelectedFile();
							OutputStream os = new FileOutputStream(file);
							System.out.println("开始接收文件：" + fileName);
							InputStream inputStream = blobMessage.getInputStream();
							// 写文件，你也可以使用其他方式
							byte[] buff = new byte[256];
							int len = 0;
							while ((len = inputStream.read(buff)) > 0) {
								os.write(buff, 0, len);
							}
							os.close();
							System.out.println("完成文件接收：" + fileName);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else {
					System.err.println(message);
				}
			}
		});
	}
}