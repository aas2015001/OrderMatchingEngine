package com.abdulrauf.omrestfulservice.controllers;

import com.abdulrauf.omrestfulservice.Utility;
import com.abdulrauf.omrestfulservice.controllers.SellController;
import com.abdulrauf.omrestfulservice.models.AskOffer;
import com.abdulrauf.omrestfulservice.services.OrderMatchingServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellControllerTest {

	private MockMvc mockMvc;

	@Mock
	OrderMatchingServiceImpl orderMatchingServiceMock;

	@InjectMocks
	private SellController sellControllerMock;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(sellControllerMock)
//				.addFilters(new CORSFilter())
				.build();
	}

	@Test
	public void test_post_sell() throws Exception {
		AskOffer askOffer = new AskOffer(
				1, 2.0
		);
		mockMvc.perform(post("/sell")
				.contentType(Utility.APPLICATION_JSON_UTF8)
				.content(Utility.convertObjectToJsonBytes(askOffer))
		)
				.andDo(print())
				.andExpect(status().isOk())
		;
	}
}
