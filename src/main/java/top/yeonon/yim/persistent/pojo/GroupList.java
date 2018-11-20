package top.yeonon.yim.persistent.pojo;

public class GroupList {
    private Long id;

    private Long groupId;

    private Long userId;

    private String username;

    public GroupList(Long id, Long groupId, Long userId, String username) {
        this.id = id;
        this.groupId = groupId;
        this.userId = userId;
        this.username = username;
    }

    public GroupList() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}