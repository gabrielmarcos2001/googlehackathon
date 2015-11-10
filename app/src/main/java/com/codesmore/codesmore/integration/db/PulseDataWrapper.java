package com.codesmore.codesmore.integration.db;

import com.codesmore.codesmore.model.DataWrapper;
import com.codesmore.codesmore.model.pojo.Category;
import com.codesmore.codesmore.model.pojo.Issue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Darryl Staflund on 11/9/2015.
 */
public class PulseDataWrapper implements DataWrapper {

    /**
     * Returning mock data until I can insert values in database.
     * @return
     */
    @Override
    public List<Category> getCategories() {
        return new ArrayList<Category> (){
            {
                add(new Category("Cleanliness", null));
                add(new Category("Improvement", null));
                add(new Category("Infrastructure", null));
                add(new Category("Safety", null));
            }
        };
    }

    @Override
    public void saveIssue(Issue issue) {

    }
}
