package com.startcaft.permission.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.startcaft.permission.dao.ApplicationDao;
import com.startcaft.permission.domain.Application;
import com.startcaft.permission.service.ApplicationService;


@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private ApplicationDao appDao;
	
	@Override
	public Application getAppInfo(Integer appId) {
		
		Application app = appDao.findById(appId);
		if(app == null){
			app = null;
		}
		return app;
	}

}
