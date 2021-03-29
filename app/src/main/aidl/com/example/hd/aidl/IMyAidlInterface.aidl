// IMyAidlInterface.aidl
package com.example.hd.aidl;

// Declare any non-default types here with import statements
import com.example.hd.aidl.Food;
import com.example.hd.aidl.IMyNoticelInterface;
interface IMyAidlInterface {

  List<Food> getFoodList();
  void addFood(in Food food);
  void registerListener(IMyNoticelInterface i);
  void unregisterListener(IMyNoticelInterface t);
}
