package limocity.cbistech.com.limocity.onboarding;

import java.io.Serializable;

/**
 * Created by piyush on 10/12/17.
 */

public class PaperOnboardingPageApp implements Serializable {
    private String titleText;
    private String descriptionText;
    private int bgColor;
    private int contentIconRes;

    public PaperOnboardingPageApp() {
    }

    public PaperOnboardingPageApp(String titleText, String descriptionText, int bgColor, int contentIconRes) {
        this.bgColor = bgColor;
        this.contentIconRes = contentIconRes;
        this.descriptionText = descriptionText;
        this.titleText = titleText;
    }

    public String getTitleText() {
        return this.titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public String getDescriptionText() {
        return this.descriptionText;
    }

    public void setDescriptionText(String descriptionText) {
        this.descriptionText = descriptionText;
    }

    public int getContentIconRes() {
        return this.contentIconRes;
    }

    public void setContentIconRes(int contentIconRes) {
        this.contentIconRes = contentIconRes;
    }


    public int getBgColor() {
        return this.bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public int hashCode() {
        int result = this.titleText != null?this.titleText.hashCode():0;
        result = 31 * result + (this.descriptionText != null?this.descriptionText.hashCode():0);
        result = 31 * result + this.bgColor;
        result = 31 * result + this.contentIconRes;
        return result;
    }

    public String toString() {
        return "PaperOnboardingPageApp{titleText='" + this.titleText + '\'' + ", descriptionText='" + this.descriptionText + '\'' + ", bgColor=" + this.bgColor + ", contentIconRes=" + this.contentIconRes + '}';
    }
}
