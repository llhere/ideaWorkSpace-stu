package teach.code.model;

import java.io.Serializable;

/**
 * 用户
 * 
 * @author ggj
 * 
 */
public class User  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	// 账号
	private String account;
	// 账号密码
	private String password;
	// 用户名
	private String userName;
	// 用户身份
	private String identity;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String account, String password, String userName,
			String identity) {
		super();
		this.id = id;
		this.account = account;
		this.password = password;
		this.userName = userName;
		this.identity = identity;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

}
