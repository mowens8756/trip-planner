package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Access;

public interface AccessService {
	/**
	 * Access(Entity)クラスのデータを全件取得する.
	 *
	 * @return tripsテーブルの全件データ
	 */
	public List<Access> findAll();

	/**
	 * trip_idに紐付くAccess(Entity)クラスのデータを全件取得する.
	 *
	 * @param trip_id
	 * @return 該当した全件のデータ
	 */
	public List<Access> findAllByTripId(Integer trip_id);

	/**
	 * Access(Entity)クラスのデータを保存する.
	 *
	 * @param access Access(Entity)クラス
	 * @return 保存したAccess(Entity)クラス
	 */
	public Access save(Access access);
}
