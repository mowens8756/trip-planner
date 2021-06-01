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
	 * itinerary_idに紐付くItinerary(Entity)クラスのデータを1件取得する.
	 *
	 * @param itinerary_id
	 * @return 該当した1件のデータ
	 */
	public Itinerary findOne(Integer itinerary_id);

	/**
	 * Itinerary(Entity)クラスのデータを保存する.
	 *
	 * @param itinerary Itinerary(Entity)クラス
	 * @return 保存したItinerary(Entity)クラス
	 */
	public Itinerary save(Itinerary itinerary);
}
