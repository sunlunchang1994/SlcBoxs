package android.slc.toolbar;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.MenuRes;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;

/**
 * @author slc
 * @date 2019/12/9 15:00
 * 为了更方便的使用Toolbar，特此写了该Delegate
 * 此接口定义了简化部分原生Toolbar的某些功能设置或使用不方便的方法
 */

public interface ISlcToolBarDelegate {
    /**
     * 获取AppBar
     *
     * @return
     */
    AppBarLayout getAppBarLayout();

    /**
     * 获取ToolBar
     *
     * @return
     */
    Toolbar getToolBar();

    /**
     * 获取TitleTextView
     *
     * @return
     */
    TextView getTitleTextView();

    /**
     * 获取SubTitleTextView
     *
     * @return
     */
    TextView getSubTitleTextView();

    //void inflateMenu(@MenuRes int resId);

    /**
     * 获取事件菜单视图
     *
     * @return
     */
    ActionMenuView getActionMenuView();

    /**
     * 设置菜单监听
     *
     * @param listener
     */
    void setOnMenuItemClickListener(MenuItem.OnMenuItemClickListener listener);

    /**
     * 从一个View中根据过滤器获取一个符合调用者条件的View
     *
     * @param viewGroup
     * @param viewFilter
     * @return
     */
    <T extends View> T getTargetViewByViewGroup(ViewGroup viewGroup, ViewFilter viewFilter);

    /**
     * 设置工具栏立体高度
     *
     * @param elevation
     */
    void setToolBarElevation(float elevation);

    /**
     * 获取状态栏高度
     *
     * @return
     */
    int getStatusBarHeight();

    interface ViewFilter {
        boolean onFilter(View view);
    }
}
