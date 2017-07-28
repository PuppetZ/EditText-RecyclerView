# EditText-RecyclerView
列表中的item有EditText时填写、获取EditText中的值
        在RecyclerView中的item中存在EditText控件，可以进行输入，若还有保存功能，保存完毕后，无论RecyclerView如何滑动，EditText的值不会改变
        每次打开页面时，由于EditText自动获取焦点并弹出软键盘，所以软键盘会挡住一部分列表。
        需要在清单列表中所在的页面中添加这个属性
```
 android:windowSoftInputMode="adjustPan"
```
        实现滑动列表，而EditText的值不会改变需要在adapter中对EditText进行text的监听，每当text的值改变，数据源list中的值相应改变。这样无论列表如何滑动，EditText的值不会改变
```
addTextChangedListener(new TextWatcher() {//监听EditText的text变化
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mList.set(position, s.toString());//更新list的数据,防止rv滑动的时候重新绘制,数据还是之前的

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
```
        列表一滑动，页面就会重新绘制，重新绘制列表会重新从数据源list中获取值，所以需要把数据源list的值修改为text的值，这样滑动不会改变text的值了
        还有个问题，RecyclerView中的item是采用复用的，每次滑动都会保存一遍list中的EditText的值。所以会出现数据混乱，需要在adapter中关闭复用
```
holder.setIsRecyclable(false);//不使用复用
```
        在数据少的时候可以关闭复用；
        若数据很多，再关闭复用，进入页面的时候，滑动页面的时候，页面绘制就会很长时间，变得卡顿。这种情况应该可以使用setTag、getTag对每个item进行标识。因为我司需求没有这个，所以只是提供个思路。。。。
<br>
        关于`softInputMode`的更多了解可以去查看API
        http://www.android-doc.com/reference/android/view/WindowManager.LayoutParams.html#softInputMode
