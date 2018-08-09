package com.quanda.moviedb.ui.screen.tv

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.quanda.moviedb.BR
import com.quanda.moviedb.data.model.Tv
import com.quanda.moviedb.databinding.FragmentLoadmoreRefreshBinding
import com.quanda.moviedb.ui.base.fragment.BaseLoadMoreRefreshFragment

class TvListFragment : BaseLoadMoreRefreshFragment<FragmentLoadmoreRefreshBinding, TvListViewModel, Tv>(), TvListNavigator {

    companion object {
        const val TAG = "TvListFragment"

        fun newInstance() = TvListFragment()
    }

    override val bindingVariable: Int
        get() = BR.viewModel

    override val viewModel: TvListViewModel
        get() = ViewModelProviders.of(this, viewModelFactory)
                .get(TvListViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter = TvListAdapter(itemClickListener = { goToTvDetail(it) })
        viewBinding.apply {
            root.setBackgroundColor(Color.BLACK)
            recyclerView.apply {
                layoutManager = GridLayoutManager(context, 2)
                this.adapter = adapter
            }
        }
        viewModel.apply {
            listItem.observe(this@TvListFragment, Observer {
                adapter.submitList(it)
            })
            firstLoad()
        }
    }

    fun goToTvDetail(tv: Tv) {

    }
}