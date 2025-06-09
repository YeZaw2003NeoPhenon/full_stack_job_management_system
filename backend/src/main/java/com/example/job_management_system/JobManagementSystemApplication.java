package com.example.job_management_system;

import com.example.job_management_system.entity.Company;
import com.example.job_management_system.entity.Job;
import com.example.job_management_system.repository.CompanyRepository;
import com.example.job_management_system.repository.JobRepository;
import com.example.job_management_system.security.user.AppUser;
import com.example.job_management_system.security.user.AppUserRepository;
import com.example.job_management_system.security.user.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
public class JobManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobManagementSystemApplication.class, args);
	}

//	@Bean
//	CommandLineRunner commandLineRunner(JobRepository jobRepository, CompanyRepository companyRepository){
//
//		return args -> {
//
//
//			Company company1 = new Company(
//					"Dolor Cloud",
//					"Dolor Cloud is a leading technology company specializing in digital solutions for businesses of all sizes. With a focus on innovation and customer satisfaction, we are committed to delivering cutting-edge products and services.",
//					"contact@dolorsitamet.com",
//					"555-555-5555");
//
//			Company company2 = new Company(
//					"Dolor Cloud",
//					"Dolor Cloud is a leading technology company specializing in digital solutions for businesses of all sizes. With a focus on innovation and customer satisfaction, we are committed to delivering cutting-edge products and services.",
//					"contact@dolorsitamet.com",
//					"555-555-5555");
//
//			Company company3 = new Company(
//					"Veneer Solutions",
//					"Veneer Solutions is a creative agency specializing in digital design and development. Our team is dedicated to pushing the boundaries of creativity and innovation to deliver exceptional results for our clients.",
//					"contact@loremipsum.com",
//					"555-555-5555");
//
//			companyRepository.saveAll(List.of(company1, company2, company3));
//
//			List<Job> jobs = List.of(
//					new Job( "React.js Dev",
//							"Full-Time",
//							"Brooklyn, NY",
//							"Are you passionate about front-end development? Join our team in vibrant Brooklyn, NY, and work on exciting projects that make a difference. We offer competitive compensation and a collaborative work environment where your ideas are valued.",
//							"$70K - $80K", company1
//					),
//					new Job("Senior React Developer", "Full-Time",
//							"Boston, MA",
//							"We are seeking a talented Front-End Developer to join our team in Boston, MA. The ideal candidate will have strong skills in HTML, CSS, and JavaScript, with experience working with modern JavaScript frameworks such as React or Angular.",
//							"$70K - $80K",
//							company2
//					),
//					new Job( "Front-End Engineer (React & Redux)",
//							"Full-Time",
//							"Miami, FL",
//							"Join our team as a Front-End Developer in sunny Miami, FL. We are looking for a motivated individual with a passion for crafting beautiful and responsive web applications. Experience with UI/UX design principles and a strong attention to detail are highly desirable.",
//							"$70K - $80K",
//							company3
//					)
//			);
//
//			jobRepository.saveAll(jobs);
//		 };
//		}

//@Bean
//	CommandLineRunner commandLineRunner(AppUserRepository appUserRepository){
//		return args -> {
//			AppUser neo = new AppUser("Neo","neo@gmail.com", new BCryptPasswordEncoder().encode("neophenon!@#"), Role.ADMIN);
//			AppUser luzy = new AppUser("Luzy","luzy@gmail.com", new BCryptPasswordEncoder().encode("luzy1243dfdf"), Role.ADMIN);
//
//			appUserRepository.saveAll(List.of(neo,luzy));
//		};
//	}
}


