package in.devl.runner;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.devl.entity.CitizenPlan;
import in.devl.repo.CitizenPlanRepository;

@Component
public class DataLoader implements ApplicationRunner {
	@Autowired
	private CitizenPlanRepository repo;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		// Cash plan Data

		repo.deleteAll();

//		CitizenPlan c1 = new CitizenPlan();
//		c1.setCitizenName("John");
//		c1.setGender("Male");
//		c1.setPlanName("Cash");
//		c1.setPlanStatus("Approved");
//		c1.setPlanEndDate(LocalDate.now());
//		c1.setPlanEndDate(LocalDate.now().plusMonths(6));
//		c1.setBenefitAmt(5000.00);
//
//		CitizenPlan c2 = new CitizenPlan();
//		c2.setCitizenName("Smith");
//		c2.setGender("Male");
//		c2.setPlanName("Cash");
//		c2.setPlanStatus("Denied");
//		c2.setDenialReason("Rent Income");
//
//		CitizenPlan c3 = new CitizenPlan();
//		c3.setCitizenName("Mia");
//		c3.setGender("Fe-Male");
//		c3.setPlanName("Cash");
//		c3.setPlanStatus("Terminated");
//		c3.setPlanEndDate(LocalDate.now().minusMonths(4));
//		c3.setPlanEndDate(LocalDate.now().plusMonths(6));
//		c3.setBenefitAmt(5000.00);
//		c3.setTerminatedDate(LocalDate.now());
//		c3.setTerminationReason("Employed");
//
//		// Food plan Data
//		CitizenPlan c4 = new CitizenPlan();
//		c4.setCitizenName("David");
//		c4.setGender("Male");
//		c4.setPlanName("Food");
//		c4.setPlanStatus("Approved");
//		c4.setPlanEndDate(LocalDate.now());
//		c4.setPlanEndDate(LocalDate.now().plusMonths(6));
//		c4.setBenefitAmt(4000.00);
//
//		CitizenPlan c5 = new CitizenPlan();
//		c5.setCitizenName("Robert");
//		c5.setGender("Male");
//		c5.setPlanName("Food");
//		c5.setPlanStatus("Denied");
//		c5.setDenialReason("Rent Income");
//
//		CitizenPlan c6 = new CitizenPlan();
//		c6.setCitizenName("Ammi");
//		c6.setGender("Fe-Male");
//		c6.setPlanName("Food");
//		c6.setPlanStatus("Terminated");
//		c6.setPlanEndDate(LocalDate.now().minusMonths(4));
//		c6.setPlanEndDate(LocalDate.now().plusMonths(6));
//		c6.setBenefitAmt(4000.00);
//		c6.setTerminatedDate(LocalDate.now());
//		c6.setTerminationReason("Employed");
//
//		// Medical plan Data
//		CitizenPlan c7 = new CitizenPlan();
//		c7.setCitizenName("Charles");
//		c7.setGender("Male");
//		c7.setPlanName("Medical");
//		c7.setPlanStatus("Approved");
//		c7.setPlanEndDate(LocalDate.now());
//		c7.setPlanEndDate(LocalDate.now().plusMonths(6));
//		c7.setBenefitAmt(10000.00);
//
//		CitizenPlan c8 = new CitizenPlan();
//		c8.setCitizenName("Lali");
//		c8.setGender("Male");
//		c8.setPlanName("Medical");
//		c8.setPlanStatus("Denied");
//		c8.setDenialReason("Rent Income");
//
//		CitizenPlan c9 = new CitizenPlan();
//		c9.setCitizenName("Orlen");
//		c9.setGender("Fe-Male");
//		c9.setPlanName("Medical");
//		c9.setPlanStatus("Terminated");
//		c9.setPlanEndDate(LocalDate.now().minusMonths(4));
//		c9.setPlanEndDate(LocalDate.now().plusMonths(6));
//		c9.setBenefitAmt(20000.00);
//		c9.setTerminatedDate(LocalDate.now());
//		c9.setTerminationReason("Govt. Job");
//
//		// Employement plan Data
//		CitizenPlan c10 = new CitizenPlan();
//		c10.setCitizenName("Richie");
//		c10.setGender("Male");
//		c10.setPlanName("Employement");
//		c10.setPlanStatus("Approved");
//		c10.setPlanEndDate(LocalDate.now());
//		c10.setPlanEndDate(LocalDate.now().plusMonths(6));
//		c10.setBenefitAmt(50000.00);
//
//		CitizenPlan c11 = new CitizenPlan();
//		c11.setCitizenName("Duggu");
//		c11.setGender("Male");
//		c11.setPlanName("Employement");
//		c11.setPlanStatus("Denied");
//		c11.setDenialReason("Rent Income");
//
//		CitizenPlan c12 = new CitizenPlan();
//		c12.setCitizenName("Lia");
//		c12.setGender("Fe-Male");
//		c12.setPlanName("Employement");
//		c12.setPlanStatus("Terminated");
//		c12.setPlanEndDate(LocalDate.now().minusMonths(4));
//		c12.setPlanEndDate(LocalDate.now().plusMonths(6));
//		c12.setBenefitAmt(4000.00);
//		c12.setTerminatedDate(LocalDate.now());
//		c12.setTerminationReason("Employed");
//
//		List<CitizenPlan> asList = Arrays.asList(c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12);
//		repo.saveAll(asList);

