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
	
	/**
	 * ユーザー名に紐付くTrip(Entity)クラスのデータを全件取得する.
	 *
	 * @param username ユーザー名
	 * @return ユーザー名に紐付くtripsテーブルの全件データ
	 */
	public List<Trip> findAllByUsername(String username){
		return repository.findAllByUsername(username);
	}	
	
	/**
	 * ユーザー名が含まれるTrip(Entity)クラスのデータを全件取得する.
	 *
	 * @param username ユーザー名
	 * @return Access(Entity)クラスにユーザー名が含まれるtripsテーブルの全件データ
	 */
	public List<Trip> findAllShared(String username){
		return repository.findAllShared(username);
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
	
    /**
	 * PublicのTrip(Entity)クラスのデータを全件取得する.
	 *
	 * @return Publicのtripsテーブルの全件データ
	 */
	public List<Trip> findAllPublic(){
		return repository.findAllPublic();
	}	
	
	/**
     * Trip IDに紐付くTrip(Entity)クラスのデータを公開する.
     *
     * @param trip_id Trip ID
     */
	@Transactional
    public void setPublic(Integer trip_id) {
		repository.setPublicByTripId(trip_id);
	}
	
	/**
     * Trip IDに紐付くTrip(Entity)クラスのデータを非公開にする.
     *
     * @param trip_id Trip ID
     */
	@Transactional
    public void setPrivate(Integer trip_id) {
		repository.setPrivateByTripId(trip_id);
	}
}
