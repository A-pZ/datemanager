package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BusinessDate;
import com.example.demo.repository.BusinessDateDao;

@Service
public class BusinessDateServiceImpl implements BusinessDateService {

	@Autowired
	private BusinessDateDao dao;

	//テーブルの登録済データ数を取得する。
	@Override
	public int getCount() {
		return dao.getCount();
	}

	//入力した日付名をカウントする。
	@Override
	public int countDateName(String dateName) {
		return dao.countDateName(dateName);
	}

	//1件登録
	@Override
	public void insertDate(BusinessDate businessDate) {
		dao.insertDate(businessDate);
	}

	//全件取得
	@Override
	public List<BusinessDate> getAll() {

		return dao.getAll();
	}

}