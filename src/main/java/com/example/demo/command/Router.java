package com.example.demo.command;

import com.example.demo.command.constant.PagePath;

public class Router {

    public enum RouterType {
        FORWARD,
        REDIRECT,
    }

    private String page = PagePath.INDEX;
    private RouterType type = RouterType.FORWARD;

    public Router() {}

    public Router(String page) {
        this.page = page;
    }

    public Router(String page, String productName) {
        this.page = page;
    }

    public Router(String page, RouterType type) {
        this.page = page;
        this.type = type;
    }

    public String getPage() {
        return page;
    }

    public RouterType getCurrentRouterType() {
        return type;
    }

    public void setRouterTypeRedirect() {
        type = RouterType.REDIRECT;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setRedirect(RouterType type) {
        this.type = RouterType.REDIRECT;
    }
}
