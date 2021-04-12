package headu.mpp.secretpartyreservationsystem.address;

import com.fasterxml.jackson.databind.ObjectMapper;
import headu.mpp.secretpartyreservationsystem.place.Place;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
@SpringBootTest
@AutoConfigureMockMvc
public class TestAddressController {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AddressService addressService;
    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testPostExample() throws Exception {
        Address address = new Address();
                address.setId(1L);
                address.setCity("Fairfield");
                address.setStreet("1000 N 4th St1");
                address.setState("IA");
                address.setZip(52557);

        Mockito.when(addressService.createAddress(ArgumentMatchers.any())).thenReturn(address);
        String json = mapper.writeValueAsString(address);


        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmdpZTIyIiwicm9sZSI6IlVTRVIiLCJleHAiOjE2MTg5NTE4MTN9.1O9NZU403LhGvSed9CNopnF4jYIwXwmbkHqeP5LUoRyEJF6JrGL0ZQEl34aODFXdw4jl7-Pk6X-GG2WWVpdQow";
        mockMvc.perform(post("/api/address/").header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.street", Matchers.equalTo("1000 N 4th St1")))
                .andExpect(jsonPath("$.city", Matchers.equalTo("Fairfield")))
                .andExpect(jsonPath("$.state", Matchers.equalTo("IA")))
                .andExpect(jsonPath("$.zip", Matchers.equalTo(52557)));



    }

}
