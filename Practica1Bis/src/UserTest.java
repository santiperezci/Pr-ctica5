import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest {

	@Test
	void testEmail() {
		User user1 = new User("Javier", "javier@gmail.com", "javier123");
		//assertEquals(User.getEmail(), "javier@gmail.com");
	}
	@Test
	void testPassword() {
		User user1 = new User("Javier", "javier@gmail.com", "javier123");
	//assertEquals(User.getPassword(), "javier123");
	}
}
