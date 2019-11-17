package yush.apps.practise;
import android.util.Log;

public class Product {
    int id;
    String name;
    int price;

    public Product(){

        Log.d("PRODUCT", "Product created");

    }


    public void setvalues(int id, int price, String name)
    {
        this.id = id;
        this.price = price * 10;
        this.name = name + "KK";
    }

    public void get_details()
    {
        System.out.println("PRODUCT" + this.id + "," + this.name + "," + this.price);
    }
}

class Mobile extends Product {

    public Mobile (){

        Log.d("PRODUCT", "Mobile created");

    }

    public void setvalues(int id, int price, String name)
    {
        this.id = id;
        this.price = price;
      //  this.name = name;
    }
}
