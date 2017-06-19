package OutgoingRequests;

public final class LoginRequest {
    public final String Username;
    public final String Password;

    public LoginRequest(String username, String password) {
        Username = username;
        Password = password;
    }
}
