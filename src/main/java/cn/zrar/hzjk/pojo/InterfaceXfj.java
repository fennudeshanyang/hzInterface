package cn.zrar.hzjk.pojo;

import javax.persistence.Table;
import java.util.Date;

@Table(name="INTERFACE_XFJ")
public class InterfaceXfj {

    //共51个字段，其中，LASTMODIFYTIME(最后修改时间)字段不用管，即是需要给50个字段赋值
    /**
     * 1.行政区划代码
     */
    private String xzqh;

    /**
     * 2.信访件id
     */
    private String xfjid;

    /**
     * 3.信访件编号
     */
    private String xfjbh;

    /**
     * 4.查询码状态
     */
    private String cxmzt;

    /**
     * 5.查询码
     */
    private String cxm;

    /**
     * 6.信访日期
     */
    private String xfrq;

    /**
     * 7.信访形式
     */
    private String xfxs;

    /**
     * 8.信访人数
     */
    private String xfrs;

    /**
     * 9.涉及人数
     */
    private String sjrs;

    /**
     * 10.登记机构名称
     */
    private String djjgmc;

    /**
     * 11.登记机构代码
     */
    private String djjgdm;

    /**
     * 12.登记部门
     */
    private String djbm;

    /**
     * 13.登记人
     */
    private String djr;

    /**
     * 14.登记时间
     */
    private String djsj;

    /**
     * 15.概况信息
     */
    private String gkxx;

    /**
     * 16.投诉内容
     */
    private String tsnr;

    /**
     * 17.受信人
     */
    private String sxr;

    /**
     * 18.信访件状态
     */
    private String xfjzt;

    /**
     * 19.本机构初次标志
     */
    private String bjgccbz;

    /**
     * 20.全国初次标志
     */
    private String qgccbz;

    /**
     * 21.重复信访标志
     */
    private String cfxfbz;

    /**
     * 22.问题属地代码
     */
    private String wtsddm;

    /**
     * 23.内容分类代码
     */
    private String nrfldm;

    /**
     * 24.信访目的代码
     */
    private String xfmddm;

    /**
     * 25.信访原因代码
     */
    private String xfyydm;

    /**
     * 26.首次信访机构
     */
    private String scxfjg;

    /**
     * 27.首次信访日期
     */
    private String scxfrq;

    /**
     * 28.三跨三分离标志
     */
    private String sksflbz;

    /**
     * 29.案标志
     */
    private String jabz;

    /**
     * 30.复查标志
     */
    private String fcbz;

    /**
     * 31.复核标志
     */
    private String fhbz;

    /**
     * 32.办结标志
     */
    private String bjbz;

    /**
     * 33.审核认定办结标志
     */
    private String shrdbjbz;

    /**
     * 34.是否联名
     */
    private String lmbz;

    /**
     * 35.是否同意公开
     */
    private String gkbz;

    /**
     * 36.重大紧急标志
     */
    private String zdjjbz;

    /**
     * 37.匿名标志
     */
    private String nmbz;

    /**
     * 38.是否扬言
     */
    private String yybz;

    /**
     * 39.热点问题
     */
    private String rdwt;

    /**
     * 40.所属系统
     */
    private String ssxt;

    /**
     * 41.限办截止时间
     */
    private String xbjzsj;

    /**
     * 42.信访件附件描述信息
     */
    private String xgfj;

    /**
     * 43.被反映人级别
     */
    private String bfyrjb;

    /**
     * 44.被反映人职务
     */
    private String bfyrzw;

    /**
     * 45.被反映人住址代码
     */
    private String bfyrzzdm;

    /**
     * 46.被反映人住址名称
     */
    private String bfyrzzmc;

    /**
     * 47.被反映人或单位
     */
    private String bfyrhdw;

    /**
     * 48.办理程序
     */
    private String blcx;


    //====================================================

    /**
     * 49.信访件编号（格式转换后）
     */
    private String xfjbhNew;

    /**
     * 50.联合信访件id
     */
    private String lhxfjid;

    /**
     * 51.数据最后修改时间，对于接口来说，就是插入数据的时间
     */
    private Date lastmodifytime;

    /**
     *  52.登记人id
     */
    private String djrid;

