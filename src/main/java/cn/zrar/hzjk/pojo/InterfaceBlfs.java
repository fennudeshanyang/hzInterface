package cn.zrar.hzjk.pojo;

import javax.persistence.Table;

@Table(name="INTERFACE_BLFS")
public class InterfaceBlfs {

    //共21个字段
    /**
     * 1.行政区划代码
     */
    private String xzqh;

    /**
     * 2.办理方式ID
     */
    private String blfsid;

    /**
     * 3.信访件id
     */
    private String xfjid;

    /**
     * 4.办理方式
     */
    private String blfs;

    /**
     * 5.办理机构
     */
    private String bljg;

    /**
     * 6.办理机构代码
     */
    private String bljgdm;

    /**
     * 7.办理部门
     */
    private String blbm;

    /**
     * 8.经办人
     */
    private String jbr;

    /**
     * 9.办理时间
     */
    private String blsj;

    /**
     * 10.去向机构
     */
    private String qxjg;

    /**
     * 11.去向机构代码
     */
    private String qxjgdm;

    /**
     * 12.小去向名称
     */
    private String xqxmc;

    /**
     * 13.回复告知内容
     */
    private String hfgznr;

    /**
     * 19.限期汇报时间
     */
    private String xqhbsj;

//====================================================
    /**
     * 14.办理方式附件描述信息
     */
    private String fj;

    /**
     * 15.联合信访件id
     */
    private String lhxfjid;

    /**
     * 16.
     */
    private String sfyth;

    /**
     * 17.
     */
    private String issd;

    /**
     * 18.
     */
    private String dftype;

    /**
     * 20.交办人id
     */
    private String jbrid;


    public String getXzqh() {
        return xzqh;
    }

    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }

    public String getBlfsid() {
        return blfsid;
    }

    public void setBlfsid(String blfsid) {
        this.blfsid = blfsid;
    }

    public String getXfjid() {
        return xfjid;
    }

    public void setXfjid(String xfjid) {
        this.xfjid = xfjid;
    }

    public String getBlfs() {
        return blfs;
    }

    public void setBlfs(String blfs) {
        this.blfs = blfs;
    }

    public String getBljg() {
        return bljg;
    }

    public void setBljg(String bljg) {
        this.bljg = bljg;
    }

    public String getBljgdm() {
        return bljgdm;
    }

    public void setBljgdm(String bljgdm) {
        this.bljgdm = bljgdm;
    }

    public String getBlbm() {
        return blbm;
    }

    public void setBlbm(String blbm) {
        this.blbm = blbm;
    }

    public String getJbr() {
        return jbr;
    }

    public void setJbr(String jbr) {
        this.jbr = jbr;
    }

    public String getBlsj() {
        return blsj;
    }

    public void setBlsj(String blsj) {
        this.blsj = blsj;
    }

    public String getQxjg() {
        return qxjg;
    }

    public void setQxjg(String qxjg) {
        this.qxjg = qxjg;
    }

    public String getQxjgdm() {
        return qxjgdm;
    }

    public void setQxjgdm(String qxjgdm) {
        this.qxjgdm = qxjgdm;
    }

    public String getXqxmc() {
        return xqxmc;
    }

    public void setXqxmc(String xqxmc) {
        this.xqxmc = xqxmc;
    }

    public String getHfgznr() {
        return hfgznr;
    }

    public void setHfgznr(String hfgznr) {
        this.hfgznr = hfgznr;
    }

    public String getFj() {
        return fj;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }

    public String getLhxfjid() {
        return lhxfjid;
    }

    public void setLhxfjid(String lhxfjid) {
        this.lhxfjid = lhxfjid;
    }

    public String getSfyth() {
        return sfyth;
    }

    public void setSfyth(String sfyth) {
        this.sfyth = sfyth;
    }

    public String getIssd() {
        return issd;
    }

    public void setIssd(String issd) {
        this.issd = issd;
    }

    public String getDftype() {
        return dftype;
    }

    public void setDftype(String dftype) {
        this.dftype = dftype;
    }

    public String getXqhbsj() {
        return xqhbsj;
    }

    public void setXqhbsj(String xqhbsj) {
        this.xqhbsj = xqhbsj;
    }

    public String getJbrid() {
        return jbrid;
    }

    public void setJbrid(String jbrid) {
        this.jbrid = jbrid;
    }
}