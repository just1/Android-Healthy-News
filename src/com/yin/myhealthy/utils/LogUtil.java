package com.yin.myhealthy.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import android.os.Environment;
import android.util.Log;

/**
 * 日志记录
 * 
 */
public class LogUtil {
	/**
	 * �?��阶段
	 */
	private static final int DEVELOP = 0;
	/**
	 * 内部测试阶段
	 */
	private static final int DEBUG = 1;
	/**
	 * 公开测试
	 */
	private static final int BATE = 2;
	/**
	 * 正式�?
	 */
	private static final int RELEASE = 3;

	/**
	 * 当前阶段标示
	 */
	private static int currentStage = DEVELOP;

	private static String path;
	private static File file;
	private static FileOutputStream outputStream;
	private static String pattern = "yyyy-MM-dd HH:mm:ss";

	static {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File externalStorageDirectory = Environment.getExternalStorageDirectory();
			path = externalStorageDirectory.getAbsolutePath() + "/zcw/";
			File directory = new File(path);
			if (!directory.exists()) {
				directory.mkdirs();
			}
			file = new File(new File(path), "log.txt");
			android.util.Log.i("SDCAEDTAG", path);
			try {
				outputStream = new FileOutputStream(file, true);
			} catch (FileNotFoundException e) {

			}
		} else {

		}
	}

	public static void info(String msg) {
		info(LogUtil.class, msg);
	}

	public static void info(Class clazz, String msg) {
		switch (currentStage) {
			case DEVELOP:
				// 控制台输�?
				Log.i(clazz.getSimpleName(), msg);
				break;
			case DEBUG:
				// 在应用下面创建目录存放日�?
				
				break;
			case BATE:
				// 写日志到sdcard
				Date date = new Date();
				String time = DateFormatUtils.format(date, pattern);
				if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
					if (outputStream != null) {
						try {
							outputStream.write(time.getBytes());
							String className = "";
							if (clazz != null) {
								className = clazz.getSimpleName();
							}
							outputStream.write(("    " + className + "\r\n").getBytes());

							outputStream.write(msg.getBytes());
							outputStream.write("\r\n".getBytes());
							outputStream.flush();
						} catch (IOException e) {

						}
					} else {
						android.util.Log.i("SDCAEDTAG", "file is null");
					}
				}
				break;
			case RELEASE:
				// �?��不做日志记录
				break;
		}
	}
}
