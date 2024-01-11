package com.example.ovapp.tools;

import com.example.ovapp.enums.EPage;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;

import static com.example.ovapp.Main.currentStage;

public class Page {
    private static HashMap<EPage, PageInfo> PageLoaderMap = new HashMap<EPage, PageInfo>() {{
        put(EPage.HOME, new PageInfo("/com/example/ovapp/home-view.fxml"));
        put(EPage.LOGIN, new PageInfo("/com/example/ovapp/login-view.fxml"));
        put(EPage.REGISTER, new PageInfo("/com/example/ovapp/register-view.fxml"));
        put(EPage.PROFILE, new PageInfo("/com/example/ovapp/profile-view.fxml"));
        put(EPage.SEARCHRESULT, new PageInfo("/com/example/ovapp/search-result-view.fxml"));
        put(EPage.SIDEBAR, new PageInfo("/com/example/ovapp/sidebar-view.fxml"));
    }};

    public static PageInfo getPageInfo(EPage page) {
        return PageLoaderMap.get(page);
    }

    public static PageInfo navigateTo(EPage page) {
        Scene scene = null;
        PageInfo pageInfo = null;

        try {
            pageInfo = PageLoaderMap.get(page);
            scene = pageInfo.getScene();
        }
        catch (IOException e) {

        }

        if (scene != null) {
            currentStage.setScene(scene);
        }

        System.out.println("Navigeren naar " + page);
        return pageInfo;
    }
}
