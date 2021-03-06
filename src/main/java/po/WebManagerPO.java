package po;

import java.io.Serializable;

import objects.Manage;
import vo.WebManagerVO;

public class WebManagerPO implements Serializable{
	int webmanagerid;
	String name;
	String contact;
	String username;
	String password;
	boolean logged;
	
	public WebManagerPO(){
		webmanagerid=0;
		name=null;
		contact=null;
		username=null;
		password=null;
		logged=false;
	}
	
	public WebManagerPO(int i,String n,String c,String u,String p){
		webmanagerid = i;
		name=n;
		contact=c;
		username=u;
		password=p;
		logged=false;
//		id=i;
	}
	public int getwebmanagerid() {
		return webmanagerid;
	}
	public void setwebmanagerid(int webmanagerid) {
		this.webmanagerid = webmanagerid;
	}
	
	public void setname(String name) {
		this.name = name;
	}
	public String getname(){
		return name;
	}
	
	
	public void setcontact(String contact) {
		this.contact = contact;
	}
	public String getcontact(){
		return contact;
	}
	
	public void setusername(String username) {
		this.username = username;
	}
	public String getusername() {
		return username;
	}
	
	public String getpassword() {
		return password;
	}
	public void setpassword(String password) {
		this.password = password;
	}
	
	public boolean getlogged() {
		return logged;
	}
	public void setlogged(boolean logged) {
		this.logged = logged;
	}
	
	public Manage changetowebmanager(){
		Manage webmanager=new Manage();
		webmanager.setwebmanagerid(this.webmanagerid);
		webmanager.setname(this.name);
		webmanager.setcontact(this.contact);
		webmanager.setusername(this.username);
		webmanager.setpassword(this.password);
		webmanager.setlogged(this.logged);
		return webmanager;
	}
	
	public WebManagerVO changetowebmanagervo(){
		WebManagerVO vo=new WebManagerVO();
		vo.setwebmanagerid(this.webmanagerid);
		vo.setname(this.name);
		vo.setcontact(this.contact);
		vo.setusername(this.username);
		vo.setpassword(this.password);
		vo.setlogged(this.logged);
		return vo;
	}
}
