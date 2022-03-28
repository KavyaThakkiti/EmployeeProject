package empDAO;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import empmanagement.Employee;


public class EmployeeDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?employeeSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "Sek@ran084";
	
	
	private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO employee" + " (name,email,country)VALUES " + " (?,?,?);";
	private static final String SEARCH_EMPLOYEE_SQL = "search * from employee";
	private static final String DELETE_EMPLOYEE_SQL = "delete * from employee where id = ?";
	private static final String UPDATE_EMPLOYEE_SQL = "update employee set name = ?,email = ?, country =? where id = ?;";

    protected Connection getConnection() {
    	Connection connection = null;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	return connection;
    	
    }
    public void insertEmployee(Employee employee) throws SQLException {
    	try(Connection connection = getConnection();
    			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {
           preparedStatement.setString(1, employee.getName());
    	   preparedStatement.setString(2, employee.getEmail());
    	   preparedStatement.setString(1, employee.getCountry());
    	   preparedStatement.executeUpdate();
       } catch (Exception e) {
    	   e.printStackTrace();
       }	   
    }
    
    public boolean updateEmployee(Employee employee, boolean rowUpdated) throws SQLException {
    	try(Connection connection = getConnection();
    			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE_SQL);) {
           preparedStatement.setString(1, employee.getName());
           preparedStatement.setString(2, employee.getEmail());
           preparedStatement.setString(1, employee.getCountry());
           preparedStatement.setInt(4, employee.getId());
           
           rowUpdated = preparedStatement.executeUpdate() > 0;
       }  
       return rowUpdated;	
    }
    
    public Employee searchEmployee(int id) {
    	Employee employee = null;
    try (Connection connection = getConnection();
    		PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_EMPLOYEE_SQL);) {
    	preparedStatement.setInt(1,  id);
    	System.out.println(preparedStatement);
    	ResultSet rs = preparedStatement.executeQuery();
    	
    	while (rs.next()) {
    		String name = rs.getString("name");
    		String email = rs.getString("email");
    		String country = rs.getString("country");
    		employee = new Employee(id, name, country);
    	}
    } catch (SQLException e) {
    	e.printStackTrace();
    }
    return employee;
    }
    
    public boolean deleteUser(int id) throws SQLException {
    	boolean rowDeleted;
    	try (Connection connection = getConnection();
    			PreparedStatement statement = connection.prepareStatement(DELETE_EMPLOYEE_SQL);) {
    		 statement.setInt(1,  id);
    		 rowDeleted = statement.executeUpdate() > 0;
    	}
    	return rowDeleted;
    }
	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		
	}
	public Object selectEmployee(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	public java.util.List<Employee> selectAllEmployee() {
		// TODO Auto-generated method stub
		return null;
	}
 
}    
    			
    	    	
    	    	
    	    	
    	
    

