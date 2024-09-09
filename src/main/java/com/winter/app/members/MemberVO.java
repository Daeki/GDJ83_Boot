package com.winter.app.members;

import java.sql.Date;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberVO {
	
	@NotBlank
	private String username;
	
	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*\\\\\\\\W)(?=\\\\\\\\S+$).{6,12}")
	private String password;
	
	private String passwordCheck;
	
	@NotBlank
	private String name;
	@Email
	private String email;
	@Past
	private Date birth;
	private boolean enabled;
	private List<RoleVO> vos;

}
