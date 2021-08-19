package com.xworkz.customer.service;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

import com.xworkz.customer.dao.CustomerDAO;
import com.xworkz.customer.dao.CustomerDAOImpl;
import com.xworkz.customer.dto.CustomerDTO;
import com.xworkz.customer.dto.constants.Education;

public class CustomerServiceDAOImpl implements CustomerServiceDAO {
	CustomerDAO dao = new CustomerDAOImpl();

	@Override
	public int validateAndSave(CustomerDTO dto) {

		boolean nameValid = false;
		boolean fromValid = false;
		boolean toValid = false;
		boolean addressValid = false;
		boolean marriedValid = false;
		boolean passportValid = false;
		boolean educationValid = false;
		if (dto != null) {
			System.out.println("dto is not null,will start validating");
			String name = dto.getName();
			if (name != null && !name.isEmpty() && name.length() >= 3 && name.length() <= 20) {
				System.out.println("name is valid");
				nameValid = true;
			} else {
				System.out.println("name is invalid");
				nameValid = false;
			}
			String from = dto.getName();
			if (from != null && !from.isEmpty() && from.length() >= 3 && from.length() <= 30) {
				System.out.println("from is valid");
				fromValid = true;
			} else {
				System.out.println("from is invalid");
				fromValid = false;
			}
			String to = dto.getTo();
			if (to != null && !to.isEmpty() && to.length() >= 4 && to.length() <= 30) {
				System.out.println("to is valid");
				toValid = true;
			} else {
				System.out.println("to is invlid");
				toValid = false;
			}
			String address = dto.getAddress();
			if (address != null && !address.isEmpty() && address.length() >= 4 && address.length() <= 30) {
				System.out.println("to is valid");
				addressValid = true;
			} else {
				System.out.println("to is invlid");
				addressValid = false;
			}
			Boolean married = dto.isMarried();
			if (married != null) {
				System.out.println("to is valid");
				marriedValid = true;
			} else {
				System.out.println("to is invlid");
				marriedValid = false;
			}
			String passport = dto.getPassportNo();
			if (passport != null && !passport.isEmpty() && passport.length() >= 4 && passport.length() <= 30) {
				System.out.println("to is valid");
				passportValid = true;
			} else {
				System.out.println("to is invlid");
				passportValid = false;
			}
			Education education = dto.getEducation();
			if (education != null) {
				System.out.println("to is valid");
				educationValid = true;
			} else {
				System.out.println("to is invlid");
				educationValid = false;
			}
			if (nameValid && fromValid && toValid && addressValid && marriedValid && passportValid && educationValid) {
				dao.save(dto);
				
				System.out.println("data is valid returning success");
				return 1;
			}
			System.out.println("data is invalid returning failed");

			return 0;
		}

		return 0;
	}

	@Override
	public void validateAndSaveAll(Collection<CustomerDTO> collection) {

		if (!collection.isEmpty()) {
			collection.forEach(dto -> {
				this.validateAndSave(dto);
			});
		}
	}
	@Override
	public Optional<CustomerDTO> findOne(Predicate<CustomerDTO> predicate) {
		return dao.findOne(predicate);

	}

	@Override
	public Collection<CustomerDTO> findAll() {
		return dao.findAll();

	}

	@Override
	public Collection<CustomerDTO> findAll(Predicate<CustomerDTO> predicate) {
		return dao.findAll(predicate);

	}

	@Override
	public Collection<CustomerDTO> findAllSortByNameDesc() {
		return dao.findAllSortByNameDesc();

	}

	@Override
	public int total() {
		return dao.total();

	}

}
