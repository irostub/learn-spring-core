package hello.core.singleton;

public class StatefulService {
    //공유 변수를 사용했다? 문제 생길 가능성이 높다. StatefulServiceTest를 살펴보자.
    //private int price;

    //해결방법은 지역변수로 사용한다.
    public int order(String user, int price) {
        System.out.println("user = " + user + " price = "+price);
        //this.price = price;
        return price;
    }

//    public int getPrice() {
//        return price;
//    }
}
