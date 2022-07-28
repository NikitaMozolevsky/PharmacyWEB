package by.mozolevskij.pharmacy.command.impl.product;

import by.mozolevskij.pharmacy.command.Command;
import by.mozolevskij.pharmacy.command.Router;
import by.mozolevskij.pharmacy.command.attribute.PagePath;
import by.mozolevskij.pharmacy.dao.impl.ProductDaoImpl;
import by.mozolevskij.pharmacy.exception.CommandException;
import by.mozolevskij.pharmacy.exception.DaoException;
import by.mozolevskij.pharmacy.entity.product.Product;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static by.mozolevskij.pharmacy.command.attribute.ProductAttribute.PRODUCTS;

public class ShowProductListCommand implements Command {

    static Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest request) throws CommandException {
        Router router = new Router();
        try {
            List<Product> productList = ProductDaoImpl.getInstance().findAll();
            request.setAttribute(PRODUCTS, productList);
            router.setPage(PagePath.SHOW_PRODUCTS);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "command findAll exception", e);
            throw new CommandException();
        }
        return router;
    }
}