package vo;

import java.io.Serializable;

public class WebMarketVO implements Serializable{
	int webmarketid;
	String name;
	String contact;
	String username;
	String password;
	boolean logged;
	
	public WebMarketVO(){
		webmarketid = 0;
		name=null;
		contact=null;
		username=null;
		password=null;
		logged=false;
	}
	
	public WebMarketVO(int id,String n,String c,String u,String p){
		webmarketid = id;
		name=n;
		contact=c;
		username=u;
		password=p;
		logged=false;
	}

	public int getwebmarketid() {
		return webmarketid;
	}

	public void setwebmarketid(int webmarketid) {
		this.webmarketid = webmarketid;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getcontact() {
		return contact;
	}

	public void setcontact(String contact) {
		this.contact = contact;
	}

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}
	
	public boolean getlogged() {
		return this.logged;
	}

	public void setlogged(boolean logged) {
		this.logged = logged;
	}
}
