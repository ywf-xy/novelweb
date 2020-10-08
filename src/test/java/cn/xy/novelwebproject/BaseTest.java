package cn.xy.novelwebproject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration("src/main/resouces")
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class BaseTest {


		@Test
		public void test() {
				System.out.println("test");
		}
}