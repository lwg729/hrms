package com.lwg.hrms.model;

/**
 * @Author lwg
 * @Date 2020/12/15 9:52
 * @Version 1.0
 */
public class Meta {
    private Boolean requireAuth;

    private Boolean keepAlive;

    public Boolean getRequireAuth() {
        return requireAuth;
    }

    public void setRequireAuth(Boolean requireAuth) {
        this.requireAuth = requireAuth;
    }

    public Boolean getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    @Override
    public String toString() {
        return "com.lwg.hrms.model.Meta{" +
                "requireAuth=" + requireAuth +
                ", keepAlive=" + keepAlive +
                '}';
    }
}
