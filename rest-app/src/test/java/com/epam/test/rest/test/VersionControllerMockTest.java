package com.epam.test.rest.test;


import com.epam.test.rest.VersionController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;





/**
 * Created by master on 24.2.17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-rest-mock.xml"})
public class VersionControllerMockTest {
    @Resource
    private VersionController versionController;

     private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc= standaloneSetup(versionController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();

    }
   @Test
    public void getVersionTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/version").accept(MediaType.APPLICATION_JSON))
        .andDo(print()).andExpect(status().isOk()).andExpect(content().string("\"1.0\"")) ;
    }


}

