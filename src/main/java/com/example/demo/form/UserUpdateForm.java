package com.example.demo.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.demo.model.SiteUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateForm extends UserBaseForm {

	/**　シリアルバージョンUID. */
	private static final long serialVersionUID = 1L;

	/**　正規表現(半角英数字). */
	private static final String ALPHANUMERIC_REGEXP = "[a-zA-Z0-9.]*";

	/**　正規表現(半角英数字)のエラーメッセージ. */
	private static final String ALPHANUMERIC_MESSAGE = "半角英字、数字、ピリオドを使用できます";

	@Size(max=30)
	@Pattern(regexp = ALPHANUMERIC_REGEXP, message = ALPHANUMERIC_MESSAGE)
    private String username;

    @Size(max=255)
	@Pattern(regexp = ALPHANUMERIC_REGEXP, message = ALPHANUMERIC_MESSAGE)
    private String password;


    /**
     * コンストラクタ.
     */
    public UserUpdateForm() {

    }

    /**
     * コンストラクタ.
     * @param user SiteUser(Entityクラス)
     */
    public UserUpdateForm(SiteUser user) {
        this.setUsername(user.getUsername());
        this.setPassword("");
        super.setRole(user.getRole());
        this.setActive(user.isActive());
    }

    /**
     * Formクラスの設定内容を文字列で出力する.
     */
    public String toString() {
        return "User(username: " + this.getUsername() + ", password: " + this.getPassword() + 
        		", role: " + super.getRole() + ", isActive: " + super.isActive() +")";
    }

    /**
     * Formの設定内容をSiteUser Entityクラスに変換する.
     *
     * @return ユーザー情報(Entityクラス)
     */
    public SiteUser toEntity(){

        SiteUser user = new SiteUser();
        user.setUsername(this.getUsername());
        user.setPassword(this.getPassword());
        user.setRole(super.getRole());
        user.setActive(super.isActive());

        return user;
    }
}
