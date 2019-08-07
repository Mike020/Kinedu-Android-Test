package com.miguel.kineduandroidtest.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import com.miguel.kineduandroidtest.R
import com.miguel.kineduandroidtest.model.ArticleDetailResponse
import com.miguel.kineduandroidtest.model.KineduService
import com.miguel.kineduandroidtest.util.KINEDU_URL_TOKEN
import com.miguel.kineduandroidtest.util.getProgressDrawable
import com.miguel.kineduandroidtest.util.loadImage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_article_detail.*

class ArticleDetailActivity : AppCompatActivity() {

    private val disposable = CompositeDisposable()
    private val apiService = KineduService()

    private val token = KINEDU_URL_TOKEN


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)


    }

    override fun onStart() {
         val id = intent.extras?.getString("articleId")

        super.onStart()
        getArticleDetail(id.toString())

        backButton.setOnClickListener {
            finish()
        }

    }




    private fun getArticleDetail(id : String){

        disposable.add(
            apiService.getArticleDetail(token , id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<ArticleDetailResponse>(){
                    override fun onSuccess(response: ArticleDetailResponse) {
                        progressBar.isVisible = false
                        articleDetailimage.isVisible = true
                        articleTitleText.isVisible = true
                        webContent.isVisible = true
                        share_img.isVisible = true

                        share_img.setOnClickListener {
                            val shareIntent = Intent()
                            shareIntent.action = Intent.ACTION_SEND
                            shareIntent.type="text/plain"
                            shareIntent.putExtra(Intent.EXTRA_SUBJECT , "Share article")
                            shareIntent.putExtra(Intent.EXTRA_TEXT, response.data.article.link);
                            startActivity(Intent.createChooser(shareIntent,"Share article"))
                        }

                        articleDetailimage.loadImage(response.data.article.picture , getProgressDrawable( applicationContext))
                        articleTitleText.text = response.data.article.title
                        webContent.loadDataWithBaseURL(null ,response.data.article.body,"text/html", "utf-8", null)
                    }



                    override fun onError(e: Throwable) {
                        progressBar.isVisible = false
                        errorTextView.isVisible = true
                        e.printStackTrace()

                    }


                })
        )

    }


}
