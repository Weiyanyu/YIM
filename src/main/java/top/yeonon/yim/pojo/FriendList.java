package top.yeonon.yim.pojo;

public class FriendList {
    private Long id;

    private Long userId;

    private Long friendId;

    private String friendUsername;

    public FriendList(Long id, Long userId, Long friendId, String friendUsername) {
        this.id = id;
        this.userId = userId;
        this.friendId = friendId;
        this.friendUsername = friendUsername;
    }

    public FriendList() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public String getFriendUsername() {
        return friendUsername;
    }

    public void setFriendUsername(String friendUsername) {
        this.friendUsername = friendUsername == null ? null : friendUsername.trim();
    }
}