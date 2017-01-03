package cn.byhieg.threadtutorial.char02;

/**
 * Created by byhieg on 17/1/3.
 * Mail to byhieg@gmail.com
 */
public class ListThread extends Thread{

    private MyOneList list;
    private String str;

    public ListThread(MyOneList list,String str){
        this.list = list;
        this.str = str;
    }

    @Override
    public void run() {
        super.run();
        MyOneListService service = new MyOneListService();
        service.addServiceMethod(list,str);
    }
}
