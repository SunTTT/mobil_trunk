package com.mobile.publiclass;

import com.mobile.appPre.PublicApplication;
import com.mobile.appPre.R;



import android.app.Activity;
import android.app.ActivityGroup;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;

public class ErrorFeedback extends ActivityGroup {
	private static ErrorFeedback instance;
	// 5xx 服务器中出现的错误
	public final int ERROR_TYPE_NOSERVICE = 5003;// 无法获得服务 — 由于临时过载或维护，服务器无法处理请求
	public final int ERROR_TYPE_SERVIC_EUNEXPECTED = 5000;// 因为意外情况，服务器不能完成请求
	public final int ERROR_TYPE_SERVIC_SERVICELINK = 5001;// 连接服务器出错—无法获得服务
	
	public final int ERROR_TYPE_SERVIC_EMPTY_DATABASE = 5004;// 连接服务器出错—无法连接数据库
	public final int ERROR_TYPE_SERVIC_EMPTY_ILLEGAL = 5005;// 非法连接服务器
	public final int ERROR_TYPE_NO_RESOURCES = 5006; // 资源不存在

	// 4xx 客户机中出现的错误
	public final int ERROR_TYPE_NONETWORK = 4010;// 未授权：无法连接网络
	public final int ERROR_TYPE_LOGINFAIL = 4011;// 未授权：登录失败

	
	// private PublicApplication App = new PublicApplication();

	public ErrorFeedback() {
		// TODO Auto-generated constructor stub
	}

	public static ErrorFeedback getError() {
		if (instance == null) {
			instance = new ErrorFeedback();

		}
		return instance;
	}

	public String Error(final int id, Resources res) {
		String error_string = null;

		switch (id) {
		case ERROR_TYPE_NOSERVICE:
			error_string = res.getString(R.string.ERROR_TYPE_NOSERVICE)
					.toString();
			break;
		case ERROR_TYPE_SERVIC_EUNEXPECTED:
			Log.i("*********",
					"错误信息："
							+ res.getString(
									R.string.ERROR_TYPE_SERVIC_EUNEXPECTED)
									.toString());
			error_string = res
					.getString(R.string.ERROR_TYPE_SERVIC_EUNEXPECTED)
					.toString();
			break;
		case ERROR_TYPE_SERVIC_SERVICELINK:
			error_string = res
					.getString(R.string.ERROR_TYPE_SERVIC_SERVICELINK)
					.toString();
			break;
		
		case ERROR_TYPE_SERVIC_EMPTY_ILLEGAL:
			error_string = res.getString(
					R.string.ERROR_TYPE_SERVIC_EMPTY_ILLEGAL).toString();
			break;
		case ERROR_TYPE_SERVIC_EMPTY_DATABASE:
			error_string = res.getString(
					R.string.ERROR_TYPE_SERVIC_EMPTY_DATABASE).toString();
			break;
		case ERROR_TYPE_NO_RESOURCES:
			error_string = res.getString(R.string.ERROR_TYPE_NO_RESOURCES)
					.toString();
			break;

		case ERROR_TYPE_NONETWORK:
			error_string = res.getString(R.string.ERROR_TYPE_NONETWORK)
					.toString();
			break;
		case ERROR_TYPE_LOGINFAIL:
			error_string = res.getString(R.string.ERROR_TYPE_LOGINFAIL)
					.toString();
			break;

		// 6xxxx
		
			
			
		}

		return error_string;
	}

}
