package com.firespider.spidersql.lang;

/**
 * 基础数据结构
 * todo id与hashcode绑定
 */
public class Gen {
    private Integer id = 0;

    public Gen() {
    }

    public Gen(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
