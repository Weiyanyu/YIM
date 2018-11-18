package top.yeonon.yim.pojo;

public class User {
    private Long id;

    private String username;

    private String password;

    private Long friendListId;

    public User(Long id, String username, String password, Long friendListId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.friendListId = friendListId;
    }

    public User() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Long getFriendListId() {
        return friendListId;
    }

    public void setFriendListId(Long friendListId) {
        this.friendListId = friendListId;
    }
}