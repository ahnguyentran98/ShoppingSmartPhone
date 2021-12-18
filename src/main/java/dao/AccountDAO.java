package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Account;
import model.PasswordEncrypt;

public class AccountDAO {

	// check user existed or not
	public boolean check(String userMail, String password) throws Exception {
		boolean flag = false;
		try {
			// connect to database
			DBContext dbcontext = new DBContext();
			Connection conn = dbcontext.getConnection();
			Statement stmt = conn.createStatement();
			// query
			ResultSet rs = stmt.executeQuery("select * from dbo.Account");
			while (rs.next()) {
				// get email
				String rsUser = rs.getString(1);
				// get password
				String rsPass = rs.getString(2);
				// check
				if (rsUser.equalsIgnoreCase(userMail) && rsPass.equals(password)) {
					flag = true;
				}
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// check user role 1: admin, 0: customer
	public boolean checkRoleAdmin(String userMail) throws Exception {
		boolean flag = false;
		try {
			// connect to database
			DBContext dbcontext = new DBContext();
			Connection conn = dbcontext.getConnection();
			Statement stmt = conn.createStatement();
			// query
			ResultSet rs = stmt.executeQuery("select * from dbo.Account");
			while (rs.next()) {
				// get email
				String rsUser = rs.getString(1);
				// check
				if (rsUser.equalsIgnoreCase(userMail) && rs.getInt(3) == 1) {
					flag = true;
				}
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	// signup for customer
	public void signInCus(Account account) {
		try {
			// connect to database
			DBContext dbcontext = new DBContext();
			Connection conn = dbcontext.getConnection();
			// query
			PreparedStatement stmt1 = conn.prepareStatement(
					"INSERT INTO dbo.Account (user_mail,password,account_role,user_name,user_address,user_phone) values (?,?, ?, ?, ?, ?)");

			// encrypt password
			String pass = account.getPwd();
			byte[] pass1 = PasswordEncrypt.getSHA(pass);
			String password = PasswordEncrypt.toHexString(pass1);

			// set user info
			stmt1.setString(1, account.getUsr());
			stmt1.setString(2, password);
			stmt1.setInt(3, account.getRole());
			stmt1.setString(4, account.getName());
			stmt1.setString(5, account.getAddress());
			stmt1.setString(6, account.getPhone());
			stmt1.execute();
			stmt1.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// get user account for admin
	public List<Account> getAccountAdmin() throws Exception {
		List<Account> ac = new ArrayList<>();
		try {
			// connect to database
			DBContext dbcontext = new DBContext();
			Connection conn = dbcontext.getConnection();
			Statement stmt = conn.createStatement();
			// query
			ResultSet rs = stmt.executeQuery("select * from dbo.Account");
			while (rs.next()) {
				// get account
				ac.add(new Account(rs.getString(1), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6)));
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ac;
	}
}
