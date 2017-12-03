import javafx.scene.web.WebView;

class YouTubeVideo {
    static WebView howToVideo() {
        WebView webview = new WebView();
        webview.getEngine().load(
                "https://www.youtube.com/embed/lJVv9IXfVoI"
        );
        webview.setPrefSize(640, 360);
        return webview;
    }

    static WebView walkthroughVideo() {
        WebView webview = new WebView();
        webview.getEngine().load(
                "https://www.youtube.com/embed/x2GBGj0suFI"
        );
        webview.setPrefSize(640, 360);
        return webview;
    }
}
