package junit.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.offcn.bean.Authority;
import com.offcn.bean.User;
import com.offcn.service.UserService;

class Test01 {

	@Test
	void test() throws SQLException {
		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
		UserService bean = ac.getBean(UserService.class);
		User user = bean.login("a", "9bba13aaeb55b59ce72f9f6aad672e2c32544630");
		List<Authority> list = user.getRole().getAuthorities();
		for (Authority authority : list) {
			System.out.println(authority.getId()+""+authority.getDisplayName()+"=========="+authority.getParentAuthority().getDisplayName()+""+authority.getParentAuthority().getId());
		}
		
	}

}
