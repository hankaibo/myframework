/**   
 * @ProjectName: myframework
 * @Package: cn.mypandora.system.po 
 * @ClassName: ArticleType 
 * Copyright © hankaibo. All rights reserved.
 * @Author: kaibo
 * @CreateDate: 2014-5-9 上午9:46:45 
 *
 */
package cn.mypandora.system.po;

/**
 * @ClassName: ArticleType
 * @Description: 文章类型。
 * @Author: kaibo
 * @date: 2014-5-9
 * @UpdateUser: kaibo
 * @UpdateDate: 2014-5-9 上午9:46:45
 * @UpdateRemark: What is modified?
 */
public enum ArticleType {
    Notice(1), // 启事
    MinutesForum(2), // 座谈会纪要
    Statement(3), // 声明
    Diary(4), // 日记
    Instructions(5), // 说明书
    Report(6), // 报告
    Summary(7), // 总结、摘要
    Speeches(8), // 演讲辞
    GeneralRegulations(9), // 简章
    Briefing(10); // 简报

    // 定义私有变量
    private int articalCode;

    /**
     * @Description: TODO
     * @param articalCode
     */

    private ArticleType(int articalCode) {
        this.articalCode = articalCode;
    }

    //@formatter:off
    /* (非 Javadoc)
     * Title: toString
     * Description:
     * @return
     * @see java.lang.Enum#toString()
     */
    //@formatter:on
    @Override
    public String toString() {
        return String.valueOf(this.articalCode);
    }

}
