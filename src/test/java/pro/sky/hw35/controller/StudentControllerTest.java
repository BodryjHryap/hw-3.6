package pro.sky.hw35.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import pro.sky.hw35.model.Student;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StudentController studentController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void create_Test() throws Exception {
        Student student = new Student();
        student.setName("Tariq Lamptey");
        student.setAge(18);
        student.setId(1L);
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }

    @Test
    void get_Test() throws Exception {
        Student student = new Student();
        student.setName("Tariq Lamptey");
        student.setAge(18);
        student.setId(1L);
        Assertions
                .assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/student", String.class))
                .isNotNull();
    }

    @Test
    void update_Test() throws Exception {
        Student student = new Student();
        student.setName("Tariq Lamptey");
        student.setAge(18);
        student.setId(1L);
        student.setName("Antonio Valencia");
        Assertions
                .assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/student", student, String.class))
                .isNotNull();
    }
}
