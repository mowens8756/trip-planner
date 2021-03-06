package com.example.demo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * UserSearchAnnotationのインターフェース.
 * カスタマイズチェックのアノテーションチェック処理を1クラスで複数回実施できるように設定.
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserSearchAnnotation  {
	UserSearch[] value();
}
