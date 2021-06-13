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
	 * trip_idに紐付くItinerary(Entity)クラスのデータを全件取得する.
	 *
	 * @param trip_id id
	 * @return 該当した全件のデータ
	 */
	public List<Itinerary> findAllByTripId(Integer trip_id) {
		return repository.findByTripId(trip_id);
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
	
	/**
     * Trip IDに紐付くItinerary(Entity)クラスのデータを削除する.
     *
     * @param trip_id Trip ID
     */
    @Transactional
    public void delete(Integer trip_id){
        repository.deleteByTripId(trip_id);
    }

}
