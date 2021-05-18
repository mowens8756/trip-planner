package com.example.demo.form;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.example.demo.util.Role;
import com.example.demo.util.Status;

import lombok.Getter;
import lombok.Setter;

/**
 * ユーザーEntityクラスのFormクラス.
 */
@Getter
@Setter
public class UserBaseForm implements Serializable {

	/**　シリアルバージョンUID. */
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(max=30)
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    private String role;
    private boolean isAdmin;
    private boolean isActive;
    
    /**
     * ロールの選択肢の定数を取得するメソッド.
     * @return 選択肢の定数
     */
    public Role getRoleItems(){
    	for(Role role : Role.values()) {
    		return role;
    	}
    	return null;
    }

    /**
     * 有効/無効の選択肢の定数を取得するメソッド.
     * @return 選択肢の定数
     */
    public Status getStatusItems(){
    	for(Status status : Status.values()) {
    		return status;
    	}
    	return null;
    }
}