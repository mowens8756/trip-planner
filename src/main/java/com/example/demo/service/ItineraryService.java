package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Itinerary;

public interface ItineraryService {
	/**
	 * Itinerary(Entity)クラスのデータを全件取得する.
	 *
	 * @return tripsテーブルの全件データ
	 */
	public List<Itinerary> findAll();

	/**
	 * trip_idに紐付くItinerary(Entity)クラスのデータを全件取得する.
	 *
	 * @param trip_id
	 * @return 該当した全件のデータ
	 */
	public List<Itinerary> findAllByTripId(Integer trip_id);

	/**
	 * Itinerary(Entity)クラスのデータを保存する.
	 *
	 * @param itinerary Itinerary(Entity)クラス
	 * @return 保存したItinerary(Entity)クラス
	 */
	public Itinerary save(Itinerary itinerary);
	
	/**
     * Trip IDに紐付くItinerary(Entity)クラスのデータを削除する.
     *
     * @param trip_id Trip ID
     */
    public void delete(Integer trip_id);

}
