package cn.zrar.hzjk.pojo;

import java.util.List;
import java.util.Map;

public class JsonResult {

    /**
     * 保存成功的数据主键，省信访件编号用英文字符状态下的 逗号 ,  分隔拼接而成的字符串
     */
    private String successStr;
    /**
     * 省信访件编号已在中间库存在的数据，即主键冲突，用 英文字符状态下的 逗号 ,  分隔拼接而成的字符串
     */
    private String conflictStr;
    /**
     * 在保存数据时候出现未知错误的数据，即保存时候抛出了异常的数据
     */
    //private String failStr;

    /**
     * 保存数据时，保存成功和主键冲突以外的数据，会存放在此
     */
    private List<FailSituation> failSituationList;

    /**
     * 应答代码
     */
    private String code;
    /**
     * 响应描述信息
     */
    private String msg;

    public String getSuccessStr() {
        return successStr;
    }

    public void setSuccessStr(String successStr) {
        this.successStr = successStr;
    }

    public String getConflictStr() {
        return conflictStr;
    }

    public void setConflictStr(String conflictStr) {
        this.conflictStr = conflictStr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<FailSituation> getFailSituationList() {
        return failSituationList;
    }

    public void setFailSituationList(List<FailSituation> failSituationList) {
        this.failSituationList = failSituationList;
    }
}
