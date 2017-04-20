package cn.zhouzy.greatcate.entity;

import java.util.List;

/**
 * Created by gdxw on 2017/3/8.
 */

public class Result
{
    private List<Data> data ;

    private String totalNum;

    private String pn;

    private String rn;

    public List<Data> getData()
    {
        return data;
    }

    public void setData(List<Data> data)
    {
        this.data = data;
    }

    public String getTotalNum()
    {
        return totalNum;
    }

    public void setTotalNum(String totalNum)
    {
        this.totalNum = totalNum;
    }

    public String getPn()
    {
        return pn;
    }

    public void setPn(String pn)
    {
        this.pn = pn;
    }

    public String getRn()
    {
        return rn;
    }

    public void setRn(String rn)
    {
        this.rn = rn;
    }

    @Override
    public String toString()
    {
        return "Result{" +
                "data=" + data +
                ", totalNum='" + totalNum + '\'' +
                ", pn='" + pn + '\'' +
                ", rn='" + rn + '\'' +
                '}';
    }
}
