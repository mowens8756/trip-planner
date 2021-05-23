package com.example.demo.form;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.example.demo.annotation.CustomCheck;
import com.example.demo.model.SiteUser;
import com.example.demo.util.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm implements Serializable {
	
	/**　シリアルバージョンUID. */
	private static final long serialVersionUID = 1L;


	/**　正規表現(半角英数字). */
	private static final String ALPHANUMERIC_REGEXP = "[a-zA-Z0-9.]*";

	/**　正規表現(半角英数字)のエラーメッセージ. */
	private static final String ALPHANUMERIC_MESSAGE = "半角英字、数字、ピリオドを使用できます";

	@NotBlank
    @Size(max=30)
	@Pattern(regexp = ALPHANUMERIC_REGEXP, message = ALPHANUMERIC_MESSAGE)
    @CustomCheck(uniqueUsername = "username", message = "既に登録されています")
    private String username;


    @NotBlank
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
     * Formクラスの設定内容を文字列で出力する.
     */
    @Override
    public String toString() {
        return "SiteUser(username: " + this.getUsername() + ", password: " + this.getPassword()
        +", email: " + this.getEmail() +", role: " + Role.USER.toString() 
        + ", isAdmin: " + this.isAdmin() +", isActive: " + this.isActive() +")";
    }

    /**
     * Formの設定内容をUser Entityクラスに変換する.
     *
      * @return ユーザー情報(Entityクラス)
     */
    public SiteUser toEntity(){

        SiteUser user = new SiteUser();
        user.setUsername(this.getUsername());
        user.setEmail(this.getEmail());
        user.setPassword(this.getPassword());
        Timestamp current_time = new Timestamp(System.currentTimeMillis());
        user.setCreated_at(current_time);
        user.setUpdated_at(current_time);
        user.setRole(Role.USER.toString());
        user.setAdmin(false);
        user.setActive(this.isActive());
        return user;
    }
}
