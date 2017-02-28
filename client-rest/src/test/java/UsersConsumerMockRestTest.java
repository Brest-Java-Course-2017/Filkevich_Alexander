import com.epam.test.client.rest.api.UsersConsumer;
import com.epam.test.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reset;
import static org.junit.Assert.assertEquals;

/**
 * Created by master on 28.2.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:client-test-mock.xml"})
public class UsersConsumerMockRestTest {

    private Logger LOGGER= LogManager.getLogger();

    private static final User user=new User(1,"userLogin1","userPAssword1","");
    private static final User user3=new User(3,"userLogin3","userPAssword3","");
    private static final String USER_LOGIN_3="userLogin3";

    @Autowired
    UsersConsumer usersConsumer;
    @Value("${user.protocol}://${user.host}:${user.port}/")
    private String hostUrl;
    @Value("${point.users}")
    private String urlUsers;
    @Value("${point.user}")
    private String urlUser;
    @Autowired
    private RestTemplate mockRestTemplate;

    @After
    public void tearDown()throws Exception{
        reset(mockRestTemplate);
    }

    @Test
    public void getAllUsers() throws Exception{
        List expectedResult=new ArrayList(2);
        expectedResult.add(user);
        expectedResult.add(user3);
        expect(mockRestTemplate.getForEntity(hostUrl+"/"+urlUsers,List.class))
                .andReturn(new ResponseEntity<List>(expectedResult, HttpStatus.OK));
        replay(mockRestTemplate);

        List<User> users=usersConsumer.getAllUsers();
        assertEquals(2,users.size());
    }

    @Test
    public void getUserById() throws Exception{

        expect(mockRestTemplate.getForEntity(hostUrl+"/"+urlUser+"/"+1,User.class))
                .andReturn(new ResponseEntity<User>(user,HttpStatus.FOUND));
        replay(mockRestTemplate);
        usersConsumer.getUserById(1);
        assertEquals((Integer) 1,user.getUserId());
    }

    @Test
    public void addUser() throws Exception{
        Integer expectedResult=3;
        expect(mockRestTemplate.getForEntity(hostUrl+"/"+urlUser+"/"+user3,Integer.class)).
                andReturn(new ResponseEntity<Integer>(3,HttpStatus.CREATED));
        replay(mockRestTemplate);
        usersConsumer.addUser(user3);
        assertEquals((Integer)3,user3.getUserId());



    }


    @Test
    public void getUserByLogin() throws Exception{
        expect(mockRestTemplate.getForEntity(hostUrl+"/"+urlUser+"/"+"userLogin3",User.class))
                .andReturn(new ResponseEntity<User>(user3,HttpStatus.FOUND));
        replay(mockRestTemplate);
         usersConsumer.getUserByLogin("userLogin3");
        assertEquals(USER_LOGIN_3,user3.getLogin());

    }
}
