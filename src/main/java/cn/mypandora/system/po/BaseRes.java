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
    private String url;
    /**
     * 节点的深度
     */
    private int level;
    /**
     * 操作类型，是编辑还是添加...
     */
    private String oper;

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean isEnable) {
        this.isEnable = isEnable;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }
}

