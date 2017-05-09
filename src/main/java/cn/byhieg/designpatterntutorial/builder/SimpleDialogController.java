package cn.byhieg.designpatterntutorial.builder;

/**
 * Created by shiqifeng on 2017/5/7.
 * Mail byhieg@gmail.com
 */
public class SimpleDialogController {

    private String icon;
    private String title;
    private String message;
    private String positiveButton;
    private String negativeButton;

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPositiveButton(String positiveButton) {
        this.positiveButton = positiveButton;
    }

    public void setNegativeButton(String negativeButton) {
        this.negativeButton = negativeButton;
    }

    public String getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public String getPositiveButton() {
        return positiveButton;
    }

    public String getNegativeButton() {
        return negativeButton;
    }

    public static class DialogParams{
        public String icon;
        public String title;
        public String message;
        public String positiveButton;
        public String negativeButton;

        public void apply(SimpleDialogController controller) {
            if (icon != null) {
                controller.setIcon(icon);
            }
            if (title != null) {
                controller.setTitle(title);
            }
            if (message != null) {
                controller.setMessage(message);
            }
            if (positiveButton != null) {
                controller.setPositiveButton(positiveButton);
            }
            if (negativeButton != null) {
                controller.setNegativeButton(negativeButton);
            }
        }
    }

}
