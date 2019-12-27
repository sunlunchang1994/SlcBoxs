package androidx.core.view;


import android.content.Context;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.appcompat.widget.ListPopupWindow;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by achang on 2018/12/20.
 */

public abstract class BaseActionProviderImp extends ActionProvider implements BaseActionProvider {
    protected MenuItem.OnMenuItemClickListener mOnMenuItemClickListener;
    protected MenuItem mForItem;
    private SubMenu mSubMenuItem;
    private View rootView;

    /**
     * Creates cpb_complete_state_selector new instance.
     *
     * @param context Context for accessing resources.
     */
    public BaseActionProviderImp(Context context) {
        super(context);
    }

    @Override
    public void setSubMenuItem(SubMenu subMenuItem) {
        this.mSubMenuItem = subMenuItem;
    }

    public void setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener onMenuItemClickListener) {
        this.mOnMenuItemClickListener = onMenuItemClickListener;
    }

    @Override
    public boolean onPerformDefaultAction() {
        if (mOnMenuItemClickListener != null && mForItem != null) {
            return mOnMenuItemClickListener.onMenuItemClick(mForItem);
        } else {
            return super.onPerformDefaultAction();
        }
    }

    @Override
    public View onCreateActionView() {
        return LayoutInflater.from(getContext()).inflate(getActionViewLayout(), null);
    }

    /**
     * 获取视图
     *
     * @return
     */
    @LayoutRes
    protected abstract int getActionViewLayout();

    @Override
    public View onCreateActionView(MenuItem forItem) {
        this.mForItem = forItem;
        rootView = onCreateActionView();
        bindView(rootView);
        return rootView;
    }

    /**
     * 查找试图id
     *
     * @param idRes
     * @return
     */
    public View findViewById(@IdRes int idRes) {
        return rootView.findViewById(idRes);
    }

    /**
     * 获取菜单根视图
     * @return
     */
    public View getRootView(){
        return rootView;
    }
    /**
     * 绑定视图
     *
     * @param rootView
     */
    protected void bindView(View rootView) {
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSubMenuItem != null && mSubMenuItem.size() != 0) {
                    final ListPopupWindow listPopupWindow = new ListPopupWindow(getContext());
                    listPopupWindow.setWidth(getContext().getResources().getDisplayMetrics().widthPixels / 3);
                    listPopupWindow.setHeight(ListPopupWindow.WRAP_CONTENT);
                    listPopupWindow.setAnchorView(v);
                    listPopupWindow.setModal(true);
                    final List<MenuItem> menuItemList = new ArrayList<>();
                    for (int i = 0; i < mSubMenuItem.size(); i++) {
                        menuItemList.add(mSubMenuItem.getItem(i));
                    }
                    listPopupWindow.setAdapter(new BaseAdapter() {
                        @Override
                        public int getCount() {
                            return menuItemList.size();
                        }

                        @Override
                        public Object getItem(int position) {
                            return menuItemList.get(position);
                        }

                        @Override
                        public long getItemId(int position) {
                            return 0;
                        }

                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            if (convertView == null) {
                                convertView = LayoutInflater.from(getContext()).inflate(androidx.appcompat.R.layout.select_dialog_item_material, parent, false);
                            }
                            ((TextView) convertView.findViewById(android.R.id.text1)).setText(((MenuItem) getItem(position)).getTitle());
                            return convertView;
                        }
                    });
                    listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            listPopupWindow.dismiss();
                            if (mOnMenuItemClickListener != null) {
                                mOnMenuItemClickListener.onMenuItemClick(menuItemList.get(position));
                            }
                        }
                    });
                    listPopupWindow.show();
                } else {

                    onPerformDefaultAction();
                }
            }
        });
        rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

}
