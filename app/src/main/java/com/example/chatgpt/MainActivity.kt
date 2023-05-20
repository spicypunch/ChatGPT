package com.example.chatgpt

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView = findViewById(R.id.webview)

        // javascript를 활성화 시킨다.
        webView.settings.javaScriptEnabled = true

        // 웹뷰 클라이언트 설정
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                // 웹 페이지 로딩 완료시 호출되는 메서드를 작성할 수 있다.

            }
        }
        /**
         * 구글 계정으로 GPT에 로그인 해야하는 경우 추가하는 코드
         * WebView에서 Google OAuth를 실행하였을 때 발생하는 오류이다.
         * 이 코드는 Chrome 브라우저의 User Agent 문자열을 나타낸다.
         * 이를 사용하여 WebView의 User Agent를 변경하면,
         * 구글 OAuth 절차에서 구글 서버가 WebView를 신뢰할 수 있게 되어
         * 인증 과정이 성공적으로 진행될 수 있다.
         */
        webView.settings.userAgentString = "Chrome/56.0.0.0 Mobile"

        // 웹 페이지 로드
        webView.loadUrl("https://chat.openai.com/")

        /**
         * 뒤로가기 버튼 이벤트 처리
         * 아래의 코드가 없을 경우 뒤로가기를 누를 경우 앱에서 나가지는 상황이 발생한다.
         * 아래의 코드에서 webView.setOnKeyListener를 사용하여 WebView에서 키 이벤트를 수신하고
         * 뒤로가기 버튼이 눌렸을 때  WebView가 이전 페이지로 이동할 수 있는지 확인한 후 webView.goBack()을 호출한다.
         */
        webView.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP && webView.canGoBack()) {
                webView.goBack()
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
    }
}