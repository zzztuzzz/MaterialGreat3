package com.androidbelieve.drawerwithswipetabs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.androidbelieve.drawerwithswipetabs.R;
import com.androidbelieve.drawerwithswipetabs.entity.MatchItem;

import java.util.ArrayList;

/**
 * Created by teiyuueki on 2016/04/23.
 */
public class MatchListAdapter extends ArrayAdapter<MatchItem> {

    // 見易さのために定義。普段は直接 getView で指定する。
    private static final int resource = R.layout.match_listview_item;

    public MatchListAdapter(Context context){
        super(context, 0);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // super.getView() は 呼ばない(カスタムビューにしているため)
        View view;

        // テンプレート処理。
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(resource, parent, false);
        } else {
            view = convertView;
        }

        // データをgetItemで取る
        MatchItem matchItem = getItem(position);

        // カスタムビューの場合はViewが確実にあるtry-catch は不要ためか。
        TextView id = (TextView) view.findViewById(R.id.id);
        id.setText(matchItem.id);
        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(matchItem.name);

        return view;
    }

    // 設定されている CustomListItem の ArrayList を返す。
    // 縦横切替などでデータを移行するために使う。
    public ArrayList<MatchItem> getItemList() {
        // 今回は Bundle#putParcelableArrayList() を使うことを想定する。
        // 必要に応じて Bundle#putSparseParcelableArray() を使ってもいい。

        int size = getCount();
        ArrayList<MatchItem> matchItemList = new ArrayList<MatchItem>(size);
        for (int index = 0; index < size; index++) {
            matchItemList.add(getItem(index));
        }
        return matchItemList;
    }

    // Bundleから復元するときに必要になるはず。
    public void addAll(ArrayList<MatchItem> parcelableArrayList) {
        // 強制でキャスト。落ちる場合は、設計か実装が間違っている。
        @SuppressWarnings("unchecked")
        ArrayList<MatchItem> matchItemList = (ArrayList<MatchItem>) parcelableArrayList;
        super.addAll(matchItemList);
    }

    public void add(String id, String name) {
        MatchItem matchItem = new MatchItem(id, name);
        super.add(matchItem);
    }

    // 削除
    public void remove(int index) {
        if (index < 0 || index >= getCount()) {
            return;
        }
        remove(getItem(index));
    }
}
