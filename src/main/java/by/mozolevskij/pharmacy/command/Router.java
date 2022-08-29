package by.mozolevskij.pharmacy.command;

import by.mozolevskij.pharmacy.command.attribute.PagePath;

public class Router {

    public enum RouterType {
        FORWARD,
        REDIRECT,
    }

    private String currentPage = PagePath.INDEX_JSP;
    private RouterType currentRouterType = RouterType.FORWARD;

    public Router() {
    }

    public Router(String currentPage, RouterType currentRouterType) {
        this.currentRouterType = currentRouterType;
        this.currentPage = currentPage;
    }

    public Router(String currentPage) {
        this.currentPage = currentPage;
    }

    public Router(RouterType currentRouterType) {
        this.currentRouterType = currentRouterType;
    }

    public RouterType getCurrentRouterType() {
        return currentRouterType;
    }

    public void setRouterTypeRedirect() {
        this.currentRouterType = RouterType.REDIRECT;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }
}
