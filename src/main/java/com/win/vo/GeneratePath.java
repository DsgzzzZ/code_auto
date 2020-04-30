package com.win.vo;

import org.apache.commons.lang3.StringUtils;

public class GeneratePath {

    /**
     * 根目录
     */
    public  String sourceRootPackage = "src";

    /**
     * 包路径
     */
    public  String bussiPackage;

    /**
     * 实体类路径
     */
    public  String entityPackage = "entity";

    /**
     * vo Package
     */
    public  String voPackage = "vo";

    /**
     * 保存路径
     */
    public  String savePath;

    /**
     * 包路径
     */
    public  String bussiPackageUrl;

    /**
     * 实体类路径
     */
    public  String entityPackageUrl;

    /**
     * vo Package
     */
    public  String voPackageUrl;

    /**
     * 保存路径
     */
    public  String savePathUrl;

    /**
     * 默认用户名
     */
    public  String defaultUserName = "lihaixin";

    public  String getSourceRootPackage() {
        return sourceRootPackage;
    }

    public  void setSourceRootPackage(String sourceRootPackage) {
        this.sourceRootPackage = sourceRootPackage;
    }

    public  String getBussiPackage() {
        return bussiPackage;
    }

    public  void setBussiPackage(String bussiPackage) {
        this.bussiPackage = bussiPackage;
        this.bussiPackageUrl=bussiPackage.replace(".", "/");
    }

    public  String getEntityPackage() {
        return entityPackage;
    }

    public  void setEntityPackage(String entityPackage) {
        this.entityPackage = entityPackage;
    }

    public  String getVoPackage() {
        return voPackage;
    }

    public  void setVoPackage(String voPackage) {
        this.voPackage = voPackage;
    }

    public  String getSavePath() {
        return savePath;
    }

    public  void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public  String getBussiPackageUrl() {
        return bussiPackageUrl;
    }

    public  void setBussiPackageUrl(String bussiPackageUrl) {
        this.bussiPackageUrl = bussiPackageUrl;
    }

    public  String getEntityPackageUrl() {
        if(StringUtils.isBlank(entityPackageUrl)) {
            entityPackageUrl=entityPackage;
        }
        return entityPackageUrl;
    }

    public  void setEntityPackageUrl(String entityPackageUrl) {
        this.entityPackageUrl = entityPackageUrl;
    }

    public  String getVoPackageUrl() {
        if(StringUtils.isBlank(voPackageUrl)) {
            voPackageUrl=voPackage;
        }
        return voPackageUrl;
    }

    public  void setVoPackageUrl(String voPackageUrl) {
        this.voPackageUrl = voPackageUrl;
    }

    public  String getSavePathUrl() {
        return savePathUrl;
    }

    public  void setSavePathUrl(String savePathUrl) {
        this.savePathUrl = savePathUrl;
    }

    public  String getDefaultUserName() {
        return defaultUserName;
    }

    public  void setDefaultUserName(String defaultUserName) {
        this.defaultUserName = defaultUserName;
    }
}
