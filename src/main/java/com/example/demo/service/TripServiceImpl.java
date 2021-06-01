package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Trip;
import com.example.demo.repository.TripRepository;

@Service
public class TripServiceImpl implements TripService{
	/**
	 * Trip(Entity)クラスのリポジトリクラス.
	 */
	@Autowired
	private TripRepository repository;

	/**
	 * Trip(Entity)クラスのデータを全件取得する.
	 *
	 * @return tripsテーブルの全件データ
	 */
	public List<Trip> findAll() {
		return repository.findAll();
	}

	/**
	 * trip_idに紐付くTrip(Entity)クラスのデータを1件取得する.
	 *
	 * @param trip_id id
	 * @return 該当した1件のデータ
	 */
	public Trip findOne(Integer trip_id) {
		return repository.getOne(trip_id);
	}

	/**
	 * Trip(Entityクラス)のデータを保存する.
	 *
	 * @param trip Trip(Entityクラス)
	 * @return 保存したTrip(Entityクラス)
	 */
	@Transactional
	public Trip save(Trip trip) {
		return repository.save(trip);
	}
}
