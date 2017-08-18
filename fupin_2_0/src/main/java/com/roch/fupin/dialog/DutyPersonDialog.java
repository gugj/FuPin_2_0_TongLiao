/**
 * 
 */
package com.roch.fupin.dialog;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.roch.fupin.adapter.DutyPersonAdapter;
import com.roch.fupin.entity.DutyPerson;
import com.roch.fupin.utils.Common;
import com.roch.fupin_2_0.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author ZhaoDongShao
 *
 * 2016年7月12日 
 *
 */
public class DutyPersonDialog extends DialogFragment{

	DutyPersonAdapter adapter;
	
	List<DutyPerson> list = new ArrayList<DutyPerson>();
//	Context context = null;
	/**
	 * 
	 */
//	public DutyPersonDialog(Context context, List<DutyPerson> list) {
//		// TODO Auto-generated constructor stub
//		this.list = list;
//		this.context = context;
//	}
	
	@SuppressLint("InflateParams")
	@SuppressWarnings("unchecked")
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		Bundle bundle = getArguments();
		if (bundle != null){
			list.addAll((Collection<? extends DutyPerson>) bundle.getSerializable(Common.BUNDEL_KEY));
		}

		AlertDialog.Builder builder = new Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		View view = inflater.inflate(R.layout.duty_person_dialog, null);
		ListView lView = (ListView)view.findViewById(R.id.lv_duty_person);
		adapter = new DutyPersonAdapter(getActivity(), list, R.layout.duty_person_listview_item);
		lView.setAdapter(adapter);
		builder.setCancelable(true);
		builder.setView(view);
		builder.setPositiveButton("确定", null);
		return builder.create();
	}

}
