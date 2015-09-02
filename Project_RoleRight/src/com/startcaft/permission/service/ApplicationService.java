package com.startcaft.permission.service;

import com.startcaft.permission.domain.Application;

public interface ApplicationService {
	
	/**
	 * 获取应用程序信息
	 * @param appId---应用程序ID
	 * @return
	 */
	public Application getAppInfo(Integer appId);
}
