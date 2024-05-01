import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestOnDataBase {

	Connection con = null;
	java.sql.Statement stm = null;
	ResultSet rs = null;

	@BeforeTest
	public void BeforeTest() throws SQLException {

		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "root"); // `classicmodels`
																										// + userName +
																										// Password
	}

	@Test()
	public void InsertVal() throws SQLException {

		stm = con.createStatement();
		String MyQuery = "insert into customers (customerNumber , customerName , contactLastName , contactFirstName , phone , addressLine1 , city , country ) values (1997 , 'Alazzam' , 'Mostafa' , 'Omar' , '0776184151' , '60,irbid' , 'Madrid' , 'Espan' )";

		int RowEffected = stm.executeUpdate(MyQuery);

		if (RowEffected > 0) {
			System.out.println("Query is done");
		} else {
			System.out.println("Query is failed");
		}
	}

	@Test(enabled = false)
	public void updateQuery() throws SQLException {
		stm = con.createStatement();
		String Query = "update customers set customerName ='Suhaib' where customerNumber =1997";
		int RowEffect = stm.executeUpdate(Query);
		Assert.assertEquals(RowEffect > 0, true, "Sorry Update is Failed");
	}

	@Test(enabled = false)

	public void readData() throws SQLException {
		stm = con.createStatement();
		String MyQuery = "Select * from customers where customerNumber = 1997";

		rs = stm.executeQuery(MyQuery);

		while (rs.next()) {
			String Name = rs.getString("customerName");
			String City = rs.getString("city");
			String LastName = rs.getString("contactLastName");

			Assert.assertEquals(Name, "Suhaib");
		}

	}

	@Test(enabled = false)
	public void DeleteQuery() throws SQLException {
		stm = con.createStatement();
		String Query = "delete from customers where customerNumber = 1997";
		int RowEffect = stm.executeUpdate(Query);
		Assert.assertEquals(RowEffect > 0, true, "Sorry Delete is Failed");
	}

}
