package cn.byhieg.designpatterntutorial.builder;

/**
 * Created by shiqifeng on 2017/5/7.
 * Mail byhieg@gmail.com
 */
public class SimpleDialog {


    public SimpleDialogController controller;

    public SimpleDialog(){
        controller = new SimpleDialogController();
    }

    public void setIcon(String icon) {
        controller.setIcon(icon);
    }

    public void setTitle(String title) {
        controller.setTitle(title);
    }

    public void setMessage(String message) {
        controller.setMessage(message);
    }

    public void setPositiveButton(String positiveButton) {
        controller.setPositiveButton(positiveButton);
    }

    public void setNegativeButton(String negativeButton) {
        controller.setNegativeButton(negativeButton);
    }


    public static class Builder{
        SimpleDialogController.DialogParams P;

        public Builder(){
            P = new SimpleDialogController.DialogParams();
        }

        public Builder setIcon(String icon){
            P.icon = icon;
            return this;
        }

        public Builder setTitle(String title) {
            P.title = title;
            return this;
        }

        public Builder setMessage(String message) {
            P.message = message;
            return this;
        }

        public Builder setPositiveButton(String positiveButton) {
            P.positiveButton = positiveButton;
            return this;
        }

        public Builder setNegativeButton(String negativeButton) {
            P.negativeButton = negativeButton;
            return this;
        }

        public SimpleDialog create(){
            SimpleDialog dialog = new SimpleDialog();
            P.apply(dialog.controller);
            System.out.println(" ICON = " + P.icon + " MESSAGE = " + P.message + " positiveButton = " + P.positiveButton + " negativeButton" + P.negativeButton);
            return dialog;
        }
    }


}
