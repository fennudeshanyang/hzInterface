package cn.zrar.hzjk.pojo;

import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name="XF_SJ_BLRESULT")
public class SjBlResult {

    /**
     * 1.主键
     */
    private String id;

    /**
     * 2.信访件ID
     */
    private String xfjid;

    /**
     * 3.信访人ID
     */
    private String xfrid;

    /**
     * 4.信访件编号
     */
    private String xfjbh;

    /**
     * 5.信访人姓名
     */
    private String xm;

    /**
     * 6.手机号码
     */
    private String sjhm;

    /**
     * 7.证件类型
     */
    private String zjlx;

    /**
     * 8.证件号码
     */
    private String zjhm;

    /**
     * 9.地址代码
     */
    private String dzdm;

    /**
     * 10.地址名称
     */
    private String dz;

    /**
     * 11.问题属地代码
     */
    private String wtfsdid;

    /**
     * 12.问题属地名称
     */
    private String wtsd;

    /**
     * 13.登记时间
     */
    private Date djsj;

    /**
     * 14.信访形式
     */
    private String xfxs;

    /**
     * 15.内容分类
     */
    private String nrfl;

    /**
     * 16.概况
     */
    private String gk;

    /**
     * 17.信访件处理状态
     */
    private String xfjclzt;

    /**
     * 18.最后一次办理方式
     */
    private String blfs;

    /**
     * 19.最后一次办理单位
     */
    private String bldw;

    /**
     * 20.最后一次办理时间
     */
    private String blsj;

    /**
     * 21.最后一次办理说明(即：办理结果详情)
     */
    private String blsm;

    /**
     * 22.答复类型(0:告知类，1：答复类)
     */
    private String dftype;

    /**
     * 23.数据应回传哪个区县或杭州市(hzs:杭州市  yh:余杭  xs:萧山  la:临安  fy:富阳  tl:桐庐  ca:淳安  jd:建德)
     */
    private String sjss;

    /**
     * 24.数据交互状态----中间库将数据传给区县或业务库的状态 默认0：待交互   1-9 交互次数     20：已交互， 30：交互过程中发生主键冲突，40：交互失败
     */
    @Transient
    private Integer sjjhzt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXfjid() {
        return xfjid;
    }

    public void setXfjid(String xfjid) {
        this.xfjid = xfjid;
    }

    public String getXfrid() {
        return xfrid;
    }

    public void setXfrid(String xfrid) {
        this.xfrid = xfrid;
    }

    public String getXfjbh() {
        return xfjbh;
    }

    public void setXfjbh(String xfjbh) {
        this.xfjbh = xfjbh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getSjhm() {
        return sjhm;
    }

    public void setSjhm(String sjhm) {
        this.sjhm = sjhm;
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

    public String getDzdm() {
        return dzdm;
    }

    public void setDzdm(String dzdm) {
        this.dzdm = dzdm;
    }

    public String getDz() {
        return dz;
    }

    public void setDz(String dz) {
        this.dz = dz;
    }

    public String getWtfsdid() {
        return wtfsdid;
    }

    public void setWtfsdid(String wtfsdid) {
        this.wtfsdid = wtfsdid;
    }

    public String getWtsd() {
        return wtsd;
    }

    public void setWtsd(String wtsd) {
        this.wtsd = wtsd;
    }

    public Date getDjsj() {
        return djsj;
    }

    public void setDjsj(Date djsj) {
        this.djsj = djsj;
    }

    public String getXfxs() {
        return xfxs;
    }

    public void setXfxs(String xfxs) {
        this.xfxs = xfxs;
    }

    public String getNrfl() {
        return nrfl;
    }

    public void setNrfl(String nrfl) {
        this.nrfl = nrfl;
    }

    public String getGk() {
        return gk;
    }

    public void setGk(String gk) {
        this.gk = gk;
    }

    public String getXfjclzt() {
        return xfjclzt;
    }

    public void setXfjclzt(String xfjclzt) {
        this.xfjclzt = xfjclzt;
    }

    public String getBlfs() {
        return blfs;
    }

    public void setBlfs(String blfs) {
        this.blfs = blfs;
    }

    public String getBldw() {
        return bldw;
    }

    public void setBldw(String bldw) {
        this.bldw = bldw;
    }

    public String getBlsj() {
        return blsj;
    }

    public void setBlsj(String blsj) {
        this.blsj = blsj;
    }

    public String getBlsm() {
        return blsm;
    }

    public void setBlsm(String blsm) {
        this.blsm = blsm;
    }

    public String getDftype() {
        return dftype;
    }

    public void setDftype(String dftype) {
        this.dftype = dftype;
    }

    public String getSjss() {
        return sjss;
    }

    public void setSjss(String sjss) {
        this.sjss = sjss;
    }

    public Integer getSjjhzt() {
        return sjjhzt;
    }

    public void setSjjhzt(Integer sjjhzt) {
        this.sjjhzt = sjjhzt;
    }
}
