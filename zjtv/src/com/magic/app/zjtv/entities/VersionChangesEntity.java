package com.magic.app.zjtv.entities;

import javax.persistence.*;

/**
 * Created by YinJianFeng on 14-5-18.
 */
@Entity
@Table(name = "version_changes", schema = "", catalog = "buddy_db")
public class VersionChangesEntity {
    private Integer id;
    private String platform;
    private Integer versionCode;
    private String content;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "platform")
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Basic
    @Column(name = "version_code")
    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VersionChangesEntity that = (VersionChangesEntity) o;

        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (platform != null ? !platform.equals(that.platform) : that.platform != null) return false;
        if (versionCode != null ? !versionCode.equals(that.versionCode) : that.versionCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (platform != null ? platform.hashCode() : 0);
        result = 31 * result + (versionCode != null ? versionCode.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
