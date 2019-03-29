package Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaySlipGet {
    @SerializedName("id")
    private int id;
    @SerializedName("from_date")
    private String fromDate;
    @SerializedName("to_date")
    private String toDate;
    @SerializedName("net_pay")
    private float netPay;
    @SerializedName("list_item")
    private List<ListItem> mItemList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public float getNetPay() {
        return netPay;
    }

    public void setNetPay(float netPay) {
        this.netPay = netPay;
    }

    public List<ListItem> getmItemList() {
        return mItemList;
    }

    public void setmItemList(List<ListItem> mItemList) {
        this.mItemList = mItemList;
    }
}
