package vo;

import java.io.Serializable;
import java.util.ArrayList;

import po.RoomOrderPO;

public class OrderVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	int clientid;
	int hotelid;
	String state;
	boolean execute;
	String start_time;
	String end_time;
	String latest_execute_time;
	ArrayList<RoomOrderVO>room_order;
	String strategy;
	int price;
	int expect_number_of_people;
	boolean havechild;
	
	public OrderVO(){
		id=0;
		clientid=0;
		hotelid=0;
		state=null;
		execute=false;
		start_time=null;
		end_time=null;
		latest_execute_time=null;
		room_order=null;
		strategy=null;
		price=0;
		expect_number_of_people=0;
		havechild=false;
	}
	public OrderVO(int i,int cid,int hid,String s,boolean e,String st,String et,String lt,ArrayList<RoomOrderVO>list,String str,int p,int en,boolean child){
		id=i;
		clientid=cid;
		hotelid=hid;
		state=s;
		execute=e;
		start_time=st;
		end_time=et;
		latest_execute_time=lt;
		room_order=list;
		strategy=str;
		price=p;
		expect_number_of_people=en;
		havechild=child;
	}
	
	public void setid(int id){
		this.id = id;
	}
	public int getid(){
		return id;
	}
	
	public void setclientid(int id){
		clientid = id;
	}
	public int getclientid(){
		return clientid;
	}
	
	public void sethotelid(int id){
		hotelid = id;
	}
	public int gethotelid(){
		return hotelid;
	}
	
	public void setstate(String state){
		this.state = state;
	}
	public String getstate(){
		return state;
	}
	
	public void setexecute(boolean isExecute){
		execute = isExecute;
	}
	public boolean getexecute(){
		return execute;
	}
	
	public void setstart_time(String start_time){
		this.start_time = start_time;
	}
	public String getstart_time(){
		return start_time;
	}
	
	public void setend_time(String end_time){
		this.end_time = end_time;
	}
	public String getend_time(){
		return end_time;
	}
	
	public void setlatest_execute_time(String latest_execute_time){
		this.latest_execute_time = latest_execute_time; 
	}
	public String getlatest_execute_time(){
		return latest_execute_time; 
	}
	
	public void setroom_order(ArrayList<RoomOrderVO> list){
		room_order = list;
	}
	public ArrayList<RoomOrderVO> getroom_order(){
		return room_order;
	}
	
	public void setstrategy(String strategy){
		this.strategy = strategy;
	}
	public String getstrategy(){
		return strategy;
	}
	
	public void setprice(int price){
		this.price = price;
	}
	public int getprice(){
		return price;
	}
	
	public void setexpect_number_of_people(int numOfPeople){
		expect_number_of_people = numOfPeople;
	}
	public int getexpect_number_of_people(){
		return expect_number_of_people;
	}
	
	public void sethave_child(boolean child){
		havechild=child;
	}
	public boolean gethave_child(){
		return havechild;
	}
}
