package service.dataservice.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objects.ResultMessage;
import po.HotelWorkerPO;
import service.dataservice.HotelWorkerDataService;

public class HotelWorkerDataServiceImpl implements HotelWorkerDataService {
	
//	public static void main(String[] args){
//		HotelWorkerDataServiceImpl a = new HotelWorkerDataServiceImpl();
//		HotelWorkerPO po=new HotelWorkerPO(1,"John","1111","Matin","ddm");
//		System.out.println(a.update(po));
//	}
	
	@Override
	public ArrayList<HotelWorkerPO> getallhotelworkerPO() {
		Connection conn = Connect.getConn();
		String sql = "select hotelid,decode(name,'key'),decode(contact,'key'),decode(username,'key'),decode(password,'key'),logged from hotelworker "; // 需要执行的sql语句
		PreparedStatement pstmt;
		ArrayList<HotelWorkerPO> hotelworkerlist = new ArrayList<HotelWorkerPO>();
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				HotelWorkerPO po = new HotelWorkerPO();
				po.sethotelid(rs.getInt("hotelid"));
				po.setname(BlobtoString(rs.getBlob("decode(name,'key')")));
				po.setcontact(BlobtoString(rs.getBlob("decode(contact,'key')")));
				po.setusername(BlobtoString(rs.getBlob("decode(username,'key')")));
				po.setpassword(BlobtoString(rs.getBlob("decode(password,'key')")));
				po.setlogged(rs.getBoolean("logged"));
				hotelworkerlist.add(po);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hotelworkerlist;
	}


	@Override
	public HotelWorkerPO find(int hotelid) {
		Connection conn = Connect.getConn();
		String sql = "select hotelid,decode(name,'key'),decode(contact,'key'),decode(username,'key'),decode(password,'key'),logged from hotelworker where hotelid = "
				+ hotelid; // 需要执行的sql语句
		PreparedStatement pstmt;
		HotelWorkerPO po = new HotelWorkerPO();
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				po.sethotelid(rs.getInt("hotelid"));
				po.setname(BlobtoString(rs.getBlob("decode(name,'key')")));
				po.setcontact(BlobtoString(rs.getBlob("decode(contact,'key')")));
				po.setusername(BlobtoString(rs.getBlob("decode(username,'key')")));
				po.setpassword(BlobtoString(rs.getBlob("decode(password,'key')")));
				po.setlogged(rs.getBoolean("logged"));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return po;

	}


	@Override
	public synchronized ResultMessage insert(HotelWorkerPO po) {
		Connection conn = Connect.getConn();

		String sql = "insert into hotelworker(hotelid,Name,Contact,Username,Password,logged) values(?,encode(?,'key'),encode(?,'key'),encode(?,'key'),encode(?,'key'),?)";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setInt(1, po.gethotelid());
			pstmt.setString(2, po.getname());
			pstmt.setString(3, po.getcontact());
			pstmt.setString(4, po.getusername());
			pstmt.setString(5, po.getpassword());
			pstmt.setBoolean(6, po.getlogged());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			set_id(po); // setid
			return ResultMessage.Success;
		} catch (SQLException e) {
			e.printStackTrace();
			return ResultMessage.Fail;
		}
	}

	    

	public void set_id(HotelWorkerPO po) {
		Connection conn = Connect.getConn();
		String sql = "select max(hotelid) from hotelworker"; // 需要执行的sql语句
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				po.sethotelid(rs.getInt(1));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized ResultMessage update(HotelWorkerPO po) {
		Connection conn = Connect.getConn();
		String sql = "update hotelworker set name=encode(?,'key'),contact=encode(?,'key'),username=encode(?,'key'),password=encode(?,'key'),logged=? where hotelid=?";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, po.getname());
			pstmt.setString(2, po.getcontact());
			pstmt.setString(3, po.getusername());
			pstmt.setString(4, po.getpassword());
			pstmt.setBoolean(5, po.getlogged());
			pstmt.setInt(6, po.gethotelid());
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			return ResultMessage.Success;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ResultMessage.Fail;
	}

	@Override
	public synchronized ResultMessage delete(int hotelid) {
		Connection conn = Connect.getConn();
		String sql = "delete from hotelworker where hotelid='" + hotelid + "'";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
			return ResultMessage.Success;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ResultMessage.Fail;
	}

	public ResultMessage check(String username, String password) {
		Connection conn = Connect.getConn();
		String sql = "select hotelid from hotelworker where username =encode(?,'key') and password = encode(?,'key')";
		PreparedStatement pstmt;
		ResultMessage flag = null;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt("hotelid") != -1)
					flag = ResultMessage.Success;
			}
			rs.close();
			pstmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public HotelWorkerPO gethotelworkerpo(String username, String password) {
		Connection conn = Connect.getConn();

	    String sql = "select hotelid,decode(name,'key'),decode(contact,'key'),logged from hotelworker where username = encode(?,'key') and password = encode(?,'key')";	//需要执行的sql语句
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        pstmt.setString(1,username);
	        pstmt.setString(2, password);
	        ResultSet rs = pstmt.executeQuery();
	        HotelWorkerPO po = new HotelWorkerPO();
	        while(rs.next()){
	        	po = new HotelWorkerPO(rs.getInt("hotelid"),BlobtoString(rs.getBlob("decode(name,'key')")),BlobtoString(rs.getBlob("decode(contact,'key')")),username,password);
	        	po.setlogged(rs.getBoolean("logged"));
	        }
	        rs.close();
			pstmt.close();
			conn.close();
			return po;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	public int findhotelid_of_hotelworkerbyUsername(String username) {
		Connection conn = Connect.getConn();
		int result = 0;
		String sql = "select hotelid from hotelworker where username = encode(?,'key')";
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				result = rs.getInt("hotelid");
			}
			rs.close();
			pstmt.close();
			conn.close();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * 将存储在数据库中的blob类型转化成string
	 * 
	 * @param blob
	 * @return
	 */
	public String BlobtoString(Blob blob) {
		try {
			InputStream is = blob.getBinaryStream();
			byte[] b = new byte[is.available()];
			is.read(b, 0, b.length);
			String str = new String(b);
			return str;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
//	public static void main(String[]args){
//		HotelWorkerDataServiceImpl worker=new HotelWorkerDataServiceImpl();
//		System.out.println(worker.find(9).getname());
//	}
	
}
