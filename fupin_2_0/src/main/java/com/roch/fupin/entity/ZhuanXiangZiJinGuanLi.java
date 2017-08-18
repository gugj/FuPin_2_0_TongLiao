package com.roch.fupin.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 专项资金管理实体类
 * 作者：ZDS
 * 时间：2016/12/21/021 16:38
 */
public class ZhuanXiangZiJinGuanLi extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private String id;//
    private String sourcesfunds;//   资金来源
    private String fundstype;//   资金类型
    private String amount;//   金额
    private String allocationdate;//   拨付时间
    private String paymentdate;//   到账时间
    private String batch;//   批次
    private String allocationunit;//   拨付单位
    private String allocatedobject;//   拨付对象
    private String fileno;//   文件号
    private String ts;//   日期
    private String nt;//   备注
    private String fundstypename;//资金类型名称
    private String allocationunitname;//拨付单位名称
    private String allocatedobjectname;//拨付对象名称


    private String pid;//上级资金id（主要用户拨付记录拨付的金额来自哪个到账的资金）
    private String dataid; //dataid":"9505CBF7-C920-46C4-A760-54B2D055A755"
    private String allocationsituation;//下拨情况
    private String allocationyear;//下拨年度
    private String allocationsituationname;//下拨情况名称

    private List<ChildrenBean> children;

    public String getDataid() {
        return dataid;
    }

    public void setDataid(String dataid) {
        this.dataid = dataid;
    }

    public List<ChildrenBean> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBean> children) {
        this.children = children;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getAllocationsituation() {
        return allocationsituation;
    }

    public void setAllocationsituation(String allocationsituation) {
        this.allocationsituation = allocationsituation;
    }

    public String getAllocationyear() {
        return allocationyear;
    }

    public void setAllocationyear(String allocationyear) {
        this.allocationyear = allocationyear;
    }

    public String getAllocationsituationname() {
        return allocationsituationname;
    }

    public void setAllocationsituationname(String allocationsituationname) {
        this.allocationsituationname = allocationsituationname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourcesfunds() {
        return sourcesfunds;
    }

    public void setSourcesfunds(String sourcesfunds) {
        this.sourcesfunds = sourcesfunds;
    }

    public String getFundstype() {
        return fundstype;
    }

    public void setFundstype(String fundstype) {
        this.fundstype = fundstype;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAllocationdate() {
        return allocationdate;
    }

    public void setAllocationdate(String allocationdate) {
        this.allocationdate = allocationdate;
    }

    public String getPaymentdate() {
        return paymentdate;
    }

    public void setPaymentdate(String paymentdate) {
        this.paymentdate = paymentdate;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getAllocationunit() {
        return allocationunit;
    }

    public void setAllocationunit(String allocationunit) {
        this.allocationunit = allocationunit;
    }

    public String getAllocatedobject() {
        return allocatedobject;
    }

    public void setAllocatedobject(String allocatedobject) {
        this.allocatedobject = allocatedobject;
    }

    public String getFileno() {
        return fileno;
    }

    public void setFileno(String fileno) {
        this.fileno = fileno;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getNt() {
        return nt;
    }

    public void setNt(String nt) {
        this.nt = nt;
    }

    public String getFundstypename() {
        return fundstypename;
    }

    public void setFundstypename(String fundstypename) {
        this.fundstypename = fundstypename;
    }

    public String getAllocationunitname() {
        return allocationunitname;
    }

    public void setAllocationunitname(String allocationunitname) {
        this.allocationunitname = allocationunitname;
    }

    public String getAllocatedobjectname() {
        return allocatedobjectname;
    }

    public void setAllocatedobjectname(String allocatedobjectname) {
        this.allocatedobjectname = allocatedobjectname;
    }

    public static class ChildrenBean implements Serializable{
        private String allocatedobjectname;
        private String allocationsituationname;
        private String pid;
        private String allocationyear;
        private String fundstypename;
        private String allocationsituation;
        private String sourcesfunds;
        private String id;
        private String amount;
        private String fileno;
        private String batch;
        private String dataid;
        private List<?> children;

        public String getAllocatedobjectname() {
            return allocatedobjectname;
        }

        public void setAllocatedobjectname(String allocatedobjectname) {
            this.allocatedobjectname = allocatedobjectname;
        }

        public String getAllocationsituationname() {
            return allocationsituationname;
        }

        public void setAllocationsituationname(String allocationsituationname) {
            this.allocationsituationname = allocationsituationname;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getAllocationyear() {
            return allocationyear;
        }

        public void setAllocationyear(String allocationyear) {
            this.allocationyear = allocationyear;
        }

        public String getFundstypename() {
            return fundstypename;
        }

        public void setFundstypename(String fundstypename) {
            this.fundstypename = fundstypename;
        }

        public String getAllocationsituation() {
            return allocationsituation;
        }

        public void setAllocationsituation(String allocationsituation) {
            this.allocationsituation = allocationsituation;
        }

        public String getSourcesfunds() {
            return sourcesfunds;
        }

        public void setSourcesfunds(String sourcesfunds) {
            this.sourcesfunds = sourcesfunds;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getFileno() {
            return fileno;
        }

        public void setFileno(String fileno) {
            this.fileno = fileno;
        }

        public String getBatch() {
            return batch;
        }

        public void setBatch(String batch) {
            this.batch = batch;
        }

        public String getDataid() {
            return dataid;
        }

        public void setDataid(String dataid) {
            this.dataid = dataid;
        }

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<?> children) {
            this.children = children;
        }
    }
}
