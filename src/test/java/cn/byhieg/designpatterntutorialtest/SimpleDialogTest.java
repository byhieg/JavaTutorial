package cn.byhieg.designpatterntutorialtest;

import cn.byhieg.designpatterntutorial.builder.SimpleDialog;
import junit.framework.TestCase;

/**
 * Created by shiqifeng on 2017/5/7.
 * Mail byhieg@gmail.com
 */
public class SimpleDialogTest extends TestCase {

    public void testBuilder() throws Exception {
        SimpleDialog dialog = new SimpleDialog.Builder().setIcon("图标").setMessage("这是Dialog").setPositiveButton("确认").setNegativeButton("否定").create();


    }

}