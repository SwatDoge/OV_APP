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
        put(EPage.SIDEBAR, new PageInfo("/com/example/ovapp/layout/sidebar-view.fxml"));
        put(EPage.FAVORIET, new PageInfo("/com/example/ovapp/travel-history-view.fxml"));
    }};

    //Gets a PageInfo class from the given page.
    public static PageInfo getPageInfo(EPage page) {
        return PageLoaderMap.get(page);
    }

    //Navigates to the next page (and provides middleware functionality)
    public static PageInfo navigateTo(EPage page) {
        //Reset variables
        Scene scene = null;
        PageInfo pageInfo = null;

        //Try to load the given page from an Enum key
        try {
            pageInfo = PageLoaderMap.get(page);
            scene = pageInfo.getScene();
        }
        catch (IOException e) {
            System.out.println("Er was een fout tijdens het veranderen van paginas. Neem contact op met een developer.");
            System.out.println(e);
        }

        //If the scene exists, set it to the correct scene.
        currentStage.setScene(scene);

        //Find an `onSwitchToPage` method on the next page, and invoke it if it exists.
        try {
            Object controller = pageInfo.getController();
            var method = controller.getClass().getMethod("onSwitchToPage");
            method.invoke(controller, (Object[]) null);
        }
        catch (Exception e) {

        }

        System.out.println("Navigeren naar " + page);
        return pageInfo;
    }
}
