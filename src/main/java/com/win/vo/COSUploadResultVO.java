package com.win.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @ClassName COSUploadResultVO
 * @Description TODO(腾讯云存储上传返回结果)
 * @author huiziqin
 * @Date 2018年5月23日 上午11:26:11
 * @version 1.0.0
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class COSUploadResultVO {

    public static final int CODE_SUCCESS = 0;

    private int code;
    private String message;
    @JsonProperty(value = "request_id")
    private String requestId;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataBean {

        /**
         * accessUrl : http://cxsztest-1254180175.file.myqcloud.com/img/0e8c642c-cd18-4836-9efa-f55eff4468be.jpg
         * resourcePath : /1254180175/cxsztest/img/0e8c642c-cd18-4836-9efa-f55eff4468be.jpg sourceUrl :
         * http://cxsztest-1254180175.cosgz.myqcloud.com/img/0e8c642c-cd18-4836-9efa-f55eff4468be.jpg url :
         * http://gz.file.myqcloud.com/files/v2/1254180175/cxsztest/img/0e8c642c-cd18-4836-9efa-f55eff4468be.jpg vid :
         * aabf8c8a307e32b51ac53dac02f25d801510022437
         */

        @JsonProperty("access_url")
        private String accessUrl;
        @JsonProperty("resource_path")
        private String resourcePath;
        @JsonProperty("source_url")
        private String sourceUrl;
        private String url;
        private String vid;

        public String getAccessUrl() {
            return accessUrl;
        }

        public void setAccessUrl(String accessUrl) {
            this.accessUrl = accessUrl;
        }

        public String getResourcePath() {
            return resourcePath;
        }

        public void setResourcePath(String resourcePath) {
            this.resourcePath = resourcePath;
        }

        public String getSourceUrl() {
            return sourceUrl;
        }

        public void setSourceUrl(String sourceUrl) {
            this.sourceUrl = sourceUrl;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }
    }
}
