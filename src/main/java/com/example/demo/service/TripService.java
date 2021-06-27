package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Trip;

public interface TripService {
	/**
	 * Trip(Entity)クラスのデータを全件取得する.
	 *
	 * @return tripsテーブルの全件データ
	 */
	public List<Trip> findAll();

	/**
	 * trip_idに紐付くTrip(Entity)クラスのデータを1件取得する.
	 *
	 * @param trip_id
	 * @return 該当した1件のデータ
	 */
	public Trip findOne(Integer trip_id);

	/**
	 * Trip(Entity)クラスのデータを保存する.
	 *
	 * @param trip Trip(Entity)クラス
	 * @return 保存したTrip(Entity)クラス
	 */
	public Trip save(Trip trip);
	
	/**
	 * ユーザー名に紐付くTrip(Entity)クラスのデータを全件取得する.
	 *
	 * @param username ユーザー名
	 * @return ユーザー名に紐付くtripsテーブルの全件データ
	 */
	public List<Trip> findAllByUsername(String username);
	
	/**
	 * ユーザー名が含まれるTrip(Entity)クラスのデータを全件取得する.
	 *
	 * @param username ユーザー名
	 * @return Access(Entity)クラスにユーザー名が含まれるtripsテーブルの全件データ
	 */
	public List<Trip> findAllShared(String username);
	
	/**
     * Trip IDに紐付くTrip(Entity)クラスのデータを削除する.
     *
     * @param trip_id Trip ID
     */
    public void delete(Integer trip_id);
    
    /**
	 * PublicのTrip(Entity)クラスのデータを全件取得する.
	 *
	 * @return Publicのtripsテーブルの全件データ
	 */
	public List<Trip> findAllPublic();
	
	/**
     * Trip IDに紐付くTrip(Entity)クラスのデータを公開する.
     *
     * @param trip_id Trip ID
     */
    public void setPublic(Integer trip_id);
    
    /**
     * Trip IDに紐付くTrip(Entity)クラスのデータを非公開にする.
     *
     * @param trip_id Trip ID
     */
    public void setPrivate(Integer trip_id);

}
