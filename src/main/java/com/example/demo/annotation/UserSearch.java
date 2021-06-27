package com.example.demo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.demo.annotation.impl.UserSearchValidatorImpl;

/**
 * UserSearchのインターフェース.
 * RetentionPolicyはclassファイルに記録され実行時に参照できるモード(Runtime)とします.
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy={UserSearchValidatorImpl.class})
@Repeatable(UserSearchAnnotation.class)
public  @interface UserSearch {
    /** 表示するエラーメッセージ(アノテーション属性で指定). */
    String message();

    /** 特定のバリデーショングループがカスタマイズできるような設定. */
    Class<?>[] groups() default {};

    /** チェック対象のオブジェクトになんらかのメタ情報を与えるためだけの宣言. */
    Class<? extends Payload>[] payload() default {};

    /** ユーザー名(アノテーション属性で指定). */
    String existingUsername();

}
