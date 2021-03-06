package com.azhuoinfo.gbf.fragment.mine;

import mobi.cangol.mobile.base.BaseContentFragment;
import mobi.cangol.mobile.base.FragmentInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.azhuoinfo.gbf.R;
import com.azhuoinfo.gbf.fragment.adapter.FavoriteAdapter;
import com.azhuoinfo.gbf.model.Favorite;
import com.azhuoinfo.gbf.view.PromptView;
import com.azhuoinfo.gbf.view.PromptView.OnPromptClickListener;

public class UserFavoriteFragment extends BaseContentFragment{
	
	private PromptView mPromptView;
	private ListView mListView;
	private FavoriteAdapter mFavoriteAdapter;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View v = inflater.inflate(R.layout.fragment_list,container,false);
		return v;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		findViews(view);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initViews(savedInstanceState);
		initData(savedInstanceState);
	}
	
	@Override
	protected void findViews(View view) {
		mPromptView=(PromptView) findViewById(R.id.promptView);
		mListView=(ListView) findViewById(R.id.listView);
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		this.getCustomActionBar().setTitle(R.string.title_mine_favorite);
		mFavoriteAdapter=new FavoriteAdapter(getActivity());
		mListView.setAdapter(mFavoriteAdapter);
		mFavoriteAdapter.setOnActionClickListener(new FavoriteAdapter.OnActionClickListener() {
			

			@Override
			public void onBuyClick(int position) {
				buyFavorite(position);
			}

			@Override
			public void onCancelClick(int position) {
				removeFavorite(position);
			}
		});
		mPromptView.setOnPromptClickListener(new OnPromptClickListener(){

			@Override
			public void onClick(View v, int action) {
				if(mPromptView.ACTION_RETRY==action){
					getFavoriteList();
				}
			}
			
		});
	}
	protected void buyFavorite(int position) {
		Favorite item=mFavoriteAdapter.getItem(position);
		
		
		
	}
	protected void removeFavorite(int position) {
		Favorite item=mFavoriteAdapter.getItem(position);
		
		mFavoriteAdapter.remove(position);
		
	}

	@Override
	protected void initData(Bundle savedInstanceState) {
		getFavoriteList();
	}
	private void getFavoriteList() {
		
		
	}

	@Override
	protected FragmentInfo getNavigtionUpToFragment() {
		return null;
	}
	@Override
	public boolean isCleanStack() {
		return false;
	}
}
