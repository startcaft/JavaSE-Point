package com.startcaft.struts2.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;

public class EmployeeAction implements RequestAware,ModelDriven<Employee> {
	
	private Map<String,Object> requestMap;
	private Dao dao = new Dao();
	
	private Employee emp;
	
	/* 这样传递太麻烦，等于把Employee对象重写了一边
	//接受请求参数传递的值；
	private String firstName;
	private String lastName;
	private String email;
	private Integer employeeId;
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	*/
	
	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.requestMap = arg0;
	}
	
	public String update(){
		
		dao.update(this.emp);
		return "success";
	}
	
	public String edit(){
		
		/*
		//1，获取传入的employeeId
		//2，根据employeeId 获取 Employee对象
		Employee e = dao.get(emp.getEmployeeId());
		*/
		//3，把栈顶对象的属性装配好,此时栈顶对象是emp成员变量
		//目前为止 ，emp对象只有employeeId属性，其他属性为null
		/*
		 * Struts2 表单回显值：从ValueStack栈顶开始查找匹配的属性，若找到 就添加到HTML控件的value属性中
		 */
		/*
		emp.setEmail(e.getEmail());
		emp.setFirstName(e.getFirstName());
		emp.setLastName(e.getLastName());
		*/
		
		
		//没戏,除非把它再次压入到ValueStatck的栈顶，但是ValaueStack里面有两个Employee对象了，浪费!
		//emp = dao.get(emp.getEmployeeId());
		//ActionContext.getContext().getValueStack().push(dao.get(emp.getEmployeeId()));
		
		return "edit";
	}
	
	public String list(){
		requestMap.put("emps", dao.getEmployees());
		return "list";
	}
	
	public String save(){
		
		System.out.println("Action中的emp对象:" + emp.hashCode());
		System.out.println("ValueStack栈顶对象:" + ActionContext.getContext().getValueStack().peek().hashCode());
		
		//1,获取请求参数，通过ModelDriven接口里的方法获取
		
		//2,调用Dao的save方法
		dao.save(emp);
		
		//3,通过redirectAction 的方式响应结果个emp-list
		return "success";
	}
	
	public String delete(){
		dao.delete(this.employeeId);
		//返回结果的类型应该为：redirectAction
		//也可以是chain：实际上chain是没有必要的，因为不需要在下一个Action中保留当前Action的状态
		//还有，若使用chain，则到达目标页面后，地址栏显示的依然是 删除 的那个链接，刷新时会重复提交；
		return "success";
	}

	@Override
	public Employee getModel() {
		//判断是添加还是修改
		//若为新增，则new Employee();
		//若为Edit，则从后台获取
		//判断标准为是否有employeeId这个请求参数，若有，则是修改，若没有，则是新增
		//若通过employeeId 来判断，则需要在ModelDriven拦截器之前先执行一个params拦截器！
		//而这可以通过paramsPrepareParamsStack拦截器栈实现
		//需要在struts.xml文件中配置实用paramsPrepareParamsStack为默认的拦截器栈
		if(this.employeeId == null){
			this.emp = new Employee();
		}
		else{
			this.emp = dao.get(this.employeeId);
		}
		return this.emp;
	}
	
	private Integer employeeId;
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
}
