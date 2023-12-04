package pro.sky.hw35.testWithMock.controller;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pro.sky.hw35.controller.FacultyController;
import pro.sky.hw35.controller.StudentController;
import pro.sky.hw35.model.Faculty;
import pro.sky.hw35.model.Student;
import pro.sky.hw35.repository.FacultyRepository;
import pro.sky.hw35.repository.StudentRepository;
import pro.sky.hw35.service.FacultyService;
import pro.sky.hw35.service.StudentService;

import java.util.Optional;

import static java.nio.file.Paths.get;
import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class FacultyControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @SpyBean
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @Test
    public void saveFaculty_Test() throws Exception {
        final String name = "Everton";
        final String color = "blue";
        final long id = 1;

        JSONObject studentObject = new JSONObject();
        studentObject.put("name", name);

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setName("Wrexham");
        faculty.setColor("red");

        when(facultyRepository.save(ArgumentMatchers.any(Faculty.class))).thenReturn(faculty);
        when(facultyRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }
}
