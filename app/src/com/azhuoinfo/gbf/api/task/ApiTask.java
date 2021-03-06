package com.azhuoinfo.gbf.api.task;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;

public class ApiTask {
	public static final boolean DEBUG = true;
	private Context mContext;
	private String mMethod;
	private String mUrl;
	private HashMap<String, Object> mParams;
	private String mTag;
	private boolean mRunning = false;
	private ApiClient mApiClient;
	private boolean mCache;
	private long mCachePeroid;
	private ApiTask(Context context, String tag) {
		mContext = context;
		mParams = new HashMap<String, Object>();
		mTag = tag;
		mMethod = "GET";
		mApiClient = ApiClient.getInstance(mContext);
	}
	public static ApiTask build(Context context, String tag){
		return new ApiTask(context,tag);
	}
	public void setCache(boolean cache) {
		this.mCache = cache;
	}
	public void setCachePeroid(long peroid) {
		this.mCachePeroid = peroid;
	}
	public boolean IsRunning() {
		return mRunning;
	}
	public void cancel(boolean mayInterruptIfRunning) {
		mApiClient.cancel(mTag, mayInterruptIfRunning);
	}
	public static void  cancel(Context context,String tag) {
		ApiClient.getInstance(context).cancel(tag,true);
	}
	public void setUrl(String url) {
		this.mUrl = url;
	}

	public void setMethod(String method) {
		this.mMethod = method;
	}

	public void setParams(HashMap<String, String> params) {
		for (Map.Entry<String, String> entry : params.entrySet()) {
			mParams.put(entry.getKey(), entry.getValue());
		}
	}
	
	public HashMap<String, Object> getParams() {
		return mParams;
	}
	@SuppressLint("DefaultLocale")
	public <T> void execute(final OnDataLoader<T> onDataLoader) {
		if ("GET".equals(mMethod.toUpperCase())) {
			mApiClient.execute(mTag, ApiClient.Method.GET, mUrl, mParams,
					onDataLoader);
		} else {
			mApiClient.execute(mTag, ApiClient.Method.POST, mUrl, mParams,
					onDataLoader);
		}
	}
	public <T> void execute(String method,String url,HashMap<String, Object> params,final OnDataLoader<T> onDataLoader) {
		this.mMethod=method;
		this.mUrl=url;
		this.mParams=params;
		if ("GET".equals(mMethod.toUpperCase())) {
			mApiClient.execute(mTag, ApiClient.Method.GET, mUrl, mParams,
					onDataLoader);
		} else {
			mApiClient.execute(mTag, ApiClient.Method.POST, mUrl, mParams,
					onDataLoader);
		}
	}
}
