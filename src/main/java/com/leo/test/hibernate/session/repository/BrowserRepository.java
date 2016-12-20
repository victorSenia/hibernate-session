package com.leo.test.hibernate.session.repository;

import com.leo.test.hibernate.session.model.Browser;

/**
 * Created by Senchenko Viktor on 27.09.2016.
 */
public class BrowserRepository extends Repository<Browser> {
    public BrowserRepository() {
        super(Browser.class);
    }

    public Iterable<Browser> getByBrowser(String browser) {
        return custom((criteriaBuilder, query, root) -> query.where(criteriaBuilder.equal(root.get("browser"), browser)));
    }
}
