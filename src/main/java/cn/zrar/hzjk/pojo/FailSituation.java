package cn.zrar.hzjk.pojo;

public class FailSituation {
    /**
     * 保存失败数据的主键
     */
    private String failId;
    /**
     * 保存失败数据的原因
     */
    private String failReason;

    public String getFailId() {
        return failId;
    }

    public void setFailId(String failId) {
        this.failId = failId;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }
}
