package com.yin.myhealthy.utils;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

public class ImageCacheUtil {

	private final String TAG = "ImageCacheUtil";
	private LruCache<String, Bitmap> mMemoryCache;

	// 单例模式
	// 1.定义静态变量
	private static ImageCacheUtil instance = null;

	// 2.构造函数私有化
	private ImageCacheUtil() {
		// 获取手机内存
		int MAXMEMONRY = (int) (Runtime.getRuntime().maxMemory() / 1024);

		if (mMemoryCache == null)
			mMemoryCache = new LruCache<String, Bitmap>(MAXMEMONRY / 8) {
				@Override
				protected int sizeOf(String key, Bitmap bitmap) {
					// 重写此方法来衡量每张图片的大小，默认返回图片数量。
					return bitmap.getRowBytes() * bitmap.getHeight() / 1024;
				}

				@Override
				protected void entryRemoved(boolean evicted, String key,
						Bitmap oldValue, Bitmap newValue) {
					Log.v("tag", "hard cache is full , push to soft cache");

				}
			};
	}

	// 3.定义共有方法获取ImageCacheUtil对象
	public static ImageCacheUtil getInstance() {
		if (instance == null) {
			instance = new ImageCacheUtil();
		}
		return instance;
	}

	/**
	 * 清空缓存
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
	 * 增加图片到缓存中
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
	 * 从缓存中获取图片
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
	 * 移除缓存
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
