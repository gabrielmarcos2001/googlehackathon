package com.codesmore.codesmore.utils;

import com.codesmore.codesmore.R;
import com.codesmore.codesmore.integration.db.PulseContract;
import com.codesmore.codesmore.model.pojo.Category;

/**
 * Created by Darryl Staflund on 11/10/2015.
 */
public final class ImageUtils {

    public static int getDrawableId(Category category){
        if (category == null){
            return -1;
        }

        return getDrawableId(category.getName());
    }

    public static int getDrawableId(String categoryName){
        if (categoryName == null){
            return -1;
        }

        switch(categoryName){
            case PulseContract.IssueCategory.Values.CLEANLINESS:
                return R.drawable.water;
            case PulseContract.IssueCategory.Values.IMPROVEMENT:
                return R.drawable.format_paint;
            case PulseContract.IssueCategory.Values.INFRASTRUCTURE:
                return R.drawable.bus;
            case PulseContract.IssueCategory.Values.SAFETY:
                return R.drawable.ambulance;
            default:
                return R.drawable.ic_help_outline_black_48dp;
        }
    }

    private ImageUtils(){
    }
}
