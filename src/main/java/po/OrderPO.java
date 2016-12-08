package po;

import java.io.Serializable;
import java.util.ArrayList;

import objects.Order;
import objects.RoomOrder;
import vo.OrderVO;
import vo.RoomOrderVO;

public class OrderPO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;
	int clientid;
	int hotelid;
	String state;
	String cancel_time;
	boolean execute;
	String start_time;
	String end_time;
	String latest_execute_time;
	ArrayList<RoomOrderPO>room_order;
	int price;
	int expect_number_of_people;
	boolean havechild;
	
	public OrderPO(){
		id=0;
		clientid=0;
		hotelid=0;
		state=null;
		cancel_time=null;
		execute=false;
		start_time=null;
		end_time=null;
		latest_execute_time=null;
		room_order=null;
		price=0;
		expect_number_of_people=0;
		havechild=false;
	}
	public OrderPO(int i,int cid,int hid,String s,String cancel,boolean e,String st,String et,String lt,ArrayList<RoomOrderPO>list,int p,int en,boolean child){
		id=i;
		clientid=cid;
		hotelid=hid;
		state=s;
		cancel_time=cancel;
		execute=e;
		start_time=st;
		end_time=et;
		latest_execute_time=lt;
		room_order=list;
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
	
	public void setcancel_time(String cancel){
		cancel_time=cancel;
	}
	public String getcancel_time(){
		return cancel_time;
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
	
	public void setroom_order(ArrayList<RoomOrderPO> list){
		room_order = list;
	}
	public ArrayList<RoomOrderPO> getroom_order(){
		return room_order;
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
	
	public Order changetoorder(){
		Order order=new Order();
		order.setid(this.id);
		order.setclientid(this.clientid);
		order.sethotelid(this.hotelid);
		order.setstate(this.state);
		order.setcancel_time(this.cancel_time);
		order.setexecute(this.execute);
		order.setstart_time(this.start_time);
		order.setend_time(this.end_time);
		order.setlatest_execute_time(this.latest_execute_time);
		ArrayList<RoomOrder> roomorderlist = new ArrayList<RoomOrder>();
		for(int i=0;i<this.room_order.size();i++){
			roomorderlist.add(room_order.get(i).changetoroomorder());
		}
		order.setroom_order(roomorderlist);
		order.setprice(this.price);
		order.setexpect_number_of_people(this.expect_number_of_people);
		order.sethave_child(this.havechild);
		return order;
	}
	
	public OrderVO changetoordervo(){
		OrderVO vo=new OrderVO();
		vo.setid(this.id);
		vo.setclientid(this.clientid);
		vo.sethotelid(this.hotelid);
		vo.setstate(this.state);
		vo.setcancel_time(this.cancel_time);
		vo.setexecute(this.execute);
		vo.setstart_time(this.start_time);
		vo.setend_time(this.end_time);
		vo.setlatest_execute_time(this.latest_execute_time);
		ArrayList<RoomOrderVO> roomordervolist = new ArrayList<RoomOrderVO>();
		for(int i=0;i<this.room_order.size();i++){
			roomordervolist.add(room_order.get(i).changetoroomordervo());
		}
		vo.setroom_order(roomordervolist);
		vo.setprice(this.price);
		vo.setexpect_number_of_people(this.expect_number_of_people);
		vo.sethave_child(this.havechild);
		return vo;
	}
}
