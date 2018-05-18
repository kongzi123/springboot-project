package com.example.demo.model;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="DEMO实体类")
public class Demo implements Serializable {

	private static final long serialVersionUID = -7089852946104473867L;
	
	@ApiModelProperty(value="DEMO ID", name="id", required=true)
	private Integer id;

	@ApiModelProperty(value="名称", name="name", required=true)
    private String name;

	@ApiModelProperty(value="内容", name="content")
    private String content;

	@ApiModelProperty(value="备注", name="remark")
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}