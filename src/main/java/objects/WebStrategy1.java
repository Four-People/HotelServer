package objects;

public class WebStrategy1 extends WebStrategy implements Calculate {
	//开业酬宾
	
	@Override
	public double calculate(int clientid, int hotelid, double price, int roomnumber) {
		price*=0.9;
		return price;
	}


}
