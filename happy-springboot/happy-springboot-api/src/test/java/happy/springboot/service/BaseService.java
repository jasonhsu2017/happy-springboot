package happy.springboot.service;

import java.text.ParseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.happy.springboot.api.Application;



@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "dev")
@SpringBootTest(classes = Application.class)
public class BaseService {

    @Test
    public void test() throws ParseException {
	
    }
}
