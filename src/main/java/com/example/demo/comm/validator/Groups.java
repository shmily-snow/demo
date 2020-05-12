package com.example.demo.comm.validator;

import javax.validation.GroupSequence;

/**
 * @projectName: peixun
 * @author: caog
 * @date: 2020年05月12日 9:38
 * @version: 1.0
 */
public class Groups {
    public interface UpdateGroup {};

    public interface AddGroup {};

    /**
     * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
     */
    @GroupSequence({AddGroup.class, UpdateGroup.class})
    public interface Group {};
}
