package com.example.demo.annotation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.annotation.UserSearch;
import com.example.demo.service.UserService;

import lombok.Getter;
import lombok.Setter;

/**
 * UserSearchの実装クラス.
 */
@Getter
@Setter
public class UserSearchValidatorImpl implements ConstraintValidator<UserSearch, Object> {

	/** アノテーションで指定したmessageの項目名 */
	private String message;

	/** アノテーションで指定したユーザーの項目名 */
	private String existingUsername;

	/**
	 * User(Entityクラス)を操作するServiceクラス.
	 */
	@Autowired
	private UserService userService;

	/**
	 * 初期化処理.
	 * 
	 * @param annotation UserSearchの情報
	 */
	@Override
	public void initialize(UserSearch annotation) {
		// アノテーションで指定したユーザーID・メッセージの項目名を取得
		this.setExistingUsername(annotation.existingUsername());
		this.setMessage(annotation.message());
	}

	/**
	 * バリデーション.
	 * 
	 * @param value 入力値
	 * @param context バリデーション情報
	 * @return 検証結果(trueの場合OK、falseの場合NG)
	 */
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {

		String username = (String) value;
		// ユーザーIDが空か判定
		if (StringUtils.isBlank(username)) {
			// 空の場合、後続の処理をせず終了
			return true;
		}

		// ユーザー名が存在するか判定
		if (this.checkExistingUsername(username)) {
			// 存在している場合
			return true;
		} else {
			// 存在していない場合
			// バリデーションエラーとして扱う
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
			return false;
		}
	}

	/**
	 * ユーザー名が一意か、チェックするメソッド.
	 * 
	 * @param username ユーザー名
	 * @return 検証結果(true：存在する、false：存在しない)
	 */
	private boolean checkExistingUsername(String username) {

		// ユーザー名に紐付くユーザー情報の件数を取得
		long count = userService.countByUsername(username);

		// 件数を判定
		if (count > 0) {
			// 0件でない場合、存在する
			return true;
		}

		return false;
	}
}
