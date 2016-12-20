package com.leo.test.hibernate.session.controller;

import com.leo.test.hibernate.session.model.Browser;
import com.leo.test.hibernate.session.repository.BrowserRepository;
import com.leo.test.hibernate.session.util.BrowserMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by Senchenko Viktor on 26.09.2016.
 */
@Path("/browser")
public class HomeController {

    private static final BrowserRepository REPOSITORY = new BrowserRepository();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public String list() {
        return BrowserMapper.toString(REPOSITORY.list());
    }

    @GET
    @Path("/browser")
    @Produces(MediaType.APPLICATION_JSON)
    public String getByBrowser(@QueryParam("browser") String browser) throws UnsupportedEncodingException {
        return BrowserMapper.toString(REPOSITORY.getByBrowser(URLDecoder.decode(browser, "UTF-8")));
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String get(@PathParam("id") Integer id) {
        Browser browserObject;
        if (id != null && (browserObject = REPOSITORY.get(id)) != null)
            return BrowserMapper.toString(browserObject);
        throw new BadRequestException();
    }

    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String create(String browser) {
        Browser browserObject = BrowserMapper.toBrowser(browser);
        if (browserObject != null && (browserObject = REPOSITORY.edit(browserObject)) != null) {
            return BrowserMapper.toString(browserObject);
        }
        throw new BadRequestException();
    }

    @POST
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String edit(@PathParam("id") Integer id, String browser) {
        Browser browserObject = BrowserMapper.toBrowser(browser);
        if (browserObject != null && id != null) {
            browserObject.setId(id);
            if ((browserObject = REPOSITORY.edit(browserObject)) != null) {
                return BrowserMapper.toString(browserObject);
            }
        }
        throw new BadRequestException();
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        Browser browserObject;
        if (id != null && (browserObject = REPOSITORY.get(id)) != null)
            REPOSITORY.delete(browserObject);
    }
}
