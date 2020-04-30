package com.win.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.sign.Credentials;

/**
 * @ClassName CosClientFactory
 * @Description TODO(腾讯云存储访问client工厂类)
 * @author huiziqin
 * @Date 2018年5月23日 上午11:23:02
 * @version 1.0.0
 */
public class CosClientFactory {

    private static COSClient cosClient = null;

    /**
     * 创建一个client访问对象
     */
    public static synchronized COSClient getClient() {
        if (cosClient == null) {
            init();
        }
        return cosClient;
    }

    /**
     * 初始化配置信息
     */
    private static void init() {
        CosConfig configValue = ApplicationContextUtil.getContext().getBean("cosConfig", CosConfig.class);
        // 初始化秘钥信息
        Credentials cred = new Credentials(configValue.getAppId(), configValue.getSecretId(),
                configValue.getSecretKey());
        // 初始化客户端配置
        ClientConfig clientConfig = new ClientConfig();
        // 设置bucket所在的区域，比如华南园区：gz； 华北园区：tj；华东园区：sh ；
        clientConfig.setRegion("gz");
        // 构造client
        cosClient = new COSClient(clientConfig, cred);
    }
}
