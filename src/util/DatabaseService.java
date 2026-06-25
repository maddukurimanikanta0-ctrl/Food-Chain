package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Order;
import model.User;

public class DatabaseService {

	public void saveUser(
			User user) {

			try {

			    Connection con =
			            DBConnection.getConnection();

			    String sql =
			            "INSERT INTO users "
			            + "(name,phone,area,city,password) "
			            + "VALUES(?,?,?,?,?)";

			    PreparedStatement ps =
			            con.prepareStatement(sql);

			    ps.setString(
			            1,
			            user.getName());

			    ps.setString(
			            2,
			            user.getPhone());

			    ps.setString(
			            3,
			            user.getArea());

			    ps.setString(
			            4,
			            user.getCity());

			    ps.setString(
			            5,
			            user.getPassword());

			    ps.executeUpdate();

			    con.close();

			} catch(Exception e) {

				System.out.println(
				        "Database Operation Failed.");			}

			}

    public void saveFeedback(
            String customerName,
            int rating,
            String review) {

        try {

            Connection con =
                    DBConnection.getConnection();

            if(con == null) {

                System.out.println(
                        "Database Connection Failed");

                return;
            }

            String sql =
                    "INSERT INTO feedback "
                    + "(customer_name,rating,review) "
                    + "VALUES(?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    customerName);

            ps.setInt(
                    2,
                    rating);

            ps.setString(
                    3,
                    review);

            int rows =
                    ps.executeUpdate();

            System.out.println(
                    "Feedback Saved To Database");

            System.out.println(
                    "Rows Inserted : "
                    + rows);

            con.close();

        } catch(Exception e) {

        	System.out.println(
        	        "Database Operation Failed.");        }
    }
    public void saveOrder(
    		Order order) {
    		try {

    		    Connection con =
    		            DBConnection.getConnection();

    		    String sql =
    		    	    "INSERT INTO orders "
    		    	    + "(order_id,customer_name,"
    		    	    + "restaurant_name,amount,"
    		    	    + "payment_method,status,"
    		    	    + "delivery_agent,eta,"
    		    	    + "delivery_slot,"
    		    	    + "route_status,"
    		    	    + "tracking_status) "
    		    	    + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

    		    PreparedStatement ps =
    		            con.prepareStatement(sql);

    		    ps.setString(
    		            1,
    		            order.getOrderId());

    		    ps.setString(
    		            2,
    		            order.getCustomerName());

    		    ps.setString(
    		            3,
    		            order.getRestaurantName());

    		    ps.setDouble(
    		            4,
    		            order.getAmount());

    		    ps.setString(
    		            5,
    		            order.getPaymentMethod());

    		    ps.setString(
    		            6,
    		            order.getOrderStatus());

    		    ps.setString(
    		            7,
    		            order.getDeliveryAgent());

    		    ps.setString(
    		            8,
    		            order.getEta());

    		    ps.setString(
    		            9,
    		            order.getDeliverySlot());
    		    ps.setString(
    		            10,
    		            order.getRouteStatus());

    		    ps.setString(
    		            11,
    		            order.getTrackingStatus());

    		    ps.executeUpdate();

    		    con.close();

    		} catch(Exception e) {

    			System.out.println(
    			        "Database Operation Failed.");    		}

    		}

    public User loginUser(
            String phone,
            String password) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM users "
                    + "WHERE phone=? "
                    + "AND password=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    phone);

            ps.setString(
                    2,
                    password);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                return new User(
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("area"),
                        rs.getString("city"),
                        rs.getString("password"));
            }

        } catch(Exception e) {

        	System.out.println(
        	        "Database Operation Failed.");        }

        return null;
    }
    public Order getOrderById(
            String orderId) {

        try {

            Connection con =
                    DBConnection.getConnection();

            String sql =
                    "SELECT * FROM orders "
                    + "WHERE order_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(
                    1,
                    orderId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next()) {

                Order order =
                        new Order(
                                rs.getString("order_id"),
                                rs.getString("customer_name"),
                                rs.getString("restaurant_name"),
                                rs.getDouble("amount"),
                                rs.getString("payment_method"));

                order.setOrderStatus(
                        rs.getString("status"));

                order.setDeliveryAgent(
                        rs.getString("delivery_agent"));

                order.setEta(
                        rs.getString("eta"));

                order.setDeliverySlot(
                        rs.getString("delivery_slot"));
                order.setRouteStatus(
                        rs.getString("route_status"));

                order.setTrackingStatus(
                        rs.getString("tracking_status"));

                return order;
            }

        } catch(Exception e) {

        	System.out.println(
        	        "Database Operation Failed.");        }

        return null;
    }
    
}