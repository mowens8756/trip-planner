package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Access;
import com.example.demo.repository.AccessRepository;

@Service
public class AccessServiceImpl implements AccessService{
	/**
	 * Trip(Entity)クラスのリポジトリクラス.
	 */
	@Autowired
	private AccessRepository repository;

	/**
	 * Access(Entity)クラスのデータを全件取得する.
	 *
	 * @return tripsテーブルの全件データ
	 */
	public List<Access> findAll() {
		return repository.findAll();
	}

	/**
	 * trip_idに紐付くAccess(Entity)クラスのデータを全件取得する.
	 *
	 * @param trip_id id
	 * @return 該当した全件のデータ
	 */
	public List<Access> findAllByTripId(Integer trip_id) {
		return repository.findByTripId(trip_id);
	}

	/**
	 * Access(Entityクラス)のデータを保存する.
	 *
	 * @param access Trip(Entityクラス)
	 * @return 保存したTrip(Entityクラス)
	 */
	@Transactional
	public Access save(Access access) {
		return repository.save(access);
	}
	
}