		List<CitizenPlan> citizen = new ArrayList<>();
		Random random = new Random();
		// data stored
		String[] citizenName = { "John", "Emily", "Michael", "Sarah", "David", "Sophia", "Daniel", "Olivia", "James",
				"Emma", "Liam", "Isabella", "Noah", "Mia", "Ethan", "Charlotte", "Mason", "Amelia", "Logan", "Harper" };
		String[] citizenLastName = { "Smith", "Johnson", "Brown", "Williams", "Jones", "Garcia", "Miller", "Davis",
				"Rodriguez", "Martinez" };
		String[] gender = { "Male", "Fe-Male" };
		String[] planName = { "Health Plan A", "Health Plan B", "Life Plan X", "Retirement Plan", "Savings Plan" };
		String[] planStatus = { "Active", "Denied", "Terminated" };
		String[] denialReason = { "Innvalid Documents", "Eligibility Not Met", "Incomplete Application" };
		String[] terminationReason = { "Policy Violation", "Non-Payment", "Voluntary Termination" };


		for (int i = 0; i <= 30; i++) {
			CitizenPlan c1 = new CitizenPlan();
			// Generate random values for the field
			c1.setCitizenName(citizenName[random.nextInt(citizenName.length)]);
			c1.setCitizenLastName(citizenLastName[random.nextInt(citizenLastName.length)]);
			c1.setGender(gender[random.nextInt(gender.length)]);
			c1.setPlanName(planName[random.nextInt(planName.length)]);
			c1.setPlanStatus(planStatus[random.nextInt(planStatus.length)]);
			int year = random.nextInt(100)+1924;
			int month = random.nextInt(12)+1;
			int day = random.nextInt(28)+1;
			LocalDate today = LocalDate.now();
			LocalDate dateBirth = LocalDate.of(year, month, day);
			c1.setDob(dateBirth);
			Period age = Period.between(dateBirth, today);
			c1.setPlanStartDate(LocalDate.now().minusMonths(random.nextInt(12)+1));
			c1.setPlanEndDate(LocalDate.now().plusMonths(random.nextInt(12)+1));
			if(age.getYears() >= 18) {
				c1.setDenialReason("Eligibility Not Met");
			} else {
				c1.setBenefitAmt(1000.00+random.nextInt(5000));
			}
			if (c1.getPlanStatus().equals("Denied")) {
				c1.setDenialReason(denialReason[random.nextInt(denialReason.length)]);
			}
			if(c1.getPlanStatus().equals("Terminated")) {
				c1.setTerminatedDate(LocalDate.now().minusDays(random.nextInt(30)+1));
				c1.setTerminationReason(terminationReason[random.nextInt(terminationReason.length)]);
			}
			citizen.add(c1);
		}
		repo.saveAll(citizen);
	}
}
