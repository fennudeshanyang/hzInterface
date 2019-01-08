package cn.zrar.hzjk.pojo;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Table(name="INTERFACE_LDDJ")
public class InterfaceLddj {

    @Transient
    private InterfaceXfj xfj;
    @Transient
    private InterfaceXfr xfr;
    //@Transient
    //private InterfaceBlfs blfs;
    //private InterfacePjxx pjxx;
    @Transient
    private List<InterfaceBlfs> blfss;
    

    //共36个字段，其中，LASTMODIFYTIME(最后修改时间)和SFSCSJ(是否上传省局)两个字段不用管，即是需要给34个字段赋值
    /**
     *  1.行政区划代码
     */
    private String xzqh;

    /**
     *  2.主键ID
     */
    private String storeid;

    /**
     *  3.来电编号
     */
    private String xfjbh;

    /**
     *  4.来电形式
     */
    private String xfxs;

    /**
     *  5.工号
     */
    private String gh;

    /**
     *  6.坐席人姓名
     */
    private String djr;

    /**
     *  7.来电时间
     */
    private String djsj;

    /**
     *  8.来电人姓名
     */
    private String xm;

    /**
     *  9.问题属地
     */
    private String wtsd;

    /**
     *  10.来电号码
     */
    private String ldhm;

    /**
     *  11.联系电话
     */
    private String lxdh;

    /**
     *  12.性别
     */
    private String xb;

    /**
     *  13.联系地址
     */
    private String czdz;

    /**
     * 14.来电目的
     */
    private String xfmd;

    /**
     * 15.内容分类
     */
    private String nrfl;

    /**
     * 16.优先级
     */
    private String yxj;

    /**
     * 17.限办时间
     */
    private String xbsj;

    /**
     *  18.初重件标识
     */
    private String qgccxfbz;

    /**
     *  19.是否信访件
     */
    private String sfxfj;

    /**
     *  20.是否公开
     */
    private String sfgk;

    /**
     *  21.是否关联电话录音
     */
    private String sfgldhly;

    /**
     *  22.录音文件地址
     */
    private String xgfj;

    /**
     * 23.来电内容
     */
    private String fynr;

    /**
     *  24.处理结果
     */
    private String clqk;

    /**
     *  25.证件类型
     */
    private String zjlx;

    /**
     *  26.证件号码
     */
    private String zjhm;

    /**
     *  27.标题
     */
    private String bt;

    /**
     *  28.来电状态
     */
    private String ldzt;

    /**
     *  29.查询码
     */
    private String cxm;

    /**
     *  32.被呼叫号码
     */
    private String bhjhm;

//===========================================================
    /**
     *  30.来电编号（格式转换后）
     */
    private String xfjbhNew;

    /**
     *  31.联合信访件id
     */
    private String lhxfjid;


    /**
     * 33.
     */
    private String issecret;

    /**
     * 34.数据最后修改时间，对于接口来说，就是插入数据的时间
     */
    private Date lastmodifytime;

    /**
     *  35.是否上传省局
     */
    private String sfscsj;

    /**
     *  36.省局编号(HZSDH年0000000)
     */
    private String sxfjbh;


