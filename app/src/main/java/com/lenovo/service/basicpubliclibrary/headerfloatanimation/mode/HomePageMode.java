package com.lenovo.service.basicpubliclibrary.headerfloatanimation.mode;

import java.util.ArrayList;

/**
 * Created by shengtao on 2016/4/20.
 */
public class HomePageMode {

    public ArrayList<CategoryOne> category_one;

    public ArrayList<CategoryOne> getCategory_one() {
        return category_one;
    }

    public static class CategoryOne {
        public int type;
        public String title;
        public ArrayList<CategoryTwo> category_two;


        public int getType() {
            return type;
        }

        public String getTitle() {
            return title;
        }

        public ArrayList<CategoryTwo> getCategory_two() {
            return category_two;
        }

        public static class CategoryTwo {
            String url;
            String discription;
            String image_url;

            public String getUrl() {
                return url;
            }

            public String getDiscription() {
                return discription;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setDiscription(String discription) {
                this.discription = discription;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }
        }

    }


}
