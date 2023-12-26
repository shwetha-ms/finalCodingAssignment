package com.example.demo;

import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.demo.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class ProductTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

//    @Test
//    public void createProduct() throws Exception{
//    	List<Atrributes> attributes = new ArrayList<Atrributes>();
//    	attributes.add(new Atrributes(1,"color","blue"));    			
//
//        Product product = new Product(1L,"cloth","trends","trends collections",
//        		new Price(1,"USD",499.99),
//                new Inventory(1,20,50,10),
//                attributes,
//        		new Category(1,"cloth"));
//               
//        given(productService.saveProduct(any(ProductDto.class,Integer.class)))
//                .willAnswer((invocation)-> invocation.getArgument(0));
//
//        ResultActions response = mockMvc.perform(post("/product/1")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(objectMapper.writeValueAsString(product)));
//        response.andDo(print()).
//                andExpect(status().isCreated())
//                .andExpect(jsonPath("$.Name",
//                        is(product.getName())))
//                .andExpect(jsonPath("$.description",
//                        is(product.getDescription())))
//                .andExpect(jsonPath("$.price",
//                        is(product.getPrice())));
//
//    }
    
    @Test
    public void getProducts_test() throws Exception{
        long productId = 1L;
        willDoNothing().given(productService).getProduct(productId);

       ResultActions response = mockMvc.perform(get("/product/{id}", productId));
        
       response.andExpect(status().isOk())
                .andDo(print());
    }   
    
    @Test
    public void removeProducts_test() throws Exception{
        long productId = 1L;
        willDoNothing().given(productService).removeProduct(productId);;

       ResultActions response = mockMvc.perform(delete("/admin/product/{id}", productId));

        response.andExpect(status().isOk())
                .andDo(print());
    }   
    
}