package com.example.altimetrik;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.altimetrik.entity.Product;
import com.example.altimetrik.repo.ProductRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class ProductServiceTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepo productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void createProduct() throws Exception{
     			
        Product product = new Product(1,"cloth","trends collections",199.99);
               
        ResultActions response = mockMvc.perform(post("/api/kafka/product")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(product)));
        response.andDo(print()).
                andExpect(status().isCreated())
                .andExpect(jsonPath("$.Name",
                        is(product.getName())))
                .andExpect(jsonPath("$.description",
                        is(product.getDescription())))
                .andExpect(jsonPath("$.price",
                        is(product.getPrice())));

    }
    
    @Test
    public void getProducts_test() throws Exception{
        long productId = 1;
        willDoNothing().given(productService).findById((int) productId);

       ResultActions response = mockMvc.perform(get("/api/kafka/product/{id}", productId));
        
       response.andExpect(status().isOk())
                .andDo(print());
    }   
    
    @Test
    public void getAllProducts_test() throws Exception{
        willDoNothing().given(productService).findAll();

       ResultActions response = mockMvc.perform(get("/api/kafka/admin/product"));
       
        response.andExpect(status().isOk())
                .andDo(print());
    }   
    
}
