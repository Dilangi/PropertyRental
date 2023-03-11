package testCase;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import model.Customer;
import model.PointOfInterest;
import model.PropertyDetail;

class systemTester {

	@Test
	void testPoi() {
		PointOfInterest poi = new PointOfInterest("Home", "NN1 1RA", "123.56", "25.965");
		assertEquals("NN1 1RA", poi.getPostcode());
	}
	
	@Test
	void testCustomer() {
		Customer customer = new Customer(1, "Dilangi", "dil@gmail.com",  "08756562359", "female",true,2);
		assertEquals("female", customer.getGender());
		}
	
	@Test
	void testPropertyDetail() {
		PropertyDetail propertyDetail = new PropertyDetail(1, LocalDate.now(), 4, 2, 300.00, 256.23, "NN1 1QQ",
			"23.62", "123.56", "Furnished","flat", "y");
		assertEquals(300.00, propertyDetail.getRent());
	}

}