    public String getXzqh() {
        return xzqh;
    }

    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }

    public String getStoreid() {
        return storeid;
    }

    public void setStoreid(String storeid) {
        this.storeid = storeid;
    }

    public String getXfjbh() {
        return xfjbh;
    }

    public void setXfjbh(String xfjbh) {
        this.xfjbh = xfjbh;
    }

    public String getXfxs() {
        return xfxs;
    }

    public void setXfxs(String xfxs) {
        this.xfxs = xfxs;
    }

    public String getGh() {
        return gh;
    }

    public void setGh(String gh) {
        this.gh = gh;
    }

    public String getDjr() {
        return djr;
    }

    public void setDjr(String djr) {
        this.djr = djr;
    }

    public String getDjsj() {
        return djsj;
    }

    public void setDjsj(String djsj) {
        this.djsj = djsj;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getWtsd() {
        return wtsd;
    }

    public void setWtsd(String wtsd) {
        this.wtsd = wtsd;
    }

    public String getLdhm() {
        return ldhm;
    }

    public void setLdhm(String ldhm) {
        this.ldhm = ldhm;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getCzdz() {
        return czdz;
    }

    public void setCzdz(String czdz) {
        this.czdz = czdz;
    }

    public String getXfmd() {
        return xfmd;
    }

    public void setXfmd(String xfmd) {
        this.xfmd = xfmd;
    }

    public String getNrfl() {
        return nrfl;
    }

    public void setNrfl(String nrfl) {
        this.nrfl = nrfl;
    }

    public String getYxj() {
        return yxj;
    }

    public void setYxj(String yxj) {
        this.yxj = yxj;
    }

    public String getXbsj() {
        return xbsj;
    }

    public void setXbsj(String xbsj) {
        this.xbsj = xbsj;
    }

    public String getQgccxfbz() {
        return qgccxfbz;
    }

    public void setQgccxfbz(String qgccxfbz) {
        this.qgccxfbz = qgccxfbz;
    }

    public String getSfxfj() {
        return sfxfj;
    }

    public void setSfxfj(String sfxfj) {
        this.sfxfj = sfxfj;
    }

    public String getSfgk() {
        return sfgk;
    }

    public void setSfgk(String sfgk) {
        this.sfgk = sfgk;
    }

    public String getSfgldhly() {
        return sfgldhly;
    }

    public void setSfgldhly(String sfgldhly) {
        this.sfgldhly = sfgldhly;
    }

    public String getXgfj() {
        return xgfj;
    }

    public void setXgfj(String xgfj) {
        this.xgfj = xgfj;
    }

    public String getFynr() {
        return fynr;
    }

    public void setFynr(String fynr) {
        this.fynr = fynr;
    }

    public String getClqk() {
        return clqk;
    }

    public void setClqk(String clqk) {
        this.clqk = clqk;
    }

    public String getZjlx() {
        return zjlx;
    }

    public void setZjlx(String zjlx) {
        this.zjlx = zjlx;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getBt() {
        return bt;
    }

    public void setBt(String bt) {
        this.bt = bt;
    }

    public String getLdzt() {
        return ldzt;
    }

    public void setLdzt(String ldzt) {
        this.ldzt = ldzt;
    }

    public String getCxm() {
        return cxm;
    }

    public void setCxm(String cxm) {
        this.cxm = cxm;
    }

    public String getXfjbhNew() {
        return xfjbhNew;
    }

    public void setXfjbhNew(String xfjbhNew) {
        this.xfjbhNew = xfjbhNew;
    }

    public String getLhxfjid() {
        return lhxfjid;
    }

    public void setLhxfjid(String lhxfjid) {
        this.lhxfjid = lhxfjid;
    }

    public String getBhjhm() {
        return bhjhm;
    }

    public void setBhjhm(String bhjhm) {
        this.bhjhm = bhjhm;
    }

    public String getIssecret() {
        return issecret;
    }

    public void setIssecret(String issecret) {
        this.issecret = issecret;
    }

    public Date getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }

    public String getSfscsj() {
        return sfscsj;
    }

    public void setSfscsj(String sfscsj) {
        this.sfscsj = sfscsj;
    }

    public String getSxfjbh() {
        return sxfjbh;
    }

    public void setSxfjbh(String sxfjbh) {
        this.sxfjbh = sxfjbh;
    }

    public InterfaceXfj getXfj() {
        return xfj;
    }

    public void setXfj(InterfaceXfj xfj) {
        this.xfj = xfj;
    }

    public InterfaceXfr getXfr() {
        return xfr;
    }

    public void setXfr(InterfaceXfr xfr) {
        this.xfr = xfr;
    }

    public List<InterfaceBlfs> getBlfss() {
        return blfss;
    }

    public void setBlfss(List<InterfaceBlfs> blfss) {
        this.blfss = blfss;
    }
}