package com.abdulrauf.omrestfulservice.controllers;

import com.abdulrauf.omrestfulservice.Utility;
import com.abdulrauf.omrestfulservice.controllers.BookController;
import com.abdulrauf.omrestfulservice.models.AskOffer;
import com.abdulrauf.omrestfulservice.models.BidOffer;
import com.abdulrauf.omrestfulservice.models.Book;
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

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerTest {

	private MockMvc mockMvc;

	@Mock
	OrderMatchingServiceImpl orderMatchingServiceMock;

	@InjectMocks
	private BookController bookControllerMock;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(bookControllerMock)
//				.addFilters(new CORSFilter())
				.build();
	}

	@Test
	public void test_get_book() throws Exception {
		Book book = new Book(
				new ArrayList<BidOffer>() {{ add(new BidOffer (1, 2)); }},
				new ArrayList<AskOffer>() {{ add(new AskOffer (3, 4)); }}
		);
		when(orderMatchingServiceMock.getBook()).thenReturn(book);
		mockMvc.perform(get("/book"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(Utility.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.buys.length()", is(1)))
				.andExpect(jsonPath("$.buys[0].qty", is(1)))
				.andExpect(jsonPath("$.buys[0].prc", is(2.0)))
				.andExpect(jsonPath("$.sells.length()", is(1)))
				.andExpect(jsonPath("$.sells[0].qty", is(3)))
				.andExpect(jsonPath("$.sells[0].prc", is(4.0)))
		;
	}
}
