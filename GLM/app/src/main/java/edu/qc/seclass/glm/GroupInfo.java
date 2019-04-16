package edu.qc.seclass.glm;

import java.util.ArrayList;
import java.util.Collections;

public class GroupInfo implements Comparable<GroupInfo> {

    private String name;
    private ArrayList<ChildInfo> list = new ArrayList<ChildInfo>();

    public String getName() {
        return name;
    }

    public void setName(String n) {
        this.name = n;
    }

    public ArrayList<ChildInfo> getProductList() {
        Collections.sort(list);
        return list;
    }

    public ChildInfo containsProduct(String s){
        for(ChildInfo i : list){
            if(i.getName().equals(s)){
               return i;
            }
        }
        return null;
    }

    public int compareTo(GroupInfo o) {
        return name.compareTo(o.getName());
    }
}
