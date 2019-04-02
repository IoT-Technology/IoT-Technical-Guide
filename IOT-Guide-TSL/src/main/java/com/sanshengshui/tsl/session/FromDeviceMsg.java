package com.sanshengshui.tsl.session;

import java.io.Serializable;

/**
 * @Author: 穆书伟
 * @Date: 19-4-2 下午2:29
 * @Version 1.0
 */
public interface FromDeviceMsg extends Serializable {

    SessionMsgType getMsgType();

}
