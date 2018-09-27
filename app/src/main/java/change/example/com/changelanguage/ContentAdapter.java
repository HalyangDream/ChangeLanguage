package change.example.com.changelanguage;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class ContentAdapter extends BaseAdapter {

    private LayoutInflater inflater;

    private List<String> mList;

    public ContentAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        mList = new ArrayList<>();
        mList.add("这是不变的内容");
        mList.add("这是不变的内容");
        mList.add("这是不变的内容");
        mList.add("这是不变的内容");
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = this.inflater.inflate(R.layout.item_content, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        String sub = mList.get(i);
        StringBuilder stringBuilder = new StringBuilder(sub);
        stringBuilder.append(i);
        holder.titleTv.setText(R.string.content_text);
        holder.subContentTv.setText(stringBuilder);

        return view;
    }


    /**
     * 因为没有导入V7的RecyclerView 所以要手写Holder
     */
    class ViewHolder {

        private AppCompatTextView titleTv;
        private AppCompatTextView subContentTv;

        public ViewHolder(View itemView) {
            this.titleTv = itemView.findViewById(R.id.title);
            this.subContentTv = itemView.findViewById(R.id.sub_title);

        }
    }
}
