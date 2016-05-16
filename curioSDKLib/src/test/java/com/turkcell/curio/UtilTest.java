package com.turkcell.curio;

import android.test.suitebuilder.annotation.SmallTest;

import com.turkcell.curio.utils.CurioUtil;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit testing for Util class.
 *
 * Created by Can Ciloglu on 14/03/14.
 *
 * @author Can Ciloglu
 */
@SmallTest
public class UtilTest {

    @Test
    public void getRequestType_correctRequestType(){
        String url = "https://curio.turkcell.com.tr/ap/v2/visit/create";
        assertEquals(CurioUtil.getRequestType(url), 0);
        url = "https://curio.turkcell.com.tr/ap/v2/visit/end";
        assertEquals(CurioUtil.getRequestType(url), 1);
        url = "https://curio.turkcell.com.tr/ap/v2/hit/create";
        assertEquals(CurioUtil.getRequestType(url), 2);
        url = "https://curio.turkcell.com.tr/ap/v2/hit/end";
        assertEquals(CurioUtil.getRequestType(url), 3);
        url = "https://curio.turkcell.com.tr/ap/v2/event/create";
        assertEquals(CurioUtil.getRequestType(url), 4);
        url = "https://curio.turkcell.com.tr/ap/v2/event/end";
        assertEquals(CurioUtil.getRequestType(url), 7);
    }

    @Test
    public void getRequestType_wrongRequestType(){
        String url = "https://curio.turkcell.com.tr/ap/v2/visit/x";
        assertEquals(CurioUtil.getRequestType(url), -1);
    }
}
