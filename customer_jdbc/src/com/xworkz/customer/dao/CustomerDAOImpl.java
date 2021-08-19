package com.xworkz.customer.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

import com.xworkz.customer.dto.CustomerDTO;
import com.xworkz.customer.dto.constants.Education;

import static com.xworkz.customer.dto.constants.JdbcConstant.*;

public class CustomerDAOImpl implements CustomerDAO {

	@Override
	public int save(CustomerDTO dto) {
		System.out.println(dto);
		Connection temp = null;
		int tempResult = 0;
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			temp = connection;
			connection.setAutoCommit(false);
			String query = "insert into customer_table(c_name,c_from,c_to,c_address,c_married,c_passportNo,c_education)values(?,?,?,?,?,?,?)";
			PreparedStatement prepare = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			prepare.setString(1, dto.getName());
			prepare.setString(2, dto.getFrom());
			prepare.setString(3, dto.getTo());
			prepare.setString(4, dto.getAddress());
			prepare.setBoolean(5, dto.isMarried());
			prepare.setString(6, dto.getPassportNo());
			prepare.setString(7, dto.getEducation().toString());
			prepare.execute();
			ResultSet result = prepare.getGeneratedKeys();

			if (result.next()) {
				tempResult = result.getInt(1);
				System.out.println(tempResult);
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				temp.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return tempResult;
	}

	@Override
	public void saveAll(Collection<CustomerDTO> collection) {
		Connection temp = null;
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			temp = connection;
			connection.setAutoCommit(false);
			String query = "insert into customer_table(c_name,c_from,c_to,c_address,c_married,c_passportNo,c_education)values(?,?,?,?,?,?,?)";
			PreparedStatement prepare = connection.prepareStatement(query);
			collection.forEach(r -> {
				try {
					prepare.setString(1, r.getName());
					prepare.setString(2, r.getFrom());
					prepare.setString(3, r.getTo());
					prepare.setString(4, r.getAddress());
					prepare.setBoolean(5, r.isMarried());
					prepare.setString(6, r.getPassportNo());
					prepare.setString(7, r.getEducation().toString());
					prepare.execute();
					System.out.println(r);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});
			connection.commit();
		} catch (SQLException e1) {
			e1.printStackTrace();
			try {
				temp.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public Optional<CustomerDTO> findOne(Predicate<CustomerDTO> predicate) {
		Optional<CustomerDTO> optional = Optional.empty();
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String query = "select * from customer_table";
			PreparedStatement prepare = connection.prepareStatement(query);
			ResultSet result = prepare.executeQuery();

			while (result.next()) {
				int id = result.getInt("c_id");
				String name = result.getString("c_name");
				String from = result.getString("c_from");
				String to = result.getString("c_to");
				String address = result.getString("c_address");
				Boolean married = result.getBoolean("c_married");
				String passportNo = result.getString("c_passportNo");
				String education = result.getString("c_education");

				CustomerDTO dto = new CustomerDTO(name, from, to, address, married, passportNo,
						Education.valueOf(education));
				dto.setId(id);
				if (predicate.test(dto)) {
					optional = Optional.of(dto);
					break;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return optional;
	}

	@Override
	public Collection<CustomerDTO> findAll() {
		Collection<CustomerDTO> collection = new ArrayList<CustomerDTO>();

		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String query = "select * from customer_table";
			PreparedStatement prepare = connection.prepareStatement(query);
			ResultSet result = prepare.executeQuery();

			while (result.next()) {
				int id = result.getInt("c_id");
				String name = result.getString("c_name");
				String from = result.getString("c_from");
				String to = result.getString("c_to");
				String address = result.getString("c_address");
				Boolean married = result.getBoolean("c_married");
				String passportNo = result.getString("c_passportNo");
				String education = result.getString("c_education");

				CustomerDTO dto = new CustomerDTO(name, from, to, address, married, passportNo,
						Education.valueOf(education));
				dto.setId(id);
				collection.add(dto);

			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return collection;
	}

	@Override
	public Collection<CustomerDTO> findAll(Predicate<CustomerDTO> predicate) {
		Collection<CustomerDTO> collection = new ArrayList<CustomerDTO>();

		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String query = "select * from customer_table";
			PreparedStatement prepare = connection.prepareStatement(query);
			ResultSet result = prepare.executeQuery();

			while (result.next()) {
				int id = result.getInt("c_id");
				String name = result.getString("c_name");
				String from = result.getString("c_from");
				String to = result.getString("c_to");
				String address = result.getString("c_address");
				Boolean married = result.getBoolean("c_married");
				String passportNo = result.getString("c_passportNo");
				String education = result.getString("c_education");

				CustomerDTO dto = new CustomerDTO(name, from, to, address, married, passportNo,
						Education.valueOf(education));
				dto.setId(id);
				if (predicate.test(dto)) {
					collection.add(dto);
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return collection;
	}

	@Override
	public Collection<CustomerDTO> findAllSortByNameDesc() {
		Collection<CustomerDTO> collection = new ArrayList<CustomerDTO>();
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {

			String query = "select * from customer_table order by c_name desc";
			PreparedStatement prepare = connection.prepareStatement(query);
			ResultSet result = prepare.executeQuery();
			while (result.next()) {
				int id = result.getInt("c_id");
				String name = result.getString("c_name");
				String from = result.getString("c_from");
				String to = result.getString("c_to");
				String address = result.getString("c_address");
				Boolean married = result.getBoolean("c_married");
				String passportNo = result.getString("c_passportNo");
				String education = result.getString("c_education");

				CustomerDTO dto = new CustomerDTO(name, from, to, address, married, passportNo,
						Education.valueOf(education));
				dto.setId(id);
				collection.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return collection;
	}

	@Override
	public int total() {
		int total = 0;
		try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
			String query = "SELECT count(c_id) FROM customer_table";
			PreparedStatement prepare = connection.prepareStatement(query);
			prepare.execute();
			ResultSet result = prepare.executeQuery();
			if (result.next()) {
				total = result.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return total;
	}
}