package hellojpa;

public class ValueMain {

    public static void main(String[] args){

       /* int a=10;
        int b=10;*/

        int a = 10;
        int b= a;
        b = 4;
        System.out.println("a = " + a);
        System.out.println("b = " + b);

        Address aa = new Address();
        aa.setCity("Old");

        Address bb = aa;
        bb.setCity("New");

        System.out.println("aa = " + aa);
        System.out.println("bb = " + bb);


       // System.out.println("a == b " + (a==b)); //기본타입은 true (내부에 값이 10 10 )

        Address homeAddress1 = new Address("city", "street", "zipcodes");
        Address homeAddress2 = new Address("city", "street", "zipcodes");

        System.out.println("adress1 == adress2 " + (homeAddress1==homeAddress2)); //객체는(참조값을 비교하니) false
        System.out.println("adress1 == adress2 " + (homeAddress1==homeAddress2)); //객체는(참조값을 비교하니) false
        System.out.println("adress1 equals adress2 " + (homeAddress1.equals(homeAddress2))); //기본값을 제외한 -> embedded , 값타입,등등은 equals 로 비교해라!

        /**
         * 갑 타입 비교
         *
         * 1.동일성(identity) 비교: 인스턴스의 참조 값을 비교
         */
    }
}
