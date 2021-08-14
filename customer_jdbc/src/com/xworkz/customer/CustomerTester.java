package com.xworkz.customer;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import com.xworkz.customer.service.*;

import com.xworkz.customer.dto.CustomerDTO;
import com.xworkz.customer.dto.constants.Education;

public class CustomerTester {

	public static void main(String[] args) {

		CustomerDTO dto = new CustomerDTO("Ranjitha", "Bangalore", "Kerala", "#85Parimalanagar", false, "J22T4545",
				Education.BE);
		CustomerDTO dto1 = new CustomerDTO("Devika", "Bangalore", "MP", "#23Vivekanandanagar", false, "yt566511",
				Education.BTECH);
		CustomerServiceDAO dao = new CustomerServiceDAOImpl();
		dao.validateAndSave(dto);
		dao.validateAndSave(dto1);

		Collection<CustomerDTO> collect = Arrays.asList(dto, dto1);
		dao.validateAndSaveAll(collect);
		System.out.println("************************************");

		Optional<CustomerDTO> one = dao.findOne(d -> d.getName().equals("Devika"));
		if (one.isPresent()) {
			CustomerDTO name = one.get();
			System.out.println(name);
		}

		Collection<CustomerDTO> all = dao.findAll();
		all.forEach(t -> System.out.println(t));
		System.out.println("******************************");

		Collection<CustomerDTO> findAll = dao.findAll(r -> r.getId() == 9);
		findAll.forEach(r -> System.out.println(r));
		System.out.println("****************************");

		Collection<CustomerDTO> byName = dao.findAllSortByNameDesc();
		byName.forEach(f -> System.out.println(f));
		int total = dao.total();
		System.out.println(total);
	}

}