    public String getXzqh() {
        return xzqh;
    }

    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }

    public String getXfjid() {
        return xfjid;
    }

    public void setXfjid(String xfjid) {
        this.xfjid = xfjid;
    }

    public String getXfjbh() {
        return xfjbh;
    }

    public void setXfjbh(String xfjbh) {
        this.xfjbh = xfjbh;
    }

    public String getCxmzt() {
        return cxmzt;
    }

    public void setCxmzt(String cxmzt) {
        this.cxmzt = cxmzt;
    }

    public String getCxm() {
        return cxm;
    }

    public void setCxm(String cxm) {
        this.cxm = cxm;
    }

    public String getXfrq() {
        return xfrq;
    }

    public void setXfrq(String xfrq) {
        this.xfrq = xfrq;
    }

    public String getXfxs() {
        return xfxs;
    }

    public void setXfxs(String xfxs) {
        this.xfxs = xfxs;
    }

    public String getXfrs() {
        return xfrs;
    }

    public void setXfrs(String xfrs) {
        this.xfrs = xfrs;
    }

    public String getSjrs() {
        return sjrs;
    }

    public void setSjrs(String sjrs) {
        this.sjrs = sjrs;
    }

    public String getDjjgmc() {
        return djjgmc;
    }

    public void setDjjgmc(String djjgmc) {
        this.djjgmc = djjgmc;
    }

    public String getDjjgdm() {
        return djjgdm;
    }

    public void setDjjgdm(String djjgdm) {
        this.djjgdm = djjgdm;
    }

    public String getDjbm() {
        return djbm;
    }

    public void setDjbm(String djbm) {
        this.djbm = djbm;
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

    public String getGkxx() {
        return gkxx;
    }

    public void setGkxx(String gkxx) {
        this.gkxx = gkxx;
    }

    public String getTsnr() {
        return tsnr;
    }

    public void setTsnr(String tsnr) {
        this.tsnr = tsnr;
    }

    public String getSxr() {
        return sxr;
    }

    public void setSxr(String sxr) {
        this.sxr = sxr;
    }

    public String getXfjzt() {
        return xfjzt;
    }

    public void setXfjzt(String xfjzt) {
        this.xfjzt = xfjzt;
    }

    public String getBjgccbz() {
        return bjgccbz;
    }

    public void setBjgccbz(String bjgccbz) {
        this.bjgccbz = bjgccbz;
    }

    public String getQgccbz() {
        return qgccbz;
    }

    public void setQgccbz(String qgccbz) {
        this.qgccbz = qgccbz;
    }

    public String getCfxfbz() {
        return cfxfbz;
    }

    public void setCfxfbz(String cfxfbz) {
        this.cfxfbz = cfxfbz;
    }

    public String getWtsddm() {
        return wtsddm;
    }

    public void setWtsddm(String wtsddm) {
        this.wtsddm = wtsddm;
    }

    public String getNrfldm() {
        return nrfldm;
    }

    public void setNrfldm(String nrfldm) {
        this.nrfldm = nrfldm;
    }

    public String getXfmddm() {
        return xfmddm;
    }

    public void setXfmddm(String xfmddm) {
        this.xfmddm = xfmddm;
    }

    public String getXfyydm() {
        return xfyydm;
    }

    public void setXfyydm(String xfyydm) {
        this.xfyydm = xfyydm;
    }

    public String getScxfjg() {
        return scxfjg;
    }

    public void setScxfjg(String scxfjg) {
        this.scxfjg = scxfjg;
    }

    public String getScxfrq() {
        return scxfrq;
    }

    public void setScxfrq(String scxfrq) {
        this.scxfrq = scxfrq;
    }

    public String getSksflbz() {
        return sksflbz;
    }

    public void setSksflbz(String sksflbz) {
        this.sksflbz = sksflbz;
    }

    public String getJabz() {
        return jabz;
    }

    public void setJabz(String jabz) {
        this.jabz = jabz;
    }

    public String getFcbz() {
        return fcbz;
    }

    public void setFcbz(String fcbz) {
        this.fcbz = fcbz;
    }

    public String getFhbz() {
        return fhbz;
    }

    public void setFhbz(String fhbz) {
        this.fhbz = fhbz;
    }

    public String getBjbz() {
        return bjbz;
    }

    public void setBjbz(String bjbz) {
        this.bjbz = bjbz;
    }

    public String getShrdbjbz() {
        return shrdbjbz;
    }

    public void setShrdbjbz(String shrdbjbz) {
        this.shrdbjbz = shrdbjbz;
    }

    public String getLmbz() {
        return lmbz;
    }

    public void setLmbz(String lmbz) {
        this.lmbz = lmbz;
    }

    public String getGkbz() {
        return gkbz;
    }

    public void setGkbz(String gkbz) {
        this.gkbz = gkbz;
    }

    public String getZdjjbz() {
        return zdjjbz;
    }

    public void setZdjjbz(String zdjjbz) {
        this.zdjjbz = zdjjbz;
    }

    public String getNmbz() {
        return nmbz;
    }

    public void setNmbz(String nmbz) {
        this.nmbz = nmbz;
    }

    public String getYybz() {
        return yybz;
    }

    public void setYybz(String yybz) {
        this.yybz = yybz;
    }

    public String getRdwt() {
        return rdwt;
    }

    public void setRdwt(String rdwt) {
        this.rdwt = rdwt;
    }

    public String getSsxt() {
        return ssxt;
    }

    public void setSsxt(String ssxt) {
        this.ssxt = ssxt;
    }

    public String getXbjzsj() {
        return xbjzsj;
    }

    public void setXbjzsj(String xbjzsj) {
        this.xbjzsj = xbjzsj;
    }

    public String getXgfj() {
        return xgfj;
    }

    public void setXgfj(String xgfj) {
        this.xgfj = xgfj;
    }

    public String getBfyrjb() {
        return bfyrjb;
    }

    public void setBfyrjb(String bfyrjb) {
        this.bfyrjb = bfyrjb;
    }

    public String getBfyrzw() {
        return bfyrzw;
    }

    public void setBfyrzw(String bfyrzw) {
        this.bfyrzw = bfyrzw;
    }

    public String getBfyrzzdm() {
        return bfyrzzdm;
    }

    public void setBfyrzzdm(String bfyrzzdm) {
        this.bfyrzzdm = bfyrzzdm;
    }

    public String getBfyrzzmc() {
        return bfyrzzmc;
    }

    public void setBfyrzzmc(String bfyrzzmc) {
        this.bfyrzzmc = bfyrzzmc;
    }

    public String getBfyrhdw() {
        return bfyrhdw;
    }

    public void setBfyrhdw(String bfyrhdw) {
        this.bfyrhdw = bfyrhdw;
    }

    public String getBlcx() {
        return blcx;
    }

    public void setBlcx(String blcx) {
        this.blcx = blcx;
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

    public Date getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(Date lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }

    public String getDjrid() {
        return djrid;
    }

    public void setDjrid(String djrid) {
        this.djrid = djrid;
    }
}