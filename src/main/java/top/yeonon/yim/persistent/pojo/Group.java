package top.yeonon.yim.persistent.pojo;

public class Group {
    private Long id;

    private String groupName;

    public Group(Long id, String groupName) {
        this.id = id;
        this.groupName = groupName;
    }

    public Group() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }
}