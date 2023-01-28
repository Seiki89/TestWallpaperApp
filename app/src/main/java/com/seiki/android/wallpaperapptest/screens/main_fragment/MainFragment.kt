package com.seiki.android.wallpaperapptest.screens.main_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.seiki.android.wallpaperapptest.APP
import com.seiki.android.wallpaperapptest.R
import com.seiki.android.wallpaperapptest.databinding.FragmentMainBinding
import com.seiki.android.wallpaperapptest.model.Hit
import com.seiki.android.wallpaperapptest.model.Images
import com.seiki.android.wallpaperapptest.retrofit.`interface`.RetrofitServices
import com.seiki.android.wallpaperapptest.retrofit.common.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainFragment : Fragment() {
    private lateinit var bind: FragmentMainBinding
    private lateinit var myService: RetrofitServices
    private lateinit var imgAdapter: ImgAdapter
    private lateinit var recyclerViewImg: RecyclerView
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var recyclerViewCategory: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentMainBinding.inflate(layoutInflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCategoryAdapter()
        initImgAdapter()
        bind.floatingActionButton.setOnClickListener { APP.finish() }

    }
    private fun initCategoryAdapter() {
        //привязываем и наполняем адаптер с кнопками категорий
        categoryAdapter = CategoryAdapter(ButtonsPattern().add(), this)
        recyclerViewCategory = bind.categoryRecycle
        bind.categoryRecycle.adapter = categoryAdapter
    }

    private fun initImgAdapter() {
        //привязываем и наполняем предварительно адаптер с изображениями
        recyclerViewImg = bind.imgRecycle
        myService = Common.retrofitService
        val request = "nature+vertical"
        getList(request)
    }

    fun clickImgAdapter(value:String){
        //передаем изображение из адаптера в фрагмент с полноэкранным изображением
        val bundle = bundleOf("uri" to value)
        APP.navController.navigate(R.id.action_mainFragment_to_fullImage, bundle)

    }

    fun clickButtonAdapter(request: String) {
        //по нажатию на кнопку меняем запрос ретрофита на изображения
        getList(request)
    }

    private fun getList(request: String) {
        //формируем запрос ретрофита
        //обновляем рецайкл
        //обрабатываем ошибки
        //

        val call = myService.getList("33106230-b104905cd7ff74ed17e2229af", request, "photo")
        call.enqueue(object : Callback<Images> {

            override fun onResponse(call: Call<Images>, response: Response<Images>) {
                if (response.isSuccessful) {
                    imgAdapter =
                        ImgAdapter(response.body()?.hits as MutableList<Hit>, this@MainFragment)
                    imgAdapter.notifyItemRangeChanged(0, imgAdapter.itemCount)
                    bind.imgRecycle.adapter = imgAdapter
                } else {
                    when (response.code()) {
                        404 -> {
                            Toast.makeText(requireContext(),
                                "Ошибка Соединения",
                                Toast.LENGTH_SHORT).show()
                        }
                        429 -> {
                            Toast.makeText(requireContext(),
                                "Слишком частые запросы",
                                Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(requireContext(),
                                "Неизвестная ошибка",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            override fun onFailure(call: Call<Images>, t: Throwable) {
                Toast.makeText(requireContext(), "Ошибка : $t", Toast.LENGTH_SHORT).show()

            }
        })
    }
}