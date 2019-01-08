package cn.zrar.hzjk.pojo;

import javax.persistence.Table;

@Table(name="INTERFACE_XFR")
public class InterfaceXfr {

    //共21个字段
    /**
     * 1.数据发送方行政区划代码
     */
    private String xzqh;

    /**
     * 2.信访人id
     */
    private String xfrid;

    /**
     * 3.信访件id
     */
    private String xfjid;

    /**
     * 4.姓名
     */
    private String xm;

    /**
     * 5.性别
     */
    private String xb;

    /**
     * 6.民族
     */
    private String mz;

    /**
     * 7.住址
     */
    private String zz;

    /**
     * 8.住址代码
     */
    private String zzdm;

    /**
     * 9.证件类型
     */
    private String zjlx;

    /**
     * 10.证件号码
     */
    private String zjhm;

    /**
     * 11.手机号
     */
    private String sjh;

    /**
     * 12.户口所在地
     */
    private String hkszd;

    /**
     * 13.通讯地址
     */
    private String txdz;

    /**
     * 14.工作单位
     */
    private String gzdw;

    /**
     * 15.邮政编码
     */
    private String yzbm;

    /**
     * 16.固定电话
     */
    private String gddh;

    /**
     * 17.电子邮件
     */
    private String dzyj;

    /**
     * 18.出生日期
     */
    private String csrq;

    /**
     * 19.职业
     */
    private String zy;

    /**
     * 20.信访件主访人标志
     */
    private String xfjzfrbz;


    //=============================================

    /**
     * 21.联合信访件id
     */
    private String lhxfjid;


    public String getXzqh() {
        return xzqh;
    }

    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }

    public String getXfrid() {
        return xfrid;
    }

    public void setXfrid(String xfrid) {
        this.xfrid = xfrid;
    }

    public String getXfjid() {
        return xfjid;
    }

    public void setXfjid(String xfjid) {
        this.xfjid = xfjid;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    public String getZz() {
        return zz;
    }

    public void setZz(String zz) {
        this.zz = zz;
    }

    public String getZzdm() {
        return zzdm;
    }

    public void setZzdm(String zzdm) {
        this.zzdm = zzdm;
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

    public String getSjh() {
        return sjh;
    }

    public void setSjh(String sjh) {
        this.sjh = sjh;
    }

    public String getHkszd() {
        return hkszd;
    }

    public void setHkszd(String hkszd) {
        this.hkszd = hkszd;
    }

    public String getTxdz() {
        return txdz;
    }

    public void setTxdz(String txdz) {
        this.txdz = txdz;
    }

    public String getGzdw() {
        return gzdw;
    }

    public void setGzdw(String gzdw) {
        this.gzdw = gzdw;
    }

    public String getYzbm() {
        return yzbm;
    }

    public void setYzbm(String yzbm) {
        this.yzbm = yzbm;
    }

    public String getGddh() {
        return gddh;
    }

    public void setGddh(String gddh) {
        this.gddh = gddh;
    }

    public String getDzyj() {
        return dzyj;
    }

    public void setDzyj(String dzyj) {
        this.dzyj = dzyj;
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public String getZy() {
        return zy;
    }

    public void setZy(String zy) {
        this.zy = zy;
    }

    public String getXfjzfrbz() {
        return xfjzfrbz;
    }

    public void setXfjzfrbz(String xfjzfrbz) {
        this.xfjzfrbz = xfjzfrbz;
    }

    public String getLhxfjid() {
        return lhxfjid;
    }

    public void setLhxfjid(String lhxfjid) {
        this.lhxfjid = lhxfjid;
    }
}