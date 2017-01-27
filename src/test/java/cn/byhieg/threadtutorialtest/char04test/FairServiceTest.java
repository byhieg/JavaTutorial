package cn.byhieg.threadtutorialtest.char04test;

import cn.byhieg.threadtutorial.char04.FairService;
import junit.framework.TestCase;

import javax.security.auth.callback.ConfirmationCallback;

/**
 * Created by byhieg on 17/1/27.
 * Mail to byhieg@gmail.com
 */
public class FairServiceTest extends TestCase {
    public void testServiceMethod() throws Exception {

        FairService fairService = new FairService(false);
        Thread[] threadArrays = new Thread[10];
        for (int i = 0 ; i < threadArrays.length;i++){
            threadArrays[i] = new Thread(fairService::serviceMethod);
        }

        for (Thread thread : threadArrays){
            thread.start();
        }

        Thread.sleep(1000 * 5);
    }

}