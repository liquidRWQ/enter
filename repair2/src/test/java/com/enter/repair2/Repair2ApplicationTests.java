package com.enter.repair2;
import com.enter.repair2.service.ManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Repair2ApplicationTests {

    @Autowired
    private ManagerService managerService;

    @Test
    public void contextLoads() throws Exception {

    }
}
