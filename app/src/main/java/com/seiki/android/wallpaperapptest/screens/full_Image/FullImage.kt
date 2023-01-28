package com.seiki.android.wallpaperapptest.screens.full_Image

import android.app.WallpaperManager
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.seiki.android.wallpaperapptest.R
import com.seiki.android.wallpaperapptest.databinding.FragmentFullImageBinding
import com.seiki.android.wallpaperapptest.nav
import com.seiki.android.wallpaperapptest.retrofit.common.Common
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FullImage : Fragment() {
    private lateinit var bind: FragmentFullImageBinding
    private var urImage: String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bind = FragmentFullImageBinding.inflate(layoutInflater)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initImg()

        bind.btnBack.setOnClickListener {
            nav(R.id.action_fullImage_to_mainFragment)
        }

        bind.btnInstall.setOnClickListener {
            //делаем запрос по ссылке через ретрофит
            //изображение переводим в битмап
            //ставим на рабочий стол
            val value = urImage.toString()
            CoroutineScope(Dispatchers.IO).launch {
                val response = Common.retrofitService.getImg(value)
                val responseBody = response.body()
                val inputStream = responseBody?.byteStream()
                if (inputStream != null) {
                    val bitmap = BitmapFactory.decodeStream(inputStream)
                    if (bitmap != null) {
                        withContext(Dispatchers.Main) {
                            val wallpaperManager = WallpaperManager.getInstance(context)
                            wallpaperManager.setBitmap(bitmap)
                        }
                    }
                }
            }
            Toast.makeText(requireContext(),
                "Новые обои рабочего стола - установлены",
                Toast.LENGTH_SHORT).show()
        }



    }

    private fun initImg() {
        //получаем из адаптера ссылку на изображение
        //открываем его, растягивание изображение прописанно в XML
        urImage = arguments?.getString("uri")
        val uri = Uri.parse(urImage)
        Picasso.get().load(uri).into(bind.FullImg)
    }


}