package com.searshc.twilight.reports;

import java.util.ArrayList;

import com.searshc.twilight.reports.beans.DataBean;
 
public class DataBeanMaker {
  
public ArrayList<DataBean> getDataBeanList() {
ArrayList<DataBean> dataBeanList = new ArrayList<DataBean>();

dataBeanList.add(produce("AlbertEinstein", "Engineer"));
return dataBeanList;
}
 
private DataBean produce(String lineItemId, String quantity) {
DataBean dataBean = new DataBean();
 
dataBean.setLineItemId(lineItemId);
dataBean.setQuantity(quantity);
return dataBean;
  }
}