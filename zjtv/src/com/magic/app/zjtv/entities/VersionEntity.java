package com.magic.app.zjtv.entities;

import javax.persistence.*;

/**
 * Created by yunchunnan on 14-3-26.
 */
@Entity
@Table(name = "version", schema = "", catalog = "buddy_db")
public class VersionEntity {
    private Integer id;
    private String filename;
    private String platform;
    private Integer versionCode;
    private Integer majorVersion;
    private Integer minorVersion;
    private Integer revisionVersion;
    private Integer type;
    private String downloadUrl;

    private Integer appstoreOnline;
    private String appstoreDownloadurl;
    private Integer appstoreVersionCode;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Basic
    @Column(name = "platform")
    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Column(name = "version_code")
    public Integer getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(Integer versionCode) {
        this.versionCode = versionCode;
    }

    @Basic
    @Column(name = "major_version")
    public Integer getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(Integer majorVersion) {
        this.majorVersion = majorVersion;
    }

    @Basic
    @Column(name = "minor_version")
    public Integer getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(Integer minorVersion) {
        this.minorVersion = minorVersion;
    }

    @Basic
    @Column(name = "revision_version")
    public Integer getRevisionVersion() {
        return revisionVersion;
    }

    public void setRevisionVersion(Integer revisionVersion) {
        this.revisionVersion = revisionVersion;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "download_url")
    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    @Column(name = "appstore_online")
    public Integer getAppstoreOnline() {
        return appstoreOnline;
    }

    public void setAppstoreOnline(Integer appstoreOnline) {
        this.appstoreOnline = appstoreOnline;
    }

    @Column(name = "appstore_downloadurl")
    public String getAppstoreDownloadurl() {
        return appstoreDownloadurl;
    }

    public void setAppstoreDownloadurl(String appstoreDownloadurl) {
        this.appstoreDownloadurl = appstoreDownloadurl;
    }

    @Column(name = "appstore_version_code")
    public Integer getAppstoreVersionCode() {
        return appstoreVersionCode;
    }

    public void setAppstoreVersionCode(Integer appstoreVersionCode) {
        this.appstoreVersionCode = appstoreVersionCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VersionEntity that = (VersionEntity) o;

        if (downloadUrl != null ? !downloadUrl.equals(that.downloadUrl) : that.downloadUrl != null) return false;
        if (majorVersion != null ? !majorVersion.equals(that.majorVersion) : that.majorVersion != null) return false;
        if (minorVersion != null ? !minorVersion.equals(that.minorVersion) : that.minorVersion != null) return false;
        if (platform != null ? !platform.equals(that.platform) : that.platform != null) return false;
        if (revisionVersion != null ? !revisionVersion.equals(that.revisionVersion) : that.revisionVersion != null)
            return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (platform != null ? platform.hashCode() : 0);
        result = 31 * result + (majorVersion != null ? majorVersion.hashCode() : 0);
        result = 31 * result + (minorVersion != null ? minorVersion.hashCode() : 0);
        result = 31 * result + (revisionVersion != null ? revisionVersion.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (downloadUrl != null ? downloadUrl.hashCode() : 0);
        return result;
    }
}
