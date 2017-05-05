package com.example.emilstepanian.justhandworker.shared.model;

import com.example.emilstepanian.justhandworker.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emilstepanian on 04/05/2017.
 */


public class TestData {

    private static final String[] titles = {"Fiks min Balkon", "Toilet virker ikke", "Ingen vand"};
    private static final String[] locations = {"Frederiksberg", "Valby", "Amager"};
    private static final String[] comments = {"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas rhoncus, turpis non interdum varius, turpis metus cursus massa, in volutpat mi arcu a risus.",
            "Sed at placerat nisi. Sed nisl nunc, dapibus eu tellus ut", "Fusce nec aliquet leo. Cras at varius ex. Cras finibus lectus sapien, sit amet viverra eros"};
    private static final String[] dates = {"5. Maj", "10. Maj", "15. April"};

    private static final int[] icons = {R.drawable.balcony, R.drawable.wc, R.drawable.sink};


    public static List<Job> getListData() {
        List<Job> data = new ArrayList<>();


        for (int x = 0; x < 4; x++) {
            for (int i = 0; i < titles.length && i < icons.length; i++) {
                Job item = new Job();
                item.setImageResId(icons[i]);
                item.setTitle(titles[i]);
                item.setComment(comments[i]);
                item.setDate(dates[i]);
                item.setLocation(locations[i]);
                data.add(item);
            }
        }
        return data;
    }

}
