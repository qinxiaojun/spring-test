package com.example.springtest.netty.protocol;

import cn.hutool.core.util.ObjectUtil;

/**
 * @author admin
 * @date 2021-07-03 17:10
 */
public class ProtocalManagerHelper {

    private static IProtocal protocalManagerRgm;

    public static IProtocal getProtocalManagerRgm() throws Exception {
        if (ObjectUtil.isEmpty(protocalManagerRgm)) {
            protocalManagerRgm = ProtocalManagerFactory.getProtocalManager("rgm_376_1");
        }
        return protocalManagerRgm;
    }
}
