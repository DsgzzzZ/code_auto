package com.win.exception;

/**
 * @ClassName SqlLinkException
 * @Description TODO(数据库连接错误)
 * @author huiziqin
 * @Date 2018年4月20日 下午4:28:56
 * @version 1.0.0
 */
public class SqlLinkException extends Exception {

    /**
     * @Field @serialVersionUID : TODO(设置)
     */
    private static final long serialVersionUID = 5263360752840244704L;

    // 异常信息
    public String message;

    public SqlLinkException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
