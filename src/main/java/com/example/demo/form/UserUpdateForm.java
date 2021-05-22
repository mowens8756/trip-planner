package com.example.demo.form;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.demo.model.SiteUser;
import com.example.demo.util.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateForm implements Serializable {

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
    
    @NotBlank
    @Email
    private String email;
    private String role;
    private boolean isAdmin;
    private boolean isActive;

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
        this.setEmail(user.getEmail());
        this.setRole(user.getRole());
        this.setAdmin(user.isAdmin());
        this.setActive(user.isActive());
    }
    public String toString() {
        return "SiteUser(username: " + this.getUsername() + ", password: " + this.getPassword() 
        + ", role: " + Role.USER.toString() 
        + ", isAdmin: " + this.isAdmin() +", isActive: " + this.isActive() +")";
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
        user.setEmail(this.getEmail());
        Timestamp current_time = new Timestamp(System.currentTimeMillis());
        user.setUpdated_at(current_time);
        user.setRole(this.getRole());
        user.setAdmin(this.isAdmin());
        user.setActive(this.isActive());
        return user;
    }
}
