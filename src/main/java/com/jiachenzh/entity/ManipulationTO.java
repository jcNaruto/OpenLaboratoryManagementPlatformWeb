package com.jiachenzh.entity;

/**
 * @ClassName ManipulationTO
 * @Description TODO
 * @Author
 * @Date 2019/3/9 21:35
 * @Version 1.0
 */
public class ManipulationTO {
    private ManipulationDO manipulationDO;
    private ToolDO toolDO;

    public ManipulationTO() {
    }

    public ManipulationTO(ManipulationDO manipulationDO, ToolDO toolDO) {
        this.manipulationDO = manipulationDO;
        this.toolDO = toolDO;
    }

    public ManipulationDO getManipulationDO() {
        return manipulationDO;
    }

    public void setManipulationDO(ManipulationDO manipulationDO) {
        this.manipulationDO = manipulationDO;
    }

    public ToolDO getToolDO() {
        return toolDO;
    }

    public void setToolDO(ToolDO toolDO) {
        this.toolDO = toolDO;
    }
}
