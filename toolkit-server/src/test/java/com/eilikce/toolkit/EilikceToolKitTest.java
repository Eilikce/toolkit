package com.eilikce.toolkit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={EilikceToolKit.class})// 指定启动类
public class EilikceToolKitTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void controllerTest() throws Exception {
        int status = mvc.perform(MockMvcRequestBuilders.get("/mysql/query")
                .content("")).andReturn().getResponse().getStatus();
        System.out.println(status);
    }

}
