package com.example.demo;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entity.BusinessDate;
import com.example.demo.service.RestService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "/test.properties")
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class DateRestControllerTest {

	//依存しているRestServieクラスをモック化
	@MockBean
	RestService mockRestService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void 一覧取得用URLへのGETリクエストが機能する()throws Exception{
		//Mock化したBusinessDateServiceのgetAll()の戻り値を用意
		List<BusinessDate> dateList = new ArrayList<BusinessDate>();
		dateList.add(createBusinessDate(1, "19990101", "テスト日付", 1, 0, 0));

		////mock化したRestServiceクラスのgetAllメソッドの戻り値設定
		when(mockRestService.getAll()).thenReturn(dateList);

		mockMvc.perform(get("/businessdate/get"));

		//mock化したRestServiceクラスのgetAllメソッドが1回呼ばれるか検証。
		verify(mockRestService,times(1)).getAll();

	}


	@Test
	public void 個別データ取得用URLへのGETリクエストが機能する()throws Exception{
		//Mock化したBusinessDateServiceのselectOneメソッドの戻り値を用意
		BusinessDate businessDate = createBusinessDate(1, "19990101", "テスト日付", 1, 0, 0);

		////mock化したRestServiceクラスのselectOneメソッドの戻り値設定
		when(mockRestService.selectOne(1)).thenReturn(businessDate);

		mockMvc.perform(get("/businessdate/get"));

		//mock化したRestServiceクラスのgetAllメソッドが1回呼ばれるか検証。
		verify(mockRestService,times(1)).getAll();
	}

	//テスト用のEntityクラスの作成メソッド
	public BusinessDate createBusinessDate(int id,String base_date,String date_name,int diff_year,int diff_month,int diff_day) {
		BusinessDate businessDate = new BusinessDate();
		businessDate.setId(id);
		businessDate.setBase_date(base_date);
		businessDate.setDate_name(date_name);
		businessDate.setDiff_year(diff_year);
		businessDate.setDiff_month(diff_month);
		businessDate.setDiff_month(diff_day);

		return businessDate;
	}


}
