package com.example.hd.mhsearch;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hd.myapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Main20Activity extends AppCompatActivity {


    //这三种修饰符都可以写，就是不能写private
    @BindView(R.id.main20_button)
    protected Button button;
    @BindView(R.id.main20_edittext)
    EditText editText;
    @BindView(R.id.main20_textview)
    public TextView textView;
    @BindView(R.id.main20_listview)
    public ListView listView;

    private List<String> names = new ArrayList<>();
    private List<String> ks = new ArrayList<>();
    private List<Integer> lengthlist = new ArrayList<>();
    private List<String> storagelist = new ArrayList<>();
    private List<Set<String>> hanziSpellList = null;
    PinYin4j pinyin = new PinYin4j();
    private String sm[] = new String[]{"b", "p", "m", "f", "d", "t", "n", "l", "g",
            "k", "h", "j", "q", "x", "zh", "ch", "sh", "r", "z", "c", "s", "y", "w"};
    private String ym[] = new String[]{"a", "o", "e", "i", "u", "ü", "ai", "ei", "ui", "ao",
            "ou", "iu", "ie", "üe", "er", "an", "en", "in", "un", "ün", "ang", "eng", "ing", "ong"

    };
//    private String qpbw[] = new String[]{"zhi", "chi", "shi", "ri", "zi", "ci", "si", "yi", "wu",
//            "yu", "ye", "yue", "yuan", "yin", "yun", "ying"};
    //第二个输入的不是韵母就是简拼
    //输入的是韵母就是全拼

    /**
     * 获得汉字拼音首字母列表
     */
    private List<Set<String>> getHanziSpellList(boolean qporjp, List<String> hanzi) {

        List<Set<String>> listSet = new ArrayList<Set<String>>();
        for (int i = 0; i < hanzi.size(); i++) {
            if (!listSet.contains(pinyin.getPinyin(qporjp,hanzi.get(i).toString()))) {
                listSet.add(pinyin.getPinyin(qporjp, hanzi.get(i).toString()));
            }

        }
        return listSet;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main20);
        ButterKnife.bind(this);
        textView.setText("ggg");
        names.add("手机外壳");
        names.add("手机");
        names.add("手机外膜");
        names.add("电脑");
        names.add("手机电源");
        names.add("手套");
        names.add("受限");
        ks.addAll(names);

        final MyAdapter adapter = new MyAdapter();

        listView.setAdapter(adapter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                Log.e("tag", s.toString() + "\n" + start + "\n" + before + "\n" + count);


                if (s.toString().length() > 0) {
                    //判断第一个输入的是什么
                    String substring = s.toString().substring(0, 1);

                    //输入的是字母
                    Pattern p = Pattern.compile("[a-zA-Z]");
                    Matcher m = p.matcher(substring);
                    if (m.matches()) {

                        //全拼
                        hanziSpellList = getHanziSpellList(true, names);

                        if (s.toString().length() > 1) {
                            String substring1 = s.toString().substring(1, 2);
                            String substring2 = s.toString().substring(0, 2);
                            List<String> ymstrings = Arrays.asList(ym);
                            List<String> smstrings = Arrays.asList(sm);

                            if (smstrings.contains(substring2)) {

                            } else {

                                //不在韵母表中
                                if (!ymstrings.contains(substring1)) {
                                    //开启简拼
                                    hanziSpellList = getHanziSpellList(false, names);
                                }

                            }


                        }

                        if (count != 0) {
                            //输入的时候
                            for (int i = 0; i < hanziSpellList.size(); i++) {
                                lengthlist.clear();
                                storagelist.clear();
                                Set<String> innerList = hanziSpellList.get(i);
                                Iterator<String> iterator = innerList.iterator();
                                while (iterator.hasNext()) {
                                    String next = iterator.next();
                                    lengthlist.add(next.length());
                                    storagelist.add(next);
                                }
                                //移除数据中长度小于输入的
                                if (start + count > Collections.max(lengthlist)) {
                                    hanziSpellList.remove(i);
                                    names.remove(i);
                                    i--;
                                    continue;
                                }

                                for (String str : storagelist) {


                                        if (str.length() < start+count) {
                                            innerList.remove(str);

                                        } else {
                                            if (!str.substring(start, start+count).equals(s.toString().substring(start, start+count))) {
                                                innerList.remove(str);
                                            }
                                        }

                                }

                                if (innerList.size() == 0) {
                                    hanziSpellList.remove(i);
                                    names.remove(i);
                                    i--;
                                }


                            }

                        } else {
                            //回退的时候

                            for (int i = 0; i < hanziSpellList.size(); i++) {
                                lengthlist.clear();
                                storagelist.clear();
                                Set<String> innerList = hanziSpellList.get(i);
                                Iterator<String> iterator = innerList.iterator();
                                String next = null;
                                while (iterator.hasNext()) {
                                    next = iterator.next();
                                    lengthlist.add(next.length());
                                    storagelist.add(next);
                                }
                                //移除数据中长度小于输入的
                                if (start + count > Collections.max(lengthlist)) {
                                    hanziSpellList.remove(i);
                                    names.remove(i);
                                    i--;
                                    continue;
                                }

                                for (String str : storagelist) {
                                    if (str.length() < start) {
                                        innerList.remove(str);

                                    } else {
                                        if (!str.substring(0, start).equals(s.toString().substring(0, start))) {
                                            innerList.remove(str);
                                        }
                                    }

                                }

                                if (innerList.size() == 0) {
                                    hanziSpellList.remove(i);
                                    names.remove(i);
                                    i--;
                                }
                            }


                        }
                    }


                    //输入的是汉字
                    p = Pattern.compile("[\u4e00-\u9fa5]");
                    m = p.matcher(substring);
                    if (m.matches()) {

                        if (count != 0) {
                            //输入的时候
                            for (int i = 0; i < names.size(); i++) {

                                //移除数据中长度小于输入的
                                if (start + count > names.get(i).length()) {
                                    names.remove(i);
                                    i--;
                                    continue;
                                }

                                if (!names.get(i).substring(start, count + start).equals(s.toString().substring(start, count + start))) {
                                    names.remove(i);
                                    i--;
                                }
                            }
                        } else {
                            //回退的时候

                            for (int i = 0; i < names.size(); i++) {

                                //移除数据中长度小于输入的
                                if (start + count > names.get(i).length()) {
                                    names.remove(i);
                                    i--;
                                    continue;
                                }
                                if (!names.get(i).substring(0, start).equals(s.toString().substring(0, start))) {
                                    names.remove(i);
                                    i--;
                                }
                            }
                        }
                    }

                    //输入的是数字
                    p = Pattern.compile("[0-9]*");
                    m = p.matcher(substring);
                    if (m.matches()) {
                        names.clear();
                    }


                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                    names.clear();
                    names.addAll(ks);
                }
                return false;
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar instance = Calendar.getInstance();
                instance.setTime(new Date());

                //当前年
                int year = instance.get(Calendar.YEAR);
                //当前月
                int month = (instance.get(Calendar.MONTH)) + 1;
                //当前月的第几天：即当前日
                int day_of_month = instance.get(Calendar.DAY_OF_MONTH);


                String starttime = year + "-" + month + "-" + day_of_month + " 16:00:00";
                String endtime = year + "-" + month + "-" + day_of_month + " 17:30:00";

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    String sql = "select pausetime from ud_zyxk_zysq where ud_zyxk_zysqid='" + "s" + "'";
                    Date parse = simpleDateFormat.parse(starttime);
                    Date parse1 = simpleDateFormat.parse(endtime);
                    Date date = new Date(System.currentTimeMillis());
                    Log.e("w", parse.toString());
                    Log.e("n", parse1.toString());
                    Log.e("wow", date.toString());
                    Log.e("xxx", sql);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }
        });

    }


    static class ViewHold {
        private TextView tv;
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return names.size();
        }

        @Override
        public Object getItem(int position) {
            return names.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHold vh = null;
            if (convertView == null) {
                vh = new ViewHold();
                convertView = View.inflate(Main20Activity.this, R.layout.item, null);
                vh.tv = (TextView) convertView.findViewById(R.id.item_textview);
                convertView.setTag(vh);
            } else {
                vh = (ViewHold) convertView.getTag();
            }

            vh.tv.setText(names.get(position));

            return convertView;
        }

    }


}
