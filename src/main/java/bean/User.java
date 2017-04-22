package bean;

public class User {
	private String username;

    private String password;
    
    private int UserId;
    
    private String PhoneNumber;
    private String UserLocation;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getUserLocation() {
		return UserLocation;
	}

	public void setUserLocation(String usedLocation) {
		UserLocation = usedLocation;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}
}
