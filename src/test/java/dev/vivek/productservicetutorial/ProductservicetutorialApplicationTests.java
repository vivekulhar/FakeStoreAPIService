package dev.vivek.productservicetutorial;

import dev.vivek.productservicetutorial.inheritanceexamples.jointtable.JTMentorRepository;
import dev.vivek.productservicetutorial.inheritanceexamples.jointtable.Mentor;
import dev.vivek.productservicetutorial.inheritanceexamples.jointtable.User;
import dev.vivek.productservicetutorial.inheritanceexamples.jointtable.JTUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProductservicetutorialApplicationTests {
	@Autowired
	private JTUserRepository userRepository;
	@Autowired
	private JTMentorRepository mentorRepository;
	@Test
	void contextLoads() {
	}
	@Test
	void testDifferentInheritances(){
		User user  = new User();
		user.setEmail("vivek.kulhar23@gmail.com");
		user.setPassword("123456");
		userRepository.save(user);

		Mentor mentor = new Mentor();
		mentor.setEmail("vk.kulhar@gmail.com");
		mentor.setPassword("123456");
		mentor.setNumberOfMentees(4);
		mentor.setNumberOfSessions(5);
		mentorRepository.save(mentor);
	}
}
