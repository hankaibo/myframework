package cn.mypandora.system.po;

import cn.mypandora.orm.model.BaseTree;

/**
 * Created by kaibo on 2014/8/4.
 * desc
 */
public class BaseRes extends BaseTree {

    /**
     * 资源状态，启用/禁用。
     */
    private boolean isEnable;
    /**
     * 资源URL。
     */
    private String URL;

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}

