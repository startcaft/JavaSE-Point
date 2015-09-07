package com.startcaft.permission.common;


import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XMLHelper {
	
	/**
	 * 输出一个子节点为error的XML文档
	 * @param errorMsg---错误信息
	 * @return xml字符串
	 */
	public static String printlnErrorElement(String errorMsg){
		Document document  = DocumentHelper.createDocument();
		//根节点---departments
		Element root = document.addElement("departments");
		//一级节点---error
		Element error = root.addElement("error");	
		error.addText(errorMsg);
		
		Element getRootAction =  root.addElement("action");
		getRootAction.addAttribute("doc", "根据应用程序的ID获取其相关的根节点部门信息");
		getRootAction.addText("Http://ServerIP:portNumber/WebAppName/depart?action=query&appid={0}");
		
		Element getChildAction = root.addElement("action");
		getChildAction.addAttribute("doc", "获取指定父节点部门下的所有的下一级子节点部门信息");
		getChildAction.addText("Http://ServerIP:portNumber/WebAppName/depart?action=queryChild&pid={0}");
		
		return document.asXML();
	}
	
	/**
	 * 生成CREATE DELETE UPDATE后返回的XML文档
	 * @param msg
	 * @return
	 */
	public static String getMessageXml(String msg){
		Document document  = DocumentHelper.createDocument();
		Element root = document.addElement("Message");
		Element returnEle = root.addElement("Result");
		returnEle.addText(msg);
		return document.asXML();
	}
}
