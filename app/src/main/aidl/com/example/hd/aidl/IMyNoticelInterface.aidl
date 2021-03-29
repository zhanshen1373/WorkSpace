// IMyNoticelInterface.aidl
package com.example.hd.aidl;

// Declare any non-default types here with import statements
import com.example.hd.aidl.Food;
interface IMyNoticelInterface {
   void onNewFoodArrived(in Food food);
}
