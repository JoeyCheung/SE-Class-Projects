package edu.qc.seclass.glm;

import java.util.ArrayList;
import java.util.Collections;

public class GroupInfo implements Comparable<GroupInfo> {

    private String name;
    private ArrayList<ChildInfo> list = new ArrayList<ChildInfo>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ChildInfo> getProductList() {
        Collections.sort(list);
        return list;
    }

    public void setProductList(ArrayList<ChildInfo> productList) {
        this.list = productList;
    }

    public ChildInfo containsProduct(String s){
        for(ChildInfo i : list){
            if(i.getName().equals(s)){
               return i;
            }
        }
        return null;
    }

    public int compareTo(GroupInfo other) {
        return name.compareTo( other.getName() );
    }

}
