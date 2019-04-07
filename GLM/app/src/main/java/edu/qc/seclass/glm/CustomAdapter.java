package edu.qc.seclass.glm;

/**
 * Created by yigalsaperstein on 3/29/18.
 */
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends BaseExpandableListAdapter {

    private Context context;
    private ArrayList<GroupInfo> deptList;

    static AppDatabase mDb;
    GListEntryDao gListEntryDao;

    public CustomAdapter(Context context, ArrayList<GroupInfo> deptList) {
        this.context = context;
        this.deptList = deptList;

        mDb = AppDatabase.getInstance(context);
        gListEntryDao = mDb.gListEntryDao();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<ChildInfo> productList = deptList.get(groupPosition).getProductList();
        return productList.get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View view, ViewGroup parent) {

        final ChildInfo detailInfo = (ChildInfo) getChild(groupPosition, childPosition);
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.child_items, null);
        }

        TextView sequence = (TextView) view.findViewById(R.id.sequence);
        //sequence.setText(detailInfo.getSequence().trim() + ". ");
        final TextView childItem = (TextView) view.findViewById(R.id.childItem);
        childItem.setText(detailInfo.getName().trim());
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkbox_meat);
        checkBox.setChecked(detailInfo.getCheck());
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                final boolean isChecked = checkBox.isChecked();
                detailInfo.setCheck(isChecked);
                checkBox.setChecked(detailInfo.getCheck());
                // Update check_box for GListEntry in DB when checkbox is clicked in UI
                gListEntryDao.updateCheckbox(detailInfo.getCheck(), detailInfo.getGListEntry().getId());
            }
        });
        TextView quantity = view.findViewById(R.id.quantity);
        quantity.setText(" ("+detailInfo.getQuantityAndUnit()+")");

        return view;

    }

    @Override
    public int getChildrenCount(int groupPosition) {

        ArrayList<ChildInfo> productList = deptList.get(groupPosition).getProductList();
        return productList.size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return deptList.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return deptList.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isLastChild, View view,
                             ViewGroup parent) {

        GroupInfo headerInfo = (GroupInfo) getGroup(groupPosition);
        if (view == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inf.inflate(R.layout.group_items, null);
        }

        TextView heading = (TextView) view.findViewById(R.id.heading);
        heading.setText(headerInfo.getName().trim());

        return view;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
