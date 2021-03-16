package com.example.hd.myapplication;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dubojian on 2017/9/29.
 */

public class Domain implements Serializable {


    private DefaultDomain defaultDomain;
    private LonelyDomain lonelyDomain;


    public DefaultDomain getDefaultDomain() {
        return defaultDomain;
    }

    public void setDefaultDomain(DefaultDomain defaultDomain) {
        this.defaultDomain = defaultDomain;
    }

    public static class DefaultDomain implements Serializable {

        private List<InnerDomain> innerDomains;
        private List<TitleDomain> titleDomains;


        public List<InnerDomain> getInnerDomains() {
            return innerDomains;
        }

        public void setInnerDomains(List<InnerDomain> innerDomains) {
            this.innerDomains = innerDomains;
        }

        public List<TitleDomain> getTitleDomains() {
            return titleDomains;
        }

        public void setTitleDomains(List<TitleDomain> titleDomains) {
            this.titleDomains = titleDomains;
        }

        public static class TitleDomain implements Serializable {

            private String title;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

        }


        public static class InnerDomain implements Serializable {

            private String mContent;
            private String tiemMinute;
            private String timeSecond;


            public String getmContent() {
                return mContent;
            }

            public void setmContent(String mContent) {
                this.mContent = mContent;
            }

            public String getTiemMinute() {
                return tiemMinute;
            }

            public void setTiemMinute(String tiemMinute) {
                this.tiemMinute = tiemMinute;
            }

            public String getTimeSecond() {
                return timeSecond;
            }

            public void setTimeSecond(String timeSecond) {
                this.timeSecond = timeSecond;
            }


        }


    }

    public static class LonelyDomain implements Serializable {

        private String title;
        private String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }


    public LonelyDomain getLonelyDomain() {
        return lonelyDomain;
    }

    public void setLonelyDomain(LonelyDomain lonelyDomain) {
        this.lonelyDomain = lonelyDomain;
    }
}
