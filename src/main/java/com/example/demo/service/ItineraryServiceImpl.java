package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Itinerary;
import com.example.demo.repository.ItineraryRepository;

@Service
public class ItineraryServiceImpl implements ItineraryService{
	/**
	 * Itinerary(Entity)クラスのリポジトリクラス.
	 */
	@Autowired
	private ItineraryRepository repository;

	/**
	 * Itinerary(Entity)クラスのデータを全件取得する.
	 *
	 * @return tripsテーブルの全件データ
	 */
	public List<Itinerary> findAll() {
		return repository.findAll();
	}

	/**
	 * itinerary_idに紐付くItinerary(Entity)クラスのデータを1件取得する.
	 *
	 * @param itinerary_id id
	 * @return 該当した1件のデータ
	 */
	public Itinerary findOne(Integer itinerary_id) {
		return repository.getOne(itinerary_id);
	}

	/**
	 * Itinerary(Entityクラス)のデータを保存する.
	 *
	 * @param trip Itinerary(Entityクラス)
	 * @return 保存したItinerary(Entityクラス)
	 */
	@Transactional
	public Itinerary save(Itinerary itinerary) {
		return repository.save(itinerary);
	}

}
