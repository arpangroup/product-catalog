package com.arpan.productcatalog.controller;

import static org.assertj.core.api.Assertions.assertThat;
import com.arpan.productcatalog.entity.product.Category;
import com.arpan.productcatalog.repository.CategoryRepository;
import com.arpan.productcatalog.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class CategoryControllerTest {

    @Autowired CategoryController controller;
    @Autowired CategoryService service;
    @MockBean CategoryRepository repository;

    MockMvc mockMvc;

    Category CATEGORY_1 = new Category("cat1");
    Category CATEGORY_2 = new Category("cat2");

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @BeforeEach
    public void setUo() {
//        MockitoAnnotations.initMocks(this);
//        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


//    @Before()
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//    }

//    @Autowired
//    MockMvc mockMvc;
//    @MockBean
//    private CategoryRepository repository;
//    @MockBean
//    private CategoryService categoryService;
//    @MockBean
//    private CategoryController categoryController;
//    @Autowired
//    private ObjectMapper objectMapper;

//    @BeforeEach
//    void setup() {
//        calculator = new CalculatorService();
//    }

//    @Test
//    public void whenPostRequestToUsersAndValidUser_thenCorrectResponse() throws Exception {
//        MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
//        String user = "{\"name\": \"bob\", \"email\" : \"bob@domain.com\"}";
//        mockMvc.perform(MockMvcRequestBuilders.post("/users")
//                .content(user)
//                .contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content()
//                        .contentType(textPlainUtf8));
//        String categoryRequest = "{\"name\": \"demo category123\"}";
//
//        ProductCategory category = new ProductCategory();
//        category.setName(category.getName());
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/categories")
//                .content(categoryRequest)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content()
//                        .contentType(MediaType.APPLICATION_JSON));


//    }

    @Test
    public void getAllCategoriesTest() {
        List<Category> mockCategoryList = Arrays.asList(CATEGORY_1, CATEGORY_2);
        when(repository.findAll()).thenReturn(mockCategoryList);
        //assertEquals(2, service.getCategories().size());
    }

    @Test
    public void saveCategoryTest() {
        Category category = new Category("cat1");
        when(repository.save(category)).thenReturn(category);
        //assertEquals(category, service.addCategory(category));
    }

    @Test
    public void deleteCategoryTest() {
        Category category = new Category("cat1");
        //service.deleteCategory(category);
        //verify(repository, times(1)).delete(category);
    }


    @Test
    public void testGetCategoryById__Success() {
        Category category = new Category();
        category.setName("cat1");
        when(repository.findById(1L)).thenReturn(Optional.of(category));

        Category expectedCategory = service.getCategoryById(1);
        assertEquals("cat1", expectedCategory.getName());
    }

    @Test
    public void getAllCategoriesTest_success() throws Exception {
        List<Category> mockCategoryList = Arrays.asList(CATEGORY_1, CATEGORY_2);
        when(repository.findAll()).thenReturn(mockCategoryList);
        //assertEquals(2, service.getCategories().size());
    }


}