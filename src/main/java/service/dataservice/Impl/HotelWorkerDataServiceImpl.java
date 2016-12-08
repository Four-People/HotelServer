package service.dataservice.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import objects.ResultMessage;
import po.ClientPO;
import po.HotelWorkerPO;
import po.WebMarketPO;
import service.dataservice.HotelWorkerDataService;

public class HotelWorkerDataServiceImpl implements HotelWorkerDataService {
	@Override
	public HotelWorkerPO find(int hotelid){
		Connection conn = Connect.getConn();
	    String sql = "select id,decode(name,'key'),decode(contact,'key'),decode(username,'key'),decode(password,'key') from hotelworker where id = " +hotelid;	//需要执行的sql语句
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	HotelWorkerPO po = new HotelWorkerPO(rs.getInt("id"),BlobtoString(rs.getBlob("decode(name,'key')")),BlobtoString(rs.getBlob("decode(contact,'key')")),BlobtoString(rs.getBlob("decode(username,'key')")),BlobtoString(rs.getBlob("decode(password,'key')")));
			return po;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	
	@Override
	public synchronized ResultMessage insert(HotelWorkerPO po){
		Connection conn = Connect.getConn();
	    String sql = "insert into hotelworker(hotelid,Name,Contact,Username,Password) values(NULL,encode(?,'key'),encode(?,'key'),encode(?,'key'),encode(?,'key')";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, po.getname());
	        pstmt.setString(2, po.getcontact());
	        pstmt.setString(3, po.getusername());
	        pstmt.setString(4, po.getpassword());
	        pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	        set_id(po);	//setid
	        return ResultMessage.Success;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return ResultMessage.Fail;
	    }
	}
	

	public void set_id(HotelWorkerPO po){
		Connection conn = Connect.getConn();
		 String sql = "select max(id) from hotelworker" ;	//需要执行的sql语句
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	po.sethotelid(rs.getInt(1));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public synchronized ResultMessage update(HotelWorkerPO po){
		Connection conn = Connect.getConn();
	    String sql = "update hotelworker set name=encode(?,'key'),contact=(?,'key') where id=?";
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        pstmt.setString(1, po.getname());
	        pstmt.setString(2, po.getcontact());
	        pstmt.setInt(3, po.gethotelid());
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
	public synchronized ResultMessage delete(int hotelid){
		Connection conn = Connect.getConn();
	    String sql = "delete from hotelworker where id='" + hotelid + "'";
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
	
	public ResultMessage check(String username,String password){
		Connection conn = Connect.getConn();
		String sql = "select id from hotelworker where username =encode(?,'key') and password = encode(?,'key')" ;
		PreparedStatement pstmt;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				if(rs.getInt("id")!=-1)
					return ResultMessage.Success;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return ResultMessage.Fail;
	}
	
	public HotelWorkerPO gethotelworkerpo(String username,String password){
		Connection conn = Connect.getConn();
	    String sql = "select id,decode(name,'key'),decode(contact,'key') from hotelworker where username = encode(?,'key') and password = encode(?,'key')";	//需要执行的sql语句
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        pstmt.setString(1,username);
	        pstmt.setString(2, password);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	HotelWorkerPO po = new HotelWorkerPO(rs.getInt("id"),BlobtoString(rs.getBlob("decode(name,'key')")),BlobtoString(rs.getBlob("decode(contact,'key')")),username,password);
			return po;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public int findhotelid_of_hotelworkerbyUsername(String username){
		Connection conn = Connect.getConn();
		int result =0;
		String sql = "select id from hotelworker where name = encode(?,'key')";
		PreparedStatement pstmt;
		try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        pstmt.setString(1, username);
	        ResultSet rs = pstmt.executeQuery();
	        while(rs.next()){
	        	result = rs.getInt("id");
	        }
	        return result;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return -1;
	    }
	}
	
	/**
	 * 将存储在数据库中的blob类型转化成string
	 * @param blob
	 * @return
	 */
	public String BlobtoString(Blob blob){
		try{
			InputStream is = blob.getBinaryStream();
			byte[] b = new byte[is.available()];
			is.read(b, 0, b.length);
			String str = new String(b);
			return str;
		}catch(IOException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
}
