//package com.example.hp.notesapplication;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Adapter;
//import android.widget.BaseAdapter;
//import android.widget.Filter;
//import android.widget.Filterable;
//import android.widget.ImageView;
//import android.widget.TextView;
//import java.text.SimpleDateFormat;
//import com.example.hp.notesapplication.Notes;
//import com.example.hp.notesapplication.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ListAdapter extends BaseAdapter implements Filterable{
//
//        private Context context;
//        private List<Notes> notesList;
//        private LayoutInflater inflater;
//        private List<Notes> mStringFilterList;
//        private ValueFilter valueFilter;
//        int layout;
//
//
//        public ListAdapter(Context context, List<Notes> notesList) {
//            this.context = context;
//            this.notesList = notesList;
//            mStringFilterList = notesList;
//        }
//
//
//        @Override
//        public int getCount() {
//            return notesList.size();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return notesList.get(i);
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return i;
//        }
//
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//            if (inflater == null) {
//                inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            }
//            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd");
//
//            //ImageView photoView = (ImageView) view.findViewById(R.id.photo);
//            TextView titleView = (TextView) view.findViewById(R.id.title);
//            TextView timeView = (TextView) view.findViewById(R.id.time);
//
//            Notes note = notesList.get(i);
//            //photoView.setImageBitmap(note.getImage());
//            titleView.setText(note.getName());
//
//            return view;
//        }
//
//        @Override
//        public Filter getFilter() {
//            if (valueFilter == null) {
//                valueFilter = new ValueFilter();
//            }
//            return valueFilter;
//        }
//
//        private class ValueFilter extends Filter {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                FilterResults results = new FilterResults();
//
//                if (constraint != null && constraint.length() > 0) {
//                    ArrayList<Notes> filterList = new ArrayList<Notes>();
//                    for (int i = 0; i < mStringFilterList.size(); i++) {
//                        if ((mStringFilterList.get(i).getName().toUpperCase())
//                                .contains(constraint.toString().toUpperCase())) {
//
//                            Notes note = new Notes(mStringFilterList.get(i)
//                                    .getName(),mStringFilterList.get(i).getDescription(),
//                                    mStringFilterList.get(i).getNoteClass(),
//                                    mStringFilterList.get(i).getCrTime(),
//                                    mStringFilterList.get(i).getImage()
//                                    );
//                            filterList.add(note);
//                        }
//                    }
//                    results.count = filterList.size();
//                    results.values = filterList;
//                } else {
//                    results.count = mStringFilterList.size();
//                    results.values = mStringFilterList;
//                }
//                return results;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint,
//                                          FilterResults results) {
//                notesList = (ArrayList<Notes>) results.values;
//                notifyDataSetChanged();
//            }
//
//        }
//    }
//
//
