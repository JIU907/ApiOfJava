package com.jiu907.api.utils.dingrobot.model;

import java.util.List;

/**
 * @Author LeiLiMin
 * @Description: 文本类型
 * @date: 2022/3/1
 */
public class TextType {
    /**
     * 消息类型
     */
    private String msgtype;

    /**
     * 文本内容
     */
    private Text text;

    /**
     * @对象 的信息
     */
    private At at;

    public TextType(String content) {
        this.msgtype = "text";
        this.text = new Text(content);
    }

    /**
     * 文本内部类
     */
    class Text {
        /**
         * 消息主体
         */
        private String content;

        public Text(String content) {
            this.content = content;
        }
    }

    /**
     * @ 内部类
     */
    class At {
        private List<String> atMobiles;
        private List<String> atUserIds;
        private Boolean isAtAll;
    }
}
