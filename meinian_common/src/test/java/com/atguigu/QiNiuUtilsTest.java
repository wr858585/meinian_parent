package com.atguigu;

import com.atguigu.utils.QiniuUtils;
import org.junit.Test;

/**
 * @Author: oono
 * @Date: 2020/11/27
 * @Description:
 */
public class QiNiuUtilsTest {

    @Test
    public void test(){
        QiniuUtils.upload2Qiniu("C:\\Users\\oono\\Desktop\\undone.png","undone");
    }

}
