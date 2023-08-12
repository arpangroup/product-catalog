package com.arpan.productcatalog.service;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.arpan.productcatalog.dto.request.CategoryCreateRequest;
import com.arpan.productcatalog.entity.product.Category;
import com.arpan.productcatalog.exception.ValidationException;
import com.arpan.productcatalog.repository.CategoryRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Set;

@SpringBootTest
public class CategoryServiceTest {
    @Autowired CategoryService categoryService;
    @MockBean CategoryRepository categoryRepository;
    Validator validator;

    final String CATEGORY_NAME_1 = "category1";
    final String CATEGORY_NAME_2 = "category2";

    Category category1 = Category.builder()
            .id(1L)
            .name(CATEGORY_NAME_1)
            .parentCategory(null)
            .build();

    Category category2 = Category.builder()
            .id(2L)
            .name(CATEGORY_NAME_2)
            .parentCategory(category1)
            .build();

    @BeforeEach
    public void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }


    @Test
    public void whenNotNullName_thenNoConstraintViolations() {
        CategoryCreateRequest request = new CategoryCreateRequest(CATEGORY_NAME_1);
        Set<ConstraintViolation<CategoryCreateRequest>> violations = validator.validate(request);

        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void whenNullName_thenOneConstraintViolation() {
        CategoryCreateRequest request = new CategoryCreateRequest(null);
        Set<ConstraintViolation<CategoryCreateRequest>> violations = validator.validate(request);

        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void whenEmptyName_thenTwoConstraintViolations() {
        CategoryCreateRequest request = new CategoryCreateRequest("");
        Set<ConstraintViolation<CategoryCreateRequest>> violations = validator.validate(request);

        assertThat(violations.size()).isEqualTo(2);
    }

    @Test
    public void whenNameLengthIsLessThan4_thenOneConstraintViolations() {
        CategoryCreateRequest request = new CategoryCreateRequest("cat");
        Set<ConstraintViolation<CategoryCreateRequest>> violations = validator.validate(request);

        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void whenNameLengthIsGreaterThan50_thenOneConstraintViolations() {
        CategoryCreateRequest request = new CategoryCreateRequest("catsdnskjbnkjskjnkasnkksas ksbnksbndksnds sdbnskdsdsdsskbn");
        Set<ConstraintViolation<CategoryCreateRequest>> violations = validator.validate(request);

        assertThat(violations.size()).isEqualTo(1);
    }


    @Test
    public void whenOnlyCategoryNameInRequest_thenReturnCategoryWithSameName() throws ValidationException {
        CategoryCreateRequest request = new CategoryCreateRequest(CATEGORY_NAME_1);
        when(categoryService.createNewProductCategory(request)).thenReturn(category1);
        when(categoryRepository.save(category1)).thenReturn(category1);

        Category expectedResponse = categoryService.createNewProductCategory(request);
        assertEquals(CATEGORY_NAME_1, expectedResponse.getName());
        // assertEquals(any(), expectedResponse.getId());
        // verify(categoryService, times(1)).createNewProductCategory(request);
    }

}
