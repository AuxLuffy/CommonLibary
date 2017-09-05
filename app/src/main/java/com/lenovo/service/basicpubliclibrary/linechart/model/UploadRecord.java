package com.lenovo.service.basicpubliclibrary.linechart.model;

/**
 * Created by cx on 2016/12/14.
 */

public class UploadRecord {

    private SevendaysBean sevendays;

    public SevendaysBean getSevendays() {
        return sevendays;
    }

    public void setSevendays(SevendaysBean sevendays) {
        this.sevendays = sevendays;
    }

    public static class SevendaysBean {
        /**
         * sale : {"one":0,"two":0,"three":0,"four":0,"five":0,"six":0,"seven":0}
         * user : {"one":0,"two":0,"three":0,"four":0,"five":0,"six":0,"seven":0}
         * push : {"one":0,"two":0,"three":0,"four":0,"five":0,"six":0,"seven":0}
         */

        private SaleBean sale;
        private UserBean user;
        private PushBean push;

        public SevendaysBean(SaleBean sale, UserBean user, PushBean push) {
            this.sale = sale;
            this.user = user;
            this.push = push;
        }

        public SaleBean getSale() {
            return sale;
        }

        public void setSale(SaleBean sale) {
            this.sale = sale;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public PushBean getPush() {
            return push;
        }

        public void setPush(PushBean push) {
            this.push = push;
        }

        public static class SaleBean {
            /**
             * one : 0
             * two : 0
             * three : 0
             * four : 0
             * five : 0
             * six : 0
             * seven : 0
             */

            private int one;
            private int two;
            private int three;
            private int four;
            private int five;
            private int six;
            private int seven;

            public SaleBean(int one, int two, int three, int four, int five, int six, int seven) {
                this.one = one;
                this.two = two;
                this.three = three;
                this.four = four;
                this.five = five;
                this.six = six;
                this.seven = seven;
            }

            public int getOne() {
                return one;
            }

            public void setOne(int one) {
                this.one = one;
            }

            public int getTwo() {
                return two;
            }

            public void setTwo(int two) {
                this.two = two;
            }

            public int getThree() {
                return three;
            }

            public void setThree(int three) {
                this.three = three;
            }

            public int getFour() {
                return four;
            }

            public void setFour(int four) {
                this.four = four;
            }

            public int getFive() {
                return five;
            }

            public void setFive(int five) {
                this.five = five;
            }

            public int getSix() {
                return six;
            }

            public void setSix(int six) {
                this.six = six;
            }

            public int getSeven() {
                return seven;
            }

            public void setSeven(int seven) {
                this.seven = seven;
            }
        }

        public static class UserBean {
            /**
             * one : 0
             * two : 0
             * three : 0
             * four : 0
             * five : 0
             * six : 0
             * seven : 0
             */

            private int one;
            private int two;
            private int three;
            private int four;
            private int five;
            private int six;
            private int seven;

            public UserBean(int one, int two, int three, int four, int five, int six, int seven) {
                this.one = one;
                this.two = two;
                this.three = three;
                this.four = four;
                this.five = five;
                this.six = six;
                this.seven = seven;
            }

            public int getOne() {
                return one;
            }

            public void setOne(int one) {
                this.one = one;
            }

            public int getTwo() {
                return two;
            }

            public void setTwo(int two) {
                this.two = two;
            }

            public int getThree() {
                return three;
            }

            public void setThree(int three) {
                this.three = three;
            }

            public int getFour() {
                return four;
            }

            public void setFour(int four) {
                this.four = four;
            }

            public int getFive() {
                return five;
            }

            public void setFive(int five) {
                this.five = five;
            }

            public int getSix() {
                return six;
            }

            public void setSix(int six) {
                this.six = six;
            }

            public int getSeven() {
                return seven;
            }

            public void setSeven(int seven) {
                this.seven = seven;
            }
        }

        public static class PushBean {
            /**
             * one : 0
             * two : 0
             * three : 0
             * four : 0
             * five : 0
             * six : 0
             * seven : 0
             */

            private int one;
            private int two;
            private int three;
            private int four;
            private int five;
            private int six;
            private int seven;

            public PushBean(int one, int two, int three, int four, int five, int six, int seven) {
                this.one = one;
                this.two = two;
                this.three = three;
                this.four = four;
                this.five = five;
                this.six = six;
                this.seven = seven;
            }

            public int getOne() {
                return one;
            }

            public void setOne(int one) {
                this.one = one;
            }

            public int getTwo() {
                return two;
            }

            public void setTwo(int two) {
                this.two = two;
            }

            public int getThree() {
                return three;
            }

            public void setThree(int three) {
                this.three = three;
            }

            public int getFour() {
                return four;
            }

            public void setFour(int four) {
                this.four = four;
            }

            public int getFive() {
                return five;
            }

            public void setFive(int five) {
                this.five = five;
            }

            public int getSix() {
                return six;
            }

            public void setSix(int six) {
                this.six = six;
            }

            public int getSeven() {
                return seven;
            }

            public void setSeven(int seven) {
                this.seven = seven;
            }
        }
    }
}
