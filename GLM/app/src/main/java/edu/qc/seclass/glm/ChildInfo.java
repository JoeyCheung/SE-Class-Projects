package edu.qc.seclass.glm;

import edu.qc.seclass.glm.Model.GListEntry;

public class ChildInfo implements Comparable<ChildInfo> {

    private String sequence = "";
    private String name = "";
    private String quantityAndUnit= "";
    private boolean check=false;
    private GListEntry gle;

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantityAndUnit(){
        return this.quantityAndUnit;
    }
    public void setQuantityAndUnit(String q){
        this.quantityAndUnit=q;
    }
    public void setCheck(boolean c){
        check=c;
    }
    public boolean getCheck(){
        return check;
    }

    public int compareTo(ChildInfo other) {
        return name.compareTo( other.getName() );
    }

    public void setGListEntry (GListEntry g){
        gle=g;
    }
    public GListEntry getGListEntry(){
        return gle;
    }

}
