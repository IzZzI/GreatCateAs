package cn.zhouzy.greatcate.entity;

/**
 * Created by gdxw on 2017/3/8.
 */

public class Root
{
    private String resultcode;

    private String reason;

    private Result result;

    private int error_code;

    public String getResultcode()
    {
        return resultcode;
    }

    public void setResultcode(String resultcode)
    {
        this.resultcode = resultcode;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public Result getResult()
    {
        return result;
    }

    public void setResult(Result result)
    {
        this.result = result;
    }

    public int getError_code()
    {
        return error_code;
    }

    public void setError_code(int error_code)
    {
        this.error_code = error_code;
    }

    @Override
    public String toString()
    {
        return "Root{" +
                "resultcode='" + resultcode + '\'' +
                ", reason='" + reason + '\'' +
                ", result=" + result +
                ", error_code=" + error_code +
                '}';
    }
}
