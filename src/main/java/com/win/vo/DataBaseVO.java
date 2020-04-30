package com.win.vo;

/**
 * @ClassName DataBaseVO
 * @Description TODO(DataBaseVO)
 * @author huiziqin
 * @Date 2018年4月20日 下午3:22:09
 * @version 1.0.0
 */
public class DataBaseVO {

    /**
     * 链接地址
     */
    private String url;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 数据库驱动
     */
    private String driveName;

    /**
     * 数据库名
     */
    private String dataBaseName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = "jdbc:mysql://" + url+"/";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getDriveName() {
        setDriveName("com.mysql.jdbc.Driver");
        return driveName;
    }

    private void setDriveName(String driveName) {
        this.driveName = driveName;
    }

}
