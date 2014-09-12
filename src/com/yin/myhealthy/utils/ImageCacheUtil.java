package com.yin.myhealthy.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

public class ImageCacheUtil {

	private final String TAG = "ImageCacheUtil";
	private LruCache<String, Bitmap> mMemoryCache;

	// ����ģʽ
	// 1.���徲̬����
	private static ImageCacheUtil instance = null;

	// 2.���캯��˽�л�
	private ImageCacheUtil() {
		// ��ȡ�ֻ��ڴ�
		int MAXMEMONRY = (int) (Runtime.getRuntime().maxMemory() / 1024);

		if (mMemoryCache == null)
			mMemoryCache = new LruCache<String, Bitmap>(MAXMEMONRY / 8) {
				@Override
				protected int sizeOf(String key, Bitmap bitmap) {
					// ��д�˷���������ÿ��ͼƬ�Ĵ�С��Ĭ�Ϸ���ͼƬ������
					return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
				}

				@Override
				protected void entryRemoved(boolean evicted, String key,
						Bitmap oldValue, Bitmap newValue) {
					Log.v("tag", "hard cache is full , push to soft cache");

				}
			};
	}

	// 3.���干�з�����ȡImageCacheUtil����
	public static ImageCacheUtil getInstance() {
		if (instance == null) {
			instance = new ImageCacheUtil();
		}
		return instance;
	}

	/**
	 * ��ջ���
	 */
	public void clearCache() {
		if (mMemoryCache != null) {
			if (mMemoryCache.size() > 0) {
				Log.d("CacheUtils",
						"mMemoryCache.size() " + mMemoryCache.size());
				mMemoryCache.evictAll();
				Log.d("CacheUtils", "mMemoryCache.size()" + mMemoryCache.size());
			}
			mMemoryCache = null;
		}
	}

	/**
	 * ����ͼƬ��������
	 * 
	 * @param key
	 * @param bitmap
	 */
	public synchronized void addBitmapToMemoryCache(String key, Bitmap bitmap) {
		if (mMemoryCache.get(key) == null) {
			if (key != null && bitmap != null)
				mMemoryCache.put(key, bitmap);
		} else {
			Log.w(TAG, "the res is aready exits");
		}
	}

	/**
	 * �ӻ����л�ȡͼƬ
	 * 
	 * @param key
	 * @return
	 */
	public synchronized Bitmap getBitmapFromMemCache(String key) {
		Bitmap bm = mMemoryCache.get(key);
		if (key != null) {
			return bm;
		}
		return null;
	}

	/**
	 * �Ƴ�����
	 * 
	 * @param key
	 */
	public synchronized void removeImageCache(String key) {
		if (key != null) {
			if (mMemoryCache != null) {
				Bitmap bm = mMemoryCache.remove(key);
				if (bm != null)
					bm.recycle();
			}
		}
	}
}
